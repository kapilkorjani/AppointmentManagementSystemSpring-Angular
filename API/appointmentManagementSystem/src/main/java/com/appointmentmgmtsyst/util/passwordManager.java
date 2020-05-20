package com.appointmentmgmtsyst.util;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class passwordManager {

	public static String getEncryptedPassword(String password,String key) {
		try {
		String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160; // for SHA1
        int iterations = 20000; // NIST specifies 10000
 
        byte[] saltBytes = Base64.getDecoder().decode(key);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
 
        byte[] encBytes = f.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(encBytes);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	public static String getNewKey(){
		try
		{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // NIST recommends minimum 4 bytes. We use 8.
        byte[] key = new byte[8];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static boolean verifyUserPassword(String unencryptedPassword,
            String securedPassword, String key)
    {
        boolean returnValue = false;
        
        // Generate New secure password with the same salt
        String newSecurePassword = getEncryptedPassword(unencryptedPassword, key);
        
        // Check if two passwords are equal
        returnValue = newSecurePassword.equals(securedPassword);
        
        return returnValue;
    }
}
