package ru.rtz.dto;

import javax.xml.bind.annotation.*;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Calculated", namespace = "http://www.cirm.org/RTZ/1/2")
public class CalculatedDto {

  @XmlElement(name = "scheduleElement")
  private List<ScheduleElementDto> scheduleElements;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}