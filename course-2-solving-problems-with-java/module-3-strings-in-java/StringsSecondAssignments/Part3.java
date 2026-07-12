public class Part3
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

    public int countGenes(String dna)
    {
        int startIndex = 0;
        int count = 0;
        while (true)
        {
            int geneStart = dna.indexOf("ATG", startIndex);
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty())
            {
                break;
            }
            count++;
            startIndex = geneStart + currentGene.length();
        }
        return count;
    }

    public void test()
    {
        System.out.println("Total genes: " + countGenes("ATGTAAGATGCCCTAGT"));
        System.out.println("Total genes: " + countGenes("GAA"));
        System.out.println("Total genes: " + countGenes("ATAAAATAAA"));
    }

    public static void main(String[] args)
    {
        Part3 p = new Part3();
        p.test();
    }
}