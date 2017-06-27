import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Encryption {
	
	public Encryption(){
		//costructor
	}
	
	
	public String plainToBinary(String plain){
		
		byte[] crypto = plain.getBytes();
		
		StringBuilder bin = new StringBuilder();
		  for (byte b : crypto)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        bin.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     bin.append(' ');
		  }
		
		  
		  String bintoreturn = bin.toString();
		  return bintoreturn;
		  
	}
	
	
	public String plaintToSHA(String plain) throws NoSuchAlgorithmException{
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(plain.getBytes(StandardCharsets.UTF_8));
		
		return hash.toString();
		
	}
	
	
	public String plainToASCII(String plain){
		
		//byte[] b = plain.getBytes(StandardCharsets.US_ASCII);
		
		String toreturn = "";
		
		for( int i = 0 ; i< plain.length() ; i++){
			char a = plain.charAt(i);
			int ascii = (int) a;
			toreturn = toreturn + ascii;
		}
		
		return toreturn;
		
	}
	
	
	public String plainToHex(String plain) {
	   
		return String.format("%040x", new BigInteger(1, plain.getBytes()));
	
	}
	
	
	public String plainToBin64(String plain) {  
	
		Encoder encoder = Base64.getEncoder();
		String en = encoder.encodeToString(plain.getBytes());
		
		return en;
	 
	}
	
	
	public String plainToDes(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56); // for example
		SecretKey key = keyGen.generateKey();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] input = plain.getBytes();
		
		byte[] encoded = cipher.doFinal(input);
		
		return encoded.toString();
		
		
		
	}
	
	
	public String plainToAES(String plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128); // for example
		SecretKey key = keyGen.generateKey();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] input = plain.getBytes();
		
		byte[] encoded = cipher.doFinal(input);
		
		return encoded.toString();
		
		
		
	}
}
