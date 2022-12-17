package dev.pedrolobo.onlineshopping.exceptions;

public class KeyTypeNotFoundedException extends Exception {
  public KeyTypeNotFoundedException(String message) {
    this(message, null);
  }

  public KeyTypeNotFoundedException(String message, Throwable e) {
    super(message, e);
  }
}
