package com.backend.crud.repository;

import com.backend.crud.entities.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Long> {
    Optional<Parts> findByname(String name);
}
