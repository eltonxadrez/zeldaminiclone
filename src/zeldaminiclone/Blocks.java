package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//essa classe é usada para criar os blocos no ambiente do jogo
public class Blocks extends Rectangle implements Concreto{
	
	//ignore
	private static final long serialVersionUID = 1L;
	
	//ele tem tamanho e tbm um lugar onde nascerão
	public Blocks(int x, int y, Game game) {
		super(x, y, 32, 32);
		game.objetosConcretos.add(this);
	}
	//e tbm um metodo render onde definimos seus graficos, cor, tamanho e coordenadas
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
	}
	
}
