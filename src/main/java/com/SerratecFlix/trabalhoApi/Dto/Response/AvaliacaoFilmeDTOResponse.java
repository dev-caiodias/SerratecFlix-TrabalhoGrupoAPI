package com.SerratecFlix.trabalhoApi.Dto.Response;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Schema(description = "Modelo de dados para apresentação de informações de avaliação de um filme ao usuario.")
public class AvaliacaoFilmeDTOResponse {

    @Schema(description = "Chave primária de identificação da avaliação cinematográfica.", example = "15")
    private Long id;

    @Schema(description = "Nota de avaliação do filme.", example = "7.9")
    private Double nota;

    @Schema(description = "Comentario de avaliação que acompanha a nota.", example = "Muito Legal!")
    private String comentario;

    @Schema(description = "Data em que a avaliação foi registrada", example = "2026-05-27")
    private LocalDateTime dataAvaliacao;

    @Schema(description = "O nome do autor da avaliação", example = "João Carlos")
    private String nomeUsuario;

    @Schema(description = "Título do filme avaliado", example = "De volta pro futuro")
    private  String tituloFilme;
}
