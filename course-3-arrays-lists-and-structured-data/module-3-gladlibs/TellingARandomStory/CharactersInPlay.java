/**
 * CharactersInPlay — determines the characters in a Shakespeare play with
 * the most speaking parts, by finding the text before the first period on
 * each line (Shakespeare's convention for marking who is speaking).
 *
 * @author Togzhan K.
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CharactersInPlay
{
    private ArrayList<String> characterNames;
    private ArrayList<Integer> counts;

    public CharactersInPlay()
    {
        characterNames = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update(String person)
    {
        int index = characterNames.indexOf(person);
        if (index == -1)
        {
            characterNames.add(person);
            counts.add(1);
        }
        else
        {
            int freq = counts.get(index);
            counts.set(index, freq + 1);
        }
    }

    public void findAllCharacters(String filename)
    {
        characterNames.clear();
        counts.clear();

        try (BufferedReader resource = new BufferedReader(new FileReader(filename)))
        {
            String s;
            while ((s = resource.readLine()) != null)
            {
                if (s.contains("."))
                {
                    int idx = s.indexOf(".");
                    String possibleName = s.substring(0, idx);
                    update(possibleName);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
        }
    }

    // Prints every character whose speaking-part count is >= threshold
    public void tester(String filename, int threshold)
    {
        findAllCharacters(filename);

        System.out.println("Main characters in " + filename + " (threshold >= " + threshold + "):");
        for (int k = 0; k < counts.size(); k++)
        {
            if (counts.get(k) >= threshold)
            {
                System.out.println(characterNames.get(k) + "\tspeaking parts: " + counts.get(k));
            }
        }

        charactersWithNumParts(2, 3);
    }

    public void charactersWithNumParts(int num1, int num2)
    {
        for (int k = 0; k < counts.size(); k++)
        {
            if (counts.get(k) >= num1 && counts.get(k) <= num2)
            {
                System.out.println("Character with " + num1 + "-" + num2 + " speaking parts: "
                        + characterNames.get(k) + "\t" + counts.get(k));
            }
        }
    }

    public static void main(String[] args)
    {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester("data/macbethSmall.txt", 2);
        System.out.println();
        cip.tester("data/macbeth.txt", 15);
    }
}
