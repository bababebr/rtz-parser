package ru.rtz.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
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