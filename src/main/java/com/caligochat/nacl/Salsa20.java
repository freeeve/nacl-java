package com.caligochat.nacl;

/**
 * Created by wfreeman on 2/11/15.
 */
public class Salsa20 {
    public static byte[] XORKeyStream(byte in[], byte nonce[], byte key[]) throws NaclException {
        byte out[];
        byte subNonce[] = new byte[16];

        if (nonce.length == 24) {
            byte subKey[];
            byte hNonce[] = new byte[16];
            for (int i = 0; i < 16; i++) {
                hNonce[i] = nonce[i];
            }
            subKey = Salsa.HSalsa20(hNonce, key, Salsa.SIGMA);
            for (int i = 0; i < 8; i++) {
                subNonce[i] = nonce[i + 16];
            }
            key = subKey;
        } else if (nonce.length == 8) {
            for (int i = 0; i < 8; i++) {
                subNonce[i] = nonce[i];
            }
        } else {
            throw new NaclException("salsa20: nonce must be 8 or 24 bytes, not " + nonce.length);
        }

        out = Salsa.XORKeyStream(in, subNonce, key);
        return out;
    }

}
