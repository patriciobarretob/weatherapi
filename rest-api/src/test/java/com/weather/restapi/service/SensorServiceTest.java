package com.weather.restapi.service;

import com.weather.restapi.model.Sensor;
import com.weather.restapi.model.SensorReading;
import com.weather.restapi.model.SensorReport;
import com.weather.restapi.repository.SensorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensorServiceTest {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorService sensorService;

    private long sensorID = 100L;

    /*@BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }*/

    @Test
    void saveSensorReading() {
        SensorReading sensorReading = new SensorReading();

        sensorReading.setSensorID(sensorID);
        sensorReading.setDateTime("2023-02-21 15:42:00");
        sensorReading.setTemperature(100.0);
        sensorReading.setHumidity(100.0);
        sensorReading.setWindSpeed(10.0);
        sensorReading.setRainFall(15.0);
        sensorReading.setSnowFall(45.2);
        String msg = sensorService.saveSensorReading(sensorReading);

        assertEquals("Success!", msg);

        Optional<Sensor> byId = sensorRepository.findById(sensorID);
        assertTrue(byId.isPresent());

        Sensor sensor = byId.get();
        List<SensorReading> sensorReadings = sensor.getSensorReadings();

        boolean found = false;

        for (var s : sensorReadings) {
            if (s.getDateTime().equalsIgnoreCase("2023-02-21 15:42:00") && s.getTemperature() == 100.0) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void getSensors() {
        List<SensorReport> sensors = sensorService.getSensors("100", "all", "avg");
        assertFalse(sensors.isEmpty());
        assertEquals(sensors.get(0).getSensorID(), 100L);
        assertEquals(sensors.get(0).getRange(), "all");
        assertEquals(sensors.get(0).getMode(), "avg");
        assertEquals(sensors.get(0).getTemperature(), 100.0);
        assertEquals(sensors.get(0).getHumidity(), 100.0);
        assertEquals(sensors.get(0).getWindSpeed(), 10.0);
        assertEquals(sensors.get(0).getRainFall(), 15.0);
        assertEquals(sensors.get(0).getSnowFall(), 45.2);
    }
}