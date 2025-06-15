package ru.rtz.dto;

import java.time.Duration;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduleElement", namespace = "http://www.cirm.org/RTZ/1/2")
public class ScheduleElementDto {

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "waypointId", required = true)
  private Long waypointId;

  @XmlAttribute(name = "etd")
  private Duration etd; // ISO 8601 dateTime

  @XmlAttribute(name = "etdWindowBefore")
  private Duration etdWindowBefore; // ISO 8601 duration

  @XmlAttribute(name = "etdWindowAfter")
  private Duration etdWindowAfter; // ISO 8601 duration

  @XmlAttribute(name = "eta")
  private Duration eta; // ISO 8601 dateTime

  @XmlAttribute(name = "etaWindowBefore")
  private Duration etaWindowBefore; // ISO 8601 duration

  @XmlAttribute(name = "etaWindowAfter")
  private Duration etaWindowAfter; // ISO 8601 duration

  @XmlAttribute(name = "stay")
  private Duration stay; // ISO 8601 duration
}