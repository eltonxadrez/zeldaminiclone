package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener{
	
	//ignore
	private static final long serialVersionUID = 1L;
	
	//variaveis globais, podem ser acessadas em qualquer parte
	//altura e largura usadas no tamanho da janela
	public static int WIDTH = 640, HEIGHT = 640;
	public Player player;
	public World world;
//	public Bullet bullet;
	public Graphics g;
	public BufferStrategy bs;
	
	public ArrayList<Entity> entities;
	public ArrayList<Concreto> objetosConcretos;
	
	public Game() {
		
		//adicionando evento do teclado a essa classe = this
		this.addKeyListener(this);
		
		//definir o tamanho da janela
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//inicia a lista de entidades
		this.entities = new ArrayList<Entity>();
		this.objetosConcretos = new ArrayList<Concreto>();
		
		//cria um player
		//adicionar um player a janela
		this.player = new Player(32, 32, this);
		
		//instanciar um mundo;
		this.world = new World(this);
		
	}
	
	//são a iterações/coisas que podem acontecer
	public void tick() {
		for (Entity entity : entities) {
			entity.tick();
		}
	}
	
	public void render() {
		//A BufferStrategyclasse representa o mecanismo com o qual organizar memória complexa em um Canvasou Window.
		//Limitações de hardware e software determinam se e como uma estratégia de buffer específica pode ser implementada.
		//Essas limitações são detectáveis ​​por meio dos recursos do GraphicsConfigurationusado ao criar o Canvasou Window.
		//Para mais informações pesquisar no link https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html.
		this.bs = this.getBufferStrategy();
		
		//apenas verificando se caso não houver um bs, que um seja instanciado
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		//A Graphics classe é a classe base abstrata para todos os contextos gráficos que permitem que um aplicativo desenhe em componentes
		//que são realizados em vários dispositivos, bem como em imagens fora da tela.
		//Para mais informações pesquisar no link https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
		this.g = bs.getDrawGraphics();
		
		//pasando a cor como preto
		this.g.setColor(Color.black);
		//e definindo um retangulo nas cordenadas x = 0, y = 0, com tamanho e altura da tela, passadas la em cima.
		this.g.fillRect(0, 0, WIDTH, HEIGHT);
//		//chama o metodo render do player e passa o render dessa tela
//		this.player.render(g);
//		//chama o metodo render do mundo e passa o render dessa tela
//		this.world.render(g);
		//no lugar de chamar um objeto por vez, passe uma lista dos objetos que implementam CONCRETO assim é possível chamar todos os objetos de uma vez só
		for (Concreto objetosConcretos : this.objetosConcretos) {
			objetosConcretos.render(g);
		}
		//desenha na tela
		this.bs.show();
	}

	public static void main (String[] args) {
		//cria essa clasee em tempo de execução
		Game game = new Game();
		//cria uma janela em tempo de execução
		JFrame frame = new JFrame();
		//adiciona essa clase game a tela pois essa clase Game exetende de Canvas
		frame.add(game);
		//seta um titul à janela
		frame.setTitle("Mini Zelda");
		//empacotar os dados
		frame.pack();
		//deixar a tela centralizada
		frame.setLocationRelativeTo(null);
		//definir o botão de fechamento, quando fechar o Jframe o processo (thread) ser fechado tbm 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//definir a janela visivel 
		frame.setVisible(true);
		//inicia um novo Thread e chama o metodo start, porém passand como parametro essa classe
		//que implementa o metodo Runnable, por ela implementar essa classe quando chamamos start ele automaticamente procura o metodo RUN da classe Game
		new Thread(game).start();
	}
	
	//metodo sobrescrito da implementa Runnable e aqui é definido certas condições e chamamentos de metodos
	@Override
	public void run() {
		//loop que irá definir o tempo do jogo
		while(true) {
			tick();
			render();
			//try -> catch é um tipo de ferramenta do JAVA para metodos que podem falhar
			//o try catch é um assunto sobre exceções é importante estudar sobre isso
//			if(this.bullet != null) {
			
//			}
			try {
				//aqui é definido que a Thread "durma" por um momento, nesse caso estamos fazendo ela dormir e essa rotina
				//faz com o que o tempo corrar 60 ticks/iterações por segundo
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				//esse metodo printa qual erro ocorreu e por estar sendo executado dentro de um try catch, isso não irá parar o programa/jogo
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	//caso o jogador aperte uma das teclas para cima, baixo, esquerda ou direita movera o retangulo que representa o jogador
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.right = true;
			this.player.direcao = Direcao.RIGHT;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.left = true;
			this.player.direcao = Direcao.LEFT;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.player.up = true;
			this.player.direcao = Direcao.UP;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.player.down = true;
			this.player.direcao = Direcao.DOWN;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			new Bullet(this.player.x, this.player.y, this.player.direcao, this);
//			System.out.println(this.player.direcao);
//			if(this.bullet == null) {
				
//				this.g = bs.getDrawGraphics();
//				this.bullet.render(this.g);
//				this.bs.show();
				
//				this.bullet.trajetoria(this.player.direcao);
//			}
		}
	}
	//após soltar uma das teclas ira para de se movimentar
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.player.down = false;
		}
		
	}
	
}
