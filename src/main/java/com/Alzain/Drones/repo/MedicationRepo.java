package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepo extends JpaRepository<Medication,String> {

}
