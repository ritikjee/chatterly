package com.chatterly.automation_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatterly.automation_service.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

}
