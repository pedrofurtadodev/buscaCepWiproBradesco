package com.wipro.demo.repository;

import com.wipro.demo.model.FreightRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FreightRateRepository extends JpaRepository<FreightRate, Integer> {

    @Query("SELECT f.price FROM FreightRate f WHERE f.state = :uf")
    Double findByState(String uf);

}
