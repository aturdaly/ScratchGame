package org.homeassignment.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum When {
    @JsonProperty("same_symbols")
    SAME_SYMBOLS,
    @JsonProperty("linear_symbols")
    LINEAR_SYMBOLS
}
