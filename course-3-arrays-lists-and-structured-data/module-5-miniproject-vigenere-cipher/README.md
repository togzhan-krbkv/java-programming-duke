# Module 5: MiniProject — Breaking the Vigenère Cipher

This final project builds a program that breaks the Vigenère cipher — a 
more complex, multi-key extension of the Caesar cipher — in three 
progressively harder stages: known language and key length, unknown key 
length, and finally unknown language and unknown key length.

## What the program does

- **CaesarCipher / CaesarCracker**: the building blocks — encrypting, 
  decrypting, and statistically breaking a single-key Caesar cipher based 
  on letter frequency.
- **VigenereCipher**: encrypts/decrypts using an array of Caesar ciphers, 
  one per key position, applied round-robin across the message.
- **VigenereBreaker**:
  - `breakVigenere()` — Stage 1: breaks a message when both the language 
    (English, most common letter 'e') and the key length are known.
  - `breakVigenereUnknownKey()` — Stage 2: tries every key length from 1 
    to 100 and keeps whichever decryption matches the most real English 
    dictionary words.
  - `breakVigenereUnknownKeyAndLanguage()` — Stage 3: reads dictionaries 
    for eight languages (Danish, Dutch, English, French, German, Italian, 
    Portuguese, Spanish), finds each language's most common letter via 
    `mostCommonCharIn()`, and tries breaking the message in every 
    language — the language with the most matched dictionary words wins.

## Data

Encrypted test messages (e.g. `athens_keyflute.txt`) and dictionaries for 
all eight languages are provided by the course and stored under `data/`.
