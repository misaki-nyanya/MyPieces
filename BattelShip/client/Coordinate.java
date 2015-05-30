package client;

import java.io.Serializable;

public class Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public Coordinate(){}
	public Coordinate(int p_x,int p_y){
		this.x=p_x;
		this.y=p_y;
	}
	public static void main(String[] args) {

	}

}
