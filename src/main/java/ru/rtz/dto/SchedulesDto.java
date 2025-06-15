package ru.rtz.dto;

import javax.xml.bind.annotation.*;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedules", namespace = "http://www.cirm.org/RTZ/1/2")
public class SchedulesDto {

  @XmlElement(name = "schedule")
  private List<ScheduleDto> schedules;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}
