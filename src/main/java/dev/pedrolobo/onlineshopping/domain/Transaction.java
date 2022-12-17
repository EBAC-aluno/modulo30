package dev.pedrolobo.onlineshopping.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import dev.pedrolobo.onlineshopping.dao.Persistence;

public class Transaction implements Persistence {
  public enum Status {
    STARTED,
    DONE,
    CANCELLED;

    public static Status getByName(String value) {
      for (Status status : Status.values()) {
        if (status.name().equals(value)) {
          return status;
        }
      }
      return null;
    }
  }

  private Long id;

  private String code;

  private Costumer customer;

  private Set<ShoppingCart> products;

  private BigDecimal totalAmount;

  private Instant sellDate;

  private Status status;

  public Transaction() {
    products = new HashSet<>();
  }

  public void addProduct(Product product, Integer amount) {
    validateStatus();
    Optional<ShoppingCart> op =
        products.stream()
            .filter(filter -> filter.getProduct().getCode().equals(product.getCode()))
            .findAny();
    if (op.isPresent()) {
      ShoppingCart productAmount = op.get();
      productAmount.add(amount);
    } else {
      ShoppingCart shoppingCart = new ShoppingCart();
      shoppingCart.setProduct(product);
      shoppingCart.add(amount);
      products.add(shoppingCart);
    }
    recalculateAmountSold();
  }

  private void recalculateAmountSold() {
    BigDecimal totalValue = BigDecimal.ZERO;
    for (ShoppingCart s : this.products) {
      totalValue = totalValue.add(s.getTotalAmount());
    }
  }

  private void validateStatus() {
    if (this.status == Status.DONE) {
      throw new UnsupportedOperationException(
          "error: transaction already finnished, can't be change.");
    }
  }

  public void removeProduct(Product product, Integer amount) {
    validateStatus();
    Optional<ShoppingCart> op =
        products.stream()
            .filter(filter -> filter.getProduct().getCode().equals(product.getCode()))
            .findAny();

    if (op.isPresent()) {
      ShoppingCart shoppingCart = op.get();
      if (shoppingCart.getAmount() > amount) {
        shoppingCart.remove(amount);
        recalculateAmountSold();
      } else {
        products.remove(op.get());
        recalculateAmountSold();
      }
    }
  }

  public void removeAllProducts() {
    validateStatus();
    products.clear();
    totalAmount = BigDecimal.ZERO;
  }

  public Integer getTotalProductsAmount() {
    int result =
        products.stream()
            .reduce(
                0,
                (parcialCountResult, prod) -> parcialCountResult + prod.getAmount(),
                Integer::sum);
    return result;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Costumer getCustomer() {
    return customer;
  }

  public void setCustomer(Costumer customer) {
    this.customer = customer;
  }

  public Set<ShoppingCart> getProducts() {
    return products;
  }

  public void setProducts(Set<ShoppingCart> products) {
    this.products = products;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Instant getSellDate() {
    return sellDate;
  }

  public void setSellDate(Instant sellDate) {
    this.sellDate = sellDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
