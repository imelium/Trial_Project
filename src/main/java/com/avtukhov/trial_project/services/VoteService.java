package com.avtukhov.trial_project.services;

import com.avtukhov.trial_project.models.Quote;
import com.avtukhov.trial_project.models.Vote;
import com.avtukhov.trial_project.repository.VoteRepository;
import com.avtukhov.trial_project.util.voteException.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final QuoteService quoteService;
    private final UserService userService;

    public Vote findOne(int id){
        Optional<Vote> foundVote = voteRepository.findById(id);
        return foundVote.orElseThrow(VoteNotFoundException::new);
    }


    public void save(Vote vote){
        enrichVote(vote);
        voteRepository.save(vote);
    }

    public List<Vote> findAllVotes(){
        return voteRepository.findAll();
    }

    @Transactional
    public void changeScore(int score, int quoteId, Vote vote){
        Quote quote = quoteService.findOne(quoteId);
        enrichVote(vote);
        quote.setScore(quote.getScore() + score);
        vote.setScore(score);
        vote.setQuote_vote(quote);
        vote.setUser1(vote.getUser1());
        save(vote);
        quoteService.save(quote);
    }
    private void enrichVote(Vote vote) {
        vote.setUser1(userService.findByName(vote.getUser1().getUserName()));
        vote.setDateOdVote(LocalDateTime.now());
    }



}
