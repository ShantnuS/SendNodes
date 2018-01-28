package com.sendnodes.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.sendnodes.GameController;

import soundengine.SoundManager;

public class UIManager {
	private boolean clickedDown = false;
	private AttackDialogue attackDialogue;
	private TextureAtlas atlas;
	
	private Stage stage;
	
	private boolean showAttackDialogue;
	
	private Texture attackDialogueTexture;
	
	public UIManager(){
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// initialising
		showAttackDialogue = false;
		
		// other
		this.atlas = new TextureAtlas(Gdx.files.internal("UIAtlas/textures.atlas"));
		
		Skin skin = new Skin();
		skin.addRegions(atlas);
		
		TextButtonStyle attackButtonStyle = new TextButtonStyle();
		
		BitmapFont font = new BitmapFont();
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
	
	public void update(){
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && clickedDown == true){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			GameController.getInstance().EM().registerClick(x, y);
			
			GameController.getInstance().getSoundManager().playSound(SoundManager.SOUNDS.CLICK.ordinal());
		}
		clickedDown = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}
	
	public void render(SpriteBatch batch){
		if (showAttackDialogue){
			attackDialogue.render(batch, attackDialogueTexture);
		}
	}
	
	public void showDialogue(int x, int y){
		System.out.println("tre");
		attackDialogue.setPos(-1, -1);
		showAttackDialogue = true;
	}
	
	public void hideDialogue(){
		attackDialogue.setPos(-200, -100);
		showAttackDialogue = false;
	}
}
