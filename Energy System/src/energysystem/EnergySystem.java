package energysystem;

import loadinput.ConsumersFactory;
import loadinput.ConsumersInput;
import loadinput.DistributorChanges;
import loadinput.MonthlyUpdates;
import loadinput.ProducerChanges;
import loadoutput.MonthlyStats;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnergySystem {

    private final Integer numberOfTurns;
    private final List<MonthlyUpdates> monthlyUpdates;
    private final List<Consumers> consumerList;
    private final List<Distributors> distributorList;
    private final List<Producers> producersList;

    public EnergySystem(final Integer numberOfTurns,
                        final List<MonthlyUpdates> monthlyUpdates,
                        final List<Consumers> consumerList,
                        final List<Distributors> distributorList,
                        final List<Producers> producersList) {
        this.numberOfTurns = numberOfTurns;
        this.monthlyUpdates = monthlyUpdates;
        this.consumerList = consumerList;
        this.distributorList = distributorList;
        this.producersList = producersList;
    }


    /**
     * Modifica costurile de infrastructura ale distribuitorilor,
     * in cazul in care s-a facut update.
     */
    private void updateContracts(final List<DistributorChanges> distributorChanges) {
        for (DistributorChanges cost : distributorChanges) {
            for (Distributors distributor : distributorList) {
                if (distributor.getIsBankrupt().equals(false)) {
                    if (cost.getId().equals(distributor.getId())) {
                        distributor.setInfrastructureCost(cost.getInfrastructureCost());
                    }
                }
            }
        }
    }

    /**
     * Calculeaza preturile contractelor si profitul tuturor distribuitorilor nefalimentati.
     */
    private void calculateAllContracts() {
        for (Distributors distributors : distributorList) {
            if (distributors.getIsBankrupt().equals(false)) {
                if (distributors.getConsumersList().size() != 0) {
                    distributors.calculateProfit();
                    distributors.calculateContractCost();
                } else {
                    distributors.calculateProfit();
                    distributors.calculateContractCostForNULL();
                }
            }
        }
    }

    /**
     * Recalculeaza bugetele tuturor consumatorilor,
     * adaugandu-le venitul lunar.
     */
    private void calculateConsumersBuget() {
        for (Consumers consumer : consumerList) {
            if (consumer.getIsBankrupt().equals(false)) {
                consumer.calculateBuget();
            }
        }
    }

    /**
     * Alege distribuitorul cu pretul pe contract cel mai mic pentru fiecare
     * consumator ce are numarul de luni din contract = 0,
     * avand grija ca niciunul din ei sa nu fi dat faliment.
     */
    private void pickContracts() {
        for (Consumers consumers : consumerList) {
            if (consumers.getIsBankrupt().equals(false)) {
                if (consumers.getContractLength().equals(0)) {
                    for (Distributors distributor : distributorList) {
                        if (distributor.getIsBankrupt().equals(false)) {
                            distributor.getConsumersList().add(consumers);
                            consumers.setBill(distributor.getContractCost());
                            consumers.setContractLength(distributor.getContractLength());
                            consumers.setCurrentDistributor(distributor);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Sterge din lista de consumatori ai unui distribuitor,
     * pe aceia care si-au terminat contractele.
     */
    private void removeConsumers() {
        for (Distributors distributor : distributorList) {
            if (distributor.getIsBankrupt().equals(false)
                    && (distributor.getConsumersList().size() != 0)) {
                List<Consumers> removeConsumers = new ArrayList<>();
                for (Consumers consumer : distributor.getConsumersList()) {
                    if (consumer.getContractLength().equals(0)) {
                        removeConsumers.add(consumer);
                    }
                }
                distributor.getConsumersList().removeAll(removeConsumers);
            }
        }
    }

    /**
     * Simuleaza platile facute de consumatori la distribuitorii la care sunt inscrisi.
     * In cazul in care consumatorii nu iti pot platii rata intr-o luna sunt penalizati
     * pentru luna urmatoare, ulterior fiind declarati faliment in cazul in care nici
     * data viitoare cand le vine randul la plata nu isi permit.
     */
    private void payAllConsumersBills() {
        for (Consumers consumer : consumerList) {
            if (consumer.getIsBankrupt().equals(false)) {
                if (consumer.getPenalty() == 0) {
                    consumer.payBill();
                    if (consumer.getOldBill() != 0) {
                        consumer.setBudget(consumer.getBudget() - consumer.getOldBill());
                    }
                    for (Distributors distributor : distributorList) {
                        if (distributor.getIsBankrupt().equals(false)) {
                            if (distributor.getConsumersList().contains(consumer)) {
                                distributor.setBudget(distributor.getBudget()
                                        + consumer.getBill() + consumer.getOldBill());
                            }
                        }
                    }
                    //scade numarul de luni din contractul consumatorului
                    consumer.setContractLength(consumer.getContractLength() - 1);
                    if (consumer.getBudget() < 0) {
                        //daca consumatorul nu isi permite sa plateasca, si este prima data,
                        //isi primeste banii inapoi, iar distribuitorul nu primeste nimic.
                        consumer.setBudget(consumer.getBudget() + consumer.getBill());
                        for (Distributors distributor : distributorList) {
                            if (distributor.getIsBankrupt().equals(false)) {
                                if (distributor.getConsumersList().contains(consumer)) {
                                    distributor.setBudget(distributor.getBudget()
                                            - consumer.getBill());
                                    consumer.setDistributorPenalty(distributor);
                                }
                            }
                        }
                        consumer.calculatePenalty();
                        consumer.setOldBill(consumer.getBill());
                    }
                } else {
                    //daca suntem in cazul in care consumatorul are penalizare,
                    //vedem daca o poate plati si daca nu , il decalaram faliment.

                    //scade numarul de luni din contractul consumatorului
                    consumer.setContractLength(consumer.getContractLength() - 1);
                    consumer.payBill();
                    consumer.setBudget(consumer.getBudget() - consumer.getPenalty()
                            - consumer.getOldBill());
                    if (consumer.getBudget() < 0) {
                        consumer.setIsBankrupt(true);
                        consumer.setBudget(consumer.getBudget()
                                + consumer.getPenalty()
                                + consumer.getBill()
                                + consumer.getOldBill());
                    } else {
                        //daca isi permite plata totala, distribuitorul la care are contractul
                        // actual isi primeste banii, iar cel la care are datoria
                        // primeste penalizarea.
                        for (Distributors distributor : distributorList) {
                            if (distributor.getIsBankrupt().equals(false)) {
                                if (distributor.getConsumersList().contains(consumer)) {
                                    //daca are contract la distribuitorul la care
                                    // are penalizarea plateste tot
                                    if (consumer.getCurrentDistributor().equals(
                                            consumer.getDistributorPenalty())) {
                                        distributor.setBudget(distributor.getBudget()
                                                + consumer.getPenalty() + consumer.getOldBill()
                                                + consumer.getBill());
                                        consumer.setDistributorPenalty(null);
                                        consumer.setPenalty(0);
                                        consumer.setOldBill(0);

                                    } else {
                                        //daca nu isi plateste doar factura veche si penalizarea
                                        //si amana noua factura
                                        for (Distributors olddistributor : distributorList) {
                                            if (olddistributor.equals(
                                                    consumer.getDistributorPenalty())) {
                                                olddistributor.setBudget(
                                                        olddistributor.getBudget()
                                                                + consumer.getPenalty()
                                                                + consumer.getOldBill());
                                                consumer.setDistributorPenalty(null);
                                                consumer.setPenalty(0);
                                                //amanam factura la distribuitorul curent
                                                consumer.setOldBill(consumer.getBill());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Simuleaza platile facute de distrubuitori.
     * Verifica daca acestia isi permit platile pe care le au si in cazul in care nu,
     * sunt declarati falimentati.
     */
    private void payDistributorsCosts() {
        for (Distributors distributor : distributorList) {
            if (distributor.getIsBankrupt().equals(false)) {
                distributor.calculateDistributorCost();
                distributor.setBudget(distributor.getBudget()
                        - distributor.getDistributorCost());
                if (distributor.getBudget() < 0) {
                    // daca distribuitorul nu isi permite plata tuturor costurilor,
                    // se  va incerca doar platirea infrastructurii.
                    distributor.setBudget(distributor.getBudget()
                            + distributor.getDistributorCost());
                    distributor.setBudget(distributor.getBudget()
                            - distributor.getInfrastructureCost());
                    if (distributor.getBudget() < 0) {
                        //daca acesta nu isi permite nici infracstructura,
                        // se declara faliment.
                        //daca distribuitorul este declarat falimentat,
                        // consumatorii sai sunt scosi iar pe piata
                        distributor.setIsBankrupt(true);
                        for (Consumers consumer : distributor.getConsumersList()) {
                            consumer.setBill(0);
                            consumer.setContractLength(0);
                        }
                        distributor.setConsumersList(new ArrayList<Consumers>());
                    }
                }
            }
        }
    }

    /**
     * Sterge din listele de consumatori ale distribuitorilor,
     * consumatorii falimentati.
     */
    private void deleteBankruptConsumers() {
        for (Distributors distributor : distributorList) {
            if (distributor.getIsBankrupt().equals(false)) {
                for (Consumers consumer : consumerList) {
                    if (consumer.getIsBankrupt().equals(true)
                            && distributor.getConsumersList().size() != 0) {
                        distributor.getConsumersList().remove(consumer);
                    }
                }
            }
        }
    }

    /**
     * initializeaza campurile de bugete costuri si contracte cu cele primite la intrare
     * pentru fiecare consumator si distribuitor.
     */
    private void initializeFields() {
        for (Consumers consumers : consumerList) {
            consumers.setBudget(consumers.getInitialBudget());
            consumers.setContractLength(0);
        }
        for (Distributors distributor : distributorList) {
            distributor.setInfrastructureCost(distributor.getInitialInfrastructureCost());
            distributor.setBudget(distributor.getInitialBudget());
        }
    }

    /**
     * Modifica energia oferita fiecarui distribuitor,
     * in cazul in care s-a facut update.
     */
    private void updateProducers(final List<ProducerChanges> producerChanges) {
        for (ProducerChanges changes : producerChanges) {
            for (Producers producer : producersList) {
                if (changes.getId().equals(producer.getId())) {
                    producer.changesEnergyPerDistributor(changes.getEnergyPerDistributor());
                }
            }
        }
    }

    /**
     * Fiecare distribuitor isi alege unul sau mai multi producatori
     * care sa ii acopere cantitatea de energie ceruta.
     * Producatorii sunt alesi in functie de strategiile fiecarui distribuitor.
     */
    private void pickProducersForDistributors() {
        Collections.sort(distributorList, new DistributorsIdComparator());
        for (Distributors distributors : distributorList) {
            if (distributors.getIsBankrupt().equals(false)
                    && distributors.getHasNoProducer().equals(true)) {
                //sterg distribuitorul din listele de observatori ale
                //producatorilor care il au
                for (Producers producers : producersList) {
                    producers.deleteObserver(distributors);
                }
                DistributorsStrategy distributorsStrategy;
                if (distributors.getProducerStrategy().equals(
                        EnergyChoiceStrategyType.GREEN.toString())) {
                    distributorsStrategy = new GreenDistributorsStrategy(producersList,
                            distributors);
                    distributors.setProducersList(distributorsStrategy.pickProducers());
                    if (distributors.getProducersList().size() > 0) {
                        distributors.setHasNoProducer(false);
                    }
                }
                if (distributors.getProducerStrategy().equals(
                        EnergyChoiceStrategyType.PRICE.toString())) {
                    distributorsStrategy = new PriceDistributorsStrategy(producersList,
                            distributors);
                    distributors.setProducersList(distributorsStrategy.pickProducers());
                    if (distributors.getProducersList().size() > 0) {
                        distributors.setHasNoProducer(false);
                    }
                }
                if (distributors.getProducerStrategy().equals(
                        EnergyChoiceStrategyType.QUANTITY.toString())) {
                    distributorsStrategy = new QuantityDistributorsStrategy(producersList,
                            distributors);
                    distributors.setProducersList(distributorsStrategy.pickProducers());
                    if (distributors.getProducersList().size() > 0) {
                        distributors.setHasNoProducer(false);
                    }
                }

            }
        }
    }

    /**
     * Calculeaza costul de productie al fiecarui distribuitor.
     */
    private void calculateProductionCost() {
        for (Distributors distributors : distributorList) {
            distributors.calculateProductionCost();
        }
    }

    /**
     * Seteaza ce distribuitori a avut fiacre producator
     * in fiecare luna.
     */
    private void setMonthlyStatsForProducers(final int month) {
        for (Producers allproducers : producersList) {
            List<Integer> distributorsId = new ArrayList<>();
            for (Distributors distributors : distributorList) {
                if (distributors.getIsBankrupt().equals(false)) {
                    if (distributors.getProducersList().contains(allproducers)) {
                        distributorsId.add(distributors.getId());
                    }
                }
            }
            //sortam id-urile
            Collections.sort(distributorsId);
            MonthlyStats monthlyStats = new MonthlyStats(month + 1, distributorsId);
            allproducers.getMonthlyStats().add(monthlyStats);
        }
    }

    /**
     * Adauga observatori producatorilor
     */
    private void addObservers() {
        for (Producers producer : producersList) {
            for (Distributors distributor : distributorList) {
                if (distributor.getIsBankrupt().equals(false)) {
                    if (distributor.getProducersList().contains(producer)) {
                        producer.addObserver(distributor);
                    }
                }
            }

        }
    }

    /**
     * Simuleaza intrg procesul ce are loc intr-un sistem energetic
     * pe parcursul mai multor luni.
     */
    public void simulateEnergySystem() {
        initializeFields();
        pickProducersForDistributors();
        calculateProductionCost();
        calculateAllContracts();
        removeConsumers();
        Collections.sort(distributorList);
        calculateConsumersBuget();
        pickContracts();
        payAllConsumersBills();
        payDistributorsCosts();
        deleteBankruptConsumers();
        for (int i = 0; i < numberOfTurns; i++) {
            ConsumersFactory consumersFactory = ConsumersFactory.getInstance();
            if (monthlyUpdates.size() != 0) {
                if (monthlyUpdates.get(i).getNewConsumers().size() != 0) {
                    for (ConsumersInput consumers : monthlyUpdates.get(i).getNewConsumers()) {
                        Consumers consumers1 = consumersFactory.createConsumer("basic",
                                consumers.getId(), consumers.getInitialBudget(),
                                consumers.getMonthlyIncome());
                        consumers1.setBudget(consumers.getInitialBudget());
                        consumerList.add(consumers1);
                    }
                }

                if (monthlyUpdates.get(i).getDistributorChanges().size() != 0) {
                    updateContracts(monthlyUpdates.get(i).getDistributorChanges());
                }
            }
            calculateAllContracts();
            removeConsumers();
            Collections.sort(distributorList);
            calculateConsumersBuget();
            pickContracts();
            payAllConsumersBills();
            payDistributorsCosts();
            deleteBankruptConsumers();
            addObservers();
            if (monthlyUpdates.size() != 0) {
                if (monthlyUpdates.get(i).getProducerChanges().size() != 0) {
                    updateProducers(monthlyUpdates.get(i).getProducerChanges());
                }
            }
            pickProducersForDistributors();
            calculateProductionCost();
            setMonthlyStatsForProducers(i);
        }
    }


}
