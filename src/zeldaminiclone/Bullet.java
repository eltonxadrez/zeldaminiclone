package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle implements Entity, Concreto {

	private static final long serialVersionUID = 1L;
	
//	public int x;
//	public int y;
	public Direcao direcao;
	private Game game;
	
	public int spd = 1;
	
	//mudar
	public Bullet(int x, int y, Direcao direcao, Game game) {
		super(x, y, 5, 5);
		this.direcao = direcao;
		this.game = game;
		game.entities.add(this);
		game.objetosConcretos.add(this);
		
	}
	
	public void tick() {
		
		if(direcao == Direcao.LEFT) {
			x -= spd;
		}
		else if(direcao == Direcao.RIGHT) {
			x += spd;
		}
		else if(direcao == Direcao.UP) {
			y -= spd;
		}
		else {
			y += spd;
		}
		if (!World.isFree(x, y)){
//			this.game.entities.remove(this);
			this.game.objetosConcretos.remove(this);
//			this.
		}

	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
