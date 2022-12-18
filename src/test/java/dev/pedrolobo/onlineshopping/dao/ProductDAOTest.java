package dev.pedrolobo.onlineshopping.dao;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.pedrolobo.onlineshopping.domain.Product;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;
import dev.pedrolobo.onlineshopping.exceptions.MoreThanOneRegisterException;
import dev.pedrolobo.onlineshopping.exceptions.TableException;

public class ProductDAOTest {

  private IProduct productDAO;

  public ProductDAOTest() {
    productDAO = new ProductDAO();
  }

  @Test
  public void costumerRegister()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Product product = new Product();
    product.setName("some product");
    product.setCode("AX232");
    product.setDescription("amazing product");
    product.setValue(BigDecimal.valueOf(21.72));

    productDAO.register(product);
    Product productToSearch = productDAO.search(product.getCode());
    Assertions.assertNotNull(productToSearch);
    productDAO.remove(product.getCode());
  }

  @Test
  public void RemoveCostumer()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Product product = new Product();
    product.setName("some product");
    product.setCode("AX232");
    product.setDescription("amazing product");
    product.setValue(BigDecimal.valueOf(21.72));

    productDAO.register(product);

    productDAO.remove(product.getCode());

    Assertions.assertNull(productDAO.search(product.getCode()));

  }

  @Test
  public void UpdateCostumer()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Product product = new Product();
    product.setName("some product");
    product.setCode("AX232");
    product.setDescription("amazing product");
    product.setValue(BigDecimal.valueOf(21.72));

    productDAO.register(product);

    Product productToSearch = productDAO.search(product.getCode());

    productToSearch.setName("new product");
    productDAO.modify(productToSearch);

    productToSearch = productDAO.search(product.getCode());
    Assertions.assertTrue(productToSearch.getName().equals("new product"));
    productDAO.remove(product.getCode());

  }

  @Test
  public void getAllCostumer() throws KeyTypeNotFoundedException, DAOException {

    Product product = new Product();
    product.setName("some product");
    product.setCode("AX1");
    product.setDescription("amazing product");
    product.setValue(BigDecimal.valueOf(21.72));

    productDAO.register(product);

    product.setCode("AX2");
    productDAO.register(product);

    Collection<Product> productList = productDAO.getAll();
		Assertions.assertTrue(productList.size() == 2);

    productDAO.remove("AX1");
    productDAO.remove("AX2");
  }

}
