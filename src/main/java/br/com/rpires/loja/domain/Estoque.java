package br.com.rpires.loja.domain;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import anotacao.TipoChave;
import br.com.rpires.loja.dao.Persistente;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente {
	
	@ColunaTabela(dbName = "id", setJavaName = "setId")
	private Long id;

	@TipoChave("getCodigoNotaFiscalFornecedor")
	@ColunaTabela(dbName = "codigo_nota_fiscal_fornecedor", setJavaName = "setCodigoNotaFiscalFornecedor")
	private String codigoNotaFiscalFornecedor;
	
	@ColunaTabela(dbName = "id_produto_quantidade_fk", setJavaName = "setIdProdutoQuantidadeFk")
	private ProdutoQuantidade produtoQuantidade;
	
	@ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
	private Integer quantidade;

//  public Estoque(){
//    produtoQuantidade = new ProdutoQuantidade();
//  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;  
  }

  public String getCodigoNotaFiscalFornecedor() {
    return codigoNotaFiscalFornecedor;
  }

  public void setCodigoNotaFiscalFornecedor(String codigoNotaFiscalFornecedor) {
    this.codigoNotaFiscalFornecedor = codigoNotaFiscalFornecedor;
  }

  public ProdutoQuantidade getProdutoQuantidade() {
    return produtoQuantidade;
  }

  public void setProdutoQuantidade(ProdutoQuantidade produtoQuantidade) {
    this.produtoQuantidade = produtoQuantidade;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
	
}
