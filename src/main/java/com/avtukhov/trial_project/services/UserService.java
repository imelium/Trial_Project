package com.avtukhov.trial_project.services;

import com.avtukhov.trial_project.models.User1;
import com.avtukhov.trial_project.repository.UserRepository;
import com.avtukhov.trial_project.util.userError.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User1> findAll(){
        return userRepository.findAll();
    }

    public User1 findOne(int id) {
        Optional<User1> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
    }
    @Transactional
    public void save(User1 user){
        enrichUser(user);
        userRepository.save(user);
    }

    public User1 findByName(String name){
        Optional<User1> foundUser = userRepository.findByUserName(name);
       return foundUser.orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void delete(User1 user){
        userRepository.delete(user);
    }

    private void enrichUser(User1 user) {
        user.setDateOfRegistration(LocalDateTime.now());
    }

}
