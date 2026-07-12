import edu.duke.*;

public class Part1
{
    private int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (stopIndex != -1)
        {
            if ((stopIndex - startIndex) % 3 == 0)
            {
                return stopIndex;
            }
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
        return -1;
    }

    private int findEarliestStopCodon(String dna, int startIndex)
    {
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");

        int minIndex = -1;
        for (int candidate : new int[] {taaIndex, tgaIndex, tagIndex})
        {
            if (candidate != -1 && (minIndex == -1 || candidate < minIndex))
            {
                minIndex = candidate;
            }
        }
        return minIndex;
    }

    public String findGene(String dna, int fromIndex)
    {
        int startIndex = dna.indexOf("ATG", fromIndex);
        if (startIndex == -1)
        {
            return "";
        }
        int stopIndex = findEarliestStopCodon(dna, startIndex);
        if (stopIndex == -1)
        {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }

    public StorageResource getAllGenes(String dna)
    {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int totalGenes = 0;
        while (true)
        {
            int geneStart = dna.indexOf("ATG", startIndex);
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty())
            {
                break;
            }
            geneList.add(currentGene);
            totalGenes++;
            startIndex = geneStart + currentGene.length();
        }
        System.out.println("Total number of genes: " + totalGenes);
        return geneList;
    }

    public double cgRatio(String dna)
    {
        int cgCount = 0;
        for (int i = 0; i < dna.length(); i++)
        {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
            {
                cgCount++;
            }
        }
        return (double) cgCount / dna.length();
    }

    public int countCTG(String dna)
    {
        String target = "CTG";
        int count = 0;
        int currIndex = dna.indexOf(target);
        while (currIndex != -1)
        {
            count++;
            currIndex = dna.indexOf(target, currIndex + target.length());
        }
        System.out.println("Total CTG occurrences: " + count);
        return count;
    }

    public void processGenes(StorageResource sr)
    {
        int longerThanThreshold = 0;
        for (String gene : sr.data())
        {
            if (gene.length() > 60)
            {
                System.out.println("String longer than 60 characters: " + gene);
                longerThanThreshold++;
            }
        }
        System.out.println("Total strings longer than 60 characters: " + longerThanThreshold);

        int highCgRatioCount = 0;
        for (String gene : sr.data())
        {
            if (cgRatio(gene) > 0.35)
            {
                System.out.println("String with high C-G ratio (>0.35): " + gene);
                highCgRatioCount++;
            }
        }
        System.out.println("Total strings with high C-G ratio: " + highCgRatioCount);

        int maxLength = 0;
        for (String gene : sr.data())
        {
            if (gene.length() > maxLength)
            {
                maxLength = gene.length();
            }
        }
        System.out.println("Length of the longest gene: " + maxLength);
    }

    public void testOn(String dna)
    {
        countCTG(dna);
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource geneList = getAllGenes(dna);

        for (String gene : geneList.data())
        {
            System.out.println("Gene: " + gene);
        }

        System.out.println("Processing genes:");
        processGenes(geneList);
    }

    public void test()
    {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        testOn(dna);
    }

    public static void main(String[] args)
    {
        Part1 p = new Part1();
        p.test();
    }
}
