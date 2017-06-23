package br.com.helpme.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataPedido;
	
	@OneToMany(mappedBy ="produto",cascade =CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private List<Produto> produtos;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private PessoaFisica pessoaFisica;

	@OneToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;

	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Municipio municipio;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((pessoaFisica == null) ? 0 : pessoaFisica.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
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
		Pedido other = (Pedido) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (id != other.id)
			return false;
		if (pessoaFisica == null) {
			if (other.pessoaFisica != null)
				return false;
		} else if (!pessoaFisica.equals(other.pessoaFisica))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}
	
}
