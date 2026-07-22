/**
 * CaesarCracker — breaks a Caesar-encrypted message using statistical
 * letter frequency analysis, assuming a given character (by default 'e')
 * is the most common letter in the underlying language.
 *
 * @author Togzhan K.
 */
public class CaesarCracker
{
    char mostCommon;

    public CaesarCracker()
    {
        mostCommon = 'e';
    }

    public CaesarCracker(char c)
    {
        mostCommon = c;
    }

    public int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++)
        {
            int idx = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (idx != -1)
            {
                counts[idx] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals)
    {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++)
        {
            if (vals[k] > vals[maxDex])
            {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted)
    {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos)
        {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted)
    {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
    }
}
