package dev.pedrolobo.onlineshopping.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dev.pedrolobo.onlineshopping.dao.generic.GenericDAO;
import dev.pedrolobo.onlineshopping.domain.Costumer;

public class CostumerDAO extends GenericDAO<Costumer, Long> implements ICostumerDAO {

  CostumerDAO() {
    super();
  }

  @Override
  public Class<Costumer> getClassType() {
    return Costumer.class;
  }

  @Override
  public void updateData(Costumer entity, Costumer entityRegistered) {
    entityRegistered.setCity(entity.getCity());
    entityRegistered.setCpf(entity.getCpf());
    entityRegistered.setAddress(entity.getAddress());
    entityRegistered.setState(entity.getState());
    entityRegistered.setName(entity.getName());
    entityRegistered.setHouseNumber(entity.getHouseNumber());
    entityRegistered.setTelephoneNumber(entity.getTelephoneNumber());
  }

  @Override
  protected String getQueryInsertion() {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_costumer");
    sb.append("(id, name, cpf, telephone_number, city, address, state, house_number) ");
    sb.append("VALUES(nextval('sq_costumer'), ?, ?, ?, ?, ?, ?, ?)");
    return sb.toString();
  }

  @Override
  public String getQueryExclusion() {
    return "DELETE FROM tb_costumer WHERE cpf = ?";
  }

  @Override
  protected String getQueryUpdate() {
    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE tb_costumer SET ");
    sb.append("name = ?, ");
    sb.append("telephone_number = ?, ");
    sb.append("city = ?, ");
    sb.append("address = ?, ");
    sb.append("state = ?, ");
    sb.append("house_number = ? ");
    sb.append("WHERE cpf = ?");
    return sb.toString();
  }

  @Override
  protected void setInsertionQueryParameters(PreparedStatement stmInsert, Costumer entity) throws SQLException {
    stmInsert.setString(1, entity.getName());
    stmInsert.setLong(2, entity.getCpf());
    stmInsert.setLong(3, entity.getTelephoneNumber());
    stmInsert.setString(4, entity.getCity());
    stmInsert.setString(5, entity.getAddress());
    stmInsert.setString(6, entity.getState());
    stmInsert.setLong(7, entity.getHouseNumber());
  }

  @Override
  protected void setExclusionQueryParameters(PreparedStatement stmDelete, Long value) throws SQLException {
    stmDelete.setLong(1, value);
  }

  @Override
  protected void setUpdateQueryParameters(PreparedStatement stmUpdate, Costumer entity) throws SQLException {
    stmUpdate.setString(1, entity.getName());
    stmUpdate.setLong(2, entity.getTelephoneNumber());
    stmUpdate.setString(3, entity.getCity());
    stmUpdate.setString(4, entity.getAddress());
    stmUpdate.setString(5, entity.getState());
    stmUpdate.setLong(6, entity.getHouseNumber());
    stmUpdate.setLong(7, entity.getCpf());
  }

  @Override
  protected void setSelectQueryParameters(PreparedStatement stmSelect, Long value) throws SQLException {
    stmSelect.setLong(1, value);
  }

}
