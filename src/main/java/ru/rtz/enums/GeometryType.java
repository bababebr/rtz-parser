package ru.rtz.enums;

import jakarta.xml.bind.annotation.XmlEnumValue;
import lombok.Getter;

@Getter
public enum GeometryType {
  @XmlEnumValue("LOXODROME") LOXODROME("LOXODROME"),
  @XmlEnumValue("ORTHODROME") ORTHODROME("ORTHODROME");

  private final String value;

  GeometryType(String value) {
    this.value = value;
  }
}