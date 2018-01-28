package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.sendnodes.GameController;
import com.sendnodes.Network;
import com.sendnodes.Properties;
import com.sendnodes.ai.PlayerAI;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;
import com.sendnodes.ui.AttackDialogue;

public class EntityManager {
	private HashMap<String, Texture> images;

	private Network map;
	private ArrayList<Player> players;
	private int[] node_size;
	private int tile_size;
	private int imageSize;
	private double timeSinceAttack = 0;

	private ArrayList<Label> labels;
	private ShapeRenderer sr;
	
    Stage stage;
    
    private AttackDialogue attackDialogue;
    
    
	public EntityManager(int map_size) {
    	attackDialogue = null;
		node_size = new int[2];
		node_size[0] = Properties.SCREEN_WIDTH/map_size;
		node_size[1] = Properties.SCREEN_HEIGHT/map_size;
		
		images = new HashMap<String, Texture>();
		images.put("node_blue", new Texture("Nodes/Node_blue.png"));
		images.put("node_red", new Texture("Nodes/Node_red.png"));
		images.put("node_green", new Texture("Nodes/Node_green.png"));
		images.put("node_grey", new Texture("Nodes/Node_grey.png"));
		
		images.put("node_player_blue", new Texture("Nodes/King_node_blue.png"));
		images.put("node_player_red", new Texture("Nodes/King_node_red.png"));
		images.put("node_player_green", new Texture("Nodes/King_node_green.png"));

		map = new Network(map_size);
		addLabels(map);
		
		ArrayList<Node> allNodes = map.getNodes();
		Collections.shuffle(allNodes);
		
		
		players = new ArrayList<Player>();
		players.add(new Player(allNodes.get(0), "node_player_blue", "node_blue"));
		players.get(0).getNode().setupRoot(players.get(0));
		
		players.add(new Player(allNodes.get(1), "node_player_red", "node_red"));
		players.get(1).getNode().setupRoot(players.get(1));
		
		players.add(new Player(allNodes.get(2), "node_player_green", "node_green"));
		players.get(2).getNode().setupRoot(players.get(2));
		
		tile_size = images.get("node_blue").getWidth() * Properties.GRAPHICS_SCALE;

		sr = new ShapeRenderer();
	}

	private void addLabels(Network map) {
		stage = new Stage();
		labels = new ArrayList<Label>();

		LabelStyle textStyle;
		BitmapFont font = new BitmapFont();

		textStyle = new LabelStyle();
		textStyle.font = font;

		for (Node n : map.getMapNodesList()) {
			Label testLabel;
			testLabel = new Label("" + n.getHp(), textStyle);
			testLabel.setBounds(n.getXPos() * node_size[0], n.getYPos() * node_size[1], node_size[0], node_size[1]);
			testLabel.setFontScale(1f, 1f);
			labels.add(testLabel);
		}
	}

	public void update() {
		PlayerAI.AIMove(players.get(1));
		PlayerAI.AIMove(players.get(2));
		timeSinceAttack += Gdx.graphics.getDeltaTime();
		if (timeSinceAttack >= 0.3) {
			for (Player player : players) {
				player.update();
			}
			timeSinceAttack = 0;
		}
		// map.update();
	}

	public void create() {

		// LABEL STUFF
	}

	public void render(SpriteBatch batch) {
		for (int i = 0; i < labels.size(); i++)
			labels.get(i).setText("" + map.getMapNodesList().get(i).getHp());

		batch.end();
		sr.begin(ShapeType.Line);

		for (Connection c : map.getConnections()) {
			//Set line colour depending on bandwidth
			if(c.getBandwidth() <= 10) {
				sr.setColor(Color.RED);
			}
			if(c.getBandwidth() > 10 && c.getBandwidth() <=14) {
				sr.setColor(Color.YELLOW);
			}
			if(c.getBandwidth() >= 15) {
				sr.setColor(Color.GREEN);
			}
			
		
/*			float value = c.getBandwidth();
			sr.setColor(new Color(50.0f,50.0f,value,1));*/
			
			//sr.setColor(1, 1, 1, 1);
			sr.line(getLinePoint(c, 0, true), getLinePoint(c, 0, false), getLinePoint(c, 1, true),
					getLinePoint(c, 1, false));

		}

		sr.end();
		batch.begin();

		for (int x = 0; x < map.getMap().length; x++) {
			for (int y = 0; y < map.getMap()[x].length; y++) {
				Node currentNode = map.getMap()[x][y];

				if (currentNode != null) {
					// if (currentNode)
					if (map.getMap()[x][y].getOwner() != null) {
						batch.draw(images.get(map.getMap()[x][y].getOwner().getNodeTextureName()), x * node_size[0], y * node_size[1], tile_size,
								tile_size);
					} else {
						batch.draw(images.get("node_grey"), x * node_size[0], y * node_size[1], tile_size, tile_size);
					}
				}

			}
		}

		for (Player p : players) {
			System.out.println(p.getPlayerTextureName());
			batch.draw(images.get(p.getPlayerTextureName()), (p.getX() * node_size[0])-(tile_size/2), (p.getY() * node_size[1])-(tile_size/2), tile_size*2, tile_size*2);
		}
		
		
		batch.flush();
		
		for (Label l : labels)
			stage.addActor(l);

		stage.draw();
	}

	private int getLinePoint(Connection c, int point, boolean x) {
		if (x)
			return (c.getConnectedNodes()[point].getXPos() * node_size[0]) + (tile_size / 2);
		else
			return (c.getConnectedNodes()[point].getYPos() * node_size[1]) + (tile_size / 2);
	}

	public void registerLeftClick(int x, int y) {
		y = Properties.SCREEN_HEIGHT - y;
		x = x + (node_size[0] / 2);
		y = y + (node_size[1] / 2);

		x = (x < 0) ? 0 : x;
		y = (y < 0) ? 0 : y;

		int xNode = (int) Math.floor(x / node_size[0]);
		int yNode = (int) Math.floor(y / node_size[1]);
		
		if(xNode>map.getMap().length-1){
			xNode = map.getMap().length-1;
		}
		if(yNode>map.getMap()[0].length-1){
			yNode = map.getMap()[0].length-1;
		}
		
		boolean clickedOnATarget = false;
		if (map.getMap()[xNode][yNode] != null) {
			if (map.getMap()[xNode][yNode].getOwner() != players.get(0) 
					&& map.isConnected(players.get(0), players.get(0).getNode(), map.getMap()[xNode][yNode])) {
				

				GameController.getInstance().UI().showDialogue(map.getMap()[xNode][yNode].getXPos() * node_size[0], map.getMap()[xNode][yNode].getYPos() * node_size[1],
						map.getMap()[xNode][yNode], node_size[0], node_size[1], players.get(0));
				
				//GameController.getInstance().UI().showDialogue(map.getMap()[xNode][yNode].getXPos() * node_size[0], map.getMap()[xNode][yNode].getYPos() * node_size[1]);
				clickedOnATarget = true;
				//GameController.getInstance().UI().initialiseSideDialogue();
			}
		}
		
		if (!clickedOnATarget)
			GameController.getInstance().UI().hideDialogue();
	}
	
	public void registerRightClick(int x, int y) {
		y = Properties.SCREEN_HEIGHT - y;
		x = x + (node_size[0] / 2);
		y = y + (node_size[1] / 2);

		x = (x < 0) ? 0 : x;
		y = (y < 0) ? 0 : y;

		int xNode = (int) Math.floor(x / node_size[0]);
		int yNode = (int) Math.floor(y / node_size[1]);
		
		if(xNode>map.getMap().length-1){
			xNode = map.getMap().length-1;
		}
		if(yNode>map.getMap()[0].length-1){
			yNode = map.getMap()[0].length-1;
		}
		System.out.print(xNode + " " + yNode);
		if (map.getMap()[xNode][yNode] != null) {
			map.getMap()[xNode][yNode].setOwner(players.get(1));
		}
	}

	public int getPlayerLoad(int player){
		return players.get(player).getCPU();
	}
	
	
	public Player getPlayer1()
	{
		return players.get(0);
	}
}
