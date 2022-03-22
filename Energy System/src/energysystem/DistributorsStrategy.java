package energysystem;

import java.util.List;

public interface DistributorsStrategy {
    /**
     * In cadul acestei metode se aleg producatorii pentru un distribuitor
     *
     * @return
     */
    List<Producers> pickProducers();
}
