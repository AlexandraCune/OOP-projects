package loadoutput;

public class Contracts {
    private Integer consumerId;
    private Integer price;
    private Integer remainedContractMonths;

    public final Integer getConsumerId() {
        return consumerId;
    }

    public final void setConsumerId(final Integer consumerId) {
        this.consumerId = consumerId;
    }

    public final Integer getPrice() {
        return price;
    }

    public final void setPrice(final Integer price) {
        this.price = price;
    }

    public final Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public final void setRemainedContractMonths(final Integer remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
