import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.util.*;

public class input extends Thread {
    Socket socket = null;
    Variaveis var;
    Jogo g;

    public input(Socket socket, Variaveis var, Jogo g){
        this.var = var;
        this.socket = socket;
        this.g = g;
    }

    public void run() {
        try {
            Scanner is = new Scanner(socket.getInputStream());
            String inputLine;
            while ((inputLine = is.nextLine()) != null) {
                var.setA(inputLine);
                change(inputLine, is);
                System.out.println(var.getA());
                g.repaint();
            }
            is.close();

        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (NoSuchElementException e) {
            System.out.println("Conexacao terminada.");
        }
    }

    public void change(String server, Scanner is){
        String a;

        try {

            if (server.contains("Alt1")) {
                a = is.nextLine();
                var.setAlt1(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Alt2")) {
                a = is.nextLine();
                var.setAlt2(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Ataq1")) {
                a = is.nextLine();
                var.setAtaq1(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Ataq2")) {
                a = is.nextLine();
                var.setAtaq2(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("EstadoA")) {
                a = is.nextLine();
                var.setEstadoA(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("EstadoB")) {
                a = is.nextLine();
                var.setEstadoB(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Vida1")) {
                a = is.nextLine();
                var.setVida1(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Vida2")) {
                a = is.nextLine();
                var.setVida2(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Winner1")) {
                a = is.nextLine();
                var.setWinner1(Integer.parseInt(a));
                g.repaint();
            }

            if (server.contains("Winner2")) {
                a = is.nextLine();
                var.setWinner2(Integer.parseInt(a));
                g.repaint();
            }

        }catch (NumberFormatException e){}
    }

}