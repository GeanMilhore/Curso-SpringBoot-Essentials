package com.cursospring.springboot2.util;

import com.cursospring.springboot2.domain.Anime;

public class AnimeCreator {

    public static  Anime createAnimeToBeSaved(){
        return Anime
                .builder()
                .build();
    }

    public static Anime createValidAnime(){
        return Anime
                .builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime
                .builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }

    public static Anime createAnimeWithName(String animeName){
        return Anime.builder().name(animeName).build();
    }

}
