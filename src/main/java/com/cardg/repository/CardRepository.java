package com.cardg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cardg.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
//	@Query(value = "SELECT r.role_name FROM spsp_user u,spsp_role r where r.user_id=u.user_id and u.cuid = :cuid", nativeQuery = true)
//	public String findByCuidAndPassword(@Param("cuid") String cuid);
	
	@Query(value = "SELECT r.role_name FROM spsp_user u, spsp_role r, spsp_user_roles ur where r.role_id=ur.role_id and ur.user_id=u.user_id  and u.cuid = :cuid", nativeQuery = true)
	public List<String> getUserRolesByCuid(@Param("cuid") String cuid);
	
//	@Query(value = "SELECT * from spsp_user where cuid = :cuid", nativeQuery = true)
//	public User findUserByCuid(@Param("cuid") String cuid);
	

	@Query(value = "SELECT full_Name FROM spsp_user where cuid = :cuid", nativeQuery = true)
	public String findFullNamebyCuid(@Param("cuid") String cuid);
}
