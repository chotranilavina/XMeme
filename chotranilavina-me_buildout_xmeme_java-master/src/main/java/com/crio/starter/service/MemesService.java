package com.crio.starter.service;

import com.crio.starter.data.*;
import com.crio.starter.exchange.*;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface MemesService {

  public getResponseMeme getAllMemes();

  public MemesEntity getMemes(String id);

  public getPostResponse postMeme(@RequestParam @NotNull MemesEntity meme);
}
