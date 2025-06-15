package ru.rtz.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedule", namespace = "http://www.cirm.org/RTZ/1/2")
public class ScheduleDto {

  @XmlElement(name = "manual")
  private ScheduleManualDto manual;

  @XmlElement(name = "calculated")
  private CalculatedDto calculated;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "id", required = true)
  private Long id;

  @XmlAttribute(name = "name")
  private String name;
}