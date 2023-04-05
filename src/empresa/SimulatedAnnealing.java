package empresa;

import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

    public static Empresa calculate(List<Empresa> empresas, int pontoInicial) {
        Empresa atual = empresas.get(pontoInicial);
        double atualDesempenho = atual.getDesempenho();
        double probabilidade = 0.3; // inicia com probabilidade de aceitar valor
        double taxaResfriamento = -0.05;
        final Random RANDOM = new Random();

        boolean caminharDireita = caminharDireita(empresas, pontoInicial);
        while (true) {
            if (caminharDireita) {
                for (int i = pontoInicial + 1; i < empresas.size(); i++) {
                    if (empresas.get(i).getDesempenho() > atualDesempenho || RANDOM.nextDouble() <= probabilidade) {
                        atual = empresas.get(i);
                        atualDesempenho = atual.getDesempenho();
                        probabilidade = probabilidade * Math.exp(taxaResfriamento);
                    } else {
                        return atual;
                    }
                }
            } else {
                for (int i = pontoInicial - 1; i >= 0; i--) {
                    if (empresas.get(i).getDesempenho() > atualDesempenho || RANDOM.nextDouble() <= probabilidade) {
                        atual = empresas.get(i);
                        atualDesempenho = atual.getDesempenho();
                        probabilidade = probabilidade * Math.exp(taxaResfriamento);
                    } else {
                        return atual;
                    }
                }
            }
        }
    }

    private static boolean caminharDireita(List<Empresa> empresas, int pontoInicial) {
        final Random RANDOM = new Random();
        double probabilidade = 0.3;
        if (pontoInicial == 0) {
            return true;
        } else if (pontoInicial == empresas.size() - 1) {
            return false;
        } else if (empresas.get(pontoInicial + 1).getDesempenho() >= empresas.get(pontoInicial - 1).getDesempenho()
                || RANDOM.nextDouble() <= probabilidade) {
            return true;
        } else {
            return false;
        }
    }
}
