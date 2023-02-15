package com.avtukhov.trial_project.controller;

import com.avtukhov.trial_project.DTO.QuoteResponse;
import com.avtukhov.trial_project.DTO.VoteDTO;
import com.avtukhov.trial_project.DTO.VoteResponse;
import com.avtukhov.trial_project.models.Vote;
import com.avtukhov.trial_project.services.QuoteService;
import com.avtukhov.trial_project.services.VoteService;
import com.avtukhov.trial_project.util.voteException.VoteErrorResponse;
import com.avtukhov.trial_project.util.voteException.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
    private final VoteService voteService;
    private final QuoteService quoteService;
    private final ModelMapper modelMapper;

    @PostMapping("/plusScore/{id}")
    public ResponseEntity<HttpStatus> plusScore(@PathVariable("id") int id, @RequestBody VoteDTO voteDTO) {
        voteService.changeScore(1, id, convertToVote(voteDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/minusScore/{id}")
    public ResponseEntity<HttpStatus> minusScore(@PathVariable("id") int id, @RequestBody VoteDTO voteDTO) {
        voteService.changeScore( -1, id, convertToVote(voteDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/all")
    public List<VoteResponse> getAllQuoteVote(@RequestBody QuoteResponse quoteResponse) {

        return quoteService.findOne(quoteResponse.getId()).getTotalVotes().stream()
                .map(this::convertToVoteResponse).collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<VoteErrorResponse> handleException(VoteNotFoundException e){
        VoteErrorResponse response = new VoteErrorResponse(
                "vote with this id wasn't found!",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



    private Vote convertToVote(VoteDTO voteDTO){
        return modelMapper.map(voteDTO, Vote.class);
    }

    private VoteResponse convertToVoteResponse(Vote vote){
        return modelMapper.map(vote, VoteResponse.class);
    }
}
