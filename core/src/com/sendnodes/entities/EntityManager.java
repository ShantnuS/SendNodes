package com.sendnodes.entities;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sendnodes.Network;
import com.sendnodes.Properties;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class EntityManager {
	private HashMap<String, Texture> images;

	private Network map;
	private ArrayList<Player> players;
	private int[] node_size;
	private int tile_size;
	private int imageSize;
	
	private ShapeRenderer sr;
	
	
    Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
	
	public EntityManager(int map_size) {
		images = new HashMap<String, Texture>();
		images.put("node_blue", new Texture("Nodes/Node_blue.png"));
		images.put("node_grey", new Texture("Nodes/Node_red.png"));

		map = new Network(map_size);
		players = new ArrayList<Player>();
		players.add(new Player(map.getRandomNode()));

		node_size = new int[2];
		node_size[0] = Properties.SCREEN_WIDTH/map_size;
		node_size[1] = Properties.SCREEN_HEIGHT/map_size;
		
		tile_size = images.get("node_blue").getWidth()*Properties.GRAPHICS_SCALE;
		
		sr = new ShapeRenderer();
	}

	public void update() {
		for (Player player : players) {
			player.update();
		}
		// map.update();
	}
	
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("test/test.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("test01");
        textButtonStyle.down = skin.getDrawable("test02");
        textButtonStyle.checked = skin.getDrawable("test02");
        button = new TextButton("", textButtonStyle);
        stage.addActor(button);
        
        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
            }
        });     
	}
	
	public void render(SpriteBatch batch){
		
		batch.end();
		sr.begin(ShapeType.Line);
		
		for (Connection c:map.getConnections()){
			sr.setColor(1, 1, 1, 1); 
			sr.line(getLinePoint(c, 0, true), getLinePoint(c, 0, false), 
					getLinePoint(c, 1, true), getLinePoint(c, 1, false));
			
		}
		
		sr.end();
		batch.begin();
		
		for (int x=0; x<map.getMap().length; x++){
			for (int y=0; y<map.getMap()[x].length; y++){
				Node currentNode = map.getMap()[x][y];


				if (currentNode != null) {
					// if (currentNode)
					if (map.getMap()[x][y].getOwner() != null){
						if (map.getMap()[x][y].getOwner() == players.get(0))
							batch.draw(images.get("node_blue"), x * node_size[0], y * node_size[1], tile_size, tile_size);
					} else {
						batch.draw(images.get("node_grey"), x * node_size[0], y * node_size[1], tile_size, tile_size);
					}
				}
				
			}
		}

		for (Player p : players) {
			batch.draw(images.get("node_blue"), p.getX() * node_size[0], p.getY() * node_size[1], tile_size, tile_size);
		}
		
		stage.draw();

	}
	
	private int getLinePoint(Connection c, int point, boolean x){
		if (x)
			return (c.getConnectedNodes()[point].getX()*node_size[0])+(tile_size/2);
		else
			return (c.getConnectedNodes()[point].getY()*node_size[1])+(tile_size/2);
	}

	public void registerClick(int x, int y) {
		y = Properties.SCREEN_HEIGHT - y;
		x = x + (node_size[0] / 2);
		y = y + (node_size[1] / 2);

		x = (x < 0) ? 0 : x;
		y = (y < 0) ? 0 : y;

		int xNode = (int) Math.floor(x / node_size[0]);
		int yNode = (int) Math.floor(y / node_size[1]);
		if (map.getMap()[xNode][yNode] != null) {
			System.out.println(map.isConnected(players.get(0), players.get(0).getNode(), map.getMap()[xNode][yNode]));
			if (map.getMap()[xNode][yNode].getOwner() != players.get(0)
					&& map.isConnected(players.get(0), players.get(0).getNode(), map.getMap()[xNode][yNode])) {
				Attack attack = new Attack(players.get(0), map.getMap()[xNode][yNode], 1);
				if (!Attack.alreadyExists(players.get(0).getTargets(), attack)) {
					players.get(0).addTarget(attack);
				}
			}
		}
	}

}
