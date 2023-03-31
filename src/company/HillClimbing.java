package company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HillClimbing {
	public static void main(String[] args) {

		final Random RANDOM = new Random();
		List<Company> companies = new ArrayList<>();

		final int MAX_COMPANIES = 50;
		final int STARTING_POINT = RANDOM.nextInt(MAX_COMPANIES);

		for (int i = 0; i < MAX_COMPANIES; i++) {
			companies.add(new Company("Company " + (i + 1), RANDOM.nextDouble() * 100, RANDOM.nextDouble() * 100,
					RANDOM.nextInt(100) + 1));
		}

		Company best_Company = hillClimbing(companies, STARTING_POINT);

		System.out.println("*** PERFORMANCE CHART ***\n");
		getGrafico(companies, STARTING_POINT, companies.indexOf(best_Company));
		System.out.println("\nSTARTING_POINT => " + (STARTING_POINT + 1));
		System.out.println("LOCAL_MAXIMUM => " + (companies.indexOf(best_Company) + 1));
		System.out.println("\nCOMPANY_WITH_BEST_PERFORMANCE: \n" +
				best_Company.toString());

	}

	public static Company hillClimbing(List<Company> companies, int starting_point) {
		Company current = companies.get(starting_point);
		double current_Performance = current.getPerformance();

		boolean walk_Right = walkRight(companies, starting_point);
		while (true) {
			if (walk_Right) {
				for (int i = starting_point + 1; i < companies.size(); i++) {
					if (companies.get(i).getPerformance() > current_Performance) {
						current = companies.get(i);
						current_Performance = current.getPerformance();
					} else {
						return current;
					}
				}
			} else {
				for (int i = starting_point - 1; i >= 0; i--) {
					if (companies.get(i).getPerformance() > current_Performance) {
						current = companies.get(i);
						current_Performance = current.getPerformance();
					} else {
						return current;
					}
				}
			}
		}
	}

	public static void getGrafico(List<Company> companies, int starting_Point, int localMaximum) {
		String bars[] = new String[companies.size()];
		Arrays.fill(bars, "");

		for (int i = 0; i < companies.size(); i++) {
			int valuePerformance = (int) companies.get(i).getPerformance();
			bars[i] = companies.get(i).getName() + " | ";
			for (int j = 0; j < valuePerformance; j++) {
				bars[i] += "░";
			}
		}
		String newBar = bars[starting_Point];
		newBar = newBar.replaceAll("░", "▓");
		newBar += " <= STARTING POINT";
		bars[starting_Point] = newBar;

		newBar = bars[localMaximum];
		newBar = newBar.replaceAll("░", "█");
		newBar += " <= LOCAL MAXIMUM";
		bars[localMaximum] = newBar;

		for (int i = 0; i < bars.length; i++) {
			System.out.println(bars[i]);
		}
	}

	private static boolean walkRight(List<Company> companies, int starting_Point) {

		if (starting_Point == 0) {
			return true;
		} else if (starting_Point == companies.size() - 1) {
			return false;
		} else if (companies.get(starting_Point + 1).getPerformance() > companies.get(starting_Point - 1)
				.getPerformance()) {
			return true;
		} else {
			return false;
		}
	}
}
