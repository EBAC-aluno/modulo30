package dev.pedrolobo.onlineshopping.dao.factory.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.pedrolobo.onlineshopping.dao.factory.ConnectionFactory;

class ConnectionFactoryTest {

  @Test
  void getConnectionTest() throws SQLException {
    Connection c = ConnectionFactory.getConnection();
    Assertions.assertNotNull(c);
    c.close();
  }
}
