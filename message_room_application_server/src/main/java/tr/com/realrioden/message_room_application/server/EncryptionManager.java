package tr.com.realrioden.message_room_application.server;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.stereotype.Component;

import tr.com.realrioden.message_room_application.service.EncryptionService;

@Component
public class EncryptionManager implements EncryptionService{

	
	public SecretKey generateKey(String method, int keyBitSize) throws Exception {
        KeyGenerator keygenerator = KeyGenerator.getInstance(method);
        SecureRandom secure = new SecureRandom();
        keygenerator.init(keyBitSize, secure);
        SecretKey key = keygenerator.generateKey();
        return key;
    }

    public byte[] createInitializationVector(int bitSize) {
        byte[] initializationVector = new byte[bitSize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public String encryption(String algorithm, SecretKey key, String plainText, byte[] initializationVector) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec	= new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        String cipherTextString =  Base64.getEncoder().encodeToString(cipherText);
        return cipherTextString;
    }

    public String decryption(String algorithm, SecretKey key, String cipherText, byte[] initializationVector) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec	= new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        //byte[] text = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
    }
	
	
	
}
