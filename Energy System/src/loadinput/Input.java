package loadinput;

import java.util.List;

public class Input {
    private Integer numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdates> monthlyUpdates;

    public final Integer getNumberOfTurns() {
        return numberOfTurns;
    }

    public final void setNumberOfTurns(final Integer numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public final InitialData getInitialData() {
        return initialData;
    }

    public final void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public final List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public final void setMonthlyUpdates(final List<MonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}


