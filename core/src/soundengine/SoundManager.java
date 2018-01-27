package soundengine;



import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Queue;

public class SoundManager {
	
	public float volume = 1.0f;
	public boolean muted = false;
	
	public static enum SOUNDS{
		BACK,
		EXCHANGE,
		HOVER,
		SCROLL,
		START_GAME,
		CLICK,
	}
	
	private static String[] soundPaths = {
		"back.mp3",
		"exchange.mp3",
		"hover.mp3",
		"scroll.mp3",
		"startgame.mp3",
		"click.mp3"
	};
	
	public ArrayList<Sound> sounds = new ArrayList<Sound>();
	
	public SoundManager()
	{
		for(String s : soundPaths)
			sounds.add(Gdx.audio.newSound(Gdx.files.internal(s)));		
	}
	
	public void playSound(int NAME)
	{
		if(!muted)
			sounds.get(NAME).play(volume);
	}
	
	public float setVolume(float volume)
	{
		return this.volume = volume;
	}
	
	public boolean toggleMute()
	{
		return muted = !muted;
	}
	
	private void test()
	{
		for(Sound s : sounds)
		 s.play();
	}
}
