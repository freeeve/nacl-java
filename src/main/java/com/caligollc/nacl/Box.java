package com.caligollc.nacl;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Box {
	private byte privKey[];
	private byte pubKey[];
	private byte sharedKey[];
	private byte zeros[] = new byte[16];

	/**
	 * Box convenience constructor takes base64 of public / private key
	 *
	 * @param peerPublicKey base64 encoding of public key
	 * @param privateKey base64 encoding of private key
	 */
	public Box(String peerPublicKey, String privateKey) {
		privKey = DatatypeConverter.parseBase64Binary(privateKey);
		pubKey = DatatypeConverter.parseBase64Binary(peerPublicKey);
		sharedKey = precompute(pubKey, privKey);
	}

	public Box(byte[] peerPublicKey, byte[] privateKey) {
		privKey = privateKey;
		pubKey = peerPublicKey;
		sharedKey = precompute(pubKey, privKey);
	}

	private byte[] precompute(byte[] peersPublicKey, byte[] privateKey) {
		byte sharedKey[];
		sharedKey = Curve25519.scalarMult(privateKey, peersPublicKey);
		sharedKey = Salsa.HSalsa20(zeros, sharedKey, Salsa.SIGMA);
		return sharedKey;
	}

	public byte[] open(String box, String nonce) throws NaclException {
		return SecretBox.open(DatatypeConverter.parseBase64Binary(box), DatatypeConverter.parseBase64Binary(nonce), sharedKey);
	}
}
