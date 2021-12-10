package com.example.projectshoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Role")
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel extends AbstractModel<RoleModel> {

  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;

}
