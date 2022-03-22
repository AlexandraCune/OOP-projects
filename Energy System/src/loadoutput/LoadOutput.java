package loadoutput;

import com.fasterxml.jackson.databind.ObjectMapper;
import energysystem.Consumers;
import energysystem.Distributors;
import energysystem.Producers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadOutput {
    private final List<Consumers> consumerList;
    private final List<Distributors> distributorList;
    private final List<Producers> producersList;
    private final String outputsFile;

    public LoadOutput(final List<Consumers> consumerList,
                      final List<Distributors> distributorList,
                      final List<Producers> producersList,
                      final String outputsFile) {
        this.consumerList = consumerList;
        this.distributorList = distributorList;
        this.producersList = producersList;
        this.outputsFile = outputsFile;
    }

    /**
     * Folosit la scrierea in fisier a datelor de iesire.
     */
    public void writeOutput() throws IOException {
        Output output = new Output();
        for (Consumers consumer : consumerList) {
            output.getConsumers().add(new OutputConsumers(consumer.getId(),
                    consumer.getIsBankrupt(), consumer.getBudget()));
        }
        for (Distributors distributor : distributorList) {
            List<Contracts> contracts = new ArrayList<>();
            if (distributor.getIsBankrupt().equals(false)) {
                List<Consumers> consumers = distributor.getConsumersList();
                if (consumers.size() != 0) {
                    for (Consumers consumer : distributor.getConsumersList()) {
                        Contracts contract = new Contracts();
                        contract.setConsumerId(consumer.getId());
                        contract.setPrice(consumer.getBill());
                        contract.setRemainedContractMonths(consumer.getContractLength());
                        contracts.add(contract);
                    }
                }
            }
            output.getDistributors().add(new OutputDistributors(distributor.getId(),
                    distributor.getEnergyNeededKW(), distributor.getContractCost(),
                    distributor.getBudget(), distributor.getProducerStrategy(),
                    distributor.getIsBankrupt(), contracts));
        }

        for (Producers producer : producersList) {
            output.getEnergyProducers().add(new OutputProducers(producer.getId(),
                    producer.getMaxDistributors(),
                    producer.getPriceKW(), producer.getEnergyType(),
                    producer.getEnergyPerDistributor(), producer.getMonthlyStats()));
        }
        //sortam distribuitorii dupa id
        Collections.sort(output.getDistributors());
        //sortam producatorii dupa id
        Collections.sort(output.getEnergyProducers());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(outputsFile), output);
    }
}
