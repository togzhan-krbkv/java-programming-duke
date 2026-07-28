/**
 * WordGramTester, tests the WordGram class: constructing WordGrams from a
 * source text and checking equality between them.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class WordGramTester
{
    public void testWordGram()
    {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;

        for (int index = 0; index <= words.length - size; index += 1)
        {
            WordGram wg = new WordGram(words, index, size);
            System.out.println(index + "\t" + wg.length() + "\t" + wg);
        }
    }

    public void testWordGramEquals()
    {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;

        for (int index = 0; index <= words.length - size; index += 1)
        {
            WordGram wg = new WordGram(words, index, size);
            list.add(wg);
        }

        WordGram first = list.get(0);
        System.out.println("checking " + first);
        for (int k = 0; k < list.size(); k++)
        {
            // Using == here would only match the exact same object in
            // memory, not two different WordGrams with equal words, which
            // is why equals() must be implemented explicitly.
            if (first.equals(list.get(k)))
            {
                System.out.println("matched at " + k + " " + list.get(k));
            }
        }
    }

    public static void main(String[] args)
    {
        WordGramTester t = new WordGramTester();
        t.testWordGram();
        System.out.println();
        t.testWordGramEquals();
    }
}
