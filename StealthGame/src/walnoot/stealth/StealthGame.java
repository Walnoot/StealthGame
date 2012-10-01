package walnoot.stealth;

import walnoot.stealth.states.GameState;
import walnoot.stealth.states.State;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StealthGame implements ApplicationListener{
	public static final float UPDATES_PER_SECOND = 60, SECONDS_PER_UPDATE = 1 / UPDATES_PER_SECOND;
	public static final float FONT_SCALE = 1 / 64f;
	public static BitmapFont FONT;
	public static TextureRegion[][] TEXTURES;
	public static TweenManager TWEEN_MANAGER;
	public static final MusicManager MUSIC_MANAGER = new MusicManager();
	
	private static State state;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float updateTimer;
	
	public void create(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(2f, 2f * h / w);
		camera.zoom = 4f;
		
		TWEEN_MANAGER = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		batch = new SpriteBatch();
		
		FONT = new BitmapFont(Gdx.files.internal("font/font.fnt"), false);
		FONT.setColor(1, 0, 0, 1);
		
		FONT.setUseIntegerPositions(false);
		FONT.setScale(FONT_SCALE);
		FONT.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		Texture texture = new Texture("images.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TEXTURES = new TextureRegion(texture).split(256, 256);
		
		MUSIC_MANAGER.init();
		
		state = new GameState(camera);
	}
	
	public void dispose(){
		batch.dispose();
		FONT.dispose();
		TEXTURES[0][0].getTexture().dispose();
		MUSIC_MANAGER.dispose();
	}
	
	public void render(){
		updateTimer += Gdx.graphics.getDeltaTime();
		while(updateTimer > SECONDS_PER_UPDATE){
			updateTimer -= SECONDS_PER_UPDATE;
			
			update();
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//camera.apply(Gdx.gl10);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		state.render(batch);
		
		FONT.setScale(FONT_SCALE);
		FONT.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), -camera.viewportWidth * camera.zoom / 2f, camera.viewportHeight * camera.zoom / 2f);
		
		batch.end();
	}
	
	public void update(){
		state.update();
		TWEEN_MANAGER.update(SECONDS_PER_UPDATE);
		MUSIC_MANAGER.update();
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
