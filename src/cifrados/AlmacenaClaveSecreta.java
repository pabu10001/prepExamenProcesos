package cifrados;

import java.io.*;
import java.security.*; 

import javax.crypto.*; 

public class AlmacenaClaveSecreta { 
      
      public static void main(String[] args) { 
            
            try { 
                  KeyGenerator kg = KeyGenerator.getInstance("AES"); 
                  kg.init(128); 
                  //genera clave secreta 
                  SecretKey clave = kg.generateKey(); 
                  ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Clave.secreta")); 
                  out.writeObject(clave); 
                  out.close(); 

                  /*
                  //Para recuperar la clave secreta del fichero
                  ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clave.secreta")); 
                  Key secreta = (Key) in.readObject();
                  in.close();
                  */
                  
            } catch (NoSuchAlgorithmException e) {e.printStackTrace();
            } catch (FileNotFoundException e) {e.printStackTrace();
            //} catch (ClassNotFoundException e) { e.printStackTrace();//Para recuperar la clave secreta
            } catch (IOException e) {e.printStackTrace();} 
            
      }//Fin de main 
}//Fin de AlmacenaClaveSecreta
