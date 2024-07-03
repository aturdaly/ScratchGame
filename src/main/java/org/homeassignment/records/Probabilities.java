package org.homeassignment.records;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Probabilities(@JsonProperty("standard_symbols")
                            List<SymbolsProbability> standardSymbols,
                            @JsonProperty("bonus_symbols")
                            SymbolsProbability bonusSymbols) {
}
