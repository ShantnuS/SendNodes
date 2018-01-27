package powerups.node;

import com.sendnodes.nodes.Node;

import powerups.NodePowerUp;

public class NPUHealthUp extends NodePowerUp{

	public NPUHealthUp(String name, String description, Node node) {
		super(name, description, node);
	}

	public void start() {
		//Add health to the node 
		int oldHp = this.getNode().getHp();
		int HpToAdd = 50;
		this.getNode().setHp(oldHp+HpToAdd);
	}
	
	public void finish() {
		
	}
	
}
