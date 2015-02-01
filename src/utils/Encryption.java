package utils;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Encryption {
	public static String MD5(String string) {
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = string.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			return String.valueOf(md.digest(bytesOfMessage));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
}
