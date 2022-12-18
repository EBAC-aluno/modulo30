package dev.pedrolobo.onlineshopping.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import dev.pedrolobo.onlineshopping.dao.generic.GenericDAO;
import dev.pedrolobo.onlineshopping.domain.Product;

public class ProductDAO extends GenericDAO<Product, String> implements IProduct {

  public ProductDAO() {
    super();
  }

  @Override
  public Class<Product> getClassType() {
    return Product.class;
  }

  @Override
  public void updateData(Product entity, Product entityRegistered) {
    entityRegistered.setCode(entity.getCode());
    entityRegistered.setDescription(entity.getDescription());
    entityRegistered.setName(entity.getName());
    entityRegistered.setValue(entity.getValue());
  }

  @Override
  protected String getQueryInsertion() {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_product ");
    sb.append("(id, code, name, description, value) "); 
    sb.append("VALUES(nextval('sq_product'), ?, ?, ?, ?)");
    return sb.toString();
  }

  @Override
  public String getQueryExclusion() {
    return "DELETE FROM tb_product WHERE code = ?";
  }

  @Override
  protected String getQueryUpdate() {
    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE tb_product SET ");
    sb.append("name = ?, description = ?, value = ? ");
    sb.append("WHERE code = ?");
    return sb.toString();
  }

  @Override
  protected void setInsertionQueryParameters(PreparedStatement stmInsert, Product entity) throws SQLException {
    stmInsert.setString(1, entity.getCode());
    stmInsert.setString(2, entity.getName());
    stmInsert.setString(3, entity.getDescription());
    stmInsert.setBigDecimal(4, entity.getValue());
  }

  @Override
  protected void setExclusionQueryParameters(PreparedStatement stmDelete, String value) throws SQLException {
    stmDelete.setString(1, value);
  }

  @Override
  protected void setUpdateQueryParameters(PreparedStatement stmUpdate, Product entity) throws SQLException {
    stmUpdate.setString(1, entity.getName());
    stmUpdate.setString(2, entity.getDescription());
    stmUpdate.setBigDecimal(3, entity.getValue());
    stmUpdate.setString(4, entity.getCode());
  }

  @Override
  protected void setSelectQueryParameters(PreparedStatement stmSelect, String value) throws SQLException {
    stmSelect.setString(1, value);
  }

}
