package cifrados;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
/*
Genera el resúmen de un texto plano. Con el método MessageDigest.getInstance(“SHA”) 
se obtiene una instancia del algoritmo SHA. El texto plano lo pasamos a un array 
de bytes y el array se pasa como argumento al método upate(), finalmente con el 
método digest() se obtiene el resumen del mensaje. Después se muestra en pantalla 
el número de bytes generados en el mensaje, el algoritmo utilizado, el resumen 
generado y convertido a Hexadecimal y por último información del proveedor.

Se puede crear un resúmen cifrado con clave usando el segundo método 
digest(bytes[]), donde se proporciona la clave en un array de bytes.

String clave="clave de cifrado";
byte dataBytes[] = texto.getBytes(); //TEXTO A BYTES
md.update(dataBytes) ; // SE INTRODUCE TEXTO EN BYTES A RESUMIR
byte resumen[] = md.digest(clave.getBytes());  // SE CALCULA EL RESUMEN CIFRADO


*/
public class Ejemplo4 { 
      
      public static void main(String[] args) { 
            
            MessageDigest md; 
            try { 
                  md = MessageDigest.getInstance("SHA"); 
                  String texto = "Esto es un texto plano."; 
                  
                  byte dataBytes[] = texto.getBytes();//TEXTO A BYTES
                  md.update(dataBytes) ;//SE INTRQDUCE TEXTO EN BYTES A RESUMIR 
                  byte resumen[] = md.digest();//SE CALCULA EL RESUMEN 
                  
                  //PARA CREAR UN RESUMEN CIFRADO CON CLAVE
                  //String clave="clave de cifrado"; 
                  //byte resumen[] = md.digest(clave.getBytes()); // SE CALCULA EL RESUMEN CIFRADO
                  
                  
                  System.out.println("Mensaje original: " + texto); 
                  System.out.println("Número de bytes: " + md.getDigestLength()); 
                  System.out.println("Algoritmo: " + md.getAlgorithm()); 
                  System.out.println("Mensaje resumen: " + new String(resumen)); 
                  System.out.println("Mensaje en Hexadecimal: " + Hexadecimal(resumen)); 
                  Provider proveedor = md.getProvider(); 
                  System.out.println("Proveedor: " + proveedor.toString()); 
            } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
            
      }//Fin de main 
      
      
      // CONVIERTE UN ARRAY DE BYTES A HEXADECIMAL 
      static String Hexadecimal(byte[] resumen) { 
            
            String hex = ""; 
            for (int i = 0; i < resumen.length; i++) { 
                  
                  String h = Integer.toHexString(resumen[i] & 0xFF); 
                  /*
                  Anding an integer with 0xFF leaves only the least significant byte. For example, to get the first byte in a short s, you can write s & 0xFF. This is typically referred to as "masking". If byte1 is either a single byte type (like uint8_t) or is already less than 256 (and as a result is all zeroes except for the least significant byte) there is no need to mask out the higher bits, as they are already zero.
                  */
                  if (h.length() == 1) 
                        hex += "0"; 
                  hex += h;
            }//Fin de for
            
            return hex.toUpperCase(); 
            
      }// Fin de Hexadecimal 
      
}//Fin de Ejemplo4
