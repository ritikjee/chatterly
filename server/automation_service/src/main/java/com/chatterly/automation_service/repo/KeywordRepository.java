package com.chatterly.automation_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatterly.automation_service.entity.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

}
