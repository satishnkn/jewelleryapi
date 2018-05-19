package com.jewellerypos.api.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 *
 */
@Component
public class PasscodeEncryptorUtil {
	private static final Logger LOGGER=LoggerFactory.getLogger(PasscodeEncryptorUtil.class);
	private static final String AUTHENTICATION_SCHEME = "Basic";
		
	/**
	 * This method used to encrypt the parties password instead of storing plain text in database.
	 * 
	 * @param password A password of a party.
	 * @return Returns encrypted password.
	 */
	public static String encryptPasscode(String password)
    {
        String generatedPassword = null;
        try {
            byte[] salt = "s@1^!s|-|kazzaz$~#@^!@".getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            md.update(password.getBytes());
            md.update(salt);
            generatedPassword = new BigInteger(1,md.digest()).toString(16);
            LOGGER.debug("the plain passcode is "+password);
            LOGGER.debug("the encrypted passcode is "+generatedPassword);
        }
        catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        return generatedPassword;
    }
	
	public static long getCreatorId(@Context HttpServletRequest req) {
	    
	    String tocken = req.getHeader("Authorization");
	    final String encodedUserPassword = tocken.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
        
        // Decode username and password
        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
        
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        //final String password = tokenizer.nextToken();
        
        return Long.parseLong(username);
        
        
	}
}
