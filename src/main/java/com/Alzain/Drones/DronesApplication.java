package com.Alzain.Drones;

import com.Alzain.Drones.dto.request.AddDroneRequest;
import com.Alzain.Drones.dto.request.AddMedication;
import com.Alzain.Drones.model.DroneModel;
import com.Alzain.Drones.service.DroneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DronesApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context= SpringApplication.run(DronesApplication.class, args);
	}
	@Bean
	CommandLineRunner loadData(DroneService droneService){
		return args -> {
			droneService.addMedicationToSystem(AddMedication.builder()
					.code("AAF")
					.image("imagebyte")
					.name("kataflam")
					.weight(50).build());
			droneService.addMedicationToSystem(AddMedication.builder()
					.code("SFD")
					.image("imagebyte")
					.name("MegaMox")
					.weight(150).build());
			droneService.addMedicationToSystem(AddMedication.builder()
					.code("YOL")
					.image("Panadol")
					.name("Panadol")
					.weight(100).build());
			droneService.addMedicationToSystem(AddMedication.builder()
					.code("TTE")
					.image("TeleF")
					.name("teleFast15")
					.weight(10).build());
			droneService.addMedicationToSystem(AddMedication.builder()
					.code("REF")
					.image("Asboded")
					.name("asbosed")
					.weight(770).build());

			droneService.registerDroneToSystem(AddDroneRequest.builder()
					.droneModel(DroneModel.Middleweight)
					.battery(BigDecimal.valueOf(0.9))
					.speedKMH(400)
					.weightLimit(400)
					.build());

			droneService.registerDroneToSystem(AddDroneRequest.builder()
					.droneModel(DroneModel.Heavyweight)
					.battery(BigDecimal.valueOf(0.100))
					.speedKMH(350)
					.weightLimit(450)
					.build());

			droneService.registerDroneToSystem(AddDroneRequest.builder()
					.droneModel(DroneModel.Lightweight)
					.battery(BigDecimal.valueOf(.10))
					.speedKMH(450)
					.weightLimit(300)
					.build());

			droneService.registerDroneToSystem(AddDroneRequest.builder()
					.droneModel(DroneModel.Cruiserweight)
					.battery(BigDecimal.valueOf(0.75))
					.speedKMH(300)
					.weightLimit(400)
					.build());

			droneService.registerDroneToSystem(AddDroneRequest.builder()
					.droneModel(DroneModel.Heavyweight)
					.battery(BigDecimal.valueOf(0.25))
					.speedKMH(300)
					.weightLimit(400)
					.build());


		};
	}

}
