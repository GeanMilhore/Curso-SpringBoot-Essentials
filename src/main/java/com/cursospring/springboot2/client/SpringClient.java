package com.cursospring.springboot2.client;

import com.cursospring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //                                                                  podemos passar como placeholder {id} e adicionar o valor depois da classe
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/7", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/7", Anime.class);
        log.info(object);
    }
}
