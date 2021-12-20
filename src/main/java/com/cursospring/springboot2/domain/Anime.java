package com.cursospring.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // método get, set, equals, toString, hashcode -- vai gerar tudo isto na classe
@AllArgsConstructor
public class Anime {
    private Long id;
    private String name;
}