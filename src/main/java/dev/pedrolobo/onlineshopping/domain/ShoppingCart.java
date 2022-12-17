package dev.pedrolobo.onlineshopping.domain;

import anotation.ColumnTable;
import anotation.Table;
import java.math.BigDecimal;

@Table("tb_shopping_cart")
public class ShoppingCart {

  @ColumnTable(dbName = "id", setJavaName = "setId")
  private Long id;

  private Product product;

  @ColumnTable(dbName = "amount", setJavaName = "setAmount")
  private Integer amount;

  @ColumnTable(dbName = "total_amount", setJavaName = "setTotalAmount")
  private BigDecimal totalAmount;

  public ShoppingCart() {
    this.amount = 0;
    this.totalAmount = BigDecimal.ZERO;
  }

  public void add(Integer amount) {
    this.amount = +amount;
    BigDecimal tempValue = this.product.getValue().multiply(BigDecimal.valueOf(amount));
    totalAmount = this.totalAmount.add(tempValue);
  }

  public void remove(Integer amount) {
    this.amount = -amount;
    BigDecimal tempValue = this.product.getValue().multiply(BigDecimal.valueOf(amount));
    totalAmount = this.totalAmount.subtract(tempValue);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }
}
