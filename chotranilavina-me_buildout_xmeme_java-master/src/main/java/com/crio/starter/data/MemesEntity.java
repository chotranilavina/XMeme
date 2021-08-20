package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(value="Unknown")
@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemesEntity {

  private String memeId;
  @NonNull
  private String name;
  @NonNull
  private String url;
  @NonNull
  private String caption;
  private LocalDateTime date;
}
