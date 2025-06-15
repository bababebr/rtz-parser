package ru.rtz.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Waypoints", namespace = "http://www.cirm.org/RTZ/1/2")
public class WaypointsDto {

  @XmlElement(name = "defaultWaypoint")
  private WaypointDto defaultWaypoint;

  @XmlElement(name = "waypoint", required = true)
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<WaypointDto> waypoint;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}