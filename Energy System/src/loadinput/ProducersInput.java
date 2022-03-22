package loadinput;

public class ProducersInput {
    private Integer id;
    private String energyType;
    private Integer maxDistributors;
    private Double priceKW;
    private Integer energyPerDistributor;

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final String getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public final Integer getMaxDistributors() {
        return maxDistributors;
    }

    public final void setMaxDistributors(final Integer maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final Double getPriceKW() {
        return priceKW;
    }

    public final void setPriceKW(final Double priceKW) {
        this.priceKW = priceKW;
    }

    public final Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(final Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
