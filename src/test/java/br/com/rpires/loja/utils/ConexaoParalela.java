package br.com.rpires.loja.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.rpires.loja.dao.generic.jdbc.ConnectionFactory;

//public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E> {
public class ConexaoParalela {
  public enum Tipo {
    CLIENTE(1), PRODUTO(2), VENDA(3), PRODUTO_QUANTIDADE(4);

    private final int valor;

    Tipo(int valorOpcao) {
      valor = valorOpcao;
    }

    public int getValorOpcao() {
      return valor;
    }
  }

  public static void deletaConteudoTodasTabelas() throws SQLException {
    Connection connection = ConnectionFactory.getConnection();

    connection.prepareStatement("DELETE FROM tb_estoque").execute();
    connection.prepareStatement("DELETE FROM tb_produto_quantidade").execute();
    connection.prepareStatement("DELETE FROM tb_produto").execute();
    connection.prepareStatement("DELETE FROM tb_venda").execute();
    connection.prepareStatement("DELETE FROM tb_cliente").execute();
  }

  public static void adicionarNoBancoDeDados(Tipo opcao) throws SQLException {
    Connection connection = ConnectionFactory.getConnection();
    StringBuilder sb = null;
    if (opcao == Tipo.CLIENTE) {
      sb = montaStringCliente("1");
    }

    if (opcao == Tipo.PRODUTO) {
      sb = montaStringProduto("P1");
    }

    if (opcao == Tipo.VENDA) {
      sb = montaStringVenda("V1", "1");
    }

    if (opcao == Tipo.PRODUTO_QUANTIDADE) {
      sb = montaStringProdutoQuantidade("P1", "V1");
    }

    PreparedStatement stm = connection.prepareStatement(sb.toString());
    stm.executeUpdate();
  }

  public static StringBuilder montaStringCliente(String identificador) {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_cliente(id, nome, cpf, tel, endereco, numero, cidade, estado, email) ");
    sb.append("VALUES(");
    sb.append("nextval('sq_cliente'), ");
    sb.append("'alguem', ");
    sb.append(identificador + ", ");
    sb.append("333, ");
    sb.append("'casa de alguem', ");
    sb.append("99, ");
    sb.append("'alguma cidade', ");
    sb.append("'algum estado', ");
    sb.append("'email@email.com'");
    sb.append(")");
    return sb;
  }

  public static StringBuilder montaStringProduto(String identificador) {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_produto(id, codigo, nome, descricao, valor, unidade_de_mensuracao) ");
    sb.append("VALUES(");
    sb.append("nextval('sq_produto'), '");
    sb.append(identificador + "', ");
    sb.append("'algum produto', ");
    sb.append("'descricao do produto', ");
    sb.append("990.00, ");
    sb.append("'Kg'");
    sb.append(")");
    return sb;
  }

  public static StringBuilder montaStringVenda(String identificador, String cpfCliente) {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_venda(id, codigo, id_cliente_fk, valor_total, data_venda, status_venda)");
    sb.append("VALUES(");
    sb.append("nextval('sq_venda'), '");
    sb.append(identificador + "', ");
    sb.append("(SELECT id FROM tb_cliente WHERE cpf = " + cpfCliente + "), ");
    sb.append("9.99, ");
    sb.append("'2012-01-12', ");
    sb.append("'CONCLUIDA'");
    sb.append(")");
    return sb;
  }

  public static StringBuilder montaStringProdutoQuantidade(String codigoProduto, String codigoVenda) {
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO tb_produto_quantidade(id, id_produto_fk, id_venda_fk, quantidade, valor_total) ");
    sb.append("VALUES(");
    sb.append("nextval('sq_produto_quantidade'), ");
    sb.append("(SELECT id FROM tb_produto WHERE codigo = '" + codigoProduto + "'), ");
    sb.append("(SELECT id FROM tb_venda WHERE codigo = '" + codigoVenda + "'), ");
    sb.append("85, ");
    sb.append("534.41");
    sb.append(")");
    return sb;
  }

  public static Long getIdDaTbProdutoQuantidade() throws SQLException {

    Connection connection = ConnectionFactory.getConnection();
    PreparedStatement stm = connection.prepareStatement(
        "SELECT id FROM tb_produto_quantidade WHERE id_venda_fk = (SELECT id FROM tb_venda WHERE codigo = 'V1')");
    ResultSet rs = stm.executeQuery();
    Long id = null;
    while (rs.next()) {
      id = rs.getLong("id");
    }
    return id;
  }
}
