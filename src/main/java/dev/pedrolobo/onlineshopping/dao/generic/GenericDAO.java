package dev.pedrolobo.onlineshopping.dao.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import anotation.ColumnTable;
import anotation.KeyType;
import anotation.Table;
import dev.pedrolobo.onlineshopping.dao.Persistence;
import dev.pedrolobo.onlineshopping.dao.generic.jdbc.ConnectionFactory;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.ElementTypeNotFoundedException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;
import dev.pedrolobo.onlineshopping.exceptions.MoreThanOneRegisterException;
import dev.pedrolobo.onlineshopping.exceptions.TableException;

public abstract class GenericDAO<T extends Persistence, E extends Serializable> implements IGenericDAO<T, E> {

  public abstract Class<T> getClassType();

  public abstract void updateData(T entity, T entityRegistered);

  protected abstract String getQueryInsertion();

  public abstract String getQueryExclusion();

  protected abstract String getQueryUpdate();

  protected abstract void setInsertionQueryParameters(PreparedStatement stmInsert, T entity) throws SQLException;

  protected abstract void setExclusionQueryParameters(PreparedStatement stmDelete, E value) throws SQLException;

  protected abstract void setUpdateQueryParameters(PreparedStatement stmUpdate, T entity) throws SQLException;

  protected abstract void setSelectQueryParameters(PreparedStatement stmSelect, E value) throws SQLException;

  public GenericDAO() {

  }

  @SuppressWarnings("unchecked")
  public E getKey(T entity) throws KeyTypeNotFoundedException {
    Field[] fields = entity.getClass().getDeclaredFields();
    E returnValue = null;
    for (Field field : fields) {
      if (field.isAnnotationPresent(KeyType.class)) {
        KeyType keyType = field.getAnnotation(KeyType.class);
        String methodName = keyType.value();
        try {
          Method method = entity.getClass().getMethod(methodName);
          returnValue = (E) method.invoke(entity);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
          throw new KeyTypeNotFoundedException(
              "error: Main object key of the object " + entity.getClass() + " not found", e);
        }
      }
    }
    if (returnValue == null) {
      String message = "error: Main object key of the object " + entity.getClass() + " not found";
      throw new KeyTypeNotFoundedException(message);
    }
    return returnValue;
  }

  protected Connection getConnection() throws DAOException {
    try {
      return ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new DAOException("error: connection can't be stablished", e);
    }
  }

  protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
    try {
      if (rs != null && !rs.isClosed()) {
        rs.close();
      }
      if (stm != null && !stm.isClosed()) {
        stm.close();
      }
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  @Override
  public Boolean register(T entity) throws KeyTypeNotFoundedException, DAOException {
    Connection connection = null;
    PreparedStatement stm = null;
    try {
      connection = getConnection();
      stm = connection.prepareStatement(getQueryInsertion(), Statement.RETURN_GENERATED_KEYS);
      setInsertionQueryParameters(stm, entity);
      int rowsAffected = stm.executeUpdate();

      if (rowsAffected > 0) {
        try (ResultSet rs = stm.getGeneratedKeys()) {
          if (rs.next()) {
            Persistence per = (Persistence) entity;
            per.setId(rs.getLong(1));
          }
        }
        return true;
      }

    } catch (SQLException e) {
      throw new DAOException("Object could't be registered.", e);
    } finally {
      closeConnection(connection, stm, null);
    }
    return false;
  }

  @Override
  public void remove(E value) throws DAOException {
    Connection connection = getConnection();
    PreparedStatement stm = null;
    try {
      stm = connection.prepareStatement(getQueryExclusion());
      setExclusionQueryParameters(stm, value);
			stm.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("error: object can't be removed.", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }

  @Override
  public void modify(T entity) throws KeyTypeNotFoundedException, DAOException {
    Connection connection = getConnection();
    PreparedStatement stm = null;
    try {
      stm = connection.prepareStatement(getQueryUpdate());
      setUpdateQueryParameters(stm, entity);
			stm.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("error: Object can't be modified.", e);
    } finally {
      closeConnection(connection, stm, null);
    }
  }

  private String getTableName() throws TableException {
    if (getClassType().isAnnotationPresent(Table.class)) {
      Table table = getClassType().getAnnotation(Table.class);
      return table.value();
    } else {
      throw new TableException("TABELA NO TIPO " + getClassType().getName() + " NÃO FOI ENCONTRADA");
    }
  }

  @SuppressWarnings("rawtypes")
  public String getKeyFieldName(Class clazz) throws KeyTypeNotFoundedException {
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(KeyType.class)
          && field.isAnnotationPresent(ColumnTable.class)) {
        return field.getAnnotation(ColumnTable.class).dbName();
      }
    }

    return null;
  }

  private Long validatesMoreThanOneRegister(E valor)
      throws MoreThanOneRegisterException, TableException, KeyTypeNotFoundedException, DAOException {
    Connection connection = getConnection();
    PreparedStatement stm = null;
    ResultSet rs = null;
    Long count = null;
    try {
      stm = connection.prepareStatement(
          "SELECT count(*) FROM " + getTableName() + " WHERE " + getKeyFieldName(getClassType()) + " = ?");
      setSelectQueryParameters(stm, valor);
      rs = stm.executeQuery();
      if (rs.next()) {
        count = rs.getLong(1);
        if (count > 1) {
          throw new MoreThanOneRegisterException("More than one record was found. " + getTableName());
        }
      }

      return count;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection, stm, rs);
    }
    return count;
  }

  private void setValueByType(T entity, Method method, Class<?> classField, ResultSet rs, String fieldName)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException,
      ElementTypeNotFoundedException {

    if (classField.equals(Integer.class)) {
      Integer val = rs.getInt(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Long.class)) {
      Long val = rs.getLong(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Double.class)) {
      Double val = rs.getDouble(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Short.class)) {
      Short val = rs.getShort(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(BigDecimal.class)) {
      BigDecimal val = rs.getBigDecimal(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(String.class)) {
      String val = rs.getString(fieldName);
      method.invoke(entity, val);
    } else {
      throw new ElementTypeNotFoundedException("TIPO DE CLASSE NÃO CONHECIDO: " + classField);
    }

  }

  @Override
  public T search(E value) throws MoreThanOneRegisterException, TableException, DAOException {
    try {
      validatesMoreThanOneRegister(value);
      Connection connection = getConnection();
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM " + getTableName() + " WHERE " + getKeyFieldName(getClassType()) + " = ?");
      setSelectQueryParameters(stm, value);
      ResultSet rs = stm.executeQuery();
      if (rs.next()) {
        T entity = getClassType().getConstructor().newInstance();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
          if (field.isAnnotationPresent(ColumnTable.class)) {
            ColumnTable column = field.getAnnotation(ColumnTable.class);
            String dbName = column.dbName();
            String javaSetName = column.setJavaName();
            Class<?> classField = field.getType();
            try {
              Method method = entity.getClass().getMethod(javaSetName, classField);
              setValueByType(entity, method, classField, rs, dbName);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
              throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
            } catch (ElementTypeNotFoundedException e) {
              throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
            }
          }
        }
        return entity;
      }

    } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException | KeyTypeNotFoundedException e) {
      throw new DAOException("ERRO CONSULTANDO OBJETO ", e);
    }
    return null;
  }

  @Override
  public Collection<T> getAll() throws DAOException {
    List<T> list = new ArrayList<>();
    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {

      connection = getConnection();
      stm = connection.prepareStatement("SELECT * FROM " + getTableName());
      rs = stm.executeQuery();
      while (rs.next()) {
        T entity = getClassType().getConstructor().newInstance();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
          if (field.isAnnotationPresent(ColumnTable.class)) {
            ColumnTable coluna = field.getAnnotation(ColumnTable.class);
            String dbName = coluna.dbName();
            String javaSetName = coluna.setJavaName();
            Class<?> classField = field.getType();
            try {
              Method method = entity.getClass().getMethod(javaSetName, classField);
              setValueByType(entity, method, classField, rs, dbName);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
              throw new DAOException("ERRO LISTANDO OBJETOS ", e);
            } catch (ElementTypeNotFoundedException e) {
              throw new DAOException("ERRO LISTANDO OBJETOS ", e);
            }
          }
        }
        list.add(entity);
      }

    } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException | TableException e) {
      throw new DAOException("ERRO LISTANDO OBJETOS ", e);
    } finally {
      closeConnection(connection, stm, rs);
    }
    return list;
  }

}
