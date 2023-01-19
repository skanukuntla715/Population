/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Shrutik Kanukuntla
 *	@since January 16, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String designation;
	private int population;
	
	// constructor
	public City(String name, String state, String designation, int population) {
		this.name = name;
		this.state = state;
		this.designation = designation;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other) {
		if (this.population != other.population) {
			return this.population - other.population;
		} else if (!this.state.equals(other.state)) {
			return this.state.compareTo(other.state);
		} else {
			return this.name.compareTo(other.name);
		}
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof City)) {
			return false;
		}
		City c = (City) other;
		return this.name.equals(c.name) && this.state.equals(c.state);
	}
	
	/**	Accessor methods */
	public String getName(){
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}

	public String getState(){
		return this.state;
	}

	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}