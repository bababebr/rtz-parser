package ru.rtz.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import ru.rtz.enums.GeometryType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Leg", namespace = "http://www.cirm.org/RTZ/1/2")
public class LegDto {

  @XmlElement(name = "extensions")
  private ExtensionsDto extensions;

  @XmlAttribute(name = "starboardXTD")
  private Double starboardXTD;

  @XmlAttribute(name = "portsideXTD")
  private Double portsideXTD;

  @XmlAttribute(name = "safetyContour")
  private Double safetyContour;

  @XmlAttribute(name = "safetyDepth")
  private Double safetyDepth;

  @XmlAttribute(name = "geometryType")
  private GeometryType geometryType;

  @XmlAttribute(name = "speedMin")
  private Double speedMin;

  @XmlAttribute(name = "speedMax")
  private Double speedMax;

  @XmlAttribute(name = "draughtForward")
  private Double draughtForward;

  @XmlAttribute(name = "draughtAft")
  private Double draughtAft;

  @XmlAttribute(name = "staticUKC")
  private Double staticUKC;

  @XmlAttribute(name = "dynamicUKC")
  private Double dynamicUKC;

  @XmlAttribute(name = "masthead")
  private Double masthead;

  @XmlAttribute(name = "legReport")
  private String legReport;

  @XmlAttribute(name = "legInfo")
  private String legInfo;

  @XmlAttribute(name = "legNote1")
  private String legNote1;

  @XmlAttribute(name = "legNote2")
  private String legNote2;
}