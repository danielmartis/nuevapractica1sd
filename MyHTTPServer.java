package MyHTTPServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;

class MyHTTPServer {
    public static void main(String args[]) {
        String ipController;
        int portController; 
        String ipServer; 
        int portServer; 
        int conex;
        try {
            if(args.length >= 5){
                ipServer = args[0];
                portServer = Integer.parseInt(args[1]);
                ipController = args[2];
                portController = Integer.parseInt(args[3]);
                conex = Integer.parseInt(args[4]);
                init(ipServer, portServer, ipController,portController, conex);
            }
        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

  
    private static void init(String ipServer, int pServer, String ipControl, int pControl, int conex) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ipServer,pServer));
            System.out.println("Socket opened at port " + serverSocket.getLocalPort());

            //noinspection InfiniteLoopStatement
            while (true) { //Server is stopped with ^C
                Socket requestSocket = serverSocket.accept();
                System.out.println("Serving to " + requestSocket.getRemoteSocketAddress());
                if (Thread.activeCount() <= conex) {
                    Thread t = new MyHTTPThread(requestSocket, ipControl, pControl);
                    t.start();
                }
                else {
                    System.err.println("Connection with " + requestSocket.getRemoteSocketAddress() + " closed due to connection limit");
                    requestSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to open socket");
        }
    }
}
