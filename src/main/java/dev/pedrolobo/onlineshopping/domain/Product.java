package dev.pedrolobo.onlineshopping.domain;

import anotation.ColumnTable;
import anotation.KeyType;
import anotation.Table;
import dev.pedrolobo.onlineshopping.dao.Persistence;
import java.math.BigDecimal;

@Table("tb_product")
public class Product implements Persistence {

  @ColumnTable(dbName = "id", setJavaName = "setId")
  private Long id;

  @KeyType("getCode")
  @ColumnTable(dbName = "code", setJavaName = "setCode")
  private String code;

  @ColumnTable(dbName = "name", setJavaName = "setName")
  private String name;

  @ColumnTable(dbName = "description", setJavaName = "setDescription")
  private String description;

  @ColumnTable(dbName = "value", setJavaName = "setValue")
  private BigDecimal value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

}
