package com.example.projectshoes.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Stock")
@Table(name = "stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockModel extends AbstractModel<StockModel> {

  @Column(name = "amount")
  private Integer amount;

  @OneToMany(mappedBy = "stock",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<ProductModel> product = new ArrayList<>();



}
