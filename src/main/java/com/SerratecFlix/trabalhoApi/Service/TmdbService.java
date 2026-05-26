package com.SerratecFlix.trabalhoApi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.SerratecFlix.trabalhoApi.Domain.Filme;
import com.SerratecFlix.trabalhoApi.Repository.FilmeRepository;
import com.SerratecFlix.trabalhoApi.Dto.Integration.TmdbSearchResponse;
import com.SerratecFlix.trabalhoApi.Domain.enums.ClassificacaoIndicativa;
import java.time.LocalDate;

@Service
public class TmdbService {

    @Autowired
    private FilmeRepository filmeRepository;

    private final RestClient restClient = RestClient.create();

    @Value("${tmdb.api.token}")
    private String TMDB_TOKEN;

    public void buscarESalvarFilme(String nomeDoFilmeBusca) {
        String url = "https://api.themoviedb.org/3/search/movie?query=" + nomeDoFilmeBusca + "&language=pt-BR";

        TmdbSearchResponse response = restClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + TMDB_TOKEN)
                .retrieve()
                .body(TmdbSearchResponse.class);

        if (response != null && response.results() != null && !response.results().isEmpty()) {
            var tmdbMovie = response.results().get(0);

            Filme novoFilme = Filme.builder()
                    .titulo(tmdbMovie.title())
                    .descricao(tmdbMovie.overview())
                    .duracao(120)
                    .dataLancamento(LocalDate.parse(tmdbMovie.release_date()))
                    .classificacaoIndicativa(ClassificacaoIndicativa.LIVRE) // Padrão inicial
                    .notaMedia(0.0)
                    .build();

            filmeRepository.save(novoFilme);
            System.out.println("Filme salvo com sucesso: " + novoFilme.getTitulo());
        }
    }
}