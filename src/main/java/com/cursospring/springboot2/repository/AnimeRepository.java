package com.cursospring.springboot2.repository;

import com.cursospring.springboot2.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
