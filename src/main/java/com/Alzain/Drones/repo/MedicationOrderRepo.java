package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationOrderRepo extends JpaRepository<MedicationOrder,Long> {

    @Query(value = "SELECT m.* FROM medication_order m where m.id =:orderNumber ",nativeQuery = true)
    MedicationOrder findById(@Param("orderNumber") String orderNumber);
}
