package org.homeassignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homeassignment.gamelogic.MatrixBoard;
import org.homeassignment.gamelogic.RewardCalculator;
import org.homeassignment.gamelogic.WinCombinationChecker;
import org.homeassignment.records.Config;
import org.homeassignment.records.Result;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ScratchGameApp {

    private static final String CONFIG_PATH_KEY = "--config";
    private static final String BETTING_AMOUNT_KEY = "--betting-amount";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> params = Map.of(
                    args[0], args[1],
                    args[2], args[3]
            );

            Config config = mapper.readValue(new File(params.get(CONFIG_PATH_KEY)), Config.class);
            BigDecimal bettingAmount = new BigDecimal(params.get(BETTING_AMOUNT_KEY));

            String[][] matrix = MatrixBoard.generateMatrix(config);
            Map<String, List<String>> appliedWinningCombinations = WinCombinationChecker.getAppliedWinningCombinations(matrix, config);
            Result result = RewardCalculator.calc(bettingAmount, appliedWinningCombinations, matrix, MatrixBoard.bonusSymbol, config);

            mapper.writeValue(new File("output.json"), result);
        } catch (Exception e) {
            mapper.writeValue(new File("output.json"), "Please use the following arguments: --config and --betting-amount");
        }
    }
}
