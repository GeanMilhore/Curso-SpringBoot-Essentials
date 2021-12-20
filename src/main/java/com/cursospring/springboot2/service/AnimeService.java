package com.cursospring.springboot2.service;

import com.cursospring.springboot2.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService{

    private List<Anime> animes = List.of(new Anime(1L,"Boku no Hero"), new Anime(2L,"Dragon Ball"));

    // private final AnimeRepository animeRepository; --futuro

    public List<Anime> listAll(){
        return animes;
    }

    public Anime findById(long id){
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found!"));
    }
}
