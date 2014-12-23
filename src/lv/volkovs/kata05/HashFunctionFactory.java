package lv.volkovs.kata05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class HashFunctionFactory {

	public static MessageDigest create() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(UUID.randomUUID().toString().getBytes());
			return md;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
