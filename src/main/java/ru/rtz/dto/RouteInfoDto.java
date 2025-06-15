package ru.rtz.dto;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RouteInfo", namespace = "http://www.cirm.org/RTZ/1/2")
public class RouteInfoDto {

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "routeName", required = true)
  private String routeName;

  @XmlAttribute(name = "routeAuthor")
  private String routeAuthor;

  @XmlAttribute(name = "routeStatus")
  private String routeStatus;

  @XmlAttribute(name = "validityPeriodStart")
  private LocalDateTime validityPeriodStart; // Using String for ISO 8601 dateTime

  @XmlAttribute(name = "validityPeriodStop")
  private LocalDateTime validityPeriodStop; // Using String for ISO 8601 dateTime

  @XmlAttribute(name = "vesselName")
  private String vesselName;

  @XmlAttribute(name = "vesselMMSI")
  private Long vesselMMSI;

  @XmlAttribute(name = "vesselIMO")
  private Long vesselIMO;

  @XmlAttribute(name = "vesselVoyage")
  private String vesselVoyage;

  @XmlAttribute(name = "vesselDisplacement")
  private Long vesselDisplacement;

  @XmlAttribute(name = "vesselCargo")
  private Long vesselCargo;

  @XmlAttribute(name = "vesselGM")
  private Double vesselGM;

  @XmlAttribute(name = "optimizationMethod")
  private String optimizationMethod;

  @XmlAttribute(name = "vesselMaxRoll")
  private Integer vesselMaxRoll;

  @XmlAttribute(name = "vesselMaxWave")
  private Double vesselMaxWave;

  @XmlAttribute(name = "vesselMaxWind")
  private Double vesselMaxWind;

  @XmlAttribute(name = "vesselSpeedMax")
  private Double vesselSpeedMax;

  @XmlAttribute(name = "vesselServiceMin")
  private Double vesselServiceMin;

  @XmlAttribute(name = "vesselServiceMax")
  private Double vesselServiceMax;

  @XmlAttribute(name = "routeChangesHistory")
  private String routeChangesHistory;
}