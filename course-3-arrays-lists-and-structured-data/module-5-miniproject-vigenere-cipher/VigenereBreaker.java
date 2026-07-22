/**
 * VigenereBreaker — breaks a Vigenère-encrypted message in three
 * progressively harder stages: (1) known language and key length,
 * (2) known language, unknown key length, (3) unknown language and
 * unknown key length, by trying every language's dictionary and keeping
 * whichever decryption has the most real dictionary words.
 *
 * @author Togzhan K.
 */
import java.util.*;
import edu.duke.*;

public class VigenereBreaker
{
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        StringBuilder slicedMessage = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        {
            slicedMessage.append(message.charAt(i));
        }
        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon)
    {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++)
        {
            String slicedMessage = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slicedMessage);
        }
        return key;
    }

    // Stage 1: known language (English, 'e') and known key length
    public void breakVigenere()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Decrypted message = " + decrypted);
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> wordList = new HashSet<String>();
        for (String word : fr.lines())
        {
            wordList.add(word.toLowerCase());
        }
        return wordList;
    }

    public int countWords(String message, HashSet<String> dictionary)
    {
        String[] wordList = message.split("\\W+");
        int totalOccurrences = 0;
        for (String word : wordList)
        {
            if (dictionary.contains(word.toLowerCase()))
            {
                totalOccurrences++;
            }
        }
        return totalOccurrences;
    }

    // Tries every key length from 1 to 100 and keeps the decryption with
    // the most real words from the given language's dictionary
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        String finalMessage = "";
        int maxOccurrences = Integer.MIN_VALUE;
        char mostCommon = mostCommonCharIn(dictionary);

        for (int i = 1; i <= 100; i++)
        {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currOccurrences = countWords(decrypted, dictionary);

            if (currOccurrences > maxOccurrences)
            {
                maxOccurrences = currOccurrences;
                finalMessage = decrypted;
            }
        }
        return finalMessage;
    }

    // Stage 2: known language (English), unknown key length
    public void breakVigenereUnknownKey()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dictResource = new FileResource("data/dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictResource);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println("Decrypted message = " + decrypted);
    }

    // Finds the most common letter of the English alphabet (a-z) among all
    // words in dictionary
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();

        for (String word : dictionary)
        {
            for (int i = 0; i < word.length(); i++)
            {
                char currChar = Character.toLowerCase(word.charAt(i));
                if (currChar < 'a' || currChar > 'z')
                {
                    continue;
                }
                if (!charCounts.containsKey(currChar))
                {
                    charCounts.put(currChar, 1);
                }
                else
                {
                    charCounts.put(currChar, charCounts.get(currChar) + 1);
                }
            }
        }

        char mostCommon = 'e';
        int maxOccurrences = Integer.MIN_VALUE;
        for (char key : charCounts.keySet())
        {
            int currCount = charCounts.get(key);
            if (currCount > maxOccurrences)
            {
                maxOccurrences = currCount;
                mostCommon = key;
            }
        }
        return mostCommon;
    }

    // Stage 3: tries every language's dictionary and keeps whichever
    // decryption matches the most real words
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int maxOccurrences = Integer.MIN_VALUE;
        String detectedLanguage = "";
        String decrypted = "";

        for (String language : languages.keySet())
        {
            HashSet<String> dictionary = languages.get(language);
            String currentDecryption = breakForLanguage(encrypted, dictionary);
            int currentOccurrences = countWords(currentDecryption, dictionary);

            if (currentOccurrences > maxOccurrences)
            {
                detectedLanguage = language;
                maxOccurrences = currentOccurrences;
                decrypted = currentDecryption;
            }
        }

        System.out.println("Language = " + detectedLanguage);
        System.out.println("Decrypted message = " + decrypted);
    }

    // Stage 3 entry point: unknown language, unknown key length
    public void breakVigenereUnknownKeyAndLanguage()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] languageList = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};

        for (String language : languageList)
        {
            FileResource dictResource = new FileResource("data/dictionaries/" + language);
            languages.put(language, readDictionary(dictResource));
            System.out.println("Finished reading " + language + " dictionary");
        }

        breakForAllLangs(encrypted, languages);
    }
}
