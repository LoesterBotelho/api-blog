package com.botelho.loester.api_blog.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class CategoriaModel  implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @JdbcTypeCode(SqlTypes.CHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

	public CategoriaModel() {
	}

	public CategoriaModel(UUID id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategoriaModel(String nome) {	
		this.nome = nome;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		CategoriaModel other = (CategoriaModel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CategoriaModel [id=" + id + ", nome=" + nome + "]";
	}    
	
}


