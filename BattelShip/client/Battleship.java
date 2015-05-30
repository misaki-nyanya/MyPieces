package client;

public class Battleship {

	public static int DEAD=1;
	public static int ALIVE=0;
	
	public Coordinate position;
	public int hitpoints=3;
	public int state=0;
	public Battleship(int x,int y){
		position=new Coordinate();
		position.x=x;
		position.y=y;
	}
	public static void main(String[] args) {

	}

}
