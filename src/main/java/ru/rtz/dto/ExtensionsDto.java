package ru.rtz.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import jakarta.xml.bind.annotation.*;
import java.util.List;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extensions", namespace = "http://www.cirm.org/RTZ/1/2")
public class ExtensionsDto {

  @XmlElement(name = "extension")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<ExtensionDto> extensions;
}