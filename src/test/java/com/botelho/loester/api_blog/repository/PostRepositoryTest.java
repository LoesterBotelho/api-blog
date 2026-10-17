
package com.botelho.loester.api_blog.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.botelho.loester.api_blog.model.PostModel;

import org.assertj.core.api.Assertions;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Testes de integração do PostRepository")
class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    @DisplayName("Deve salvar e retornar um post")
    void deveSalvarERetornarPost() {

        // Arrange (Organizar)
        PostModel post = new PostModel(
                "Loester Botelho",
                "Primeiro post",
                "Conteúdo do primeiro post"
        );

        // Act (Executar)
        PostModel postSalvo = repository.save(post);

        Optional<PostModel> resultado =
                repository.findById(postSalvo.getId());

        // Assert (Verificar)
        Assertions.assertThat(postSalvo.getId())
                .isNotNull();

        Assertions.assertThat(resultado)
                .isPresent();

        PostModel postEncontrado = resultado.orElseThrow();

        Assertions.assertThat(postEncontrado.getAutor())
                .isEqualTo("Loester Botelho");

        Assertions.assertThat(postEncontrado.getTitulo())
                .isEqualTo("Primeiro post");

        Assertions.assertThat(postEncontrado.getTexto())
                .isEqualTo("Conteúdo do primeiro post");

        Assertions.assertThat(postEncontrado.getData())
                .isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Deve gerar um identificador para o post salvo")
    void deveGerarIdentificadorAoSalvarPost() {

        // Arrange
        PostModel post = new PostModel(
                "Henrique",
                "Testes unitários",
                "Aprendendo testes com JUnit"
        );

        // Act
        PostModel postSalvo = repository.save(post);

        // Assert
        Assertions.assertThat(postSalvo.getId())
                .isNotNull()
                .isInstanceOf(UUID.class);
    }

    @Test
    @DisplayName("Deve retornar vazio quando o post não existir")
    void deveRetornarVazioQuandoPostNaoExistir() {

        // Arrange
        UUID idInexistente = UUID.randomUUID();

        // Act
        Optional<PostModel> resultado =
                repository.findById(idInexistente);

        // Assert
        Assertions.assertThat(resultado)
                .isEmpty();
    }
}