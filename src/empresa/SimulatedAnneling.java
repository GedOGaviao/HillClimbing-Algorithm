package empresa;

import java.util.Random;
import java.util.ArrayList;

public class SimulatedAnneling {
	private ArrayList<Empresa> empressas;
	private double temperatura = 1000;
	private double taxaResfriamento = 0.03;
	private Random random = new Random();
	
	public SimulatedAnneling(ArrayList<Empresa> empressas) {
		this.empressas = empressas;
	}
	
	public void run() {
		double pontuacaoAtual  = calculaPontuacao(empressas);
		double melhorPontuacao = pontuacaoAtual;
		ArrayList<Empresa> melhoresEmpressas = empressas;
		
		while(temperatura > 1) {
			ArrayList<Empresa> novaEmpressa = gerarNovasSolucoes(melhoresEmpressas);
			double novaPontuacao = calculaPontuacao(novaEmpressa);
			double delta = novaPontuacao - pontuacaoAtual; // diferença entre as pontuações
			
			if(delta > 0) { //aceita novas soluções
				pontuacaoAtual = novaPontuacao;
				melhoresEmpressas = novaEmpressa;
				melhorPontuacao = novaPontuacao;
			}else if(Math.exp(delta / temperatura) >  random.nextDouble()) { //aceita a nova solução com probalidade menor se a pontuação for menor
				pontuacaoAtual = novaPontuacao;
			}
			
			temperatura *= 1 - taxaResfriamento; // diminui a temperatura
		}
		
		System.out.println("Melhores Empressas" + melhoresEmpressas);
		System.out.println("Melhor Pontuação" + melhorPontuacao);
		
	}
	

	private ArrayList<Empresa> gerarNovasSolucoes(ArrayList<Empresa> empressas) {
		ArrayList<Empresa> novaEmpressa = new ArrayList<>(empressas);
		int index1 = random.nextInt(novaEmpressa.size());
		int index2 = random.nextInt(novaEmpressa.size());
		Empresa empressas1 = novaEmpressa.get(index1);
		Empresa empressas2 = novaEmpressa.get(index2);
		novaEmpressa.set(index1, empressas1);
		novaEmpressa.set(index1, empressas2);
		return novaEmpressa;
	}

	private double calculaPontuacao(ArrayList<Empresa> empressas) {
		double totalLucros = 0;
		double totalReceita = 0;
		
		for (Empresa empresa : empressas) {
			totalLucros += empresa.getDesempenho();
			totalReceita += empresa.getReceita();
		}
		
		return totalLucros / totalReceita;
		
	}
	


}
