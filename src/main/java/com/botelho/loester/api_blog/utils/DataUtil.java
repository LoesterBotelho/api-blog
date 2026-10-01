package com.botelho.loester.api_blog.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.botelho.loester.api_blog.model.CommentModel;
import com.botelho.loester.api_blog.model.PostModel;
import com.botelho.loester.api_blog.repository.CommentRepository;
import com.botelho.loester.api_blog.repository.PostRepository;

@Component
public class DataUtil {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    //@PostConstruct
    public void savePosts() {

        List<PostModel> postList = new ArrayList<>();

        PostModel post1 = new PostModel();

        post1.setAutor("Loester Botelho");
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto(
                "O Docker é uma plataforma utilizada para criar, executar e distribuir "
                + "aplicações em ambientes isolados chamados containers. "
                + "Com o Docker, é possível empacotar a aplicação junto com suas dependências, "
                + "facilitando a configuração do ambiente de desenvolvimento e evitando problemas "
                + "causados por diferenças entre as máquinas. "
                + "Em projetos Java com Spring Boot, o Docker pode ser utilizado para executar "
                + "a aplicação e também serviços externos, como bancos de dados MariaDB, "
                + "MySQL ou PostgreSQL."
        );

        PostModel post2 = new PostModel();

        post2.setAutor("Loester Botelho");
        post2.setData(LocalDate.now());
        post2.setTitulo("API REST");
        post2.setTexto(
                "Uma API REST permite que diferentes aplicações se comuniquem através da internet "
                + "utilizando o protocolo HTTP. "
                + "Em uma aplicação Spring Boot, podemos criar endpoints para realizar operações "
                + "como cadastrar, consultar, atualizar e excluir informações. "
                + "Os principais métodos HTTP utilizados em uma API REST são GET para consultas, "
                + "POST para criação, PUT ou PATCH para alterações e DELETE para exclusão. "
                + "Normalmente, os dados são enviados e recebidos no formato JSON, "
                + "facilitando a integração entre o backend e aplicações frontend."
        );

        PostModel post3 = new PostModel();

        post3.setAutor("Loester Botelho");
        post3.setData(LocalDate.now());
        post3.setTitulo("Spring Boot");
        post3.setTexto(
                "O Spring Boot facilita o desenvolvimento de aplicações Java utilizando o ecossistema "
                + "Spring. Ele oferece configurações automáticas, integração com diferentes tecnologias "
                + "e recursos que permitem criar APIs REST de forma rápida e organizada. "
                + "Com Spring Boot, podemos trabalhar com persistência de dados, validação, segurança, "
                + "documentação de APIs e integração com diferentes serviços."
        );

        postList.add(post1);
        postList.add(post2);
        postList.add(post3);

        for (PostModel post : postList) {

            PostModel postSaved = postRepository.save(post);

            System.out.println(
                    "Post salvo com sucesso: " + postSaved.getId()
            );

            saveComments(postSaved);
        }
    }

    private void saveComments(PostModel post) {

        List<CommentModel> commentList = new ArrayList<>();

        CommentModel commentEinstein = new CommentModel();

        commentEinstein.setAutor("Albert Einstein");
        commentEinstein.setData(LocalDate.now());
        commentEinstein.setTexto(
                "A tecnologia deve ampliar nossa capacidade de compreender "
                + "e transformar o mundo."
        );
        commentEinstein.setPost(post);

        CommentModel commentJung = new CommentModel();

        commentJung.setAutor("Carl Jung");
        commentJung.setData(LocalDate.now());
        commentJung.setTexto(
                "O conhecimento técnico também pode contribuir para "
                + "o desenvolvimento e a compreensão humana."
        );
        commentJung.setPost(post);

        CommentModel commentFreud = new CommentModel();

        commentFreud.setAutor("Sigmund Freud");
        commentFreud.setData(LocalDate.now());
        commentFreud.setTexto(
                "A relação entre tecnologia e comportamento humano "
                + "é um tema interessante para reflexão."
        );
        commentFreud.setPost(post);

        commentList.add(commentEinstein);
        commentList.add(commentJung);
        commentList.add(commentFreud);

        for (CommentModel comment : commentList) {

            CommentModel commentSaved =
                    commentRepository.save(comment);

            System.out.println(
                    "Comentário salvo com sucesso: "
                    + commentSaved.getId()
                    + " - Post: "
                    + post.getId()
            );
        }
    }
}
