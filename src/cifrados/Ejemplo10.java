package cifrados;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Ejemplo10 {
      
      public static void main(String[] args) { 

            
            try {
            
                  //Creamos la clave secreta usando el algoritmo AES y deﬁnimos un tamaño de clave de 128 bits
                  KeyGenerator kg = KeyGenerator.getInstance("AES");
                  kg.init (128); 
                  SecretKey clave = kg.generateKey();
                  
                  //Creamos un objeto Cipher con el algoritmo AES/ECB/PKCS5Padding, lo inicializamos en modo encriptación con la clave creada anteriormente.
                  Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
                  c.init(Cipher.ENCRYPT_MODE, clave);
                  
                  //Realizamos el cifrado de la información con el método doFinal()
                  byte textoPlano[] = "Esto es un Texto Plano".getBytes();
                  byte textoCifrado[] = c.doFinal(textoPlano);
                  System.out.println("Encriptado: "+ new String(textoCifrado));
                  
                  //Conﬁguramos el objeto Cipher en modo desencriptación con la clave anterior para desencriptar el texto, usamos el método doFinal()
                  c.init(Cipher.DECRYPT_MODE, clave);
                  byte desencriptado[] = c.doFinal(textoCifrado);
                  System.out.println("Desencriptado: "+ new String(desencriptado));


                  /*
                  //Muchos modos de algoritmo (por ejemplo CBC) requieren un vector de inicialización que se especifica cuando se inicializa 
                  //el objeto Cipher en modo desencriptación. En estos casos, se debe pasar al método init() el vector de inicialización. 
                  //La clase IvParameterSpec se usa para hacer esto en el cifrado DES.
                  KeyGenerator kg = KeyGenerator.getInstance("DES");
                  Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
                  Key clave = kg.generateKey();
                  
                  //Devuelve el vector IV inicializado en un nuevo buffer
                  byte iv[]=c.getIV();
                  IvParameterSpec dps = new IvParameterSpec(iv);
                  c.init(Cipher.DECRYPT_MODE, clave, dps);
                  */

            } catch (NoSuchAlgorithmException e) {         
            } catch (NoSuchPaddingException e) {
            } catch (InvalidKeyException e) {
            } catch (IllegalBlockSizeException e) {
            } catch (BadPaddingException e) {
      //    } catch (InvalidAlgorithmParameterException e) {
            }
            
      }// Fin de main
}// Fin de Ejemplo10
