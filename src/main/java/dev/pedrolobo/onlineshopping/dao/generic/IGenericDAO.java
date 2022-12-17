package dev.pedrolobo.onlineshopping.dao.generic;

import java.io.Serializable;
import java.util.Collection;

import dev.pedrolobo.onlineshopping.dao.Persistence;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;
import dev.pedrolobo.onlineshopping.exceptions.MoreThanOneRegisterException;
import dev.pedrolobo.onlineshopping.exceptions.TableException;

public interface IGenericDAO <T extends Persistence, E extends Serializable> {

  public Boolean register(T entity) throws KeyTypeNotFoundedException, DAOException;

  public void remove(E value) throws DAOException;

  public void modify(T entity) throws KeyTypeNotFoundedException, DAOException;

  public T search(E value) throws MoreThanOneRegisterException, TableException, DAOException;

  public Collection<T> getAll() throws DAOException;

}
