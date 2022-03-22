package loadinput;

public interface DistributorsInterface {
    /**
     *Calculeaza costul contractului in cazul in care distribuitorul nu are consumatori.
     */
    void calculateContractCostForNULL();
    /**
     *Calculeaza costul contractului in cazul in care distribuitorul are consumatori.
     */
    void calculateContractCost();
    /**
     *Calculeaza profitul distribuitorul intr-o luna.
     */
    void calculateProfit();
    /**
     *Calculeaza cheltuielile distribuitorul intr-o luna.
     */
    void calculateDistributorCost();

}
