package model.gameObjects;


import mathematics.Vector;
import resources.SpriteSet;
import view.Renderable;
import view.Sprite;

/**
 * A box that can hold a ball inside of it
 * 
 * @author Anthony Klobas
 *
 */

public class Box extends Collidable implements Renderable{
	private int width,height;
	private Line[] lines=new Line[4];
	private SpriteSet spriteSet;
	int x, y;
	/**
	 * creates a box
	 * @param x top left x
	 * @param y top left right
	 * @param width width of box
	 * @param height height of box
	 */
	public Box(double x, double y, int width,int height) {
		this.x=(int)x;
		this.y=(int)y;
		this.width=width;
		this.height=height;
		lines[0]=new Line(x,y,x+width,y);
		lines[1]=new Line(x+width,y,x+width,y+height);
		lines[2]=new Line(x+width,y+height,x,y+height);
		lines[3]=new Line(x,y+height,x,y);
		
	}



	@Override
	public int getBoundingHeight() {
		
		return height;
	}

	@Override
	public int getBoundingWidth() {
		return width;
	}


	@Override
	public Sprite getImage() {
		return spriteSet.currentSprite();
	}

	@Override
	public int getImageX() {
		return x;
	}

	@Override
	public int getImageY() {
		return y;
	}

	@Override
	public boolean canCollideWith(Collidable C) {
		return C instanceof Sphere;
	}

	@Override
	public boolean collide(Collidable C) {
		return collide(C, false);
		
	}

	@Override
	public boolean collide(Collidable C, boolean ignorePosition) {
		for(Line l: lines){
			if(l.collide(C))return true;
		}
		return false;
		
	}

	@Override
	public int getCollisionPrecedence() {
		return 2;
	}



	@Override
	public void advance(double seconds) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}



	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	public boolean stillExists() {
		return true;
	}



	@Override
	public double collideTime(Collidable C) {
		double time = 400000000;
		for(Line l: lines){
			double tempTime=l.collideTime(C);
			if(tempTime<time)time=tempTime;
		}
		return time;
	}



	@Override
	public int getImageWidth() {
		// TODO Auto-generated method stub
		return width;
	}



	@Override
	public int getImageHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	public void addSpriteSet(SpriteSet set) {
		this.spriteSet=set;
		
	}



	@Override
	public GameObject cloneAt(int x, int y) {
		Box ret=new Box(x,y,width,height);
		ret.addSpriteSet(this.spriteSet.clone());
		return ret;
	}



	@Override
	public GameObject cloneAt(Vector v) {
		return cloneAt((int)v.getElement(0),(int)v.getElement(1));
	}



	@Override
	public void translate(int deltaX, int deltaY) {
		translate(new Vector(deltaX, deltaY));
		
	}



	@Override
	public void translate(Vector v) {
		for(Line l:this.lines){
			l.translate(v);
		}
		this.x+=v.getElement(0);
		this.y+=v.getElement(1);
	}

}
