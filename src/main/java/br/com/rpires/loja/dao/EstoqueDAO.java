package br.com.rpires.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.loja.dao.factory.EstoqueFactory;
import br.com.rpires.loja.dao.generic.GenericDAO;
import br.com.rpires.loja.dao.generic.jdbc.ConnectionFactory;
import br.com.rpires.loja.domain.Estoque;
import br.com.rpires.loja.exceptions.DAOException;

public class EstoqueDAO extends GenericDAO<Estoque, String> implements IEstoqueDAO {

  @Override
  public Class<Estoque> getTipoClasse() {
    return Estoque.class;
  }

  @Override
  public void atualiarDados(Estoque entity, Estoque entityCadastrado) {
    entityCadastrado.setQuantidade(entity.getQuantidade() - entity.getProdutoQuantidade().getQuantidade());
    entityCadastrado.setCodigoNotaFiscalFornecedor(entity.getCodigoNotaFiscalFornecedor());
  }

  @Override
  protected String getQueryInsercao() {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_estoque");
    sb.append("(id, codigo_nota_fiscal_fornecedor, id_produto_quantidade_fk, quantidade) ");
    sb.append("VALUES(nextval('sq_estoque'), ?, ?, ?)");
    return sb.toString();
  }

  @Override
  protected String getQueryExclusao() {
    throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
  }

  @Override
  protected String getQueryAtualizacao() {
    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE tb_estoque ");
    sb.append("SET quantidade = ? ");
    sb.append("WHERE codigo_nota_fiscal_fornecedor = ?");
    return sb.toString();
  }

  @Override
  protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque entity) throws SQLException {
    stmInsert.setString(1, entity.getCodigoNotaFiscalFornecedor());
    stmInsert.setLong(2, entity.getProdutoQuantidade().getId());
    stmInsert.setLong(3, entity.getQuantidade());
  }

  @Override
  protected void setParametrosQueryExclusao(PreparedStatement stmDelete, String valor) throws SQLException {

  }

  @Override
  protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque entity) throws SQLException {
    stmUpdate.setLong(1, entity.getQuantidade());
    stmUpdate.setString(2, entity.getCodigoNotaFiscalFornecedor());
  }

  @Override
  protected void setParametrosQuerySelect(PreparedStatement stmSelect, String valor) throws SQLException {
    stmSelect.setString(1, valor);
  }
}
