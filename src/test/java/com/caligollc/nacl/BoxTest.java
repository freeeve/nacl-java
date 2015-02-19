package com.caligollc.nacl;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by wfreeman on 2/18/15.
 */
public class BoxTest {
	@Test
	public void TestBoxDecrypt() {
		byte raw[] = DatatypeConverter.parseBase64Binary("/9D6iXp2OdxMfVh+CHbk7/AAoyAgMGasnehvDBmZuZFGwkw2pBxmxhTSlT1d+QRMFG464R/5JcDsNQAsmfxQWC5ZtLdF1rvnHz1W2e/4bgd3j7kNV0QWC13CVxsFVANadkbmhYHl7WSuIsG5972ZT4rKIDlTPkqtHhreCo52giOqnFeIHWRNq6mhugnvnRYNHeFIGUqMnWe0XmVyzFfAANk=");
		byte nonce[] = new byte[24];
		for(int i = 0; i < nonce.length; i++) {
			nonce[i] = raw[i];
		}
		byte cmsg[] = new byte[raw.length - 24];
		for(int i = 0; i < cmsg.length; i++) {
			cmsg[i] = raw[i+24];
		}

		byte privkey[] = DatatypeConverter.parseBase64Binary("ceCUea+ufmPbR5/P6C0l/w+kj4Cs+QnRagDRGiZxVig=");
		byte pubkey[] = DatatypeConverter.parseBase64Binary("DAyeHkFGQsyBkidxBCVIUKiwhXJyhT6IFPRiz6HI7V4=");

		Box b = new Box(pubkey, privkey);
		try {
			byte bytes[] = b.open(cmsg, nonce);
			String expected = "7b0a202022636f6e74616374496422203a2022323333373630353835222c0a202022636f6e74656e7422203a2022506f6f70222c0a2020226d6573736167655479706522203a20302c0a20202274696d657374616d7022203a203434353337323533392c0a20202274696d65546f4c69766522203a20300a7d";
			//System.out.println(new String(bytes, "UTF-8"));
			assertEquals(expected, DatatypeConverter.printHexBinary(bytes).toLowerCase());
		} catch(NaclException e) {
			fail("got an exception decrypting");
		} catch(Exception e) {
			fail("wut");
		}
	}

	@Test
	public void TestLong() {
		for(int i = 0; i < 100; i++) {
			Box.KeyPair alice = Box.generate();
			Box.KeyPair bob = Box.generate();
			Box aliceToBob = new Box(bob.pubKey, alice.privKey);
			Box bobFromAlice = new Box(alice.pubKey, bob.privKey);

			SecureRandom r = new SecureRandom();
			byte stuff[] = new byte[r.nextInt(100000)];
			r.nextBytes(stuff);

			byte nonce[] = new byte[24];
			r.nextBytes(nonce);

			byte cipherstuff[] = aliceToBob.seal(stuff, nonce);
			byte after[];
			try {
				//System.out.println(i);
				after = bobFromAlice.open(cipherstuff, nonce);
				assertEquals(DatatypeConverter.printHexBinary(after), DatatypeConverter.printHexBinary(stuff));
			} catch(NaclException e) {
				Util.printHex("stuff", stuff);
				Util.printHex("cipherstuff", cipherstuff);
				Util.printHex("alicepub", alice.pubKey);
				Util.printHex("alicepriv", alice.privKey);
				Util.printHex("bobpub", bob.pubKey);
				Util.printHex("bobpriv", bob.privKey);
				fail("failed to decrypt");
			}
		}
	}
}
