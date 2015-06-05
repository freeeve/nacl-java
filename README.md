# nacl-java

A port of [NaCl](http://nacl.cr.yp.to/) to Java. I had too much trouble getting android to build with JNI NaCl, so here you go... certainly not intended for anyone else to use; it's terribly unoptimized and not reviewed by a security professional.

## TODO

* clean up unnecessary byte array allocations (partly done)
* more thorough testing cross platform (I can test encrypt/decrypt random data with this lib, but if I don't test cross platform with another NaCl lib, there are potentially some symmetric bugs that might not show up)
* security review?

# LICENSE

MIT License.
