package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepo extends JpaRepository<Drone,String> {

}
