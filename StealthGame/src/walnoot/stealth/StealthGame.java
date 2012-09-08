package walnoot.stealth;

import walnoot.stealth.components.ControllerComponent;
import walnoot.stealth.components.GuardComponent;
import walnoot.stealth.components.GuardWalkComponent;
import walnoot.stealth.components.SpriteComponent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StealthGame implements ApplicationListener{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Entity randomEntity;
	private Entity playerEntity;
	
	public void create(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(2f, 2f * h / w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("guy.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerEntity = new Entity(1, 1, 0);
		playerEntity.addComponent(new SpriteComponent(playerEntity, texture));
		playerEntity.addComponent(new ControllerComponent(playerEntity));
		
		randomEntity = new Entity(0, 0, 0);
		randomEntity.addComponent(new SpriteComponent(randomEntity, texture));
		//randomEntity.addComponent(new GuardWalkComponent(randomEntity));
		randomEntity.addComponent(new GuardComponent(randomEntity, playerEntity));
		
		camera.zoom = 3f;
	}
	
	public void dispose(){
		batch.dispose();
		texture.dispose();
	}
	
	public void render(){
		randomEntity.update();
		playerEntity.update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.apply(Gdx.gl10);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		randomEntity.render(batch);
		playerEntity.render(batch);
		batch.end();
	}
	
	public void resize(int width, int height){
		camera.viewportWidth = 2f;
		camera.viewportHeight = 2f * height / width;
	}
	
	public void pause(){
	}
	
	public void resume(){
	}
}
