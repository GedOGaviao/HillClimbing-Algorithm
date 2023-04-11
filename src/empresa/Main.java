package empresa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        final Random RANDOM = new Random();
        List<Empresa> empresas = new ArrayList<>();

        final int MAX_EMPRESAS = 50;
        final int PONTO_INICIAL = RANDOM.nextInt(MAX_EMPRESAS);

        for (int i = 0; i < MAX_EMPRESAS; i++) {
            empresas.add(new Empresa("Empresa " + (i + 1), RANDOM.nextDouble() * 100, RANDOM.nextDouble() * 100,
                    RANDOM.nextInt(100) + 1));
        }

        Empresa melhorHill = HillClimbing.calculate(empresas, PONTO_INICIAL);
        Empresa melhorSimulated = SimulatedAnnealing.calculate(empresas, PONTO_INICIAL);

        // exibir HillClimbing
        print(empresas, PONTO_INICIAL, melhorHill, "HILL CLIMBING");

        // exibir SimulatedAnnealing
        print(empresas, PONTO_INICIAL, melhorSimulated, "SIMULATED ANNEALING");     

    }

    static void print(List<Empresa> empresas, int pontoInicial, Empresa melhor, String algoritmo) {
        System.out.println("*** GRÁFICO DE DESEMPENHO ***\n");
        System.out.println("*** " + algoritmo + " ***\n");
        getGrafico(empresas, pontoInicial, empresas.indexOf(melhor));
        System.out.println("\nPONTO INICIAL => " + (pontoInicial + 1));
        System.out.println("MAXIMO LOCAL => " + (empresas.indexOf(melhor) + 1));
        System.out.println("\nEMPRESA COM MELHOR DESEMPENHO: \n" +
                melhor.toString());
    }

    public static void getGrafico(List<Empresa> empresas, int pontoInicial, int maximoLocal) {
        String bars[] = new String[empresas.size()];
        Arrays.fill(bars, "");

        for (int i = 0; i < empresas.size(); i++) {
            int valorDesempenho = (int) empresas.get(i).getDesempenho();
            bars[i] = empresas.get(i).getName() + " | ";
            for (int j = 0; j < valorDesempenho; j++) {
                bars[i] += "░";
            }
        }
        String newBar = bars[pontoInicial];
        newBar = newBar.replaceAll("░", "▓");
        newBar += " <= Ponto Inicial";
        bars[pontoInicial] = newBar;

        newBar = bars[maximoLocal];
        newBar = newBar.replaceAll("░", "█");
        newBar += " <= MÁXIMO LOCAL";
        bars[maximoLocal] = newBar;

        for (int i = 0; i < bars.length; i++) {
            System.out.println(bars[i]);
        }
    }

}
