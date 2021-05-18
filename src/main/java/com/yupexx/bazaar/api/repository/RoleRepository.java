package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yupexx.bazaar.api.model.RoleModel;


public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

}
