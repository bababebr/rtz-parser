package ru.rtz.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Manual", namespace = "http://www.cirm.org/RTZ/1/2")
public class ScheduleManualDto {

  @XmlElement(name = "scheduleElement", required = true)
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<ScheduleElementDto> scheduleElements;

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;
}