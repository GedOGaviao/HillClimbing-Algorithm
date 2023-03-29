package empresa;

import java.util.ArrayList;
import java.util.List;

public class HillClimbing {
	
	public static void main(String [] args) {
		//Criando uma lista de empressas com pontuações aleatorias 
		List<Empresas> empresas = new ArrayList<>();
		empresas.add(new Empresas("Empresa A", 6));
		empresas.add(new Empresas("Empresa B", 10));
		empresas.add(new Empresas("Empresa C", 4));
		empresas.add(new Empresas("Empresa D", 8));
		empresas.add(new Empresas("Empresa E", 9));
		
		//Configuração inicial
		int maxIteration = 100;
		int iteration = 0;
		int currentScore = 0;
		
		//Seleciona uma Empresa aleatoria para comerçar 
		Empresas empresaAtual = empresas.get((int) (Math.random() * empresas.size()));
		
		//execução do algoritimo
		while(iteration < maxIteration) {
			
			currentScore = empresaAtual.getScore();
			
			Empresas novaEmpresa = empresas.get((int) (Math.random() * empresas.size()));
			
			int newScore = novaEmpresa.getScore();
			
			if(newScore > currentScore) {
				empresaAtual = novaEmpresa;
			}
			
			iteration++;
		}
		
		System.out.println("Melhor opção para co-branding é a " + empresaAtual.getName());
	}
	
	

}
