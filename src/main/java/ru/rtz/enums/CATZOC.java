package ru.rtz.enums;

import lombok.Getter;

/**
 * Associate CATZOC type with basic UKC requirements (% of max. static draught)
 */
@Getter
public enum CATZOC {
    A1(.1d),
    A2(.1d),
    B(.15d),
    C(.25d),
    D(.25d),
    U(1);

    CATZOC(double basicUKC) {

    }
}
