package com.example.demo.repository;

import com.example.demo.entity.PublicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<PublicData, Long> {
}
