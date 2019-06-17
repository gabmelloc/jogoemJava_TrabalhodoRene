import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.*;

class Jogo extends JFrame {
    Variaveis var;
    Image[] img = new Image[10];
    Desenho des = new Desenho();
    boolean continua = true;

    // Índices das imagens utilizadas.
    final int V0 = 0;       // Número 0
    final int V1 = 1;       // Número 1
    final int V2 = 2;       // Número 2
    final int V3 = 3;       // Número 3
    final int V4 = 4;       // Número 4
    final int P1 = 5;       // Ícone navegador Jogador 1 (Netscape)
    final int P2 = 6;       // Ícone navegador Jogador 2 (Internet Explorer)
    final int A1 = 7;       // "Pacote" de ataque
    final int LV = 8;       // Label escrito Vida
    final int LP = 9;       // Label escrito Jogador

    // Plano de fundo
    private Image loadImage(){
        ImageIcon icon = new ImageIcon("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/36010.gif");
        Image img = icon.getImage();
        return img;
    }

    class Desenho extends JPanel {
        Desenho() {
            try {
                // Tamanho da tela do jogo.
                setPreferredSize(new Dimension(1000, 800));

                Image image;

                // Personagens
                img[P1] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/netscape.png"));
                img[P2] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/iexplorer.png"));

                // Números para o contador vida
                img[V0] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/0.png"));
                img[V1] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/1.png"));
                img[V2] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/2.png"));
                img[V3] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/3.png"));
                img[V4] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/4.png"));

                // Ataque
                img[A1] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/tiro.png"));

                // Labels de vida e jogador
                img[LV] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/vida.png"));
                img[LP] = ImageIO.read(new File("/Users/gabrielcruz/IdeaProjects/ProjetodoRene/src/jogador.png"));

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "A imagem não pode ser carregada!\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                System.exit(1);
            }
        }

        /* Doc de drawImage()
            drawImage(Image img, int x1, int y1, int x2, int y2)
            img - imagem especificada
            x1 - coordenada na tela
            y1 - coordenada na tela
            x2 - tamanho da imagem na horizontal
            y2 - tamanho da imagem na vertical
        */

        // Desenha na tela todos os elementos do jogo
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Imagem de fundo permanecerá inalterada durante todo o jogo.
            g.drawImage(loadImage(), 0, 0, getSize().width, getSize().height, this);

            // Jogador 1 (Netscape) começará sempre no lado esquerdo da tela.
            g.drawImage(img[P1], 0, var.getAlt1(), img[P1].getWidth(this), img[P1].getHeight(this), this);

            // Jogador 2 (Internet Explorer) começará sempre no lado direito da tela.
            g.drawImage(img[P2], getSize().width - img[P2].getWidth(this), var.getAlt2(), img[P2].getWidth(this), img[P2].getHeight(this), this);

            // Label de vida Jogador 1
            g.drawImage(img[LV], 0, 0, 100, 50, this);

            // Label de vida Jogador 2
            g.drawImage(img[LV], getSize().width - 170, 0, 100, 50, this);

            // Quantidade de vidas Jogador 1
            g.drawImage(img[var.getVida1()], 100, 0, 50, 50, this);

            // Quntidade de vida Jogadorr 2
            g.drawImage(img[var.getVida2()], getSize().width - 70, 0, 50, 50, this);

            // Desenha "pacote" quando ação do Jogador 1 muda para ataque
            if( var.getEstadoA() == 2) {
                g.drawImage(img[A1], var.getAtaq1(), var.getAlt1(), 75, img[P2].getHeight(this), this);
            }

            // Desenha "pacote" quando ação do Jogador 2 muda para ataque
            if( var.getEstadoB() == 2) {
                g.drawImage(img[A1], var.getAtaq2(), var.getAlt2(), 75, img[P2].getHeight(this), this);
            }

            // Indica Jogador 1 como vencedor
            if( var.getWinner1() == 1 && var.getVida2() == 0) {
                g.drawImage(img[LP], 300, 300, 400, 200, this);
                g.drawImage(img[V1], 430, 430, 150, 150, this);
            }

            // Indica Jogador 2 como vencedor
            if( var.getWinner2() == 1 && var.getVida1() == 0) {
                g.drawImage(img[LP], 300, 300, 400, 200, this);
                g.drawImage(img[V2], 430, 430, 150, 150, this);
            }

            Toolkit.getDefaultToolkit().sync();
        }
    }

    public Jogo(Variaveis var) {
        // Título do jogo na barra superior
        super("Guerra dos navegadores");
        this.var = var;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(des);
        pack();
        setVisible(true);
    }



}