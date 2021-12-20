package com.cursospring.springboot2.service;

import com.cursospring.springboot2.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService{

    // private final AnimeRepository animeRepository; --futuro

    public List<Anime> listAll(){
        return List.of(new Anime(1L,"Boku no Hero"), new Anime(2L,"Dragon Ball"));
    }
}
