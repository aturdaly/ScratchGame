package org.homeassignment.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.homeassignment.enums.Impact;
import org.homeassignment.enums.Type;

import java.math.BigDecimal;

public record Symbol(@JsonProperty("reward_multiplier")
                     BigDecimal rewardMultiplier,
                     BigDecimal extra,
                     Type type,
                     Impact impact) {
}
