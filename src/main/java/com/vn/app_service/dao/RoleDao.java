package com.vn.app_service.dao;

import com.vn.app_service.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, String> {
}
