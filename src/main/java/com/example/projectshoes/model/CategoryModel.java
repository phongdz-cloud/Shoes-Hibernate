package com.example.projectshoes.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel extends AbstractModel<CategoryModel> {

  @Column(name = "code")
  private String code;

  @OneToMany(mappedBy = "category",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<ProductModel> products = new ArrayList<>();


}
