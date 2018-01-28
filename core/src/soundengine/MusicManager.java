package soundengine;

import java.util.ArrayList;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Sound;

public class MusicManager implements Runnable {

	public volatile float volume = 1.0f;
	public volatile boolean muted = false;
	
	private volatile int currentTrack = 0;
	private volatile Music currentSound;
	private volatile boolean paused = false;
	
	private volatile boolean locked = false;
	
	private static  String path = "Music/";
	public static enum SOUNDS{
		track1
	}	
	private static String[] soundPaths = {
		path + "track1.mp3"
	};
	
/*	private static String path = "Sounds/";
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
	*/
	public MusicManager()
	{			
		updateTrack();
		new Thread(this).start();
	}
	
	
	public void pause()
	{
		currentSound.pause();			
		paused = true;
	}
	
	public void play()
	{
		currentSound.play();	
		paused = false;
	}
	
	public void togglePause()
	{
		paused = !paused;
		if(paused)
			currentSound.pause();
		else
			currentSound.play();	
	}
	
	public float setVol(float volume)
	{
		currentSound.setVolume(volume);
		return this.volume = volume;
	}
	
	public void mute()
	{
		currentSound.setVolume(0.0f);
		muted = true;
	}
	
	public void unmute()
	{
		currentSound.setVolume(volume);	
		muted = false;
	}
	
	public boolean toggleMute()
	{
		muted = !muted;
		if(muted)
			currentSound.setVolume(0.0f);
		else
			currentSound.setVolume(volume);	

		return muted;
	}
	
	public void nextTrack()
	{
		currentTrack += 1;
		currentTrack %= soundPaths.length;
	}
	public void previousTrack()
	{
		currentTrack -= 1;
		currentTrack %= soundPaths.length;
	}

	public void updateTrack()
	{
		currentSound = Gdx.audio.newMusic(Gdx.files.internal(soundPaths[currentTrack]));
	}
	
	public void playTrack()
	{
		currentSound.play();
	}
	
	public void checkTrack() 
	{
		while(currentSound.isPlaying() || paused)
		{
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {

		while(true){			
			updateTrack();
			playTrack();
			checkTrack();
			nextTrack();
			
		}
	}
	
}
