package com.avtukhov.trial_project.controller;

import com.avtukhov.trial_project.DTO.QuoteDTO;
import com.avtukhov.trial_project.DTO.UserDTO;
import com.avtukhov.trial_project.models.Quote;
import com.avtukhov.trial_project.models.User1;
import com.avtukhov.trial_project.services.UserService;
import com.avtukhov.trial_project.util.userError.UserErrorResponse;
import com.avtukhov.trial_project.util.userError.UserNotCreatedException;
import com.avtukhov.trial_project.util.userError.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<UserDTO> getUser() {
        return userService.findAll().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id){
        return convertToUserDTO(userService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError erro : errors){
                errorMessage.append(erro.getField())
                        .append(" - ").append(erro.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMessage.toString());
        }
        userService.save(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/getQuotes/{id}")
    public List<QuoteDTO> getQuotes(@PathVariable("id") int id){
        User1 user = userService.findOne(id);
        return user.getUserQuotes().stream().map(this::convertToQuoteDto)
                .collect(Collectors.toList());

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id, @RequestBody
                                             @Valid UserDTO userDTO,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError erro : errors){
                errorMessage.append(erro.getField())
                        .append(" - ").append(erro.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMessage.toString());
        }
        try{
            User1 user = userService.findOne(id);
            user.setUserName(userDTO.getUserName());
            user.setPassword(userDTO.getPassword());
            user.setEmail(user.getEmail());
            userService.save(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e){
            throw new UserNotCreatedException("user with this id wasn't found!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id){
        try {
            User1 user = userService.findOne(id);
            userService.delete(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (UserNotFoundException e){
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

    }


    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
        UserErrorResponse response = new UserErrorResponse(
                "user with this id wasn't found!",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private User1 convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User1.class);
    }

    private UserDTO convertToUserDTO(User1 user){
        return modelMapper.map(user, UserDTO.class);
    }

    private Quote convertToQuote(QuoteDTO quoteDTO){
        return modelMapper.map(quoteDTO, Quote.class);
    }

    private QuoteDTO convertToQuoteDto(Quote quote){
        return modelMapper.map(quote, QuoteDTO.class);
    }




}
