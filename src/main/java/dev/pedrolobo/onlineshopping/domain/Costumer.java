package dev.pedrolobo.onlineshopping.domain;


import anotation.ColumnTable;
import anotation.KeyType;
import anotation.Table;
import dev.pedrolobo.onlineshopping.dao.Persistence;

@Table("tb_costumer")
public class Costumer implements Persistence {

  @ColumnTable(dbName = "id", setJavaName = "setId")
  private Long id;

  @ColumnTable(dbName = "name", setJavaName = "setName")
  private String name;

  @KeyType("getCpf")
  @ColumnTable(dbName = "cpf", setJavaName = "setCpf")
  private Long cpf;

  @ColumnTable(dbName = "telephone_number", setJavaName = "setTelephoneNumber")
  private Long telephoneNumber;

  @ColumnTable(dbName = "address", setJavaName = "setAddress")
  private String address;

  @ColumnTable(dbName = "city", setJavaName = "setCity")
  private String city;

  @ColumnTable(dbName = "state", setJavaName = "setState")
  private String state;

  @ColumnTable(dbName = "house_number", setJavaName = "setHouseNumber")
  private Long houseNumber;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCpf() {
    return cpf;
  }

  public void setCpf(Long cpf) {
    this.cpf = cpf;
  }

  public Long getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(Long telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Long getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(Long houseNumber) {
    this.houseNumber = houseNumber;
  }

}
