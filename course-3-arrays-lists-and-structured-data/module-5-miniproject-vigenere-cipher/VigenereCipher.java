/**
 * VigenereCipher — encrypts and decrypts a message using a Vigenère cipher:
 * an array of Caesar ciphers, one per key position, applied round-robin to
 * successive characters of the message.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class VigenereCipher
{
    CaesarCipher[] ciphers;

    public VigenereCipher(int[] key)
    {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++)
        {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    public String encrypt(String input)
    {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray())
        {
            CaesarCipher thisCipher = ciphers[i % ciphers.length];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String decrypt(String input)
    {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray())
        {
            CaesarCipher thisCipher = ciphers[i % ciphers.length];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    public String toString()
    {
        return Arrays.toString(ciphers);
    }
}
