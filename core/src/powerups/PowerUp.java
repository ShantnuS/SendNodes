package powerups;

public abstract class PowerUp implements PUInterface {

	String name; 
	String description;
	
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

	public PowerUp(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.start();
	} 
	
}
