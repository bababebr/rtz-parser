package ru.rtz.dto;

import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Waypoint", namespace = "http://www.cirm.org/RTZ/1/2")
public class WaypointDto {

  @XmlElement(name = "position", required = true)
  private GMPointDto position;

  @XmlElement(name = "leg")
  private LegDto leg;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "id", required = true)
  @Positive
  private Long id;

  @XmlAttribute(name = "revision", required = true)
  private Long revision;

  @XmlAttribute(name = "name")
  private String name;

  @XmlAttribute(name = "radius")
  private Double radius;
}