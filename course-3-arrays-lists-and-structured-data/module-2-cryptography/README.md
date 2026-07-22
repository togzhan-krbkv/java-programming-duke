# Module 2: Cryptography. Keeping Information Secret

In this module I learned the basics of cryptography by implementing and 
breaking classic substitution ciphers (used from the Roman Empire through 
the early 1900s), then refactored the code using object-oriented design.

## Topics covered

- Combining strings using concatenation and building strings with StringBuilder
- Using arrays to store and manipulate collections of data (letter frequency counts)
- Statistical letter-frequency analysis to break an encrypted message without knowing the key
- Refactoring procedural code into an object-oriented design (encapsulation, fields, constructors, visibility)
- Effective algorithm design and testing/debugging

## Projects

- [ CaesarCipherImplementation](./CaesarCipherImplementation) — Implementing the Caesar Cipher. 
  Encrypts text by shifting each letter by a fixed key, including a version 
  that alternates between two keys for even/odd character positions.

- [ CaesarCipherBreaking](./CaesarCipherBreaking) — Breaking the Caesar Cipher. 
  Decrypts a Caesar-encrypted message without knowing the key, using 
  statistical letter frequency analysis (assuming 'e' is the most common 
  letter in English), and extends this to break messages encrypted with 
  two alternating keys.

- [ ObjectOrientedCaesarCipher](./ObjectOrientedCaesarCipher) — Object 
  Oriented Caesar Cipher. Refactors the cipher into classes with 
  encapsulated state (the key and shifted alphabets become fields set in 
  the constructor), for both the single-key and two-key versions, including 
  automatic key-breaking.

- [ StringAndArrayPractice](./StringAndArrayPractice) — Practice exercises 
  on string and array manipulation covered earlier in the module: vowel 
  detection/replacement, emphasizing characters in a string, and finding 
  the most common word length in a text file.
