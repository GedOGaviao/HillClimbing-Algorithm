package empresa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HillClimbing {
	public static void main(String[] args) {

		final Random RANDOM = new Random();
		List<Empresa> empresas = new ArrayList<>();

		final int MAX_EMPRESAS = 50;
		final int PONTO_INICIAL = RANDOM.nextInt(MAX_EMPRESAS);
		
		for (int i = 0; i < MAX_EMPRESAS; i++) {
			empresas.add(new Empresa("Empresa " + (i + 1), RANDOM.nextDouble() * 100, RANDOM.nextDouble() * 100,
					RANDOM.nextInt(100) + 1));
		}

		Empresa melhor = hillClimbing(empresas, PONTO_INICIAL);
		
		System.out.println("*** GRÁFICO DE DESEMPENHO ***\n");
		getGrafico(empresas, PONTO_INICIAL, empresas.indexOf(melhor));
		System.out.println("\nPONTO INICIAL => "+(PONTO_INICIAL+1));
		System.out.println("MAXIMO LOCAL => " + (empresas.indexOf(melhor) + 1));
		System.out.println("\nEMPRESA COM MELHOR DESEMPENHO: \n" +
				melhor.toString());

	}

	public static Empresa hillClimbing(List<Empresa> empresas, int pontoInicial) {
		Empresa atual = empresas.get(pontoInicial);
		double atualDesempenho = atual.getDesempenho();

		boolean caminharDireita = caminharDireita(empresas, pontoInicial);
		while (true) {
			if (caminharDireita) {
				for (int i = pontoInicial + 1; i < empresas.size(); i++) {
					if (empresas.get(i).getDesempenho() > atualDesempenho) {
						atual = empresas.get(i);
						atualDesempenho = atual.getDesempenho();
					} else {
						return atual;
					}
				}
			} else {
				for (int i = pontoInicial - 1; i >= 0; i--) {
					if (empresas.get(i).getDesempenho() > atualDesempenho) {
						atual = empresas.get(i);
						atualDesempenho = atual.getDesempenho();
					} else {
						return atual;
					}
				}
			}
		}
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

	private static boolean caminharDireita(List<Empresa> empresas, int pontoInicial) {

		if (pontoInicial == 0) {
			return true;
		} else if (pontoInicial == empresas.size() - 1) {
			return false;
		} else if (empresas.get(pontoInicial + 1).getDesempenho() > empresas.get(pontoInicial - 1).getDesempenho()) {
			return true;
		} else {
			return false;
		}
	}
}