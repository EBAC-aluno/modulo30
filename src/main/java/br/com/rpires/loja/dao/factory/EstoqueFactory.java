package br.com.rpires.loja.dao.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.loja.dao.generic.jdbc.ConnectionFactory;
import br.com.rpires.loja.domain.Estoque;
import br.com.rpires.loja.exceptions.DAOException;


public class EstoqueFactory {

	public static Estoque convert(ResultSet rs) throws SQLException, DAOException {
		Estoque estoque = new Estoque();
		estoque.setId(rs.getLong("id"));
    estoque.setCodigoNotaFiscalFornecedor(rs.getString("codigo_nota_fiscal_fornecedor"));
    estoque.setQuantidade(rs.getInt("quantidade"));
		return estoque;
	}

  public static Long getIdDaTbProdutoQuantidade() throws SQLException {

    Connection connection = ConnectionFactory.getConnection();
    PreparedStatement stm = connection.prepareStatement(
        "SELECT id FROM tb_produto_quantidade WHERE id_venda_fk = (SELECT id FROM tb_venda WHERE codigo = 'V1')");
    ResultSet rs = stm.executeQuery();
    Long id = null;
    while (rs.next()) {
      id = rs.getLong("id");
    }
    return id;
  }

}

