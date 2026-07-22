/**
 * GladLib — generates a randomly filled-in "mad lib" style story from a
 * template file, substituting placeholder labels (like &lt;noun&gt;,
 * &lt;adjective&gt;) with random words from category word lists, without
 * repeating a word already used in the same story.
 *
 * @author Togzhan K.
 */
import edu.duke.*;
import java.util.*;

public class GladLib
{
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> wordSeen;
    private int wordSeenCount = 0;

    private Random myRandom;

    // Alternative remote source for the word lists (not used by default;
    // dataSourceDirectory below is used instead)
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/datalong";

    public GladLib()
    {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordSeen = new ArrayList<String>();
    }

    public GladLib(String source)
    {
        initializeFromSource(source);
        myRandom = new Random();
        wordSeen = new ArrayList<String>();
    }

    private void initializeFromSource(String source)
    {
        adjectiveList = readIt(source + "/adjective.txt");
        nounList = readIt(source + "/noun.txt");
        colorList = readIt(source + "/color.txt");
        countryList = readIt(source + "/country.txt");
        nameList = readIt(source + "/name.txt");
        animalList = readIt(source + "/animal.txt");
        timeList = readIt(source + "/timeframe.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
    }

    private String randomFrom(ArrayList<String> source)
    {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    // Takes a category label and replaces it with a random word from the
    // matching ArrayList
    private String getSubstitute(String label)
    {
        if (label.equals("country")) return randomFrom(countryList);
        if (label.equals("color")) return randomFrom(colorList);
        if (label.equals("noun")) return randomFrom(nounList);
        if (label.equals("name")) return randomFrom(nameList);
        if (label.equals("adjective")) return randomFrom(adjectiveList);
        if (label.equals("animal")) return randomFrom(animalList);
        if (label.equals("timeframe")) return randomFrom(timeList);
        if (label.equals("verb")) return randomFrom(verbList);
        if (label.equals("fruit")) return randomFrom(fruitList);
        if (label.equals("number")) return "" + (myRandom.nextInt(50) + 5);
        return "**UNKNOWN**";
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
        System.out.println();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
    }

    public static void main(String[] args)
    {
        GladLib gl = new GladLib();
        gl.makeStory();
    }
}
