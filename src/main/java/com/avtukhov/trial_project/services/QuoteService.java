package com.avtukhov.trial_project.services;

import com.avtukhov.trial_project.models.Quote;
import com.avtukhov.trial_project.repository.QuoteRepository;
import com.avtukhov.trial_project.util.quoteError.QuoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;


    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public Quote findOne(int id){
    Optional<Quote> foundQuote = quoteRepository.findById(id);
    return foundQuote.orElseThrow(QuoteNotFoundException::new);
    }


    @Transactional
    public void save(Quote quote){
        enrichQuote(quote);
        quoteRepository.save(quote);
    }

    @Transactional
    public void delete(Quote quote){
        quoteRepository.delete(quote);
    }

    @Transactional
    public void update(Quote quote){
        updateEnrichQuote(quote);
        quoteRepository.save(quote);
    }

    private void enrichQuote(Quote quote) {
        quote.setOwner(userService.findByName(quote.getOwner().getUserName()));
        quote.setTimeOfCreation(LocalDateTime.now());
    }
    private void updateEnrichQuote(Quote quote) {
        quote.setTimeOfLastEdit(LocalDateTime.now());
    }
}
