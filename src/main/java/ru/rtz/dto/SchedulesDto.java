package ru.rtz.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.xml.bind.annotation.*;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedules", namespace = "http://www.cirm.org/RTZ/1/2")
public class SchedulesDto {

  @XmlElement(name = "schedule")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<ScheduleDto> schedules;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}
