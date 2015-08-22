//NWEN 303 Assignment 4 - EchoServer
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 13/10/2014 23:59

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
        
            String inputLine, outputLine;
            
            // Initiate conversation with client
            Decrypt de = new Decrypt();
            outputLine = Decrypt.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = Decrypt.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}