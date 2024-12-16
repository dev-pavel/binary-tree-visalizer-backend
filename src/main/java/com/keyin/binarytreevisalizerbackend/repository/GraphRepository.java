package com.keyin.binarytreevisalizerbackend.repository;

import com.keyin.binarytreevisalizerbackend.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends JpaRepository<Graph, String> {
}