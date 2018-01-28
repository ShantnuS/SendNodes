package com.sendnodes.ui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.sendnodes.GameController;
import com.sendnodes.Properties;
import com.sendnodes.entities.Attack;
import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Node;

import soundengine.SoundManager;

public class UIManager {
	private boolean clickedDownLeft = false;
	private boolean clickedDownRight = false;
	private AttackDialogue attackDialogue;
	private TextureAtlas atlas;
	
	private Stage stage;
	
	private boolean showAttackDialogue;
	
	private Texture attackDialogueTexture;
	private Texture containerTexture;
	
	private Container dialogueContainer, dialogueSliderContainer;
	private Container container1, container2;
	
	private GridContainer dialogueGridContainer,targetList;
	private GridContainer gridContainer;
	
	Container tc;
	
	private Label loadLabel;
	
	public UIManager(Stage stage){
		this.stage = stage;
		
		initialiseAttackDialogue();
		initialiseSideDialogue();
		
		initTargetList();
		// initialising
		showAttackDialogue = false;
		
		// labels
		LabelStyle textStyle;
		BitmapFont font = new BitmapFont();

		textStyle = new LabelStyle();
		textStyle.font = font;
		
		loadLabel = new Label("", textStyle);
		loadLabel.setBounds(Properties.SCREEN_WIDTH-100, Properties.SCREEN_HEIGHT-100, 50, 50);
		loadLabel.setFontScale(1f, 1f);
		
		stage.addActor(loadLabel);
		
		// other
		this.atlas = new TextureAtlas(Gdx.files.internal("UIAtlas/textures.atlas"));
		
		Skin skin = new Skin();
		skin.addRegions(atlas);
		
		TextButtonStyle attackButtonStyle = new TextButtonStyle();
		
		attackButtonStyle.font = font;
		
		attackButtonStyle.up = skin.getDrawable("UI_menu_button_up");
		attackButtonStyle.down = skin.getDrawable("UI_menu_button_down");
        
        //textButtonStyle.checked = skin.getDrawable("test02");
        TextButton attackButton = new TextButton("", attackButtonStyle);
        attackButton.setPosition(50, 70);
        //stage.addActor(attackButton);
		
        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
                System.out.println("Attack");
            }
        }); 
        
		this.attackDialogue = new AttackDialogue(-100, -100);
		
		attackDialogueTexture = new Texture("UI/UI_node_menu.png");
	}
	
	public void initTargetList()
	{
		targetList = new GridContainer();
	}
	
	public void initialiseSideDialogue(){
		gridContainer = new GridContainer();

		container2 = new Container(Properties.SCREEN_WIDTH-200,Properties.SCREEN_HEIGHT-400, 100, 100, stage);
		TextButton s1 = ButtonMaker.getTexturedButton("", "PU_node_defence", "PU_node_defence");
        s1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });     
        
        TextButton s2 = ButtonMaker.getBasicButton("Show");
        s2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });     
        
        TextButton s3 = ButtonMaker.getBasicButton("X");
        s2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });   
        container2.addButton(s3); 
        container2.addButton(s2); 
        container2.addButton(s1); 
        container2.resizeActors(true);
		
        container1 = new Container(600,200,50,50, stage);
	     
		// Clicking the attack triggers an expenditure of resources on the target node
        TextButton tb = ButtonMaker.getTexturedButton("", "PU_node_attack", "PU_node_attack");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });   
        container1.addButton(tb);        
        
        tb = ButtonMaker.getTexturedButton("", "PU_node_defence", "PU_node_defence");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });   
        container1.addButton(tb);
        
        tb = ButtonMaker.getTexturedButton("", "PU_node_interference", "PU_node_interference");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {}
        });   
        container1.addButton(tb);
        container1.resizeActors(true);

        gridContainer.addContainer(container1);
        gridContainer.addContainer(container2);
	}
	
	public void initialiseAttackDialogue(){
		dialogueGridContainer = new GridContainer();
		
		dialogueSliderContainer = new Container(-400,-400,200,100, stage);
		TextButton s1 = ButtonMaker.getBasicButton("Up");
        s1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	ArrayList<Attack> attacks = attackDialogue.getPlayer().getTargets();
            	
            	// for each attack, see if the target node is the attack's target, if so, increment that attack's damage
            	for (Attack a:attacks)
            		if (a.getTarget() == attackDialogue.getQuery())
            			a.incrementDamage();
            }
        });   
        dialogueSliderContainer.addButton(s1);   
        
        TextButton s2 = ButtonMaker.getBasicButton("Down");
        s2.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
            }
        });   
        dialogueSliderContainer.addButton(s2);   
		
		dialogueContainer = new Container(-200,-200,50,50, stage);
	     
		// Clicking the attack triggers an expenditure of resources on the target node
        TextButton tb = ButtonMaker.getTexturedButton("", "PU_node_attack", "PU_node_attack");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
				Attack attack = new Attack(attackDialogue.getPlayer(), attackDialogue.getQuery(), -1);
				
				if (!Attack.alreadyExists(attackDialogue.getPlayer().getTargets(), attack))
				{
					attackDialogue.getPlayer().addTarget(attack);
					GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.EXCHANGE.ordinal());
				}
            }
        });   
        dialogueContainer.addButton(tb);        
        
        tb = ButtonMaker.getTexturedButton("", "PU_node_defence", "PU_node_defence");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	
            }
        });   
        dialogueContainer.addButton(tb);
        
        tb = ButtonMaker.getTexturedButton("", "PU_node_interference", "PU_node_interference");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	
            }
        });   
        dialogueContainer.addButton(tb);
        dialogueContainer.resizeActors(true);
        
		dialogueGridContainer.addContainer(dialogueSliderContainer);
		dialogueGridContainer.addContainer(dialogueContainer);
	}
	
	public void update(){
		loadLabel.setText(""+GameController.getInstance().EM().getPlayerLoad(0));
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickedDownLeft == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			GameController.getInstance().EM().registerLeftClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDownLeft = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && clickedDownRight == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			//GameController.getInstance().EM().registerRightClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDownRight = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
	}
	
	public void render(SpriteBatch batch){
		if (showAttackDialogue){
			//dialogueContainer.render(batch);
			dialogueGridContainer.render(batch);
			//attackDialogue.render(batch, attackDialogueTexture);
		}
		gridContainer.render(batch);
		
		//container2.render(batch);
		targetList.render(batch);
		stage.draw();
	}
	
	public void showDialogue(int x, int y, Node n, int nodeWidth, int nodeHeight, Player p){
		
		if (x<100)
			x = 100;
		if (y<250)
			y = 250;
		
		System.out.println("tre");
		dialogueContainer.move(x, y-100, true);
		dialogueSliderContainer.move(x, y-200, true);
		
		attackDialogue.setQuery(n, nodeWidth, nodeHeight, p);
		
		//attackDialogue.setPos(-1, -1);
		showAttackDialogue = true;
	}
	
	public void hideDialogue(){
		dialogueContainer.move(-400, -400, true);
		dialogueSliderContainer.move(-400, -400, true);
		//attackDialogue.setPos(-200, -100);
		showAttackDialogue = false;
	}
}
