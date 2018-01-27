package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sendnodes.GameController;

public class MenuScreen {	
    Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
	
	public MenuScreen() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}
	
	public void create() {
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("test/test.atlas"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("test01");
        textButtonStyle.down = skin.getDrawable("test03");
        textButtonStyle.checked = skin.getDrawable("test02");
        button = new TextButton("START", textButtonStyle);
        button.setPosition(50, 70);
        stage.addActor(button);
        
        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	GameController.getInstance().setScreenNumber(1);
                System.out.println("Button Pressed");
            }
        });     
	}

	
	public void render(SpriteBatch batch) {
		batch.end();
		batch.begin();
		stage.draw();
	}
	
}
