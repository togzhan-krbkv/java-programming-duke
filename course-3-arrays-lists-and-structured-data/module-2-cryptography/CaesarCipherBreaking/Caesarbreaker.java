/**
 * CaesarBreaker decrypts a message encrypted with a single Caesar cipher key
 * using statistical letter frequencies of English text, and extends this to
 * break a message encrypted with two alternating keys.
 *
 * @author Togzhan K.
 */
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

public class CaesarBreaker
{
    public int[] countLetters(String message)
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

    public int maxIndex(int[] values)
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

    // Estimates the Caesar cipher key by assuming the most frequent letter
    // corresponds to 'e' (the most common letter in English), index 4.
    public int getKey(String s)
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

    public String decrypt(String encrypted, int key)
    {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26 - key);
    }

    public String decryptTwoKeys(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        String message1 = halfOfString(encrypted, 0);
        String message2 = halfOfString(encrypted, 1);
        StringBuilder decryptedTwo = new StringBuilder(encrypted);

        int key1 = getKey(message1);
        int key2 = getKey(message2);

        String decryptedMessage1 = cc.encrypt(message1, 26 - key1);
        String decryptedMessage2 = cc.encrypt(message2, 26 - key2);

        for (int k = 0; k < message1.length(); k++)
        {
            decryptedTwo.setCharAt(2 * k, decryptedMessage1.charAt(k));
        }
        for (int k = 0; k < message2.length(); k++)
        {
            decryptedTwo.setCharAt(2 * k + 1, decryptedMessage2.charAt(k));
        }

        System.out.println("The first key is " + key1 + "\nThe second key is: " + key2);
        System.out.println("The message decrypted with two keys:\n" + decryptedTwo);
        return decryptedTwo.toString();
    }

    public String halfOfString(String message, int start)
    {
        StringBuilder result = new StringBuilder();
        for (int k = start; k < message.length(); k += 2)
        {
            result.append(message.charAt(k));
        }
        return result.toString();
    }

    public void testHalfOfString()
    {
        try (BufferedReader resource = new BufferedReader(new FileReader("data/wordsLotsOfEs.txt")))
        {
            String s;
            String message = "";
            while ((s = resource.readLine()) != null)
            {
                message += s;
            }
            System.out.println("Half of string starting at 0: " + halfOfString(message, 0));
            System.out.println("Half of string starting at 1: " + halfOfString(message, 1));

            CaesarCipher cc = new CaesarCipher();
            String encrypted = cc.encrypt(halfOfString(message, 0), 20);
            String decrypted = decrypt(encrypted, 20);
            System.out.println(encrypted);
            System.out.println(decrypted);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The data was not found");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void testDecrypt()
    {
        int key = 23;
        try (BufferedReader fr = new BufferedReader(new FileReader("data/wordsLotsOfEs.txt")))
        {
            String c;
            String message = "";
            while ((c = fr.readLine()) != null)
            {
                message += c;
            }
            CaesarCipher cc = new CaesarCipher();
            String decrypted = cc.encrypt(message, 26 - key);
            System.out.println("Key is: " + key + "\n" + decrypted);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void testDecryptTwoKeys()
    {
        try (BufferedReader resource = new BufferedReader(new FileReader("data/twoKeysEncryptedMessage.txt")))
        {
            String s;
            String message = "";
            while ((s = resource.readLine()) != null)
            {
                message += s;
            }
            String decryptedMessage = decryptTwoKeys(message);
            System.out.println(message);
            System.out.println(decryptedMessage);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The data is missing");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
