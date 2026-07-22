/**
 * Tests CaesarCipherTwo, including a method that automatically decrypts an
 * encrypted message by determining the two keys that were used to encrypt it.
 *
 * @author Togzhan K.
 */
import java.util.Scanner;

public class TestCaesarCipherTwo
{
    public void simpleTests()
    {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        CaesarCipherTwo cc2 = new CaesarCipherTwo(21, 8);
        String encrypted = cc2.encrypt(message);
        System.out.println("The encryption result is " + encrypted);

        String decrypted = cc2.decrypt(encrypted);
        System.out.println("The decryption outcome is " + decrypted);

        // Break the ENCRYPTED message, not the original plaintext
        String broken = breakCaesarCipher(encrypted);
        System.out.println("The broken (auto-decrypted) result is " + broken);
    }

    private String halfOfString(String message, int start)
    {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < message.length(); i += 2)
        {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    private int[] countLetters(String message)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++)
        {
            char ch = Character.toLowerCase(message.charAt(k));
            int idx = alphabet.indexOf(ch);
            if (idx != -1)
            {
                counts[idx]++;
            }
        }
        return counts;
    }

    private int getKey(String s)
    {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4)
        {
            key = 26 - (4 - maxDex);
        }
        return key;
    }

    private int maxIndex(int[] values)
    {
        int max = 0;
        int indexOfMax = 0;
        for (int k = 0; k < values.length; k++)
        {
            if (values[k] > max)
            {
                max = values[k];
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }

    private String breakCaesarCipher(String input)
    {
        String message1 = halfOfString(input, 0);
        String message2 = halfOfString(input, 1);

        int key1 = getKey(message1);
        int key2 = getKey(message2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decryption = cc.decrypt(input);

        System.out.println("The first key is " + key1 + "\nThe second key is: " + key2);
        return decryption;
    }

    public static void main(String[] args)
    {
        TestCaesarCipherTwo t = new TestCaesarCipherTwo();
        t.simpleTests();
    }
}
