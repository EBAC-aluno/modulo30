package br.com.rpires.loja.dao.factory;

/**
 * 
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.loja.domain.Produto;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoFactory {

	
	public static Produto convert(ResultSet rs) throws SQLException {
		Produto prod = new Produto();
		prod.setId(rs.getLong("ID"));
		prod.setCodigo(rs.getString("codigo"));
		prod.setNome(rs.getString("NOME"));
		prod.setDescricao(rs.getString("DESCRICAO"));
		prod.setValor(rs.getBigDecimal("VALOR"));
		prod.setUnidadeDeMensuracao(rs.getString("unidade_de_mensuracao"));
		return prod;
	}
}
