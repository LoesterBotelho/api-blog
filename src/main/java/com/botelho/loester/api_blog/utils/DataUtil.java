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

        postList.add(createPost(
                "Socrates",
                "A importância das perguntas",
                "Questionar é uma forma de buscar conhecimento. "
                + "Uma boa pergunta pode ajudar uma pessoa a analisar suas próprias ideias "
                + "e perceber que ainda existem muitos pontos que precisam ser compreendidos."
        ));

        postList.add(createPost(
                "Platao",
                "Conhecimento e realidade",
                "O conhecimento pode ser analisado a partir da relação entre aquilo que percebemos "
                + "e aquilo que buscamos compreender por meio da razão. "
                + "A busca pelo conhecimento exige reflexão e investigação."
        ));

        postList.add(createPost(
                "Aristoteles",
                "Observação e conhecimento",
                "A observação do mundo pode contribuir para a construção do conhecimento. "
                + "Estudar diferentes fenômenos permite comparar informações, identificar padrões "
                + "e desenvolver explicações mais organizadas."
        ));

        postList.add(createPost(
                "Pitágoras",
                "Matemática e padrões",
                "A matemática permite representar relações e padrões encontrados em diferentes áreas. "
                + "Números, proporções e formas podem ser utilizados para compreender problemas "
                + "e organizar informações."
        ));

        postList.add(createPost(
                "Arquimedes",
                "Matemática aplicada",
                "A matemática pode ser utilizada para resolver problemas práticos. "
                + "Geometria, medidas e princípios físicos permitem criar soluções para diferentes "
                + "desafios encontrados na engenharia e na construção."
        ));

        postList.add(createPost(
                "Galileu Galilei",
                "Observação científica",
                "A ciência pode avançar quando observações são combinadas com experimentos "
                + "e análises matemáticas. Testar hipóteses ajuda a compreender melhor "
                + "os fenômenos naturais."
        ));

        postList.add(createPost(
                "Isaac Newton",
                "Leis do movimento",
                "O estudo do movimento permite compreender relações entre força, massa e aceleração. "
                + "Modelos matemáticos podem ajudar a representar fenômenos físicos "
                + "e prever diferentes comportamentos."
        ));

        postList.add(createPost(
                "Albert Einstein",
                "Espaço e tempo",
                "O estudo da física mostra que conceitos como espaço e tempo podem ser analisados "
                + "de maneiras diferentes dependendo do referencial utilizado. "
                + "A matemática ajuda a representar essas relações."
        ));

        postList.add(createPost(
                "Marie Curie",
                "Pesquisa científica",
                "A pesquisa científica exige observação, experimentação e persistência. "
                + "O estudo cuidadoso de fenômenos pode produzir novos conhecimentos "
                + "e contribuir para aplicações importantes."
        ));

        postList.add(createPost(
                "Nikola Tesla",
                "Eletricidade e inovação",
                "A eletricidade transformou diversas áreas da sociedade. "
                + "O desenvolvimento de sistemas elétricos exige conhecimento científico, "
                + "engenharia e capacidade de transformar conceitos em aplicações práticas."
        ));

        postList.add(createPost(
                "Michael Faraday",
                "Eletricidade e magnetismo",
                "Fenômenos elétricos e magnéticos estão relacionados de maneiras importantes. "
                + "Experimentos podem revelar relações entre diferentes forças e ajudar "
                + "a desenvolver novas tecnologias."
        ));

        postList.add(createPost(
                "James Clerk Maxwell",
                "Equações e fenômenos físicos",
                "A matemática pode ser utilizada para representar fenômenos físicos complexos. "
                + "Equações permitem organizar diferentes relações e criar modelos "
                + "para estudar o comportamento da natureza."
        ));

        postList.add(createPost(
                "Niels Bohr",
                "Estrutura do átomo",
                "O estudo da estrutura atômica mostra que a matéria possui comportamentos "
                + "que não podem ser compreendidos apenas pela observação cotidiana. "
                + "Modelos científicos ajudam a representar essas características."
        ));

        postList.add(createPost(
                "Max Planck",
                "Energia e quantização",
                "O estudo da energia em escala microscópica levou ao desenvolvimento "
                + "de novos modelos físicos. A física quântica surgiu a partir da investigação "
                + "de fenômenos que não eram explicados adequadamente pelos modelos clássicos."
        ));

        postList.add(createPost(
                "Richard Feynman",
                "Aprender por compreensão",
                "Aprender um conceito exige mais do que memorizar informações. "
                + "Explicar uma ideia com palavras simples pode ajudar a identificar "
                + "o que realmente foi compreendido."
        ));

        postList.add(createPost(
                "Alan Turing",
                "Computação e algoritmos",
                "Um algoritmo pode representar uma sequência organizada de operações "
                + "para resolver determinado problema. A computação utiliza conceitos "
                + "matemáticos e lógicos para processar informações."
        ));

        postList.add(createPost(
                "Ada Lovelace",
                "Programação e algoritmos",
                "Um algoritmo pode ser descrito de maneira estruturada para que uma máquina "
                + "possa executar determinadas operações. A programação combina lógica, "
                + "matemática e representação de instruções."
        ));

        postList.add(createPost(
                "Charles Babbage",
                "Máquinas de cálculo",
                "Máquinas projetadas para realizar cálculos automaticamente demonstram "
                + "como operações matemáticas podem ser organizadas em processos mecânicos. "
                + "Essa ideia contribuiu para a história da computação."
        ));

        postList.add(createPost(
                "Antoine Lavoisier",
                "Transformações químicas",
                "A química pode ser estudada por meio da observação e da medição das substâncias. "
                + "Analisar cuidadosamente os elementos envolvidos em uma transformação "
                + "ajuda a compreender os processos químicos."
        ));

        postList.add(createPost(
                "Dmitri Mendeleev",
                "Organização dos elementos",
                "Organizar elementos químicos de acordo com suas propriedades permite identificar "
                + "relações e padrões. A tabela periódica tornou-se uma importante ferramenta "
                + "para o estudo da química."
        ));

        for (PostModel post : postList) {

            PostModel postSaved = postRepository.save(post);

            System.out.println(
                    "Post salvo com sucesso: " + postSaved.getId()
            );

            saveComments(postSaved);
        }
    }

    private PostModel createPost(
            String autor,
            String titulo,
            String texto) {

        PostModel post = new PostModel();

        post.setAutor(autor);
        post.setData(LocalDate.now());
        post.setTitulo(titulo);
        post.setTexto(texto);

        return post;
    }

    private void saveComments(PostModel post) {

        List<CommentModel> commentList = new ArrayList<>();

        CommentModel comment1 = new CommentModel();

        comment1.setAutor(getCommentAuthor(post.getAutor(), 1));
        comment1.setData(LocalDate.now());
        comment1.setTexto(getCommentText(post.getAutor(), 1));
        comment1.setPost(post);

        CommentModel comment2 = new CommentModel();

        comment2.setAutor(getCommentAuthor(post.getAutor(), 2));
        comment2.setData(LocalDate.now());
        comment2.setTexto(getCommentText(post.getAutor(), 2));
        comment2.setPost(post);

        CommentModel comment3 = new CommentModel();

        comment3.setAutor(getCommentAuthor(post.getAutor(), 3));
        comment3.setData(LocalDate.now());
        comment3.setTexto(getCommentText(post.getAutor(), 3));
        comment3.setPost(post);

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

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

    private String getCommentAuthor(String postAuthor, int index) {

        List<String> authors = new ArrayList<>();

        authors.add("Leonardo da Vinci");
        authors.add("William Shakespeare");
        authors.add("Charles Darwin");

        if (postAuthor.equals("Leonardo da Vinci")) {
            authors.clear();
            authors.add("Michelangelo");
            authors.add("Galileu Galilei");
            authors.add("Nicolau Copérnico");
        }

        return authors.get(index - 1);
    }

    private String getCommentText(String postAuthor, int index) {

        if (postAuthor.equals("Socrates")) {

            if (index == 1) {
                return "Uma pergunta bem formulada pode iniciar uma investigação profunda.";
            }

            if (index == 2) {
                return "A reflexão sobre nossas próprias ideias pode revelar novos caminhos.";
            }

            return "O diálogo permite comparar diferentes perspectivas sobre um problema.";
        }

        if (postAuthor.equals("Platao")) {

            if (index == 1) {
                return "A busca pelo conhecimento exige reflexão e análise.";
            }

            if (index == 2) {
                return "Uma ideia pode ser investigada a partir de diferentes perspectivas.";
            }

            return "O conhecimento pode transformar a maneira como interpretamos a realidade.";
        }

        if (postAuthor.equals("Aristoteles")) {

            if (index == 1) {
                return "A observação cuidadosa é importante para compreender os fenômenos.";
            }

            if (index == 2) {
                return "Organizar informações ajuda a construir explicações mais claras.";
            }

            return "O estudo sistemático permite comparar diferentes possibilidades.";
        }

        if (postAuthor.equals("Pitágoras")) {

            if (index == 1) {
                return "Os padrões matemáticos aparecem em muitas áreas do conhecimento.";
            }

            if (index == 2) {
                return "A matemática oferece uma linguagem para representar relações.";
            }

            return "Estudar proporções ajuda a compreender estruturas e medidas.";
        }

        if (postAuthor.equals("Arquimedes")) {

            if (index == 1) {
                return "Problemas práticos podem ser analisados utilizando princípios matemáticos.";
            }

            if (index == 2) {
                return "A geometria possui aplicações importantes na engenharia.";
            }

            return "Medir corretamente é essencial para construir soluções confiáveis.";
        }

        if (postAuthor.equals("Galileu Galilei")) {

            if (index == 1) {
                return "Experimentos podem ajudar a verificar hipóteses científicas.";
            }

            if (index == 2) {
                return "A observação precisa é fundamental para a investigação científica.";
            }

            return "A matemática pode ajudar a descrever o comportamento dos fenômenos.";
        }

        if (postAuthor.equals("Isaac Newton")) {

            if (index == 1) {
                return "Modelos matemáticos permitem representar diferentes tipos de movimento.";
            }

            if (index == 2) {
                return "Forças podem ser estudadas utilizando relações matemáticas.";
            }

            return "A física permite relacionar observações com modelos quantitativos.";
        }

        if (postAuthor.equals("Albert Einstein")) {

            if (index == 1) {
                return "Modelos científicos podem mudar quando novas evidências aparecem.";
            }

            if (index == 2) {
                return "A relação entre espaço, tempo e movimento é um tema fascinante.";
            }

            return "A matemática oferece ferramentas importantes para representar a natureza.";
        }

        if (postAuthor.equals("Marie Curie")) {

            if (index == 1) {
                return "A pesquisa científica exige dedicação e observação cuidadosa.";
            }

            if (index == 2) {
                return "Novas descobertas podem surgir de perguntas simples e experimentos rigorosos.";
            }

            return "A ciência pode produzir conhecimentos com aplicações importantes.";
        }

        if (postAuthor.equals("Nikola Tesla")) {

            if (index == 1) {
                return "A engenharia transforma princípios científicos em aplicações práticas.";
            }

            if (index == 2) {
                return "A eletricidade possui inúmeras aplicações tecnológicas.";
            }

            return "Inovação depende da combinação entre conhecimento e experimentação.";
        }

        if (postAuthor.equals("Michael Faraday")) {

            if (index == 1) {
                return "Experimentos podem revelar relações entre eletricidade e magnetismo.";
            }

            if (index == 2) {
                return "A investigação experimental é importante para descobrir novos fenômenos.";
            }

            return "Fenômenos físicos podem ser estudados por meio de experimentos controlados.";
        }

        if (postAuthor.equals("James Clerk Maxwell")) {

            if (index == 1) {
                return "Equações permitem representar relações entre diferentes fenômenos.";
            }

            if (index == 2) {
                return "Modelos matemáticos ajudam a organizar conceitos físicos complexos.";
            }

            return "A matemática pode conectar diferentes áreas da física.";
        }

        if (postAuthor.equals("Niels Bohr")) {

            if (index == 1) {
                return "O mundo microscópico apresenta comportamentos diferentes da experiência cotidiana.";
            }

            if (index == 2) {
                return "Modelos atômicos ajudam a representar estruturas que não podemos observar diretamente.";
            }

            return "A física moderna exige novas formas de interpretar determinados fenômenos.";
        }

        if (postAuthor.equals("Max Planck")) {

            if (index == 1) {
                return "A física quântica trouxe novas formas de compreender a energia.";
            }

            if (index == 2) {
                return "Fenômenos microscópicos podem apresentar comportamentos diferentes dos modelos clássicos.";
            }

            return "A investigação científica pode exigir mudanças nos modelos existentes.";
        }

        if (postAuthor.equals("Richard Feynman")) {

            if (index == 1) {
                return "Explicar um conceito com palavras simples é uma boa forma de verificar a compreensão.";
            }

            if (index == 2) {
                return "A curiosidade é importante para continuar aprendendo.";
            }

            return "Resolver problemas exige compreender os princípios envolvidos.";
        }

        if (postAuthor.equals("Alan Turing")) {

            if (index == 1) {
                return "Algoritmos permitem organizar processos para resolver problemas.";
            }

            if (index == 2) {
                return "A lógica matemática possui aplicações importantes na computação.";
            }

            return "Computadores podem executar instruções seguindo processos bem definidos.";
        }

        if (postAuthor.equals("Ada Lovelace")) {

            if (index == 1) {
                return "Programas podem representar sequências organizadas de operações.";
            }

            if (index == 2) {
                return "A programação combina lógica, matemática e instruções.";
            }

            return "Algoritmos podem ser utilizados para resolver diferentes tipos de problemas.";
        }

        if (postAuthor.equals("Charles Babbage")) {

            if (index == 1) {
                return "Máquinas de cálculo podem automatizar operações matemáticas.";
            }

            if (index == 2) {
                return "A automação de cálculos exige processos organizados.";
            }

            return "A computação possui uma longa história baseada em matemática e engenharia.";
        }

        if (postAuthor.equals("Antoine Lavoisier")) {

            if (index == 1) {
                return "A medição precisa é importante para estudar transformações químicas.";
            }

            if (index == 2) {
                return "A química pode ser investigada por meio de experimentos controlados.";
            }

            return "Observar e medir corretamente ajuda a construir explicações científicas.";
        }

        if (postAuthor.equals("Dmitri Mendeleev")) {

            if (index == 1) {
                return "Organizar elementos permite observar relações entre suas propriedades.";
            }

            if (index == 2) {
                return "A classificação ajuda a identificar padrões em grandes conjuntos de informações.";
            }

            return "A tabela periódica é uma ferramenta importante para estudar os elementos químicos.";
        }

        return getDefaultComment(index);
    }

    private String getDefaultComment(int index) {

        if (index == 1) {
            return "O conhecimento pode ser desenvolvido por meio de estudo e investigação.";
        }

        if (index == 2) {
            return "A análise de diferentes perspectivas pode ampliar nossa compreensão.";
        }

        return "A busca por conhecimento depende de curiosidade, estudo e reflexão.";
    }
}