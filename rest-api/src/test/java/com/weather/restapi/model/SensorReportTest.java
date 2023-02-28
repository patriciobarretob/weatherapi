package com.weather.restapi.model;

import static com.weather.restapi.model.SensorReport.getAns;
import static com.weather.restapi.model.SensorReport.getDayToSubtract;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SensorReportTest {

  @Test
  void getAnsTest() {

    List<Double> values = new ArrayList<>();
    values.add(100.0);
    values.add(98.0);
    values.add(46.8);
    values.add(67.8);
    values.add(96.2);
    values.add(87.2);
    values.add(72.0);
    values.add(105.2);

    assertEquals(getAns("avg", values), 84.15);
    assertEquals(getAns("min", values), 46.8);
    assertEquals(getAns("max", values), 105.2);
    assertEquals(getAns("sum", values), 673.2);
  }

  @Test
  void getDayToSubtractTest() {

    assertEquals(getDayToSubtract("day"), 1);
    assertEquals(getDayToSubtract("week"), 7);
    assertEquals(getDayToSubtract("month"), 30);
    assertEquals(getDayToSubtract("daay"), -1);
  }
}