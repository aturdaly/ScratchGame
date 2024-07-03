package org.homeassignment.records;

import java.util.Map;

public record SymbolsProbability(Integer column,
                                 Integer row,
                                 Map<String, Integer> symbols) {
}
