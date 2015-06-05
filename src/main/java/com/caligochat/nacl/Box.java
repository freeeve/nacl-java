package com.caligochat.nacl;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

public class Box {
    private byte privKey[];
    private byte pubKey[];
    private byte sharedKey[];
    private byte zeros[] = new byte[16];

    /**
     * Box convenience constructor takes base64 of public / private key
     *
     * @param peerPublicKey base64 encoding of public key
     * @param privateKey    base64 encoding of private key
     */
    public Box(String peerPublicKey, String privateKey) {
        this(DatatypeConverter.parseBase64Binary(privateKey), DatatypeConverter.parseBase64Binary(peerPublicKey));
    }

    public Box(byte[] peerPublicKey, byte[] privateKey) {
        privKey = privateKey;
        pubKey = peerPublicKey;
        sharedKey = precompute(pubKey, privKey);
    }

    public static KeyPair generate() {
        KeyPair kp = new KeyPair();
        SecureRandom random = new SecureRandom();
        kp.privKey = new byte[32];
        random.nextBytes(kp.privKey);
        kp.pubKey = Curve25519.scalarBaseMult(kp.privKey);
        return kp;
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

    public byte[] seal(byte message[], byte nonce[]) {
        return SecretBox.seal(message, nonce, sharedKey);
    }

    public byte[] open(byte box[], byte nonce[]) throws NaclException {
        return SecretBox.open(box, nonce, sharedKey);
    }

    public static class KeyPair {
        public byte pubKey[];
        public byte privKey[];
    }
}
