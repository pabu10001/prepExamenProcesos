package cifrados;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
Guardaremos un mensaje en un fichero.
También guardaremos en el fichero el resumen del mensaje, para asegurarnos de 
que a la hora de leer el mensaje el fichero no esté dañado o no haya sido 
manipulado y los datos sean los correctos

*/

public class Ejemplo5 { 
      
      public static void main(String args[]) { 
            
            try { 
                  FileOutputStream fileout = new FileOutputStream("C:\\Users\\achil\\Documents\\NetBeansProjects\\CIFRADOS\\DATOS.DAT"); 
                  ObjectOutputStream dataOS = new ObjectOutputStream(fileout); 
                  MessageDigest md = MessageDigest.getInstance("SHA"); 
                  String datos = "En un lugar de la Mancha, " 
                             + "de cuyo nombre no quiero acordarme, no ha mucho tiempo " 
                             + "que vivía un hidalgo de los de lanza en astillero, " 
                             + "adarga antigua, rocín flaco y galgo corredor."; 
                  byte dataBytes[] = datos.getBytes(); 
                  md.update(dataBytes) ;// TEXTQ A RESUMIR 
                  byte resumen[] = md.digest(); // SE CALCULA EL RESUMEN 
                  dataOS.writeObject(datos); //se escriben los datos 
                  dataOS.writeObject(resumen);//Se escribe el resumen 
                  dataOS.close(); 
                  fileout.close(); 
            } catch (IOException e) { e.printStackTrace(); 
            } catch (NoSuchAlgorithmException e) { e.printStackTrace(); } 
            
      }//Fin de main
}//Fin de Ejemplo5
