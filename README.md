# nacl-java

[![Build Status](https://travis-ci.org/wfreeman/nacl-java.svg?branch=master)](https://travis-ci.org/wfreeman/nacl-java)[![Coverage Status](https://coveralls.io/repos/wfreeman/nacl-java/badge.svg?branch=master)](https://coveralls.io/r/wfreeman/nacl-java?branch=master)

A port of [NaCl](http://nacl.cr.yp.to/) to Java. I had too much trouble getting android to build with JNI NaCl, so here you go... certainly not intended for anyone else to use (but here it is, in case you find it useful); it's terribly unoptimized and not reviewed by a security professional.

## minimal viable snippet
Also available as an [example unit test](https://github.com/wfreeman/nacl-java/blob/master/src/test/java/com/caligochat/nacl/ExampleTest.java).
```Java
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
```

## TODO

* remove java 7 stdlib use (the dataconverter stuff isn't in android's java)
* improve readme
* improve javadocs
* maven repo?
* clean up unnecessary byte array allocations (partly done)
* more thorough testing cross platform (I can test encrypt/decrypt random data with this lib, but if I don't test cross platform with another NaCl lib, there are potentially some symmetric bugs that might not show up)
* security review?

## LICENSE

MIT License.
