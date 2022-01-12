package com.cursospring.springboot2.controller;

import com.cursospring.springboot2.domain.Anime;
import com.cursospring.springboot2.service.AnimeService;
import com.cursospring.springboot2.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

// @SpringBootTest vai tentar iniciar a aplicação spring antes de realizar os testes, causa dependencia (ex:docker)

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks // utilizado para classe que estamos testando
    private AnimeController animeController;

    @Mock // todas as classes injetadas dentro da classe que são utilizadas dentro da principal de teste
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp(){
        //                                         JDK Error / change
        PageImpl<Anime> animePage = new PageImpl<>(Arrays.asList(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any())).thenReturn(animePage);
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}