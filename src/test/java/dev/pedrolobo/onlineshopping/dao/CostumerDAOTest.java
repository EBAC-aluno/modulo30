package dev.pedrolobo.onlineshopping.dao;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.pedrolobo.onlineshopping.domain.Costumer;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;
import dev.pedrolobo.onlineshopping.exceptions.MoreThanOneRegisterException;
import dev.pedrolobo.onlineshopping.exceptions.TableException;

public class CostumerDAOTest {

  private ICostumerDAO costumerDAO;

  public CostumerDAOTest() {
    costumerDAO = new CostumerDAO();
  }

  @Test
  public void costumerRegister()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Costumer costumer = new Costumer();
    costumer.setName("someone");
    costumer.setCpf(123L);
    costumer.setTelephoneNumber(313123L);
    costumer.setCity("someplace");
    costumer.setAddress("somecity");
    costumer.setState("MG");
    costumer.setHouseNumber(4L);

    costumerDAO.register(costumer);
    Costumer costumerToSearch = costumerDAO.search(costumer.getCpf());
    Assertions.assertNotNull(costumerToSearch);
    costumerDAO.remove(costumer.getCpf());
  }

  @Test
  public void RemoveCostumer()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Costumer costumer = new Costumer();
    costumer.setName("someone");
    costumer.setCpf(999L);
    costumer.setTelephoneNumber(313123L);
    costumer.setCity("someplace");
    costumer.setAddress("somecity");
    costumer.setState("MG");
    costumer.setHouseNumber(4L);

    costumerDAO.register(costumer);

    costumerDAO.remove(costumer.getCpf());

    Assertions.assertNull(costumerDAO.search(costumer.getCpf()));

  }

  @Test
  public void UpdateCostumer()
      throws MoreThanOneRegisterException, TableException, DAOException, KeyTypeNotFoundedException {

    Costumer costumer = new Costumer();
    costumer.setName("someone");
    costumer.setCpf(0L);
    costumer.setTelephoneNumber(313123L);
    costumer.setCity("someplace");
    costumer.setAddress("somecity");
    costumer.setState("MG");
    costumer.setHouseNumber(4L);

    costumerDAO.register(costumer);

    Costumer costumerToSearch = costumerDAO.search(costumer.getCpf());

    costumerToSearch.setName("someone else");
    costumerDAO.modify(costumerToSearch);

    costumerToSearch = costumerDAO.search(costumer.getCpf());
    Assertions.assertTrue(costumerToSearch.getName().equals("someone else"));
    costumerDAO.remove(costumer.getCpf());

  }

  @Test
  public void getAllCostumer() throws KeyTypeNotFoundedException, DAOException {
    Costumer costumer = new Costumer();
    costumer.setName("someone");
    costumer.setCpf(0L);
    costumer.setTelephoneNumber(313123L);
    costumer.setCity("someplace");
    costumer.setAddress("somecity");
    costumer.setState("MG");
    costumer.setHouseNumber(4L);

    costumerDAO.register(costumer);

    costumer.setCpf(1L);
    costumerDAO.register(costumer);

    Collection<Costumer> costumersList = costumerDAO.getAll();
		Assertions.assertTrue(costumersList.size() == 2);

    costumerDAO.remove(0L);
    costumerDAO.remove(1L);
  }

}
