package cifrados;

import java.io.*;
import java.security.*; 
import javax.crypto.*; 

public class Ejemplo13Descifra { 
      
      public static void main(String[] args) { 
            
            try { 
                  //RECUPERAMOS CLAVE SECRETA DEL FICHERO 
                  ObjectInputStream oin = new ObjectInputStream(new FileInputStream("Clave.secreta")); 
                  Key clavesecreta = (Key) oin.readObject(); 
                  oin.close(); 
                  
                  //SE DEFINE EL OBJETO Cipher para desencriptar 
                  Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
                  c.init(Cipher.DECRYPT_MODE, clavesecreta); 
                  
                  //OBJETO CipherInputStream CUYO CONTENIDO SE VA A DESCIFRAR 
                  CipherInputStream in = new CipherInputStream(new FileInputStream("FicheroPDF.Cifrado"), c); 
                  int tambloque = c.getBlockSize();//tamaño de bloque 
                  byte[] bytes = new byte[tambloque];//bloque de bytes 
                  
                  //FICHERO CON EL CONTENIDO DESCIFRADO QUE SE CREARÁ 
                  FileOutputStream fileout = new FileOutputStream("FICHEROdescifrado.pdf"); 
                  
                  //LEEMOS BLOQUES DE BYTES DEL FICHERO cifrado 
                  //Y LO VAMOS ESCRIBIENDO desencriptados al FileOutputStream 
                  int i = in.read(bytes); 
                  while (i != -1){ 
                        fileout.write(bytes, 0, i);    
                        i = in.read(bytes); 
                  }
                  fileout.close(); 
                  in.close(); 
                  System.out.println("Fichero descifrado con clave secreta."); 
                  
            } catch (Exception e) {e.printStackTrace();} 
            
      }//Fin de main 
}//Fin de Ejemplo13Descifra
