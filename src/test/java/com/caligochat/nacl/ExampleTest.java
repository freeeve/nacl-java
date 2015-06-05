package com.caligochat.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by wfreeman on 6/5/15.
 */
public class ExampleTest {
    @Test
    public void ExampleTest() throws Exception {
        // string to encrypt
        String helloBob = "Hello, Bob!";
        // we need it in bytes
        byte[] helloBobBytes = helloBob.getBytes("UTF-8");

        // get a nonce
        byte nonce[] = new byte[24];
        SecureRandom r = new SecureRandom();
        r.nextBytes(nonce);

        // generate some keypairs (this probably won't be done in the same spot as the rest of this code)
        Box.KeyPair alice = Box.generate();
        Box.KeyPair bob = Box.generate();

        // make some boxes with the keypairs
        Box aliceToBob = new Box(bob.pubKey, alice.privKey);
        Box bobFromAlice = new Box(alice.pubKey, bob.privKey);

        // seal the box (encrypt)
        byte box[] = aliceToBob.seal(helloBobBytes, nonce);

        // voila!
        try {
            // open the box (decrypt)
            byte decryptedBytes[] = bobFromAlice.open(box, nonce);

            String decryptedString = new String(decryptedBytes, "UTF-8");
            assertEquals(helloBob, decryptedString);
        } catch(NaclException e) {
            fail("failed to decrypt");
        }
    }
}
