package walnoot.stealth.states;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State{
	protected final Camera camera;
	
	public State(OrthographicCamera camera){
		this.camera = camera;
	}
	
	public abstract void update();
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void dispose();
	
	public Camera getCamera(){
		return camera;
	}
}
