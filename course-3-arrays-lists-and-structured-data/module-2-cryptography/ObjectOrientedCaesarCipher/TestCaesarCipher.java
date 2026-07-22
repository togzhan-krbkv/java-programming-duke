import java.util.Scanner;

public class TestCaesarCipher
{
    public void simpleTests()
    {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        System.out.println("The encryption result is " + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("The decryption outcome is " + decrypted);

        String broken = breakCaesarCipher(encrypted);
        System.out.println("The broken (auto-decrypted) result is " + broken);
    }

    public String breakCaesarCipher(String input)
    {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (key < 0)
        {
            key = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }

    private int maxIndex(int[] values)
    {
        int max = 0;
        int position = 0;
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] > max)
            {
                max = values[i];
                position = i;
            }
        }
        return position;
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

    public static void main(String[] args)
    {
        TestCaesarCipher t = new TestCaesarCipher();
        t.simpleTests();
    }
}
