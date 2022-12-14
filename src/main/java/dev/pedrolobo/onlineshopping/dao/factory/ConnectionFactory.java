package dev.pedrolobo.onlineshopping.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {
  public static Connection connection;

  private static Connection initConnection() {
    try {
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_online_shopping", "online_shopping_adm", "magic");
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }
  public static Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = initConnection();
      return connection;
    } else if (connection.isClosed()) {
      connection = initConnection();
      return connection;
    } else {
      return connection;
    }
  }
}
