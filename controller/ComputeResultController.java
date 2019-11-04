package com.apress.quickpoll.controller;

import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.domain.Vote;
import com.apress.quickpoll.dto.VoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultController {

  @Autowired
  private VoteRepository voteRepository;

  @RequestMapping(value = "/computerresult", method = RequestMethod.GET)
  public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
    VoteResult voteResult = new VoteResult();
    Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

    return new ResponseEntity<>(voteResult, HttpStatus.OK);
  }

}
