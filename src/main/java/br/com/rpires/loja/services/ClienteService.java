package br.com.rpires.loja.services;

/**
 * 
 */

import br.com.rpires.loja.dao.IClienteDAO;
import br.com.rpires.loja.domain.Cliente;
import br.com.rpires.loja.exceptions.DAOException;
import br.com.rpires.loja.exceptions.MaisDeUmRegistroException;
import br.com.rpires.loja.exceptions.TableException;
import br.com.rpires.loja.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.loja.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {
	
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) throws DAOException {
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			e.printStackTrace();
		}
		return null;
	}

}
