package com.cursospring.springboot2.service;

import com.cursospring.springboot2.domain.Anime;
import com.cursospring.springboot2.exception.BadRequestException;
import com.cursospring.springboot2.mapper.AnimeMapper;
import com.cursospring.springboot2.repository.AnimeRepository;
import com.cursospring.springboot2.requests.AnimePostRequestBody;
import com.cursospring.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService{

    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id)
                        .orElseThrow(() -> new BadRequestException("Anime not Found!"));
    }

    // não da rollback para exeções do tipo checked
    // apenas com @Transactional(roolbackFor = Exception.class);
    @Transactional // caso haja uma exeção ele faz o rollback
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
