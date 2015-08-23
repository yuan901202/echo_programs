# echo_programs
[NWEN303] Implement Echo server and client in Java

## Goals:
Implement a client server program that allows a client to send a server an encryption key, a key size and a ciphertext.
The server should return the decrypted ciphertext to the client and the client should print this out.

## Running it:

### First, you should running EchoServer first:
$ javac EchoServer.java

$ java EchoServer [Port_Number]

### Then, you should running EchoClient second:
$ javac EchoClient.java

$ java EchoClient [IP_Address] [Port_Number]

If you running both program on the same machine, you should use following command:

$ java EchoClient localhost [Port_Number]

### Finally, within the EchoClient program, you should use following format command to get a response from EchoServer:
$ [key] [keysize] [ciphertext]
