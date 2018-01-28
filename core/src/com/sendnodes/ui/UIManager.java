package com.sendnodes.ui;

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

import soundengine.SoundManager;

public class UIManager {
	private boolean clickedDownLeft = false;
	private boolean clickedDownRight = false;
	private AttackDialogue attackDialogue;
	private TextureAtlas atlas;
	
	private Stage stage;
	
	private boolean showAttackDialogue;
	
	private Texture attackDialogueTexture;
	
	private Container dialogueContainer, dialogueSliderContainer;
	
	private GridContainer dialogueGridContainer;
	
	private Label loadLabel;
	
	public UIManager(){
		initialiseAttackDialogue();
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
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
        stage.addActor(attackButton);
		
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
	
	public void initialiseAttackDialogue(){
		dialogueGridContainer = new GridContainer();
		

		dialogueSliderContainer = new Container(-400,-400,200,100);
		TextButton s1 = ButtonMaker.getBasicButton("Up");
        s1.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
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
		
		
		dialogueContainer = new Container(-400,-400,200,100);
	     
        TextButton tb = ButtonMaker.getBasicButton("Play");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
            }
        });   
        dialogueContainer.addButton(tb);        
        
        tb = ButtonMaker.getBasicButton("Settings");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	//GameController.getInstance().setScreenNumber(1);
            }
        });   
        dialogueContainer.addButton(tb);
        
        tb = ButtonMaker.getBasicButton("Exit");
        tb.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	System.exit(0);
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
			
			GameController.getInstance().EM().registerRightClick(x, y);
			
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
		
		stage.draw();
	}
	
	public void showDialogue(int x, int y){
		System.out.println("tre");
		dialogueContainer.move(x, y-100, true);
		dialogueSliderContainer.move(x, y-200, true);
		
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
