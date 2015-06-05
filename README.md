# nacl-java

[![Build Status](https://travis-ci.org/wfreeman/nacl-java.svg?branch=master)](https://travis-ci.org/wfreeman/nacl-java)

A port of [NaCl](http://nacl.cr.yp.to/) to Java. I had too much trouble getting android to build with JNI NaCl, so here you go... certainly not intended for anyone else to use; it's terribly unoptimized and not reviewed by a security professional.

## TODO

* remove java 7 stdlib use (the dataconverter stuff isn't in android's java)
* improve readme
* improve javadocs
* maven repo?
* clean up unnecessary byte array allocations (partly done)
* more thorough testing cross platform (I can test encrypt/decrypt random data with this lib, but if I don't test cross platform with another NaCl lib, there are potentially some symmetric bugs that might not show up)
* security review?

# LICENSE

MIT License.
