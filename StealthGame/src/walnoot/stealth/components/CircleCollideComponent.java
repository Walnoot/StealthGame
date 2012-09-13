package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;

public class CircleCollideComponent extends Component{
	private static final float BOUNCYNESS = 1 / 16f;
	private final float radius;
	private final Map map;
	
	public CircleCollideComponent(Entity owner, Map map, float radius){
		super(owner);
		this.map = map;
		this.radius = radius;
		
		map.addCollidable(this);
	}
	
	public void update(Map map){
		float x = owner.getxPos();
		float y = owner.getyPos();
		
		for(int j = 0; j < map.getCollidables().size(); j++){
			CircleCollideComponent otherCollidable = map.getCollidables().get(j);
			
			if(otherCollidable == this) continue;
			
			float otherX = otherCollidable.owner.getxPos();
			float otherY = otherCollidable.owner.getyPos();
			
			float distanceSquared = (x - otherX) * (x - otherX) + (y - otherY) * (y - otherY);
			if(distanceSquared < radius * radius + otherCollidable.radius * otherCollidable.radius){
				float centerX = (x + otherX) / 2;
				float centerY = (y + otherY) / 2;
				
				owner.translate((x - centerX) * BOUNCYNESS, (y - centerY) * BOUNCYNESS);
				otherCollidable.owner.translate((otherX - centerX) * BOUNCYNESS, (otherY - centerY) * BOUNCYNESS);
			}
		}
	}
	
	public Component getCopy(Entity owner){
		return new CircleCollideComponent(owner, map, radius);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.COLLIDE_COMPONENT;
	}
}
