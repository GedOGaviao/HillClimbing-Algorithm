package empresa;
import java.util.List;

public class HillClimbing {
	
	public static Empresa calculate(List<Empresa> empresas, int pontoInicial) {
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