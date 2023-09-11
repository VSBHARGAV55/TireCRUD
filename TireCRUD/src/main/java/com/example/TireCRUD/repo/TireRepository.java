package com.example.TireCRUD.repo;

import com.example.TireCRUD.model.Tire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TireRepository extends JpaRepository<Tire, Long> {

}
