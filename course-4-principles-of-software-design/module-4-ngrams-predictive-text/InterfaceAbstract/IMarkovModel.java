/**
 * IMarkovModel, common interface for text generation models trained on a
 * body of text, so that different orders and implementations can be used
 * interchangeably.
 *
 * @author Togzhan K.
 */
public interface IMarkovModel
{
    void setTraining(String text);

    void setRandom(int seed);

    String getRandomText(int numChars);
}
