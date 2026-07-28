/**
 * MarkovZero, an order 0 Markov model: generates each character
 * independently at random from the training text, with no dependence on
 * previous characters.
 *
 * @author Togzhan K.
 */
import java.util.Random;

public class MarkovZero extends AbstractMarkovModel
{
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

    public String toString()
    {
        return "MarkovModel of order 0.";
    }
}
