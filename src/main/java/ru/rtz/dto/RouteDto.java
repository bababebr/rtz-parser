package ru.rtz.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@Data
@XmlRootElement(name = "route", namespace = "http://www.cirm.org/RTZ/1/2")
@XmlAccessorType(XmlAccessType.FIELD)
public class RouteDto {

  @XmlElement(name = "routeInfo", required = true)
  private RouteInfoDto routeInfo;

  @XmlElement(name = "waypoints", required = true)
  private WaypointsDto waypoints;

  @XmlElement(name = "schedules")
  private SchedulesDto schedules;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "version", required = true)
  private String version = "1.2";
}