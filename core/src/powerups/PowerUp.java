package powerups;

public abstract class PowerUp implements PUInterface {

	String name; 
	String description;
	final int max_cost = 20; // cost
	int curr_cost = 20; // the cost at the moment
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// called each round
	// takes the points applied to power up
	// returns whether or not the power up cost has been paid yet or not
	public boolean payPowerUpCost(int points) {
		curr_cost -= points;
		if (curr_cost <= 0) {
			curr_cost = max_cost;
			return true;
		}
		return false;
	}
	
	// cost of this power up at the moment
	public int getCurrentCost() {
		return curr_cost;
	}

	public PowerUp(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.start();
	} 
	
}
