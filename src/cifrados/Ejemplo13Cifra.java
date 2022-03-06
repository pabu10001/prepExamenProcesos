package cifrados;

import java.io.*;
import java.security.*; 
import javax.crypto.*; 

public class Ejemplo13Cifra {  
      
      public static void main(String[] args) { 
            
            try { 
                  //RECUPERAMOS CLAVE SECRETA DEL FICHERO 
                  ObjectInputStream oin = new ObjectInputStream( new FileInputStream("Clave.secreta")); 
                  Key clavesecreta = (Key) oin.readObject(); 
                  oin.close(); 
                  
                  //SE DEFINE EL OBJETO Cipher para encriptar 
                  Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
                  c.init(Cipher.ENCRYPT_MODE, clavesecreta); 
                  
                  //FICHERO A CIFRAR 
                  FileInputStream filein = new FileInputStream("FICHERO.pdf"); 
                  //OBJETO CipherOutputStream donde se almacena el fichero cifrado 
                  CipherOutputStream out = new CipherOutputStream( new FileOutputStream("FicheroPDF.Cifrado"), c); 
                  int tambloque = c.getBlockSize();//tamaño de bloque objeto Cipher 
                  byte[] bytes = new byte[tambloque];//bloque de bytes 
                  
                  //LEEMOS BLOQUES DE BYTES DEL FICHERO PDF 
                  //Y int LO VAMOS ESCRIBIENDO AL CipherOutputStream 
                  int i = filein.read(bytes); 
                  while (i != -1) {  
                        out.write(bytes, 0, i); 
                        i = filein.read(bytes); 
                  }
                  
                  out.flush(); 
                  out.close(); 
                  filein.close();
                  System.out.println("Fichero cifrado con clave secreta."); 
                  
            } catch (Exception e) {e.printStackTrace();} 
            
      }//Fin de main 
}// Fin de Ejemplo13Cifra
