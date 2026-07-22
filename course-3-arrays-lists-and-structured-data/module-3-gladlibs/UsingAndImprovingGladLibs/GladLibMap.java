/**
 * GladLibMap — a refactored version of GladLib that stores all word
 * categories in a single HashMap (category name -&gt; word list) instead of
 * one ArrayList field per category, making it easy to add new categories
 * without changing the substitution logic.
 *
 * @author Togzhan K.
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap
{
    private ArrayList<String> wordSeen;
    private int wordSeenCount = 0;
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> usedCategories;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/datalong";

    public GladLibMap()
    {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordSeen = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }

    public GladLibMap(String source)
    {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        wordSeen = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }

    private void initializeFromSource(String source)
    {
        String[] categories = {"adjective", "animal", "color", "country", "fruit", "name", "noun", "timeframe", "verb"};
        for (String category : categories)
        {
            ArrayList<String> list = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
    }

    private String randomFrom(ArrayList<String> source)
    {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    // Takes a category label and replaces it with a random word from myMap
    private String getSubstitute(String label)
    {
        if (label.equals("number"))
        {
            return "" + (myRandom.nextInt(50) + 5);
        }
        addUsedCategory(label);
        return randomFrom(myMap.get(label));
    }

    private void addUsedCategory(String label)
    {
        if (!usedCategories.contains(label))
        {
            usedCategories.add(label);
        }
    }

    private String processWord(String w)
    {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1)
        {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);

        String sub;
        while (true)
        {
            sub = getSubstitute(w.substring(first + 1, last));
            if (!wordSeen.contains(sub))
            {
                wordSeen.add(sub);
                wordSeenCount++;
                break;
            }
        }
        return prefix + sub + suffix;
    }

    public int totalWordsInMap()
    {
        int sum = 0;
        for (String category : myMap.keySet())
        {
            ArrayList<String> words = myMap.get(category);
            System.out.println("Category: " + category + "\tTotal words in category: " + words.size());
            sum += words.size();
        }
        System.out.println("Total words across all categories: " + sum);
        return sum;
    }

    public int totalWordsConsidered()
    {
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (String category : usedCategories)
        {
            int wordsInCategory = myMap.get(category).size();
            System.out.println("Category: " + category + "\tWords in category: " + wordsInCategory);
            sum += wordsInCategory;
        }
        System.out.println("Total words considered: " + sum);
        return sum;
    }

    private void printOut(String s, int lineWidth)
    {
        int charsWritten = 0;
        for (String w : s.split("\\s+"))
        {
            if (charsWritten + w.length() > lineWidth)
            {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source)
    {
        String story = "";
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for (String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        else
        {
            FileResource resource = new FileResource(source);
            for (String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        System.out.println("Total number of words replaced: " + wordSeenCount);
        return story;
    }

    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        else
        {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory()
    {
        wordSeen.clear();
        wordSeenCount = 0;
        usedCategories.clear();

        System.out.println();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);

        System.out.println();
        totalWordsInMap();
        totalWordsConsidered();
    }

    public static void main(String[] args)
    {
        GladLibMap glm = new GladLibMap();
        glm.makeStory();
    }
}
