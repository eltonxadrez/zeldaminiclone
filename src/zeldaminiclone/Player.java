package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle implements Entity, Concreto{
	
	//ignore
	private static final long serialVersionUID = 1L;
	
	//velocidade do player
	public int spd = 5;
	//booleanas se está ou não sendo pressionadas na classe GAME
	public boolean right, up, down, left;
	
	public Direcao direcao;

	public Player(int x, int y, Game game) {
		//aqui estamos definindo o tamanho do player bem como aonde ele aparece na janela
		super(x, y, 32, 32);
		game.entities.add(this);
		game.objetosConcretos.add(this);
	}
	//caso algumas dessas seja apertada, com base na speed movera as coordenadas do jogador tanto em x quanto em y
	//World.isFree é um sistema basico de colisão
	public void tick() {
		if(right && World.isFree(x+spd, y)) {
			x += spd;
		}
		else if(left && World.isFree(x-spd, y)) {
			x -= spd;
		}
		
		if(up && World.isFree(x, y-spd)) {
			y -= spd;
		}
		else if(down && World.isFree(x, y+spd)) {
			y += spd;
		}
	}
	//aqui é onde o player recebe graficos sendo azul e um retangulo
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	

}
