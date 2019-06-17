import java.io.*;
import java.net.*;
import java.util.*;
import java.io.IOException;

public class Servidor extends Thread {
    static Vector<Socket> clientes;
    static Variaveis var;
    static ServerSocket server;
    static Socket cliente;
    private int id;
    Scanner is;
    PrintStream os;

    public Servidor(Socket cliente, int id) {
        /*  Método construtor recebe um socket, um cliente, e já cria dois
            objetos relacionados a eles, um que recebe e outro que envia dados.*/
        this.cliente = cliente;
        this.id = id;
        clientes.add(cliente);
        try {

            is = new Scanner(cliente.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String msg;
            while (is.hasNextLine()) {
                msg = is.nextLine();

                // Jogador 1.
                if(id == 1) {
                    // Lê salto e processa ação.
                    if (msg.contains("VK_SPACE")) {
                        if(var.getEstadoA() == 0) {
                            var.setEstadoA(1);
                            processa(var);
                        }
                    }

                    // Lê ataque e processa ação.
                    if (msg.contains("VK_P")) {
                        if(var.getEstadoA() == 0){
                            var.setEstadoA(2);
                            processa(var);
                        }                        
                    }
                }

                // Jogador 2.
                if(id == 2) {
                    // Lê salto e processa ação.
                    if (msg.contains("VK_SPACE")) {
                        if(var.getEstadoB() == 0) {
                            var.setEstadoB(1);
                            processa(var);
                        }
                    }

                    // Lê ataque e processa ação.
                    if (msg.contains("VK_P")) {
                        if(var.getEstadoB() == 0) {
                            var.setEstadoB(2);
                            processa(var);
                        }                        
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processa(Variaveis var) {
        // Jogador 1
        // (Salto): Movimento vertical do Jogador.
        if(var.getEstadoA() == 1){
            try {
                // Subida.
                for(var.getAlt1(); var.getAlt1() >= 280; var.setAlt1(var.getAlt1() - 20)){
                    sendToAll("Alt1");
                    sendToAll(var.getAlt1());
                    Thread.sleep(30);
                }
                // Descida.
                for(var.getAlt1(); var.getAlt1() <= 580; var.setAlt1(var.getAlt1() + 20)){
                    sendToAll("Alt1");
                    sendToAll(var.getAlt1());
                    Thread.sleep(30);
                }

                var.setEstadoA(0);      // Retorna a posição neutra (sem ação).
            } catch(InterruptedException ex) {}
        }

        /*  (Ataque): Apenas enquanto adversário possui vida.
            Estado de ataque funciona por meio do deslocamento horizontal
            do pacote até atingir, ou não, o adversário. */
        if(var.getEstadoA() == 2 && var.getVida2() > 0) {
            sendToAll("EstadoA");
            sendToAll(var.getEstadoA());
            int a = var.getAtaq1();         // Guarda posição inicial do pacote.
            
            try {
                // Deslocamento horizontal, para a direita, do ataque.
                for(var.getAtaq1(); var.getAtaq1() <= 780; var.setAtaq1(var.getAtaq1() + 20)){
                    sendToAll("Ataq1");
                    sendToAll(var.getAtaq1());
                    Thread.sleep(30);
                }

                // Atinge oponente caso ele esteja na mesma altura que o ataque.
                if(var.getAlt2() == 580) {
                    var.setVida2(var.getVida2() - 1);   // Redução da vida do adversário.
                    sendToAll(var.getVida2());
                }

                // Declara vencedor quando oponente não tem mais vidas.
                if(var.getVida2() == 0) {
                    var.setWinner1(1);
                    sendToAll("Winner1");
                    sendToAll(var.getWinner1());
                }
                
                var.setAtaq1(a);    // Retoma posição inicial do próximo ataque.
                var.setEstadoA(0);      // Retorna a posição neutra (sem ação).
                
                sendToAll("EstadoA");
                sendToAll(var.getEstadoA());
                sendToAll("Vida2");
                sendToAll(var.getVida2());
            } catch(InterruptedException ex) {}
        }

        // Jogador 2
        // (Salto): Movimento vertical do Jogador.
        if(var.getEstadoB() == 1){
            try {
                // Subida.
                for(var.getAlt2(); var.getAlt2() >= 280; var.setAlt2(var.getAlt2() - 20)){
                    sendToAll("Alt2");
                    sendToAll(var.getAlt2());
                    Thread.sleep(30);
                }

                // Descida.
                for(var.getAlt2(); var.getAlt2() <= 580; var.setAlt2(var.getAlt2() + 20)){
                    sendToAll("Alt2");
                    sendToAll(var.getAlt2());
                    Thread.sleep(30);
                }
                var.setEstadoB(0);      // Retorna a posição neutra (sem ação).            
            } catch(InterruptedException ex) {}
        }

        /*  (Ataque): Apenas enquanto adversário possui vida.
            Estado de ataque funciona por meio do deslocamento horizontal
            do pacote até atingir, ou não, o adversário. */
        if(var.getEstadoB() == 2 && var.getVida1() > 0) {
            sendToAll("EstadoB");
            sendToAll(var.getEstadoB());
            int a = var.getAtaq2();     // Guarda posição inicial do pacote.
            
            try {
                // Deslocamento horizontal, para a esquerda, do ataque.
                for(var.getAtaq2(); var.getAtaq2() >= 100; var.setAtaq2(var.getAtaq2() - 20)){
                    sendToAll("Ataq2");
                    sendToAll(var.getAtaq2());
                    Thread.sleep(30);
                }

                // Atinge oponente caso ele esteja na mesma altura que o ataque.
                if(var.getAlt1() == 580) {
                    var.setVida1(var.getVida1() - 1);   // Redução da vida do adversário.
                    sendToAll(var.getVida1());
                }

                // Declara vencedor quando oponente não tem mais vidas.
                if(var.getVida1() == 0) {
                    var.setWinner2(1);
                    sendToAll("Winner2");
                    sendToAll(var.getWinner2());
                }

                var.setAtaq2(a);    // Retoma posição inicial do próximo ataque.
                var.setEstadoB(0);      // Retorna a posição neutra (sem ação).

                sendToAll("EstadoB");
                sendToAll(var.getEstadoB());
                sendToAll("Vida1");
                sendToAll(var.getVida1());
            } catch(InterruptedException ex) {}
        }

    }

    public void sendToAll(int hehe) {

        String msg = Integer.toString(hehe);
        PrintStream abc = null;
        Socket bcd;
        Iterator<Socket> i = clientes.iterator();
        while (i.hasNext()) {

            bcd = i.next();
            try {
                abc = new PrintStream(bcd.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            abc.println(msg);
            System.out.println("enviando " + msg);
        }
    }

    public void sendToAll(String msg) {

        PrintStream abc = null;
        Socket bcd;
        Iterator<Socket> i = clientes.iterator();
        while (i.hasNext()) {

            bcd = i.next();
            try {
                abc = new PrintStream(bcd.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            abc.println(msg);
            System.out.println("enviando " + msg);
        }
    }

    public static void main(String[] args) {

        try {
            /*Cria os objetos necessário para instânciar o servidor,
             no caso, teremos dois jogadores apenas.*/

            server = new ServerSocket(80);
            clientes = new Vector<Socket>();
            var = new Variaveis();
            int id=0;

            while (true) {
                Socket con = null;
                try {
                    System.out.println("Aguardando conexão...1");
                    con = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(con);
                System.out.println("Cliente conectado...1");
                System.out.println(clientes);
                Thread tA = new Servidor(con, 1 + id);
                tA.start();

                con = null;
                try {
                    System.out.println("Aguardando conexão...2");
                    con = server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(con);
                System.out.println("Cliente conectado...2");
                System.out.println(clientes);
                Thread tB = new Servidor(con, 2 + id);
                tB.start();
                id++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}