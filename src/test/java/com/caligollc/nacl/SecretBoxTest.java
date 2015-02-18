package com.caligollc.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by wfreeman on 2/18/15.
 */
public class SecretBoxTest {
	@Test
	public void TestSecretBox() {
		String expected = "8442bc313f4626f1359e3b50122b6ce6fe66ddfe7d39d14e637eb4fd5b45beadab55198df6ab5368439792a23c87db70acb6156dc5ef957ac04f6276cf6093b84be77ff0849cc33e34b7254d5a8f65ad";
		byte key[] = new byte[32];
		byte nonce[] = new byte[24];
		byte message[] = new byte[64];
		for(int i = 0; i < key.length; i++) {
			key[i] = 1;
		}
		for(int i = 0; i < nonce.length; i++) {
			nonce[i] = 2;
		}
		for(int i = 0; i < message.length; i++) {
			message[i] = 3;
		}
		byte result[] = SecretBox.seal(message, nonce, key);
		assertEquals(expected, DatatypeConverter.printHexBinary(result).toLowerCase());
	}

	@Test
	public void TestSecretBoxOpen() {
		String expected = "8442bc313f4626f1359e3b50122b6ce6fe66ddfe7d39d14e637eb4fd5b45beadab55198df6ab5368439792a23c87db70acb6156dc5ef957ac04f6276cf6093b84be77ff0849cc33e34b7254d5a8f65ad";
		byte key[] = new byte[32];
		byte nonce[] = new byte[24];
		byte message[] = new byte[64];
		for(int i = 0; i < key.length; i++) {
			key[i] = 1;
		}
		for(int i = 0; i < nonce.length; i++) {
			nonce[i] = 2;
		}
		for(int i = 0; i < message.length; i++) {
			message[i] = 3;
		}
		try {
			byte result[] = SecretBox.open(DatatypeConverter.parseHexBinary(expected), nonce, key);
			assertEquals(DatatypeConverter.printHexBinary(message).toLowerCase(), DatatypeConverter.printHexBinary(result).toLowerCase());
		} catch(NaclException e) {
			fail("threw exception, not valid");
		}
	}
}
