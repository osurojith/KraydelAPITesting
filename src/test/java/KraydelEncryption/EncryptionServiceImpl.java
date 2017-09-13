package KraydelEncryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;



public class EncryptionServiceImpl{
	private static final String DEFAULT_SECRET_KEY = "adkj@#$02#@adflkj)(*jlj@##$02#@jasdjlkj<.,mo@#$2Z@#kljlkdsu343";
	private static final String UTF8 = "UTF-8";
	private static final String CYPER_TYPE = "AES/ECB/PKCS5Padding";

	private SecretKeySpec setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] key;
		MessageDigest sha = null;
		key = myKey.getBytes(UTF8);
		sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		return new SecretKeySpec(key, "AES");
	}


	public String encrypt(String message) throws Exception {
		try {
			return encode(getEncryptCipher().doFinal(message.getBytes(UTF8)));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new Exception(e);
		}
	}

	public String decrypt(String message) throws Exception {
		try {
			return new String(getDecryptCipher().doFinal(decode(message)));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new Exception(e);
		}
	}


	public String encryptToString(Long id) throws Exception {
		try {
			return encode(getEncryptCipher().doFinal(id.toString().getBytes(UTF8)));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new Exception(e);
		}

	}


	public static Long decryptToLong(String id) {
		try {
			EncryptionServiceImpl ency=new EncryptionServiceImpl();
			return Long.valueOf(new String(ency.getDecryptCipher().doFinal(ency.decode(id))));
		} catch (Exception e) {
			return null;
		}
	}

	private byte[] decode(String encodedMessage) {
		return Base64.getUrlDecoder().decode(encodedMessage.getBytes());
	}

	private String encode(byte[] rawMessage) {
		return Base64.getUrlEncoder().encodeToString(rawMessage);
	}

	private Cipher getEncryptCipher()
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecretKeySpec secretKey = setKey(DEFAULT_SECRET_KEY);
		Cipher cipher = Cipher.getInstance(CYPER_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher;
	}

	private Cipher getDecryptCipher()
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecretKeySpec secretKey = setKey(DEFAULT_SECRET_KEY);
		Cipher cipher = Cipher.getInstance(CYPER_TYPE);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher;
	}
}
