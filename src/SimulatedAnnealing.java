import java.util.Arrays;
import java.util.Random;

import company.Company;

public class SimulatedAnnealing {

    // Define o algoritmo Simulated Annealing
    public static Company simulatedAnnealing(Company current, double initialTemperature, double coolingRate,
            int numIterations) {
        Company best = current;
        Company currentSolution = current;
        Random rand = new Random();

        for (int i = 0; i < numIterations; i++) {
            double temperature = initialTemperature * Math.pow(coolingRate, i);

            // Gera uma nova solução aleatória
            double[] newAttributes = Arrays.copyOf(currentSolution.getAttributes(),
                    currentSolution.getAttributes().length);
            int attributeIndex = rand.nextInt(currentSolution.getAttributes().length);
            newAttributes[attributeIndex] += (rand.nextDouble() * 2 - 1); // Altera o valor aleatoriamente
            Company newSolution = new Company(currentSolution.getName(), newAttributes[0], newAttributes[1],
                    newAttributes[2]);

            // Verifica se a nova solução é melhor ou pior do que a solução atual
            double deltaPerformance = newSolution.getPerformance() - currentSolution.getPerformance();
            if (deltaPerformance > 0 || Math.exp(deltaPerformance / temperature) > rand.nextDouble()) {
                currentSolution = newSolution;
            }

            // Verifica se a nova solução é a melhor solução encontrada até agora
            if (currentSolution.getPerformance() > best.getPerformance()) {
                best = currentSolution;
            }
        }

        return best;
    }

    public static void main(String[] args) {
        // Cria empresas aleatórias para testar o algoritmo
        Company[] companies = new Company[5];
        Random rand = new Random();
        for (int i = 0; i < companies.length; i++) {
            String name = "Company " + (i + 1);
            double revenue = rand.nextDouble() * 1000000; // Receita aleatória até 1 milhão
            double profit = rand.nextDouble() * 100000; // Lucro aleatório até 100 mil
            double employees = rand.nextInt(1000); // Número de funcionários aleatório até 1000
            companies[i] = new Company(name, revenue, profit, employees);
        }

        // Encontra a empresa com melhor desempenho usando Simulated Annealing
        Company best = simulatedAnnealing(companies[0], 100, 0.99, 1000);
        for (int i = 1; i < companies.length; i++) {
            Company current = companies[i];
            if (current.getPerformance() > best.getPerformance()) {
                best = current;
            }
        }

        // Imprime o resultado
        System.out.println("Empresa com melhor desempenho:");
        System.out.println(best);
    }

}
