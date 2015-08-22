import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Arrays;
import java.util.*;

//Code borrowed from NWEN303 Project2

/**
 * This class decrypts a Base64 ciphertext given a particular key (integer).
 * 
 * Requires 
*/
public class Decrypt {

    /**
    * Example of using Blowfish to decrypt a given ciphertext and key.
    *
    * Errors are suppressed, if this fails the output will be null.
    *
    * @param theInput[0] key represented as a big integer value
    * @param theInput[1] key size
    * @param theInput[2] ciphertext encoded as base64 value
    */
    public String processInput(String theInput) {

	String theOutput = null
        // simple sanity check
        if (theInput.length<3) {
            System.out.println("Missing arguments.");
            System.out.println("<key> <keysize> <ciphertext>");
            System.exit(-1);
        }
        
        // check our key argument
        BigInteger bi = null;
        try {
            bi = new BigInteger(theInput[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + theInput[0] + " must be an integer.");
            System.exit(1);
        }

        // check our key size argument
        int keySize = 4;
        try {
            keySize = Integer.parseInt(theInput[1]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + theInput[1] + " must be an integer.");
            System.exit(1);
        }

        // check our ciphertext is valid base64 and convert to an array
        byte[] ciphertext = null;
        try {
            ciphertext = Blowfish.fromBase64(theInput[2]);
        } catch (IllegalArgumentException e) {
            System.err.println("Argument" + theInput[2] + " must be base64 format.");
            System.exit(1);
        }

        // Convert the key from a big integer
        // Key size data is used because we need to pad out the key because
        //   each byte counts!
        byte[] key = Blowfish.asByteArray(bi,keySize);
        theOutput = ("key is (hex) " + Blowfish.toHex(key));
        
        // Initialise the key
        Blowfish.setKey(key);
        
        // Do the decryption
        String plaintextStr = Blowfish.decryptToString(ciphertext);
        theOutput = ("decrypted string : " + plaintextStr);
        
        return theOutput;
    }
}