package ru.rtz.enums;

import javax.xml.bind.annotation.XmlEnumValue;

public enum GeometryType {
  @XmlEnumValue("Loxodrome") LOXODROME,
  @XmlEnumValue("Orthodrome") ORTHODROME
}