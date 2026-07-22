/**
 * WordsInFiles — maps each word to the list of files it appears in, so we
 * can determine which words appear in the greatest number of files.
 *
 * @author Togzhan K.
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles
{
    private HashMap<String, ArrayList<String>> wordFileMap;

    public WordsInFiles()
    {
        wordFileMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fileResource = new FileResource(f);
        String name = f.getName();

        for (String word : fileResource.words())
        {
            word = word.toLowerCase();
            if (!wordFileMap.containsKey(word))
            {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(name);
                wordFileMap.put(word, newList);
            }
            else if (!wordFileMap.get(word).contains(name))
            {
                wordFileMap.get(word).add(name);
            }
        }
    }

    public void buildWordFileMap()
    {
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }

    // Returns the maximum number of files any word appears in
    public int maxNumber()
    {
        int max = 0;
        for (String word : wordFileMap.keySet())
        {
            int currentNum = wordFileMap.get(word).size();
            if (currentNum > max)
            {
                max = currentNum;
            }
        }
        return max;
    }

    // Returns all words that appear in exactly `number` files
    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> result = new ArrayList<String>();
        for (String word : wordFileMap.keySet())
        {
            if (wordFileMap.get(word).size() == number)
            {
                result.add(word);
            }
        }
        return result;
    }

    public void printFilesIn(String word)
    {
        ArrayList<String> fileNames = wordFileMap.get(word);
        for (String fileName : fileNames)
        {
            System.out.println(fileName);
        }
    }

    public void test()
    {
        buildWordFileMap();
        int maximum = maxNumber();
        ArrayList<String> wordsInMostFiles = wordsInNumFiles(maximum);

        System.out.println("The maximum number of files any word is in: " + maximum
                + ", and there are " + wordsInMostFiles.size() + " such words:");

        for (String word : wordsInMostFiles)
        {
            System.out.println(word);
        }

        System.out.println();
        for (String word : wordsInMostFiles)
        {
            System.out.println("Files where \"" + word + "\" appears:");
            printFilesIn(word);
        }
    }

    public static void main(String[] args)
    {
        WordsInFiles wif = new WordsInFiles();
        wif.test();
    }
}
