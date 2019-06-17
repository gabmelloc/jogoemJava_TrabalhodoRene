import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Cliente{
    Socket socket = null;
    String host = null;

    public Cliente(){ // objeto cliente
        try {
            host = "3.80.131.41";
            socket = new Socket(host, 80);
        } catch (NullPointerException e) {
            System.err.println("Don't know about host.");
            System.exit(0);
        } catch (UnknownHostException e) {
        System.err.println("Trying to connect to unknown host: ");

         } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }

        Variaveis var = new Variaveis();
        Jogo a = new Jogo(var);


        Thread t = new input(socket,var, a); // enviar g como parametro
        t.start();

        try {
            Thread p = new output(socket, var,a);
            p.start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}