package dev.pedrolobo.onlineshopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import dev.pedrolobo.onlineshopping.dao.generic.GenericDAO;
import dev.pedrolobo.onlineshopping.domain.Transaction;
import dev.pedrolobo.onlineshopping.domain.Transaction.Status;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;

public class TransactionDAO extends GenericDAO<Transaction, String> implements ITransactionDAO {

  @Override
  public void finalizeTransaction(Transaction transaction) throws KeyTypeNotFoundedException, DAOException {
    Connection connection = null;
    PreparedStatement stm = null;
    try {
      String sql = "UPDATE tb_transaction SET STATUS_VENDA = ? WHERE ID = ?";
      connection = getConnection();
      stm = connection.prepareStatement(sql);
      stm.setString(1, Status.DONE.name());
      stm.setLong(2, transaction.getId());
      stm.executeUpdate();

    } catch (SQLException e) {
      throw new DAOException("ERRO ATUALIZANDO OBJETO ", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }

  @Override
  public void cancelTransaction(Transaction venda) throws KeyTypeNotFoundedException, DAOException {
    Connection connection = null;
    PreparedStatement stm = null;
    try {
      String sql = "UPDATE tb_transacton SET STATUS_transa = ? WHERE ID = ?";
      connection = getConnection();
      stm = connection.prepareStatement(sql);
      stm.setString(1, Status.CANCELLED.name());
      stm.setLong(2, venda.getId());
      stm.executeUpdate();

    } catch (SQLException e) {
      throw new DAOException("ERRO ATUALIZANDO OBJETO ", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }

  @Override
  public Class<Transaction> getClassType() {
    return Transaction.class;
  }

  @Override
  public void updateData(Transaction entity, Transaction entityRegistered) {
    entityRegistered.setCode(entity.getCode());
    entityRegistered.setStatus(entity.getStatus());
  }

  @Override
  protected String getQueryInsertion() {
		StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_transaction... ");
		return sb.toString();
  }

  @Override
  public String getQueryExclusion() {
		throw new UnsupportedOperationException("Operation denied");
  }

  @Override
  protected String getQueryUpdate() {
		throw new UnsupportedOperationException("Operation denied");
  }

  @Override
  protected void setInsertionQueryParameters(PreparedStatement stmInsert, Transaction entity) throws SQLException {
		stmInsert.setString(1, entity.getCode());
		stmInsert.setLong(2, entity.getCustomer().getId());
		stmInsert.setBigDecimal(3, entity.getTotalAmount());
		stmInsert.setTimestamp(4, Timestamp.from(entity.getSellDate()));
		stmInsert.setString(5, entity.getStatus().name());
  }

  @Override
  protected void setExclusionQueryParameters(PreparedStatement stmDelete, String value) throws SQLException {
		throw new UnsupportedOperationException("Operation denied");
  }

  @Override
  protected void setUpdateQueryParameters(PreparedStatement stmUpdate, Transaction entity) throws SQLException {
		throw new UnsupportedOperationException("Operation denied");
  }

  @Override
  protected void setSelectQueryParameters(PreparedStatement stmSelect, String value) throws SQLException {
    stmSelect.setString(1, value);
  }
}
