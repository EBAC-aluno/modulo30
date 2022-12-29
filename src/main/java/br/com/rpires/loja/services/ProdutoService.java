package br.com.rpires.loja.services;

/**
 * 
 */

import br.com.rpires.loja.dao.IProdutoDAO;
import br.com.rpires.loja.domain.Produto;
import br.com.rpires.loja.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
