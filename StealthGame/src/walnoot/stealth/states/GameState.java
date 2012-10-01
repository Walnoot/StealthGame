package walnoot.stealth.states;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;
import walnoot.stealth.StealthGame;
import walnoot.stealth.components.CircleCollideComponent;
import walnoot.stealth.components.ControllerComponent;
import walnoot.stealth.components.FootprintComponent;
import walnoot.stealth.components.GuardComponent;
import walnoot.stealth.components.SpriteComponent;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State{
	private Map map;
	
	public GameState(OrthographicCamera camera){
		super(camera);
		
		map = new Map();
		
		Entity playerEntity = new Entity(1, 1, 0);
		playerEntity.addComponent(new SpriteComponent(playerEntity, StealthGame.TEXTURES[0][1]));
		playerEntity.addComponent(new CircleCollideComponent(playerEntity, map, 0.08f));
		playerEntity.addComponent(new FootprintComponent(playerEntity, StealthGame.TEXTURES[0][0]));
		
		Entity randomEntity = playerEntity.getCopy();
		randomEntity.setxPos(0);
		randomEntity.setyPos(0);
		//randomEntity.addComponent(new SpriteComponent(randomEntity, texture));
		randomEntity.addComponent(new GuardComponent(randomEntity, playerEntity));
		
		playerEntity.addComponent(new ControllerComponent(playerEntity));
		
		map.addEntity(playerEntity);
		map.addEntity(randomEntity);
	}
	
	public void update(){
		map.update();
	}
	
	public void render(SpriteBatch batch){
		map.render(batch);
	}
	
	public void dispose(){
	}
}
