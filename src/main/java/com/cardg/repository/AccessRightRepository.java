package com.cardg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardg.model.AccessRights;

@Repository
public interface AccessRightRepository extends JpaRepository<AccessRights, Long> {

}
