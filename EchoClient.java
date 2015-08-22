//NWEN 303 Assignment 4 - EchoClient
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 13/10/2014 23:59

import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient [IP_Address] [Port_Number].");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket EchoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(EchoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(EchoSocket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromClient;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("bye"))
                    break;
                
                fromClient = stdIn.readLine();
                if (fromClient != null) {
                    System.out.println("Client: " + fromClient);
                    out.println(fromClient);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}