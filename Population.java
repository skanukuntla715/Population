import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *	Population - Create a database from USA cities and sort this data by using
 *				Selection Sort, Insertion Sort, and Merge Sort.
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Shrutik Kanukuntla
 *	@since	January 16, 2023
 */
public class Population {
	// List of cities
	private List<City> cities = new ArrayList<>();
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	/**
	 * This method is used to load the data and cities in the
	 * text file and to check each line using FileUtils. This
	 * will then add name, state, cityType, and population to
	 * the cities array.
	 * @param filePath
	 * @return cities
	 */
	public static List<City> loadCities(String filePath) {
		List<City> cities = new ArrayList<>();
		Scanner file = FileUtils.openToRead("usPopData2017.txt");
		file.useDelimiter("[\t\n]");

		while (file.hasNext()) {
			String name = file.next();
			String state = file.next();
			String cityType = file.next();
			int population = file.nextInt();
			cities.add(new City(name, state, cityType, population));
		}
		file.close();
		return cities;
	}

	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> selectionSort(List<City> cities) {
		int n = cities.size();
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (cities.get(j).compareTo(cities.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			swap(cities, minIndex, i);
		}
		return cities;
	}

	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> selectionSortByPopulationAscending(List<City> cities) {
		int n = cities.size();
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (cities.get(j).getPopulation() < cities.get(minIndex).getPopulation()) {
					minIndex = j;
				}
			}
			swap(cities, minIndex, i);
		}
		return cities;
	}

	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> insertionSort(List<City> cities) {
		int n = cities.size();
		for (int i = 1; i < n; i++) {
			City key = cities.get(i);
			int j = i - 1;
			while (j >= 0 && cities.get(j).compareTo(key) > 0) {
				cities.set(j + 1, cities.get(j));
				j = j - 1;
			}
			cities.set(j + 1, key);
		}
		return cities;
	}

	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> insertionSortNames(List<City> cities) {
		int n = cities.size();
		for (int i = 1; i < n; i++) {
			City key = cities.get(i);
			int j = i - 1;
			while (j >= 0 && cities.get(j).getName().compareTo(key.getName()) < 0) {
				cities.set(j + 1, cities.get(j));
				j = j - 1;
			}
			cities.set(j + 1, key);
		}
		return cities;
	}

	/**
	 *	Merge Sort algorithm for population - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> mergeSortPopulation(List<City> cities) {
		if (cities.size() <= 1) {
			return cities;
		}
		int middle = cities.size() / 2;
		List<City> left = new ArrayList<>(cities.subList(0, middle));
		List<City> right = new ArrayList<>(cities.subList(middle, cities.size()));
		left = mergeSortPopulation(left);
		right = mergeSortPopulation(right);
		return mergePopulation(left, right);
	}

	/**
	 * This Method is to sort the population in descending order by using the merge
	 * method. This method checks to see whether i and j satisfy the conditions
	 * of the left and right side and add it to the final result.
	 * @param left
	 * @param right
	 * @return result
	 */
	private static List<City> mergePopulation(List<City> left, List<City> right) {
		List<City> result = new ArrayList<>();
		int i = 0, j = 0;
		while (i < left.size() && j < right.size()) {
			if (left.get(i).getPopulation() > right.get(i).getPopulation()) {
				result.add(left.get(i));
				i++;
			} else {
				result.add(right.get(j));
				j++;
			}
		}
		while (i < left.size()) {
			result.add(left.get(i));
			i++;
		}
		while (j < right.size()) {
			result.add(right.get(j));
			j++;
		}
		return result;
	}

	/**
	 *	Merge Sort algorithm for names - in ascending order (you implement)
	 *	@param cities		array of Integer objects to sort
	 */
	public static List<City> mergeSortNames(List<City> cities) {
		if (cities.size() <= 1) {
			return cities;
		}
		int middle = cities.size() / 2;
		List<City> left = new ArrayList<>(cities.subList(0, middle));
		List<City> right = new ArrayList<>(cities.subList(middle, cities.size()));
		left = mergeSortNames(left);
		right = mergeSortNames(right);
		return mergeNames(left, right);
	}

	/**
	 * This Method is to sort the names in descending order by using the merge
	 * method. This method checks to see whether i and j satisfy the conditions
	 * of the left and right side and add it to the final result.
	 * @param left
	 * @param right
	 * @return result
	 */
	private static List<City> mergeNames(List<City> left, List<City> right) {
		List<City> result = new ArrayList<>();
		int i = 0, j = 0;
		while (i < left.size() && j < right.size()) {
			if (left.get(i).getName().compareTo(right.get(i).getName()) > 0) {
				result.add(left.get(i));
				i++;
			} else {
				result.add(right.get(j));
				j++;
			}
		}
		while (i < left.size()) {
			result.add(left.get(i));
			i++;
		}
		while (j < right.size()) {
			result.add(right.get(j));
			j++;
		}
		return result;
	}

	/**
	 * Uses the Selection Sort method for sorting popualtion in order
	 * to sort the population by ascending order. This method also
	 * calculates the amount of time it takes to do this.
	 * @param cities	Array of integer objects
	 * @return sortedCites
	 */
	public static List<City> sortByPopulationAscending(List<City> cities) {
		long startMillisec = System.currentTimeMillis();
		List<City> sortedCities = selectionSortByPopulationAscending(cities);
		long endMillisec = System.currentTimeMillis();
		long timeTaken = endMillisec - startMillisec;
		System.out.println("Elapsed Time " + timeTaken + " milliseconds");
		return sortedCities;
	}

	/**
	 * Uses the merge sort method for sorting popualtion in order
	 * to sort the population by descending order. This method also
	 * calculates the amount of time it takes to do this.
	 * @param cities	Array of integer objects
	 * @return sortedCites
	 */
	public static List<City> sortByPopulationDescending(List<City> cities) {
		long startMillisec = System.currentTimeMillis();
		List<City> sortedCities = mergeSortPopulation(cities);
		long endMillisec = System.currentTimeMillis();
		long timeTaken = endMillisec - startMillisec;
		System.out.println("Elapsed Time " + timeTaken + " milliseconds");
		return sortedCities;
	}

	/**
	 * Uses the Insertion Sort method for sorting names in order
	 * to sort the names by ascending order. This method also
	 * calculates the amount of time it takes to do this.
	 * @param cities	Array of integer objects
	 * @return sortedCites
	 */
	public static List<City> sortByNameAscending(List<City> cities) {
		long startMillisec = System.currentTimeMillis();
		List<City> sortedCities = insertionSortNames(cities);
		long endMillisec = System.currentTimeMillis();
		long timeTaken = endMillisec - startMillisec;
		System.out.println("Elapsed Time " + timeTaken + " milliseconds");
		return sortedCities;
	}

	/**
	 * Uses the Merge Sort method for sorting names in order
	 * to sort the population by descending order. This method also
	 * calculates the amount of time it takes to do this.
	 * @param cities	Array of integer objects
	 * @return sortedCites
	 */
	public static List<City> sortByNameDescending(List<City> cities) {
		long startMillisec = System.currentTimeMillis();
		List<City> sortedCities = mergeSortNames(cities);
		long endMillisec = System.currentTimeMillis();
		long timeTaken = endMillisec - startMillisec;
		System.out.println("Elapsed Time " + timeTaken + " milliseconds");
		return sortedCities;
	}

	/**
	 * Takes in a List of City objects and a String as parameters
	 * and returns a List of City objects.It first creates an empty List called result,
	 * then iterates through the passed in List of City objects and checks if the state
	 * of each city is equal to the passed in state.If it is, it adds the City object
	 * to the result list. The result list is then sorted by populationin descending
	 * order using a Comparator.
	 * @param cities
	 * @param state
	 * @return The sorted populations
	 */
	public static List<City> mostPopulousCitiesInState(List<City> cities, String state) {
		List<City> result = new ArrayList<>();
		for (City city : cities) {
			if (city.getState().equals(state)) {
				result.add(city);
			}
		}
		return mergeSortPopulation(result);
	}

	/**
	 * Takes in a List of City objects and a String as parameters and returns
	 * a List of City objects. It first creates an empty List called result,
	 * then iterates through the passed in List of City objects and checks
	 * if the name of each city is equal to the passed in name. If it is,
	 * it adds the City object
	 * to the result list.
	 * @param cities	Array of integer objects to sort
	 * @param name		Name of the state that the user enters
	 * @return Sorted Population of Names
	 */
	public static List<City> allCitiesWithName(List<City> cities, String name) {
		List<City> result = new ArrayList<>();
		for (City city : cities) {
			if (city.getName().equals(name)) {
				result.add(city);
			}
		}
		return mergeSortPopulation(result);
	}

	/**
	 *	Swaps two Integer objects in array arr
	 *	@param cities		array of Integer cities
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	public static void swap(List<City> cities, int x, int y) {
		City temp = cities.get(x);
		cities.set(x, cities.get(y));
		cities.set(x, temp);
	}

	/**
	 * This method asks the user for their choice and then prints
	 * the data based on thier choice and the time it takes to
	 * complete the given action.
	 * @param args
	 */
	public static void main(String[]args){
		printIntroduction();
		printMenu();

		System.out.print("Enter Selection -> ");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();

		if(choice==1){
			sortByPopulationAscending(loadCities("usPopData2017.txt"));
		}else if(choice==2){
			sortByPopulationDescending(loadCities("usPopData2017.txt"));
		}else if(choice==3){
			sortByNameAscending(loadCities("usPopData2017.txt"));
		}else if(choice==4){
			sortByNameDescending(loadCities("usPopData2017.txt"));
		}else if(choice==5){
			System.out.print("Enter state name (ie. Alabama) -> ");
			String askState = scan.next();
			mostPopulousCitiesInState(loadCities("usPopData2017.txt"), askState);
		}else if(choice==6){
			System.out.print("Enter city name -> ");
			String askCity = scan.next();
			mostPopulousCitiesInState(loadCities("usPopData2017.txt"), askCity);
		}else{
			scan.close();
		}

	}

	/**	Prints the introduction to Population */
	public static void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public static void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}