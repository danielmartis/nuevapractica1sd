package Controller;

import MyHTTPServer.SocketUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
/**
 * Created by pavel on 17/10/16.
 * Intermediate between MyHTTPServer and RMI Register
 */
class Controller {
    public static void main(String args[]) {
        String ipRMI,ipController;
        int pRMI,pController,max;
        try {
            if(args.length >= 5){
                ipRMI = args[0];
                pRMI = Integer.parseInt(args[1]);
                ipController = args[2];
                pController = Integer.parseInt(args[3]);
                max = Integer.parseInt(args[4]);
                init(ipRMI,pRMI,ipController,pController,max);
            }
        } catch (Exception e) {
            System.err.println("Controller's config file not found");
        }
    }


    private static void init(String ipRMI, int pRMI, String ipControl, int pControl,int max) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ipControl,pControl));
            System.out.println("Socket opened at port " + serverSocket.getLocalPort());

            //noinspection InfiniteLoopStatement
            while (true) { //Server is stopped with ^C
                Socket requestSocket = serverSocket.accept();
                System.out.println("Serving to " + requestSocket.getRemoteSocketAddress());
                if (Thread.activeCount() <= max) {
                    Thread t = new ControllerThread(ipRMI,pRMI, requestSocket);
                    t.start();
                } else {
                    System.err.println("Connection with " + requestSocket.getRemoteSocketAddress() + " closed due to connection limit");
                    requestSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to open socket");
        }
    }
}
