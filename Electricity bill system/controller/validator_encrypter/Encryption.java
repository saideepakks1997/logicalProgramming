package validator_encrypter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption {
	
	public String encryptPassword(String str) {
		String password = null ;
		try {
			password =toHexString(getSHA(str));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
		}
	public  byte[] getSHA(String input) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}
	
	public  String toHexString(byte[] hash)
	{
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 32)
		{
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
}

