/**
 * MarkovWordTwo, an order 2 word level Markov model: generates text one
 * word at a time, choosing each next word based on the two words that
 * precede it in the training text.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo()
    {
        myRandom = new Random();
    }

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    public void setTraining(String text)
    {
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords)
    {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 2);
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");

        for (int k = 0; k < numWords - 2; k++)
        {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0)
            {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2)
    {
        ArrayList<String> follows = new ArrayList<String>();
        int startPos = 0;

        while (true)
        {
            int index = indexOf(myText, key1, key2, startPos);
            if (index == -1 || index == myText.length - 2)
            {
                break;
            }
            follows.add(myText[index + 2]);
            startPos = index + 2;
        }

        return follows;
    }

    private int indexOf(String[] words, String target1, String target2, int start)
    {
        // The loop must stop one word early (words.length - 1), since each
        // check looks at both words[i] and words[i + 1]. Without this, the
        // last comparison would read one index past the end of the array.
        for (int i = start; i < words.length - 1; i++)
        {
            if (words[i].equals(target1) && words[i + 1].equals(target2))
            {
                return i;
            }
        }
        return -1;
    }

    public String toString()
    {
        return "MarkovWordTwo";
    }
}
