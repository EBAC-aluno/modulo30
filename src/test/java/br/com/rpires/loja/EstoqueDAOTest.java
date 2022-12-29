package br.com.rpires.loja;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rpires.loja.dao.EstoqueDAO;
import br.com.rpires.loja.dao.ProdutoDAO;
import br.com.rpires.loja.domain.Estoque;
import br.com.rpires.loja.domain.Produto;
import br.com.rpires.loja.domain.ProdutoQuantidade;
import br.com.rpires.loja.exceptions.DAOException;
import br.com.rpires.loja.exceptions.MaisDeUmRegistroException;
import br.com.rpires.loja.exceptions.TableException;
import br.com.rpires.loja.exceptions.TipoChaveNaoEncontradaException;
import br.com.rpires.loja.utils.ConexaoParalela;
import br.com.rpires.loja.utils.ConexaoParalela.Tipo;

public class EstoqueDAOTest {

  private EstoqueDAO estoqueDAO;
  private Estoque estoque;
  private ProdutoQuantidade produtoQuantidade;

  public EstoqueDAOTest() {
    estoqueDAO = new EstoqueDAO();
    estoque = new Estoque();
  }

  @Test
  public void inserir() throws TipoChaveNaoEncontradaException, DAOException {
    boolean isCadastrou = estoqueDAO.cadastrar(estoque); 
    Assert.assertTrue(isCadastrou);
  }

  @Before
  public void init() throws IOException, InterruptedException, ExecutionException, SQLException, MaisDeUmRegistroException, TableException, DAOException {
      ConexaoParalela.adicionarNoBancoDeDados(Tipo.CLIENTE);
      ConexaoParalela.adicionarNoBancoDeDados(Tipo.PRODUTO);
      ConexaoParalela.adicionarNoBancoDeDados(Tipo.VENDA);
      ConexaoParalela.adicionarNoBancoDeDados(Tipo.PRODUTO_QUANTIDADE);
      produtoQuantidade = getProdutoQuantidade();

      estoque.setQuantidade(10);
      estoque.setProdutoQuantidade(produtoQuantidade);
      estoque.setCodigoNotaFiscalFornecedor("NF-1");
  }
  
  @After
  public void finalize() throws SQLException{
      ConexaoParalela.deletaConteudoTodasTabelas();
  }

	private Estoque criarEstoque(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
		Estoque estoque = new Estoque();
		estoque.setQuantidade(10);
    estoque.setProdutoQuantidade(produtoQuantidade);
    estoque.setCodigoNotaFiscalFornecedor("C1");
		return estoque;
	}

  private ProdutoQuantidade getProdutoQuantidade() throws MaisDeUmRegistroException, TableException, DAOException, SQLException{
    ProdutoDAO produtoDAO = new ProdutoDAO();
    Produto produto = produtoDAO.consultar("P1");

    ProdutoQuantidade produtoQuantidade = new ProdutoQuantidade();
    produtoQuantidade.setProduto(produto);
    produtoQuantidade.setQuantidade(10);
    produtoQuantidade.setValorTotal(BigDecimal.valueOf(150.00));
    produtoQuantidade.setId(ConexaoParalela.getIdDaTbProdutoQuantidade());
    return produtoQuantidade;
  }
}
