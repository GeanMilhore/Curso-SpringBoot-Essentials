package com.cursospring.springboot2.client;

import com.cursospring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/7", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/7", Anime.class);
        log.info(object);

        // modo array
        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        // supertype token, método exchange
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange(
                "http://localhost:8080/animes/all",
                HttpMethod.GET,
                /* reques object  */ null,
                new ParameterizedTypeReference<List<Anime>>() {}
        );
        log.info(exchange.getBody());

        // POST

//        Anime loveIsWar = Anime.builder().name("loveiswar").build();
//        Anime loveIsWarSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", loveIsWar, Anime.class);
//        log.info("anime saved {}", loveIsWarSaved);

        Anime bakugan = Anime.builder().name("bakugan").build();
        ResponseEntity<Anime> bakuganSaved = new RestTemplate().exchange(
                "http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(bakugan, createJsonHeader()),
                Anime.class
        );
        log.info("anime saved {}", bakuganSaved);


        // PUT
        Anime animeToBeUpdated = bakuganSaved.getBody();
        animeToBeUpdated.setName("Bakugan Updatado");

        ResponseEntity<Void> bakuganUpdated = new RestTemplate().exchange(
                "http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);

        log.info(bakuganUpdated);

        // DELETE
        ResponseEntity<Void> bakuganDeleted = new RestTemplate().exchange(
                "http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());
        log.info(bakuganDeleted);

    }

    // exchange permite criar header http dentro do httpEntity
    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
