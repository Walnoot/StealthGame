package walnoot.stealth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

public class MusicManager{
	private static final String[] songPaths = {
		"01Sandblaster.mp3",
		"02SisteViator.mp3",
		"03Surveillance.mp3",
		"04Virus.mp3",
		"05MyMindIsGoing.mp3",
		"06Shapeshifter.mp3"
	};
	
	private Music currentSong;
	private int songIndex;
	private FileHandle songFolder;
	private boolean playing = true;
	
	public void init(){
		songFolder = Gdx.files.internal("music/");
		
		songIndex = MathUtils.random(songPaths.length);
		currentSong = Gdx.audio.newMusic(songFolder.child(songPaths[songIndex]));
		
		currentSong.play();
	}
	
	public void update(){
		if(!currentSong.isPlaying() && playing){
			currentSong.dispose();
			
			songIndex++;
			if(songIndex > songPaths.length) songIndex = 0;
			
			currentSong = Gdx.audio.newMusic(songFolder.child(songPaths[songIndex]));
			currentSong.play();
		}
	}
	
	public void pause(){
		playing = false;
		
		currentSong.pause();
	}
	
	public void resume(){
		playing = true;
		
		currentSong.play();
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void dispose(){
		currentSong.dispose();
	}
}
