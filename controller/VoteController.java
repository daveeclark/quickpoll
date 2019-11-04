package com.apress.quickpoll.controller;

import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class VoteController {

  @Autowired
  private VoteRepository voteRepository;

  @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
  public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
    return voteRepository. findByPoll(pollId);
  }

  @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
  public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
    vote = voteRepository.save(vote);

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

}
