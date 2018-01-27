package powerups;

import com.sendnodes.entities.Player;

public abstract class PlayerPowerUp extends PowerUp {

	Player player;
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerPowerUp(String name, String description, Player player) {
		super(name, description);
		this.player = player;
	}

}
