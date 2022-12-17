package dev.pedrolobo.onlineshopping.dao.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import dev.pedrolobo.onlineshopping.domain.Costumer;

public class CostumerFactory {
  public static Costumer convert(ResultSet resultSet) throws SQLException {
    Costumer costumer = new Costumer();
    costumer.setId(resultSet.getLong("id_costumer"));
    costumer.setName(resultSet.getString(("name")));
    costumer.setCpf(resultSet.getLong(("cpf")));
    costumer.setTelephoneNumber(resultSet.getLong(("telephone")));
    costumer.setAddress(resultSet.getString(("address")));
    costumer.setHouseNumber(resultSet.getLong(("house_number")));
    costumer.setCity(resultSet.getString(("city")));
    costumer.setState(resultSet.getString(("state")));
    return costumer;
  }

}
