package com.crio.starter.controller;

import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.*;
import com.crio.starter.service.MemesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@Log4j2
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemesController {
  
  @Autowired
  private final MemesService memesService;

  @GetMapping("/memes")
  public List<MemesEntity> getMemes() {

    log.info("getMemes called");
    getResponseMeme getMemeResponse;
    getMemeResponse = memesService.getAllMemes();

    return getMemeResponse.getMemes();
  }

  @GetMapping("/memes/{memeId}")
  public MemesEntity getMeme(@PathVariable String memeId) {

    log.info("getMeme called with {}", memeId);
    MemesEntity getMemeResponse;
    getMemeResponse = memesService.getMemes(memeId);

    return getMemeResponse;
  }

  @PostMapping("/memes")
  public getPostResponse postMeme(@Validated @RequestBody String memeJson)  {
    log.info("postMeme called with {}", memeJson);
    ObjectMapper objectMapper = new ObjectMapper();
    MemesEntity meme = new MemesEntity();
    try {
      meme = objectMapper.readValue(memeJson, MemesEntity.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    if(Stream.of(meme.getName(),meme.getUrl(),meme.getCaption()).allMatch(Objects::isNull))
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't post empty object");
    return memesService.postMeme(meme);
  }

}
