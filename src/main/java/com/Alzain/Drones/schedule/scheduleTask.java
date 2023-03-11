package com.Alzain.Drones.schedule;

import com.Alzain.Drones.model.Drone;
import com.Alzain.Drones.repo.DroneRepo;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class scheduleTask {

    private DroneRepo  droneRepo ;


    @Autowired
    public scheduleTask( DroneRepo  droneRepo) {
        this.droneRepo = droneRepo;
    }



    static final Logger log = LoggerFactory.getLogger(scheduleTask.class);

    @Scheduled(fixedRate = 10000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {

        List<Drone> arrDroneBatteryLeves = droneRepo.findAll();

        DecimalFormat decFormat = new DecimalFormat("#%");
        for(int i=0; i<arrDroneBatteryLeves.size(); i++) {

            log.info("Batery level--: SerialNumber  "+ arrDroneBatteryLeves.get(i).getSerialNumber()+"  Battery Level "+ decFormat.format(arrDroneBatteryLeves.get(i).getBattery()));
        }
        Thread.sleep(5000);

    }
}
