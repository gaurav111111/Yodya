package com.yupexx.bazaar.api.repository;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.UserReportModel;

@Repository
public interface UserReportRepository extends JpaRepository<UserReportModel, Long>{

	UserReportModel findByStatus(boolean b);

}
