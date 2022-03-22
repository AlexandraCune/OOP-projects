package loadoutput;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"month", "distributorsIds"})

public class MonthlyStats {
    private Integer month;
    private List<Integer> distributorsIds;

    public MonthlyStats(final Integer month,
                        final List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public final Integer getMonth() {
        return month;
    }

    public final void setMonth(final Integer month) {
        this.month = month;
    }

    public final List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public final void setDistributorsIds(final List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
