package org.homeassignment.gamelogic;

import org.homeassignment.enums.When;
import org.homeassignment.records.Config;
import org.homeassignment.records.WinCombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinCombinationChecker {

    private static Map<String, List<String>> appliedWinningCombinations;
    private static Map<String, Integer> countMatrixSymbols;

    public static Map<String, List<String>> getAppliedWinningCombinations(String[][] matrix, Config config) {
        appliedWinningCombinations = new HashMap<>();
        countMatrixSymbols = new HashMap<>();

        for (String[] row : matrix) {
            for (String element : row) {
                countMatrixSymbols.put(element, countMatrixSymbols.getOrDefault(element, 0) + 1);
            }
        }

        checkSameSymbolsCombinations(config);
        checkLinearCombinations(matrix, config);

        return appliedWinningCombinations;
    }

    private static void checkSameSymbolsCombinations(Config config) {
        for (Map.Entry<String, Integer> entrySymbol : countMatrixSymbols.entrySet()) {
            List<String> winningCombinations = new ArrayList<>();
            for (Map.Entry<String, WinCombination> entryWC : config.winCombinations().entrySet()) {
                if (entryWC.getValue().when().equals(When.SAME_SYMBOLS) && entryWC.getValue().count().equals(entrySymbol.getValue())) {
                    winningCombinations.add(entryWC.getKey());
                }
            }
            if (!winningCombinations.isEmpty()) {
                appliedWinningCombinations.put(entrySymbol.getKey(), winningCombinations);
            }
        }
    }

    private static void checkLinearCombinations(String[][] matrix, Config config) {
        for (Map.Entry<String, WinCombination> entryWC : config.winCombinations().entrySet()) {
            if (entryWC.getValue().when().equals(When.LINEAR_SYMBOLS)) {
                for (String symbol : countMatrixSymbols.keySet()) {
                    entryWC.getValue().coveredAreas().forEach(coveredArea -> {
                        if (coveredArea.stream()
                                .allMatch(point -> {
                                    String[] coordinates = point.split(":");
                                    return symbol.equals(matrix[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])]);
                                })
                        ) {
                            appliedWinningCombinations.get(symbol).add(entryWC.getKey());
                        }
                    });
                }
            }
        }
    }
}
