/**
 * MarkovZero, an order 0 Markov model: generates each character
 * independently at random from the training text, with no dependence on
 * previous characters. This is the standalone version before the code
 * was refactored to share a common interface and abstract base class.
 *
 * @author Togzhan K.
 */
import java.util.Random;

public class MarkovZero
{
    private String myText;
    private Random myRandom;

    public MarkovZero()
    {
        myRandom = new Random();
    }

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    public void setTraining(String s)
    {
        myText = s.trim();
    }

    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++)
        {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
}
