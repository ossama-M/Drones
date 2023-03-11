package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DroneRepo extends JpaRepository<Drone,String> {

    @Query(value = "SELECT serial_no from drone d where d.state =:state and d.battery >=0.25 and d.weight_limit >=:weight  order by d.battery desc  LIMIT  1", nativeQuery = true)
    String findSerialByState(@Param("state")String state, @Param("weight")double weight);
    @Query(value = "SELECT * from drone d where d.state = IDLE and d.battery >=0.25 ", nativeQuery = true)
    List<Drone> findAllByStateAndAndBattery();
    @Query(value = "SELECT * from drone d where d.serial_no =:serial", nativeQuery = true)
    Drone findDroneBySerial(@Param("serial") String serial);

    @Query(value = "SELECT d.order_Id from drone d where d.serial_no =:serial", nativeQuery = true)
    String findOrderNumberBySerial(@Param("serial") String serial);

}
