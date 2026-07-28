/**
 * AbstractMarkovModel, shared base class for Markov text models: stores
 * the training text and random number generator, and provides a default,
 * string search based way to find the characters that follow a given key
 * in the training text. Subclasses implement getRandomText for a specific
 * order.
 *
 * @author Togzhan K.
 */
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel
{
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel()
    {
        myRandom = new Random();
    }

    public void setTraining(String s)
    {
        myText = s.trim();
    }

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> result = new ArrayList<String>();
        int startPos = 0;

        while (true)
        {
            int index = myText.indexOf(key, startPos);
            if (index == -1 || index == myText.length() - key.length())
            {
                break;
            }
            result.add(myText.substring(index + key.length(), index + key.length() + 1));
            startPos = index + key.length();
        }

        return result;
    }

    public abstract String getRandomText(int numChars);
}
