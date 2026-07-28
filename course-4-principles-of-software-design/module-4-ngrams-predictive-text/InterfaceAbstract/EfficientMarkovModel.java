/**
 * EfficientMarkovModel, an order n Markov model that precomputes a
 * HashMap from each n gram to the list of characters that follow it, so
 * that generating text does not require rescanning the whole training
 * text for every character.
 *
 * @author Togzhan K.
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel
{
    private int n;
    private HashMap<String, ArrayList<String>> followsMap;

    public EfficientMarkovModel(int n)
    {
        this.n = n;
        this.followsMap = new HashMap<String, ArrayList<String>>();
    }

    public void setTraining(String s)
    {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);

        for (int k = 0; k < numChars - n; k++)
        {
            ArrayList<String> followList = getFollows(key);
            if (followList.size() == 0)
            {
                break;
            }
            index = myRandom.nextInt(followList.size());
            String next = followList.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public void buildMap()
    {
        for (int i = 0; i <= myText.length() - n; i++)
        {
            String key = myText.substring(i, i + n);

            if (i == myText.length() - n)
            {
                // The last n-gram in the text has no follower. Only add an
                // empty entry if this key was never seen before, so we do
                // not overwrite follow characters recorded from earlier
                // occurrences of the same key.
                if (!followsMap.containsKey(key))
                {
                    followsMap.put(key, new ArrayList<String>());
                }
                continue;
            }

            ArrayList<String> follows;
            if (!followsMap.containsKey(key))
            {
                follows = new ArrayList<String>();
            }
            else
            {
                follows = followsMap.get(key);
            }

            follows.add(myText.substring(i + key.length(), i + key.length() + 1));
            followsMap.put(key, follows);
        }
    }

    public ArrayList<String> getFollows(String key)
    {
        return followsMap.get(key);
    }

    private int getLargestValue()
    {
        int max = Integer.MIN_VALUE;
        for (String key : followsMap.keySet())
        {
            if (followsMap.get(key).size() > max)
            {
                max = followsMap.get(key).size();
            }
        }
        return max;
    }

    private ArrayList<String> getLargestKey()
    {
        int max = getLargestValue();
        ArrayList<String> keyList = new ArrayList<String>();
        for (String key : followsMap.keySet())
        {
            if (followsMap.get(key).size() == max)
            {
                keyList.add(key);
            }
        }
        return keyList;
    }

    public void printHashMapInfo()
    {
        for (String key : followsMap.keySet())
        {
            System.out.println(key + " - " + followsMap.get(key));
        }

        ArrayList<String> largestKeyList = getLargestKey();
        System.out.println("# of keys = " + followsMap.size());
        System.out.println("largest value = " + followsMap.get(largestKeyList.get(0)).size());
        for (String key : largestKeyList)
        {
            System.out.println("largest key = " + key);
        }
    }

    public String toString()
    {
        return "EfficientMarkovModel of order " + n + ".";
    }
}
