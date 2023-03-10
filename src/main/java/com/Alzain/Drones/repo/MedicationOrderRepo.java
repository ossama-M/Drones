package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationOrderRepo extends JpaRepository<MedicationOrder,Long> {

}
