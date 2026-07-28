/**
 * WordGram, an immutable sequence of consecutive words, used as a key for
 * predicting the next word in word level Markov text generation.
 * Implements equals and hashCode based on the sequence of words, so two
 * WordGrams with the same words in the same order are considered equal
 * and can be used as HashMap keys.
 *
 * @author Togzhan K.
 */
public class WordGram
{
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size)
    {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index)
    {
        if (index < 0 || index >= myWords.length)
        {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length()
    {
        return myWords.length;
    }

    public String toString()
    {
        String ret = "";
        for (int i = 0; i < myWords.length; i++)
        {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public WordGram shiftAdd(String word)
    {
        String[] myWordsCopy = new String[myWords.length];

        for (int i = 0; i < this.length() - 1; i++)
        {
            myWordsCopy[i] = this.wordAt(i + 1);
        }
        myWordsCopy[this.length() - 1] = word;

        return new WordGram(myWordsCopy, 0, this.length());
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof WordGram))
        {
            return false;
        }

        WordGram other = (WordGram) o;
        if (this.length() != other.length())
        {
            return false;
        }

        for (int i = 0; i < this.length(); i++)
        {
            if (!this.wordAt(i).equals(other.wordAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        myHash = this.toString().hashCode();
        return myHash;
    }
}
