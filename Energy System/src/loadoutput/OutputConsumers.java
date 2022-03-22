package loadoutput;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public class OutputConsumers {
    private Integer id;
    private Boolean isBankrupt;
    private Integer budget;

    public OutputConsumers(final Integer id,
                           final Boolean isBankrupt,
                           final Integer budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final Boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final Integer getBudget() {
        return budget;
    }

    public final void setBudget(final Integer budget) {
        this.budget = budget;
    }
}
