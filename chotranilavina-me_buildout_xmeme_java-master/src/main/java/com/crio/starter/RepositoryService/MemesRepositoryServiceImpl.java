package com.crio.starter.RepositoryService;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

//import javax.swing.text.Document;

import com.crio.starter.data.*;
import com.crio.starter.repository.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@ComponentScan(basePackages = "com.crio.starter")
@EnableMongoRepositories(basePackages = "com.crio.starter.repository")
@Primary
@Service
@RequestMapping("/memes")
public class MemesRepositoryServiceImpl implements MemesRespositoryService {

    @Autowired
    private MemesRepository memeRep;

    @Override
    public List<MemesEntity> getAllMemes() {
        List<MemesEntity> temp = memeRep.findAll();
        if(temp.size()<=100) return temp;
        Collections.sort(temp, new Comparator<MemesEntity>() {
            public int compare(MemesEntity o1, MemesEntity o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
          });
        List<MemesEntity> memes = temp.subList(Math.max(temp.size() - 100, 0), temp.size());
        Collections.reverse(memes);
        return memes;
    }

    @Override
    public MemesEntity getMeme(String id) {
        Optional<MemesEntity> meme = memeRep.findByMemeId(id);
        if(meme.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        return meme.get();
    }

    @Override
    public String postMeme(@RequestParam String name, @RequestParam String url, @RequestParam String caption) {
        try (MongoClient mongoClient = MongoClients.create()) {
            List<MemesEntity> temp = memeRep.findAll();
            for (MemesEntity x : temp) {
                if (caption.equals(x.getCaption()) && name.equals(x.getName()) && url.equals(x.getUrl()))
                throw new DataIntegrityViolationException("Duplicate Entry");
            }
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("greetings");
            MongoCollection<Document> memesCollection = sampleTrainingDB.getCollection("memes");
            Document doc = new Document();
            Random num = new Random();
            Integer count = num.nextInt()%100000;
            doc.append("memeId", count.toString());
            doc.append("name", name);
            doc.append("url", url);
            doc.append("caption", caption);
            doc.append("date", LocalDateTime.now());
            memesCollection.insertOne(doc); 
            return count.toString();
        }
    }
    
}