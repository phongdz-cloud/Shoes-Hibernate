package com.example.projectshoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RoleModel extends AbstractModel<RoleModel> {

  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
