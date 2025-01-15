package com.backend.crud.repository;

import com.backend.crud.entities.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Long> {
    Optional<Parts> findByname(String name);
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Parts p SET p.quantity = :quantity WHERE p.id = :id")
    int updateQuantity(@Param("id") Long id, @Param("quantity") int quantity);
}
