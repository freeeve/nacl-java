# nacl-java

A port of NaCl to Java. I had too much trouble getting android to build with JNI NaCl, so here you go... certainly not intended for anyone else to use; it's terribly slow and certainly not reviewed by a security professional.

## TODO

* clean up unnecessary byte array allocations
* more thorough testing cross platform (I can test encrypt/decrypt random data with this lib, but if I don't test cross platform with another NaCl lib, there are potentially some symmetric bugs that might not show up; as demonstrated with the latest commit)
* security review?

# LICENSE

