package soundengine;

import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;

public abstract class AudioManager {

	public float volume = 1.0f;
	public boolean muted = false;
	public ArrayList<Sound> sounds = new ArrayList<Sound>();
	
	public abstract void playSound(int NAME);

	public float setVolume(float volume)
	{
		return this.volume = volume;
	}
	
	public boolean toggleMute()
	{
		return muted = !muted;
	}
}
