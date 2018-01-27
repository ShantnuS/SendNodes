package soundengine;



import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Queue;

public class SoundManager extends AudioManager{
	
	private static String path = "Sounds/";
	public static enum SOUNDS{
		BACK,
		EXCHANGE,
		HOVER,
		SCROLL,
		START_GAME,
		CLICK,
	}	
	private static String[] soundPaths = {
		path + "back.mp3",
		path + "exchange.mp3",
		path + "hover.mp3",
		path + "scroll.mp3",
		path + "startgame.mp3",
		path + "click.mp3"
	};
	
	public SoundManager()
	{
		for(String s : soundPaths)
			sounds.add(Gdx.audio.newSound(Gdx.files.internal(s)));		
	}

	@Override
	public void playSound(final int NAME)
	{
		if(!muted)
		{
			new Thread(new Runnable() {

				@Override
				public void run() {
					sounds.get(NAME).play(volume);					
				}
				
			}).start();			
		}
	}
	
	
	
}
