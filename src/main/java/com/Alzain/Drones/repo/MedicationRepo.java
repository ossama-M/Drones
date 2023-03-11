package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepo extends JpaRepository<Medication,String> {
    @Query(value = "SELECT * from medication m where m.order_number =:orderNumber", nativeQuery = true)
    List<Medication> findByOrderNumber(@Param("orderNumber") String orderNumber);
}
