package com.example.projectshoes.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractModel<T> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Timestamp createdDate;

  private Timestamp modifiedDate;

  private String createdBy;

  private String modifiedBy;
  @Transient

  private String message;

  @Transient
  private String avatar;

  @Transient
  private Boolean checked;

  @Transient
  private String badge;

  @Transient

  private String alert;
  @Transient

  private long[] ids;
  @Transient

  private List<T> listResult = new ArrayList<>();
  @Transient

  private Integer page;
  @Transient

  private Integer maxPageItem;
  @Transient
  private Integer totalItem;
  @Transient
  private Integer totalPage;


}
