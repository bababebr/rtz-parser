package ru.rtz.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Waypoints", namespace = "http://www.cirm.org/RTZ/1/2")
public class WaypointsDto {

  @XmlElement(name = "defaultWaypoint")
  private WaypointDto defaultWaypoint;

  @XmlElement(name = "waypoint", required = true)
  private List<WaypointDto> waypoints;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}