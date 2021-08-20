package com.crio.starter.service;

import com.crio.starter.RepositoryService.MemesRespositoryService;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.exchange.getPostResponse;
import com.crio.starter.exchange.getResponseMeme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemesServiceImpl implements MemesService {

    @Autowired
    MemesRespositoryService memesRespositoryService;

    @Override
    public getResponseMeme getAllMemes() {
        return new getResponseMeme(memesRespositoryService.getAllMemes());
    }

    @Override
    public MemesEntity getMemes(String id) {
        return memesRespositoryService.getMeme(id);
    }

    @Override
    public getPostResponse postMeme(MemesEntity meme) {
        return new getPostResponse(memesRespositoryService.postMeme(meme.getName(), meme.getUrl(), 
        meme.getCaption()));
    }


}