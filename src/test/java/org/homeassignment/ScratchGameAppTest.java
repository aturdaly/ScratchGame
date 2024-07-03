package org.homeassignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homeassignment.gamelogic.MatrixBoard;
import org.homeassignment.gamelogic.RewardCalculator;
import org.homeassignment.gamelogic.WinCombinationChecker;
import org.homeassignment.records.Config;
import org.homeassignment.records.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScratchGameAppTest {

    private Config config;

    @BeforeEach
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        config = mapper.readValue(new File("config.json"), Config.class);
    }

    @Test
    public void testConfig() {
        assertNotNull(config);
        assertEquals(config.rows(), Integer.parseInt("3"));
        assertEquals(config.columns(), Integer.parseInt("3"));
    }

    @Test
    public void testGeneratedMatrix() {
        assertNotNull(MatrixBoard.generateMatrix(config));
    }

    @Test
    public void testGeneratedMatrixSymbols() {
        String[][] matrix = MatrixBoard.generateMatrix(config);
        for (int i = 0; i < config.rows(); i++) {
            for (int j = 0; j < config.columns(); j++) {
                assertNotNull(matrix[i][j]);
                assertNotNull(config.symbols().get(matrix[i][j]));
            }
        }
    }

    @Test
    public void testAppliedWinningCombinations() {
        String[][] matrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };
        Map<String, List<String>> appliedWinningCombinations = WinCombinationChecker.getAppliedWinningCombinations(matrix, config);

        assertNotNull(appliedWinningCombinations);
        assertEquals(appliedWinningCombinations.size(), 2);
        List<String> wcA = new ArrayList<>(Arrays.asList("same_symbol_5_times", "same_symbols_vertically"));
        assertEquals(appliedWinningCombinations.get("A"), wcA);
        List<String> wcB = new ArrayList<>(Arrays.asList("same_symbol_3_times", "same_symbols_vertically"));
        assertEquals(appliedWinningCombinations.get("B"), wcB);
    }

    @Test
    public void testReward() {
        String[][] matrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };
        Map<String, List<String>> appliedWinningCombinations = WinCombinationChecker.getAppliedWinningCombinations(matrix, config);

        Result result = RewardCalculator.calc(BigDecimal.valueOf(100), appliedWinningCombinations, matrix, "+1000", config);

        BigDecimal reward = BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(5)).multiply(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(2))
                .add(BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(2)))
                .add(BigDecimal.valueOf(1000));

        assertNotNull(result);
        assertEquals(result.reward(), reward);
    }
}
