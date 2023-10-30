package com.example.inventory.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadRepository<T,U> extends JpaRepository<T,U> {

}
