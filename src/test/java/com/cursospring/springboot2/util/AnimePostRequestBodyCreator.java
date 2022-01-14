package com.cursospring.springboot2.util;

import com.cursospring.springboot2.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody
                .builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}
