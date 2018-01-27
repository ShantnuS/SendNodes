package powerups;

import com.sendnodes.nodes.Node;

public abstract class NodePowerUp extends PowerUp{

	Node node;
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public NodePowerUp(String name, String description, Node node) {
		super(name, description);
		this.node = node;
	}

}
