package com.example.projectshoes.service.impl;

import com.example.projectshoes.constant.SystemQueries;
import com.example.projectshoes.dao.ICacheDAO;
import com.example.projectshoes.dao.IRoleDAO;
import com.example.projectshoes.model.RoleModel;
import com.example.projectshoes.service.IRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;

public class RoleService implements IRoleService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  IRoleDAO roleDAO;
  @Inject
  ICacheDAO cacheDAO;


  @Override
  public Long save(RoleModel role) {
    Long id = roleDAO.save(role);
    role.setId(id);
    cacheDAO.updateFindBy(SystemQueries.FINDROLEBYID + id, role);
    return id;
  }

  @Override
  public RoleModel findRoleById(Long id) {
    RoleModel role = (RoleModel) cacheDAO.getObject(SystemQueries.FINDROLEBYID + id);
    if (role == null) {
      role = roleDAO.findRoleById(id);
      cacheDAO.setObject(SystemQueries.FINDROLEBYID + id, role);
    }
    return role;
  }
}
