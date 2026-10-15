package com.botelho.loester.api_blog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_post")
public class PostModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaModel categoria;

    @OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<CommentModel> comentarios = new ArrayList<>();

    public PostModel() {
        this.data = LocalDate.now();
    }

    public PostModel(String autor, String titulo, String texto) {
        this.autor = autor;
        this.data = LocalDate.now();
        this.titulo = titulo;
        this.texto = texto;
    }

    public PostModel(UUID id, String autor, String titulo, String texto) {
        this.id = id;
        this.autor = autor;
        this.data = LocalDate.now();
        this.titulo = titulo;
        this.texto = texto;
    }

    public PostModel(
            UUID id,
            String autor,
            LocalDate data,
            String titulo,
            String texto) {
        this.id = id;
        this.autor = autor;
        this.data = data != null ? data : LocalDate.now();
        this.titulo = titulo;
        this.texto = texto;
    }

    @PrePersist
    public void prePersist() {
        if (this.data == null) {
            this.data = LocalDate.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public List<CommentModel> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<CommentModel> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "PostModel [id=" + id
                + ", autor=" + autor
                + ", data=" + data
                + ", titulo=" + titulo
                + ", texto=" + texto
                + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        PostModel other = (PostModel) obj;
        return Objects.equals(id, other.id);
    }
}