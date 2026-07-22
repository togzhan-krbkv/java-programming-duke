/**
 * Figures out the most common word length among the words in a file.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WordLengths
{
    public int[] countWordLengths()
    {
        int[] counts = new int[50];
        try (BufferedReader resource = new BufferedReader(new FileReader("data/smallHamlet.txt")))
        {
            String s;
            while ((s = resource.readLine()) != null)
            {
                int wordLength = s.length();
                for (int i = 0; i < s.length(); i++)
                {
                    char currChar = s.charAt(i);
                    if ((i == 0 || i == s.length() - 1) && !Character.isLetter(currChar))
                    {
                        wordLength--;
                    }
                }
                counts[wordLength]++;
                System.out.println("Word of length " + wordLength + ": " + s);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return counts;
    }

    public void indexOfMax(int[] values)
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
        System.out.println("The most common word length is: " + position);
    }

    public void testCountWordLengths()
    {
        int[] counts = countWordLengths();
        indexOfMax(counts);
    }

    public static void main(String[] args)
    {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
