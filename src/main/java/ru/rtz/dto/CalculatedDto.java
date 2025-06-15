package ru.rtz.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.xml.bind.annotation.*;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Calculated", namespace = "http://www.cirm.org/RTZ/1/2")
public class CalculatedDto {

  @XmlElement(name = "scheduleElement")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<ScheduleElementDto> scheduleElements;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}