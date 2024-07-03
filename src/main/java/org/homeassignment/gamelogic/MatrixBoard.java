package org.homeassignment.gamelogic;

import org.homeassignment.records.Config;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MatrixBoard {

    public static String bonusSymbol;

    public static String[][] generateMatrix(Config config) {
        String[][] matrix = new String[config.rows()][config.columns()];

        config.probabilities().standardSymbols().forEach(symbolsProbability ->
                matrix[symbolsProbability.row()][symbolsProbability.column()] = getSymbolByProbability(symbolsProbability.symbols()));

        int bonusRow = ThreadLocalRandom.current().nextInt(config.rows());
        int bonusColumn = ThreadLocalRandom.current().nextInt(config.columns());
        bonusSymbol = getSymbolByProbability(config.probabilities().bonusSymbols().symbols());
        matrix[bonusRow][bonusColumn] = bonusSymbol;

        return matrix;
    }

    private static String getSymbolByProbability(Map<String, Integer> symbols) {
        int totalSize = symbols.values().stream().mapToInt(Integer::intValue).sum();
        String[] symbolsArray = new String[totalSize];
        int index = 0;

        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            String symbol = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                symbolsArray[index++] = symbol;
            }
        }

        int symbolIndex = ThreadLocalRandom.current().nextInt(totalSize);

        return symbolsArray[symbolIndex];
    }
}
