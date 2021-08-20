package com.crio.starter.exchange;

import com.crio.starter.data.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class getResponseMeme {

  List<MemesEntity> memes = new ArrayList<>(); 

}
