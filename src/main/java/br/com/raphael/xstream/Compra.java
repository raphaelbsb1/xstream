/**
 * 
 */
package br.com.raphael.xstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author raphael.santos
 *
 */
public class Compra implements Serializable {
	
	private int codigo;
	private List<Produto> produtos  = new ArrayList<Produto>();
	
	public Compra(int id, List<Produto> produtos) {
		this.codigo = id;
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Compra other = (Compra) obj;
		if (codigo != other.codigo)
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
