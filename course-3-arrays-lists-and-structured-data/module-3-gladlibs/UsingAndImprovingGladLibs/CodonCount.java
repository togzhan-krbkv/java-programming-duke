/**
 * CodonCount — counts how many times each codon (a 3-letter sequence of
 * DNA symbols) occurs in a strand of DNA, for a given reading frame
 * (starting position 0, 1, or 2).
 *
 * @author Togzhan K.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class CodonCount
{
    private HashMap<String, Integer> codonCounts;

    public CodonCount()
    {
        codonCounts = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna)
    {
        codonCounts.clear();
        int numCodons = (dna.length() - start) / 3;

        for (int i = 0; i < numCodons; i++)
        {
            String codon = dna.substring(i * 3 + start, i * 3 + start + 3);
            if (!codonCounts.containsKey(codon))
            {
                codonCounts.put(codon, 1);
            }
            else
            {
                codonCounts.put(codon, codonCounts.get(codon) + 1);
            }
        }
    }

    public String getMostCommonCodon()
    {
        int largest = 0;
        String mostCommon = null;
        for (String codon : codonCounts.keySet())
        {
            int current = codonCounts.get(codon);
            if (current > largest)
            {
                largest = current;
                mostCommon = codon;
            }
        }
        return mostCommon;
    }

    public void printCodonCounts(int start, int end)
    {
        for (String codon : codonCounts.keySet())
        {
            int current = codonCounts.get(codon);
            if (current >= start && current <= end)
            {
                System.out.println(codon + "\t" + current);
            }
        }
    }

    public void test()
    {
        String dna = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("data/dnaMystery1.txt")))
        {
            String s;
            while ((s = reader.readLine()) != null)
            {
                dna += s;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        dna = dna.toUpperCase();
        int start = 1;
        int end = 5;

        for (int frame = 0; frame < 3; frame++)
        {
            buildCodonMap(frame, dna);
            System.out.println("Reading frame starting with " + frame + " results in "
                    + codonCounts.size() + " unique codons");
            String mostCommon = getMostCommonCodon();
            System.out.println("  and most common codon is " + mostCommon
                    + " with count " + codonCounts.get(mostCommon));
            System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
            printCodonCounts(start, end);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        CodonCount cc = new CodonCount();
        cc.test();
    }
}
