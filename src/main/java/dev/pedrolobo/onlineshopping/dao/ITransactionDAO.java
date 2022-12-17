package dev.pedrolobo.onlineshopping.dao;

import dev.pedrolobo.onlineshopping.dao.generic.IGenericDAO;
import dev.pedrolobo.onlineshopping.domain.Transaction;
import dev.pedrolobo.onlineshopping.exceptions.DAOException;
import dev.pedrolobo.onlineshopping.exceptions.KeyTypeNotFoundedException;

public interface ITransactionDAO extends IGenericDAO<Transaction, String> {

	public void finalizeTransaction(Transaction transaction) throws KeyTypeNotFoundedException, DAOException;
	
	public void cancelTransaction(Transaction venda) throws KeyTypeNotFoundedException, DAOException;
}
