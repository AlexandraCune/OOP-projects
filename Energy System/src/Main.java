import energysystem.Consumers;
import energysystem.Distributors;
import energysystem.EnergySystem;
import energysystem.Producers;
import loadinput.ConsumersFactory;
import loadinput.ConsumersInput;
import loadinput.DistributorsFactory;
import loadinput.DistributorsInput;
import loadinput.Input;
import loadinput.LoadInput;
import loadinput.MonthlyUpdates;
import loadinput.ProducersInput;
import loadoutput.LoadOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        LoadInput loader = new LoadInput(args[0]);
        //LoadInput loader = new LoadInput("checker/resources/in/complex_5.json");
        Input inputData = loader.readInput();
        Integer numberOfTurns = inputData.getNumberOfTurns();
        List<ConsumersInput> consumersInputs = inputData.getInitialData().getConsumers();
        List<DistributorsInput> distributorsInputs = inputData.getInitialData().getDistributors();
        List<ProducersInput> producersInputs = inputData.getInitialData().getProducers();
        ConsumersFactory consumersFactory = ConsumersFactory.getInstance();
        List<MonthlyUpdates> monthlyUpdates = inputData.getMonthlyUpdates();
        List<Consumers> consumerList = new ArrayList<>();
        for (ConsumersInput consumers : consumersInputs) {
            Consumers consumers1 = consumersFactory.createConsumer("basic", consumers.getId(),
                    consumers.getInitialBudget(), consumers.getMonthlyIncome());
            consumerList.add(consumers1);
        }
        DistributorsFactory distributorsFactory = DistributorsFactory.getInstance();
        List<Distributors> distributorsList = new ArrayList<>();
        for (DistributorsInput distributorsInput : distributorsInputs) {
            Distributors distributor = distributorsFactory.createDistributors("basic",
                    distributorsInput.getId(), distributorsInput.getInitialBudget(),
                    distributorsInput.getInitialInfrastructureCost(),
                    distributorsInput.getEnergyNeededKW(),
                    distributorsInput.getContractLength(),
                    distributorsInput.getProducerStrategy());
            distributorsList.add(distributor);
        }

        List<Producers> producersList = new ArrayList<>();
        for (ProducersInput producersInput : producersInputs) {
            Producers producer = new Producers(producersInput.getId(),
                    producersInput.getEnergyType(),
                    producersInput.getMaxDistributors(),
                    producersInput.getPriceKW(),
                    producersInput.getEnergyPerDistributor());
            producersList.add(producer);
        }

        EnergySystem energySystem = new EnergySystem(numberOfTurns, monthlyUpdates,
                consumerList, distributorsList, producersList);
        energySystem.simulateEnergySystem();
        LoadOutput loadOutput = new LoadOutput(consumerList,
                distributorsList, producersList, args[1]);
        loadOutput.writeOutput();
    }
}
