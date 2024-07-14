package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

//nosso mundo onde guardamos os blocos que irão compor a tela do jogo 
public class World {
	
	//aqui passamos um lista de blocos que aparecerão na tela do jogador
	public static ArrayList<Blocks> blocos = new ArrayList<Blocks>();
	
	//logo quando o mundo for instanciado/criado ele irá preencher a tela com uma logica simples adiciona quadrados
	//a medida do loop com base no tamanho da janela
	public World() {
		for(int xx = 0; xx < 15; xx++) {
			blocos.add(new Blocks(xx*32,0));
		}
		for(int xx = 0; xx < 15; xx++) {
			blocos.add(new Blocks(xx*32,480-32));
		}
		for(int yy = 0; yy < 15; yy++) {
			blocos.add(new Blocks(0,yy*32));
		}
		for(int yy = 0; yy < 15; yy++) {
			blocos.add(new Blocks(480-32,yy*32));
		}
	}
	
	//sistema de colisão
	public static boolean isFree(int x, int y) {
		for (Blocks bloco : blocos) {
			Blocks blocoAtual = bloco;
			//intersects é metodo da classe Rectangle onde retorna true ou false caso haja colisão com outro Rectangle
			if(blocoAtual.intersects(new Rectangle(x, y, 32, 32))) {
				return false;
			}
		}
		return true;
	}
	
	//renderizador do mundo adicionando blocos aos graficos da tela
	public void render(Graphics g) {
		for (Blocks blocks : blocos) {
			blocks.render(g);
		}
		
	}
	
}
