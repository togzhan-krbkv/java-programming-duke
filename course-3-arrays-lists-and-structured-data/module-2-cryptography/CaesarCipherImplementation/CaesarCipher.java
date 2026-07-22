import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CaesarCipher
{
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1)
            {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    // Same as encrypt(), but handles both uppercase and lowercase letters
    public String encryptChanged(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseAlphabet = uppercaseAlphabet.toLowerCase();
        String uppercaseShifted = uppercaseAlphabet.substring(key) + uppercaseAlphabet.substring(0, key);
        String lowercaseShifted = lowercaseAlphabet.substring(key) + lowercaseAlphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted.charAt(idx));
                }
            }
            else
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }

    // Encrypts even-position characters with key1 and odd-position characters with key2
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseAlphabet = uppercaseAlphabet.toLowerCase();
        String uppercaseShifted1 = uppercaseAlphabet.substring(key1) + uppercaseAlphabet.substring(0, key1);
        String lowercaseShifted1 = lowercaseAlphabet.substring(key1) + lowercaseAlphabet.substring(0, key1);
        String uppercaseShifted2 = uppercaseAlphabet.substring(key2) + uppercaseAlphabet.substring(0, key2);
        String lowercaseShifted2 = lowercaseAlphabet.substring(key2) + lowercaseAlphabet.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i += 2)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted1.charAt(idx));
                }
            }
            else if (Character.isUpperCase(currChar))
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted1.charAt(idx));
                }
            }
        }

        for (int i = 1; i < encrypted.length(); i += 2)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted2.charAt(idx));
                }
            }
            else if (Character.isUpperCase(currChar))
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted2.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt()
    {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
    }

    public void testEncryptChanged()
    {
        System.out.println(encryptChanged("First Legion", 23) + "\n");
        System.out.println(encryptChanged("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15) + "\n");
    }

    public void testEncryptFromFile()
    {
        int key = 23;
        try (BufferedReader inputFile = new BufferedReader(new FileReader("data/message1.txt")))
        {
            String c;
            String message = "";
            while ((c = inputFile.readLine()) != null)
            {
                message += c;
            }
            String encrypted = encrypt(message, key);
            System.out.println("Key is: " + key + "\n" + encrypted);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An exception was found");
        }
    }

    public void testEncryptTwoKeys()
    {
        int key1 = 14;
        int key2 = 24;
        try (BufferedReader fr = new BufferedReader(new FileReader("data/message2.txt")))
        {
            String s;
            String frToString = "";
            while ((s = fr.readLine()) != null)
            {
                frToString += s;
            }
            String encrypted = encryptTwoKeys(frToString, key1, key2);
            System.out.println("Key1 is: " + key1 + " and Key2 is: " + key2 + "\n" + encrypted);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The message is missing");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
