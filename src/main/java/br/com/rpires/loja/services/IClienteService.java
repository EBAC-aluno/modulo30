package br.com.rpires.loja.services;

/**
 * 
 */

import br.com.rpires.loja.domain.Cliente;
import br.com.rpires.loja.exceptions.DAOException;
import br.com.rpires.loja.services.generic.IGenericService;

/**
 * @author rodrigo.pires
 *
 */
public interface IClienteService extends IGenericService<Cliente, Long> {

	Cliente buscarPorCPF(Long cpf) throws DAOException;

}
