package dev.pedrolobo.onlineshopping.exceptions;

public class ElementTypeNotFoundedException extends Exception {
  public ElementTypeNotFoundedException(String message) {
    this(message, null);
  }

  public ElementTypeNotFoundedException(String message, Throwable e) {
    super(message, e);
  }
}
