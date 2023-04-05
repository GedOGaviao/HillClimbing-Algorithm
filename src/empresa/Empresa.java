package empresa;

public class Empresa {

	private String name;
	private double[] atributos;
	double receita;

	public Empresa(String name, double receita, double lucro, double funcionarios) {
		this.name = name;
		this.receita = receita;
		this.atributos = new double[] { receita, lucro, funcionarios };

	}

	public double getDesempenho() {
		return 0.5 * atributos[0] + 0.3 * atributos[1] + 0.2 * atributos[2];
	}
	
	public double getReceita() {
		return receita;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {

		return String.format("%s \nReceita: %.2f\nLucro: %.2f\nFuncionários: %d\nDesempenho: %.2f\n\n", this.name, this.atributos[0],
				this.atributos[1], (int) this.atributos[2], this.getDesempenho());

	}
}
