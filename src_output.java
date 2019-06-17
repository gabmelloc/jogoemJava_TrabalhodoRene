import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.util.*;

public class output extends Thread {

    Socket socket = null;
    String nome;
    Variaveis var;
    Jogo g;

    public output(Socket socket, Variaveis var, Jogo g) throws IOException {
        this.var = var;
        this.socket = socket;
        this.g = g;

        try {
            PrintStream os = new PrintStream(socket.getOutputStream());

            g.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_SPACE:
                            os.println("VK_SPACE");
                            break;
                        case KeyEvent.VK_P:
                            os.println("VK_P");
                            break;
                    }
                }
            });


        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (NoSuchElementException e) {
            System.out.println("Conexacao terminada.");
        }
    }
}