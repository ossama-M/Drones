package com.Alzain.Drones.repo;

import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.model.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface DroneRepo extends JpaRepository<Drone,String> {

    @Query(value = "SELECT serial_no from drone d where d.state =:state and d.battery >=0.25 and d.weight_limit >=:weight  order by d.battery desc  LIMIT  1", nativeQuery = true)
    String findSerialByState(@Param("state")String state, @Param("weight")double weight);

    @Query(value = "SELECT * from drone d where d.serial_no =:serial", nativeQuery = true)
    Drone findDroneBySerial(@Param("serial") String serial);

    @Modifying(clearAutomatically = true)
    @Query(value = "update drone d  set d.order_id =:orderId and d.state =:state where d.serial_no =:serial", nativeQuery = true)
    void updateStateAndOrderNumber(@Param("orderId") long orderNumber,@Param("state") DroneState state  , @Param("serial")String serial);
    @Query(value = "SELECT d.order_number from drone d where d.serial_no =:serial", nativeQuery = true)
    String findOrderNumberBySerial(@Param("serial") String serial);
}
