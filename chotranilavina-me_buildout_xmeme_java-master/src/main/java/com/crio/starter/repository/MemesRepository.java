package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;

import com.crio.starter.data.MemesEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemesRepository extends MongoRepository<MemesEntity, String> {
  public List<MemesEntity> findAll();
  public Optional<MemesEntity> findByMemeId(String id);
}
