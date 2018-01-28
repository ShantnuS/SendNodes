package com.sendnodes.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GridContainer {
	private ArrayList<Container> containers;
	
	public GridContainer(){
		containers = new ArrayList<Container>();
	}
	
	public void addContainer(Container c){
		containers.add(c);
	}
	
	public void resizeActors(boolean landscape){
		if(containers.size() > 0)
		{
			int sizeX = 0;
			int sizeY = 0;
			
			for (Container c:containers){
				sizeX += c.getSizeX();
				sizeY += c.getSizeY();
			}
			
			// Whatever direction we are arranging in, flatten in the other
			if (landscape)
				sizeY = containers.get(0).getSizeY();
			else
				sizeX = containers.get(0).getSizeX();
			
			int intervalX = sizeX / containers.size();
			int intervalY = sizeY / containers.size();
			
			int offsetX = -(sizeX/2);
			int offsetY = -(sizeY/2);
			
			for (int i=0; i<containers.size(); i++){
				if (landscape){
					containers.get(i).move(offsetX + (intervalX*i), 0, landscape);
					containers.get(i).resizeActors(landscape);
				} else {
					containers.get(i).move(0, offsetY + (intervalY*i), landscape);
					containers.get(i).resizeActors(landscape);
				}
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for (Container c:containers){

			c.render(batch);
		}
	}
}
