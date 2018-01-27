package soundengine;

import com.badlogic.gdx.Gdx;

public class MusicManager extends AudioManager {

	
	private static  String path = "Music/";
	public static enum SOUNDS{
		track1,
		track2,
	}	
	private static String[] soundPaths = {
		path + "track1.mp3",
		path + "track2.mp3",
	};
	
	public MusicManager()
	{
		for(String s : soundPaths)
			sounds.add(Gdx.audio.newSound(Gdx.files.internal(s)));		
	}
	
	@Override
	public void playSound(final int NAME) {
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
