package com.avtukhov.trial_project.controller;

import com.avtukhov.trial_project.DTO.QuoteDTO;
import com.avtukhov.trial_project.DTO.VoteResponse;
import com.avtukhov.trial_project.charts.LineChart;
import com.avtukhov.trial_project.models.Quote;
import com.avtukhov.trial_project.models.Vote;
import com.avtukhov.trial_project.services.QuoteService;
import com.avtukhov.trial_project.util.quoteError.QuoteErrorResponse;
import com.avtukhov.trial_project.util.quoteError.QuoteNotCreatedException;
import com.avtukhov.trial_project.util.quoteError.QuoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;
    private final ModelMapper modelMapper;
    private final LineChart lineChart;

    @GetMapping
    public List<QuoteDTO> getAllQuotes(){
        return quoteService.findAll().stream().map(this::convertToQuoteDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuoteDTO getQuote(@PathVariable("id") int id){
        return  convertToQuoteDto(quoteService.findOne(id));
    }
    /*
    Get total score for current quote
     */
    @GetMapping("/score/{id}")
    public int getScore(@PathVariable("id") int id){
        return quoteService.findOne(id).getScore();
    }

    /*
    Get all votes for current Quote (function for charts)
     */
    @GetMapping("/score/allvote/{id}")
    public List<VoteResponse> getVotes(@PathVariable("id") int id){
        return quoteService.findOne(id).getTotalVotes().stream().map(this::convertToVoteResponse).collect(Collectors.toList());
    }

    /*
    Get line chart of current quote
     */
    @GetMapping("/result/{id}")
    public String getChart(@PathVariable("id")int id){
        List<VoteResponse> response = quoteService.findOne(id).getTotalVotes().stream().map(this::convertToVoteResponse).toList();
        return lineChart.getLineChart(response);
    }

    /*
    Top 10 (best)
     */
    @GetMapping("/score/best10")
    public List<Integer> getTopBest(){
        List<Quote> quoteList = quoteService.findAll();
        List<Integer> scoreList = quoteList.stream().map(Quote::getScore).toList();
        scoreList.sort(Collections.reverseOrder());
        return scoreList.stream().limit(10).collect(Collectors.toList());
    }
    /*
        Top 10 (best)
         */
    @GetMapping("/score/worst10")
    public List<Integer> getTopWorst(){
        List<Quote> quoteList = quoteService.findAll();
        List<Integer> scoreList = quoteList.stream().map(Quote::getScore).toList();
        Collections.sort(scoreList);
        return scoreList.stream().limit(10).collect(Collectors.toList());
    }



    @GetMapping("/random")
    public QuoteDTO getRandomQuote(){
        int max = quoteService.findAll().size();
        return convertToQuoteDto(quoteService.findOne(getRandom(max)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody QuoteDTO quoteDTO){
            quoteService.save(convertToQuote(quoteDTO));
            return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody QuoteDTO quoteDTO){
        Quote quote = quoteService.findOne(id);
        quote.setQuoteDesc(quoteDTO.getQuoteDesc());
        quoteService.update(quote);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        Quote quote = quoteService.findOne(id);
        quoteService.delete(quote);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Quote convertToQuote(QuoteDTO quoteDTO){
        return modelMapper.map(quoteDTO, Quote.class);
    }

    private QuoteDTO convertToQuoteDto(Quote quote){
        return modelMapper.map(quote, QuoteDTO.class);
    }
    @ExceptionHandler
    private ResponseEntity<QuoteErrorResponse> handleException(QuoteNotCreatedException e){
        QuoteErrorResponse response = new  QuoteErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler
    private ResponseEntity<QuoteErrorResponse> handleException(QuoteNotFoundException e){
        QuoteErrorResponse response = new QuoteErrorResponse(
                "quote with this id wasn't found!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}

    public int getRandom(int max){
        Random random = new Random();
        return random.ints(0, max+1)
                .findFirst()
                .getAsInt();
    }

    private VoteResponse convertToVoteResponse(Vote vote){
        return modelMapper.map(vote, VoteResponse.class);
    }
}
