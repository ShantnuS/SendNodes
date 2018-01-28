package screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sendnodes.GameController;
import com.sendnodes.Properties;
import com.sendnodes.ui.ButtonMaker;
import com.sendnodes.ui.ButtonContainer;

public class MenuScreen {	
    Stage stage;   
    ButtonContainer container;
    private ArrayList<TextButton> buttons;
    Texture background;
    Sprite backgroundSprite;
	
	public MenuScreen(Stage stage) {
		this.stage = stage;
		this.buttons = new ArrayList<TextButton>();
	}

	public void create() {
		container = new ButtonContainer(Properties.SCREEN_WIDTH / 2 - 100, Properties.SCREEN_HEIGHT * 3 / 4 -200, 200,
				Properties.SCREEN_HEIGHT / 2, 200, 100, stage);

		TextButton tb = ButtonMaker.getBasicButton("Play");
		tb.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (GameController.getInstance().screenNumber == 0) {
					GameController.getInstance().setScreenNumber(1);
					
					buttons.get(0).setVisible(false);
					buttons.get(1).setVisible(false);
					buttons.get(2).setVisible(false);
				}
			}
		});
		container.addButton(tb);
		buttons.add(tb);

		tb = ButtonMaker.getBasicButton("Settings");
		tb.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (GameController.getInstance().screenNumber == 0) {
					// GameController.getInstance().setScreenNumber(1);
				}
			}
		});
		container.addButton(tb);
		buttons.add(tb);

		tb = ButtonMaker.getBasicButton("Exit");
		tb.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (GameController.getInstance().screenNumber == 0) {
					System.exit(0);
				}
			}
		});
		container.addButton(tb);
		buttons.add(tb);

		container.resizeActors();

		/*
		 * uiContainer = new
		 * UIContainer(Properties.SCREEN_WIDTH/2-100,Properties.SCREEN_HEIGHT*
		 * 3/4);
		 * 
		 * container = new
		 * ButtonContainer(0,Properties.SCREEN_HEIGHT,200,Properties.
		 * SCREEN_HEIGHT/2,200,100);
		 * 
		 * TextButton tb = ButtonMaker.getBasicButton("Play");
		 * tb.addListener(new ChangeListener() {
		 * 
		 * @Override public void changed (ChangeEvent event, Actor actor) {
		 * GameController.getInstance().setScreenNumber(1); } });
		 * container.addButton(tb);
		 * 
		 * tb = ButtonMaker.getBasicButton("Settings"); tb.addListener(new
		 * ChangeListener() {
		 * 
		 * @Override public void changed (ChangeEvent event, Actor actor) {
		 * //GameController.getInstance().setScreenNumber(1); } });
		 * container.addButton(tb);
		 * 
		 * tb = ButtonMaker.getBasicButton("Exit"); tb.addListener(new
		 * ChangeListener() {
		 * 
		 * @Override public void changed (ChangeEvent event, Actor actor) {
		 * System.exit(0); } }); container.addButton(tb);
		 * //container.resizeActors();
		 * 
		 * incrementor = new Incrementor(0,Properties.SCREEN_HEIGHT/2,200,100,
		 * inc);
		 * 
		 * uiContainer.addContainer(incrementor);
		 * uiContainer.addContainer(container);
		 * 
		 * uiContainer.resizeActors();
		 */
	}
	
	public void show(){
		buttons.get(0).setVisible(true);
		buttons.get(1).setVisible(true);
		buttons.get(2).setVisible(true);
	}

	public void render(SpriteBatch batch) {
		//background
		background = new Texture(Gdx.files.internal("Titlescreen/titlescreen.png"));
        backgroundSprite =new Sprite(background);
		backgroundSprite.draw(batch);
		
		//text
		background = new Texture(Gdx.files.internal("Titlescreen/text.png"));
        backgroundSprite =new Sprite(background);
        backgroundSprite.setPosition(Properties.SCREEN_WIDTH/4-50, Properties.SCREEN_HEIGHT*3/4 -100);
		backgroundSprite.draw(batch);
		
		batch.end();
		batch.begin();
		stage.draw();
		container.render(batch);
		//batch.draw(background,1920,1080);
	}

}
