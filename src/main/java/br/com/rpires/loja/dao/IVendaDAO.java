package br.com.rpires.loja.dao;

/**
 * 
 */

import br.com.rpires.loja.dao.generic.IGenericDAO;
import br.com.rpires.loja.domain.Venda;
import br.com.rpires.loja.exceptions.DAOException;
import br.com.rpires.loja.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
