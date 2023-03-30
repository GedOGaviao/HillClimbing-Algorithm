package empresa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HillClimbing {
	public static void main(String[] args) {

		final int MAX_ITERATIONS = 50;
		final Random RANDOM = new Random();
		List<Empresa> empresas = new ArrayList<>();

		for (int i = 0; i < MAX_ITERATIONS; i++) {
			empresas.add(new Empresa("Empresa " + (i + 1), RANDOM.nextDouble() * 100, RANDOM.nextDouble() * 100,
					RANDOM.nextInt(100) + 1));
		}

		Empresa melhor = hillClimbing(empresas, 40);

		// System.out.println("TODAS AS EMPRESAS: \n\n" + empresas.toString() + "\n\n");
		System.out.println("EMPRESA COM MELHOR DESEMPENHO: \n\n" +
				melhor.toString());

	}

	public static Empresa hillClimbing(List<Empresa> empresas, int pontoInicial) {
		Empresa melhorEmpresa = empresas.get(pontoInicial);
		int i = pontoInicial;
		while (true) {
			Empresa empresaAtual = empresas.get(i);
			Empresa empresaAnterior = i > 0 ? empresas.get(i - 1) : null;
			Empresa empresaPosterior = i < empresas.size() - 1 ? empresas.get(i + 1) : null;
			if (isBetterThanNeighbors(empresaAtual, empresaAnterior, empresaPosterior)) {
				melhorEmpresa = empresaAtual;
			} else {
				getGrafico(empresas, pontoInicial, i);
				return melhorEmpresa;
			}
			if (empresaPosterior == null || empresaAtual.getDesempenho() >= empresaPosterior.getDesempenho()) {
				while (empresaAnterior != null && empresaAtual.getDesempenho() < empresaAnterior.getDesempenho()) {
					i--;
					empresaAtual = empresas.get(i);
					empresaAnterior = i > 0 ? empresas.get(i - 1) : null;
					empresaPosterior = i < empresas.size() - 1 ? empresas.get(i + 1) : null;
					if (isBetterThanNeighbors(empresaAtual, empresaAnterior, empresaPosterior)) {
						melhorEmpresa = empresaAtual;
					}
				}
				break;
			} else {
				i++;
			}
		}
		getGrafico(empresas, pontoInicial, i);
		return melhorEmpresa;
	}

	private static boolean isBetterThanNeighbors(Empresa empresaAtual, Empresa previous, Empresa next) {
		return empresaAtual.getDesempenho() > previous.getDesempenho()
				&& empresaAtual.getDesempenho() > next.getDesempenho();
	}

	public static void getGrafico(List<Empresa> empresas, int pontoInicial, int maximoLocal) {
		String bars[] = new String[empresas.size()];
		Arrays.fill(bars, "");

		for (int i = 0; i < empresas.size(); i++) {
			int valorDesempenho = (int) empresas.get(i).getDesempenho();
			bars[i] = "Empresa " + i + " | ";
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
