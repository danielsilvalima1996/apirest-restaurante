package br.com.restaurante.apirestrestaurante.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "prato")
public class Prato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_restaurante")
	private Restaurante idRestaurante;

	@NotBlank(message = "Prato não pode ser vazio")
	@Column(name = "prato", length = 255, nullable = false)
	private String prato;

	@NotNull(message = "Preço não pode ser vazio")
	@Column(name = "preco", nullable = false)
	private Double preco;

	public Prato() {
	}

	public Prato(Long id, Restaurante idRestaurante, @NotBlank(message = "Prato não pode ser vazio") String prato,
			@NotBlank(message = "Preço não pode ser vazio") Double preco) {
		this.id = id;
		this.idRestaurante = idRestaurante;
		this.prato = prato;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurante getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Restaurante idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idRestaurante == null) ? 0 : idRestaurante.hashCode());
		result = prime * result + ((prato == null) ? 0 : prato.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prato other = (Prato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRestaurante == null) {
			if (other.idRestaurante != null)
				return false;
		} else if (!idRestaurante.equals(other.idRestaurante))
			return false;
		if (prato == null) {
			if (other.prato != null)
				return false;
		} else if (!prato.equals(other.prato))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		return true;
	}

}
