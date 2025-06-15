package ru.rtz.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Manual", namespace = "http://www.cirm.org/RTZ/1/2")
public class ScheduleManualDto {

  @XmlElement(name = "scheduleElement", required = true)
  private List<ScheduleElementDto> scheduleElements;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}