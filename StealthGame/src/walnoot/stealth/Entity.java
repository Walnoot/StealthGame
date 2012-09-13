package walnoot.stealth;

import java.util.ArrayList;

import walnoot.stealth.components.Component;
import walnoot.stealth.components.ComponentIdentifier;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Entity{
	private float xPos, yPos, rotation;
	private ArrayList<Component> components = new ArrayList<Component>();//fuck yeah entity-component design
	
	public Entity(float xPos, float yPos, float rotation){
		this.xPos = xPos;
		this.yPos = yPos;
		this.rotation = rotation;
	}
	
	public void render(SpriteBatch batch){
		for(int i = 0; i < components.size(); i++){
			components.get(i).render(batch);
		}
	}
	
	public void update(Map map){
		for(int i = 0; i < components.size(); i++){
			components.get(i).update(map);
		}
	}
	
	public void addComponent(Component component){
		components.add(component);
	}
	
	public Component getComponent(ComponentIdentifier identifier){
		for(int i = 0; i < components.size(); i++){
			if(components.get(i).getIdentifier() == identifier) return components.get(i);
		}
		
		return null;
	}
	
	public Entity getCopy(){
		Entity result = new Entity(xPos, yPos, rotation);
		
		for(int i = 0; i < components.size(); i++){
			result.addComponent(components.get(i).getCopy(result));
		}
		
		return result;
	}
	
	public void moveForward(float amount){
		xPos -= MathUtils.sinDeg(rotation) * amount;
		yPos += MathUtils.cosDeg(-rotation) * amount;
	}
	
	public void translate(float x, float y){
		xPos += x;
		yPos += y;
	}
	
	public void rotate(float rotation){
		this.rotation += rotation;
		
		if(rotation < MathUtils.PI) rotation += 2 * MathUtils.PI;
		if(rotation > MathUtils.PI) rotation -= 2 * MathUtils.PI;
	}
	
	public float getxPos(){
		return xPos;
	}
	
	public float getyPos(){
		return yPos;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public void setxPos(float xPos){
		this.xPos = xPos;
	}
	
	public void setyPos(float yPos){
		this.yPos = yPos;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
		
		if(rotation < MathUtils.PI) rotation += 2 * MathUtils.PI;
		if(rotation > MathUtils.PI) rotation -= 2 * MathUtils.PI;
	}
}
