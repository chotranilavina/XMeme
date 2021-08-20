package com.crio.starter.RepositoryService;

import com.crio.starter.data.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemesRespositoryService {

    List<MemesEntity> getAllMemes();
    MemesEntity getMeme(String id);
    String postMeme(String name, String url,String caption);
}