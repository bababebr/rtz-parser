package ru.rtz.dto;

import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GM_Point", namespace = "http://www.cirm.org/RTZ/1/2")
public class GMPointDto {

  @XmlAttribute(name = "lat", required = true)
  private Double latitude;

  @XmlAttribute(name = "lon", required = true)
  private Double longitude;
}