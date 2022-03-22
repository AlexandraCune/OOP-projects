package energysystem;

import loadinput.ConsumersInterface;

public class Consumers implements ConsumersInterface {
    private Integer id;
    private Integer initialBudget;
    private Boolean isBankrupt = false;
    private Integer contractLength = 0;
    private Integer monthlyIncome;
    private Integer budget;
    private Integer penalty = 0;
    private Integer bill = 0;
    private Integer oldBill = 0;
    private Distributors distributorPenalty;
    private Distributors currentDistributor;
    private static final Double PERCENT = 1.2;


    public Consumers(final Integer id, final Integer initialBudget,
                     final Integer monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public final Integer getOldBill() {
        return oldBill;
    }

    public final void setOldBill(final Integer oldBill) {
        this.oldBill = oldBill;
    }

    public final Distributors getCurrentDistributor() {
        return currentDistributor;
    }

    public final void setCurrentDistributor(final Distributors currentDistributor) {
        this.currentDistributor = currentDistributor;
    }

    public final Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final Boolean isBankrupt) {
        this.isBankrupt = isBankrupt;
    }

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Integer getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final Distributors getDistributorPenalty() {
        return distributorPenalty;
    }

    public final void setDistributorPenalty(final Distributors distributorPenalty) {
        this.distributorPenalty = distributorPenalty;
    }

    public final Integer getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }


    public final Integer getBill() {
        return bill;
    }


    public final void setBill(final Integer bill) {
        this.bill = bill;
    }

    public final Integer getBudget() {
        return budget;
    }

    public final void setBudget(final Integer budget) {
        this.budget = budget;
    }

    public final Integer getPenalty() {
        return penalty;
    }

    public final void setPenalty(final Integer penalty) {
        this.penalty = penalty;
    }

    public final Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setMonthlyIncome(final Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    /**
     *Calculeaza bugetul lunar al unui consumator, adaugand salariul.
     */
    public final void calculateBuget() {
        budget = monthlyIncome + budget;
    }
    /**
     *Calculeaza penalizarea unui consumator care nu si-a putut plati ratele.
     */
    public final void calculatePenalty() {
        penalty = Math.toIntExact(Math.round(Math.floor(PERCENT * bill)) + bill);
    }
    /**
     *Scade din bugetul consumatorului rata lunara.
     */
    public final void payBill() {
        budget = Math.toIntExact(budget - bill);
    }

}
