package ru.rtz.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extension", namespace = "http://www.cirm.org/RTZ/1/2")
public class ExtensionDto {

  @XmlAnyElement
  private List<Object> any;

  @XmlAttribute(name = "manufacturer", required = true)
  private String manufacturer;

  @XmlAttribute(name = "name", required = true)
  private String name;

  @XmlAttribute(name = "version")
  private String version;

  @XmlAnyAttribute
  private Map<QName, String> otherAttributes = new HashMap<>();
}