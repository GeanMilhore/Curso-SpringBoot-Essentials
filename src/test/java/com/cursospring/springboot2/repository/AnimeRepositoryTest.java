package com.cursospring.springboot2.repository;

import com.cursospring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@Log4j2
@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @DisplayName("Save creates anime when successful")
    @Test
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime("Bleach");
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @DisplayName("Save updates anime when successful")
    @Test
    void save_UpdatesAnime_WhenSuccessful(){

        Anime animeToBeSaved = createAnime("Bleach");

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("MyHeroAcademy");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();

        Assertions.assertThat(animeUpdated.getId()).isNotNull();

        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @DisplayName("Delete removes anime when successful")
    @Test
    void delete_RemovesAnime_WhenSuccessful(){

        Anime animeToBeSaved = createAnime("Bleach");

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @DisplayName("Find By Name returns list of anime when successful")
    @Test
    void findByName_ReturnsListOfAnime_WhenSuccessful(){

        Anime animeToBeSaved = createAnime("Bleach");

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();

        Assertions.assertThat(animes).contains(animeSaved);
    }

    @DisplayName("Find By Name returns empty list when anime is not found")
    @Test
    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound(){

        List<Anime> animes = this.animeRepository.findByName("not a name");

        Assertions.assertThat(animes).isEmpty();
        Assertions.assertThat(animes).isNotNull();
    }

    private Anime createAnime(String animeNameToBeCreated){
        return Anime.builder().name(animeNameToBeCreated).build();
    }
}