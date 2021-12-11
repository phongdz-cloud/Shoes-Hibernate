package com.example.projectshoes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;

@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends AbstractModel<UserModel> {

  @Column(name = "username")
  @NaturalId
  private String username;
  @Column(name = "password")
  private String password;
  @Transient
  private String repassword;
  @Column(name = "email")
  private String email;
  @Column(name = "status")
  private String status;
  @Column(name = "avatar")
  @Lob
  private String avatar;
  @Column(name = "role_id")
  private Long roleId;

  @OneToOne(mappedBy = "user")
  private CustomerModel customer;

  @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "user")
  private List<SaledetailModel> saleDetails;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Cascade(value = {CascadeType.PERSIST})
  Set<RoleModel> roles = null;

  @Transient
  private RoleModel role = new RoleModel();

  public Set<RoleModel> getRoles() {
    if (roles == null) {
      roles = new HashSet<RoleModel>();
    }
    return roles;
  }

  public List<SaledetailModel> getSaleDetails() {
    if (this.saleDetails == null) {
      this.saleDetails = new ArrayList<>();
    }
    return saleDetails;
  }

}
