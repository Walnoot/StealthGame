package walnoot.stealth;

import walnoot.stealth.components.CollideComponent;
import walnoot.stealth.components.ControllerComponent;
import walnoot.stealth.components.GuardComponent;
import walnoot.stealth.components.SpriteComponent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StealthGame implements ApplicationListener{
	private static final float FONT_SCALE = 1 / 64f;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private BitmapFont font;
	
	private Map map;
	
	public void create(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(2f, 2f * h / w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("guy.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Entity playerEntity = new Entity(1, 1, 0);
		playerEntity.addComponent(new SpriteComponent(playerEntity, texture));
		playerEntity.addComponent(new ControllerComponent(playerEntity));
		
		Entity randomEntity = new Entity(0, 0, 0);
		randomEntity.addComponent(new SpriteComponent(randomEntity, texture));
		//randomEntity.addComponent(new GuardWalkComponent(randomEntity));
		randomEntity.addComponent(new GuardComponent(randomEntity, playerEntity));
		
		camera.zoom = 4f;
		
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), false);
		font.setColor(1, 0, 0, 1);
		
		font.setUseIntegerPositions(false);
		font.setScale(FONT_SCALE);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		map = new Map();
		map.addEntity(playerEntity);
		map.addEntity(randomEntity);
		
		playerEntity.addComponent(new CollideComponent(playerEntity, map, 0.08f));
		randomEntity.addComponent(new CollideComponent(randomEntity, map, 0.08f));
	}
	
	public void dispose(){
		batch.dispose();
		texture.dispose();
		font.dispose();
	}
	
	public void render(){
		map.update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.apply(Gdx.gl10);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		map.render(batch);
		
		font.draw(batch, "FONT!", 0, 0);
		
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
