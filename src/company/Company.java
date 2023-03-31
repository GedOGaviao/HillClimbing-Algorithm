package company;

public class Company {

	private String name;
	private double[] attributes;

	public Company(String name, double revenue, double profit, double employees) {
		this.name = name;
		this.attributes = new double[] { revenue, profit, employees };

	}

	public double getPerformance() {
		return 0.5 * attributes[0] + 0.3 * attributes[1] + 0.2 * attributes[2];
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {

		return String.format("%s \nRevenue: %.2f\nProfit: %.2f\nEmployees: %d\nPerformance: %.2f\n\n", this.name,
				this.attributes[0],
				this.attributes[1], (int) this.attributes[2], this.getPerformance());

	}
}
