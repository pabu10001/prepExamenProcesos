package cifrados;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.security.MessageDigest; 

public class Ejemplo6 { 
      /*
    
    Al recuperar los datos del fichero primero necesitamos leer el String y luego el resumen, 
    a continuación hemos de calcular de nuevo el resumen con el String leído y comparar este 
    resúmen con el leído del fichero.
    
    */
      public static void main(String args[]) { 
            
            try { 
                  FileInputStream fileout = new FileInputStream("C:\\Users\\achil\\Documents\\NetBeansProjects\\CIFRADOS\\DATOS.DAT"); 
                  ObjectInputStream dataOS = new ObjectInputStream(fileout); 
                  Object o = dataOS.readObject(); 
                  
                  // Primera lectura, se obtiene el String 
                  String datos = (String) o; 
                  System.out.println("Datos: " + datos); 
                  
                  // Segunda lectura, se obtiene el resumen 
                  o = dataOS.readObject(); 
                  byte resumenOriginal[] = (byte[]) o; 
                  
                  MessageDigest md = MessageDigest.getInstance("SHA"); 
                  //Se calcula el resumen del String leído del fichero 
                  md.update(datos.getBytes());// TEXTO A RESUMIR 
                  byte resumenActual[] = md.digest(); // SE CALCULA EL RESUMEN 
                  
                  //Se comprueban lo dos resúmenes 
                  if (MessageDigest.isEqual(resumenActual, resumenOriginal)) 
                        System. out.println ("DATOS VÁLIDOS") ; 
                  else 
                        System.out.println("DATOS NO VÁLIDOS") ; 
                  dataOS.close(); 
                  fileout.close();
            }catch (Exception e) { e.printStackTrace(); } 
      
      }//Fin de main
}//Fin de Ejemplo6
