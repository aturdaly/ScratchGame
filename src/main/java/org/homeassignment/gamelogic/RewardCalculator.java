package org.homeassignment.gamelogic;

import org.homeassignment.records.Config;
import org.homeassignment.records.Result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RewardCalculator {

    public static Result calc(BigDecimal betAmount, Map<String, List<String>> appliedWinningCombinations,
                              String[][] matrix, String bonusSymbol, Config config) {

        BigDecimal finalReward = BigDecimal.ZERO;

        if (appliedWinningCombinations == null || appliedWinningCombinations.isEmpty()) {
            return new Result(matrix, finalReward, null, null);
        }

        for (Map.Entry<String, List<String>> entryAppliedWC : appliedWinningCombinations.entrySet()) {
            BigDecimal reward = BigDecimal.ONE;
            for (String appliedWC : entryAppliedWC.getValue()) {
                reward = reward.multiply(config.winCombinations().get(appliedWC).rewardMultiplier());
            }
            reward = reward.multiply(betAmount)
                    .multiply(config.symbols().get(entryAppliedWC.getKey()).rewardMultiplier());

            finalReward = finalReward.add(reward);
        }

        switch (config.symbols().get(bonusSymbol).impact()) {
            case MULTIPLY_REWARD ->
                    finalReward = finalReward.multiply(config.symbols().get(bonusSymbol).rewardMultiplier());
            case EXTRA_BONUS -> finalReward = finalReward.add(config.symbols().get(bonusSymbol).extra());
            case MISS -> {
            }
        }

        return new Result(matrix, finalReward, appliedWinningCombinations, bonusSymbol);
    }
}
