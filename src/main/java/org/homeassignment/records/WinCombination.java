package org.homeassignment.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.homeassignment.enums.Group;
import org.homeassignment.enums.When;

import java.math.BigDecimal;
import java.util.List;

public record WinCombination(@JsonProperty("reward_multiplier")
                             BigDecimal rewardMultiplier,
                             When when,
                             Integer count,
                             Group group,
                             @JsonProperty("covered_areas")
                             List<List<String>> coveredAreas) {
}
