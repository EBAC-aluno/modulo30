package dev.pedrolobo.onlineshopping.exceptions;

public class DAOException extends Exception {
  public DAOException(String message, Exception exception) {
    super(message, exception);
  }
}
