package loadinput;

public interface ConsumersInterface {
    /**
     *Calculeaza bugetul lunar al unui consumator, adaugand salariul.
     */
    void calculateBuget();
    /**
     *Calculeaza penalizarea unui consumator care nu si-a putut plati ratele.
     */
    void calculatePenalty();
    /**
     *Scade din bugetul consumatorului rata lunara.
     */
    void payBill();
}
