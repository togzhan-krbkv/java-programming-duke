import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths
{
    public void printNames()
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) // no header row in this file
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100)
            {
                System.out.println("Name: " + rec.get(0) + " Gender: " + rec.get(1) + " Number Born: " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr)
    {
        int totalBirths = 0;
        int totalBoyBirths = 0;
        int totalGirlBirths = 0;
        int boyNameCount = 0;
        int girlNameCount = 0;
        int totalNames = 0;

        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;
            if (rec.get(1).equals("M"))
            {
                totalBoyBirths += numBorn;
                boyNameCount++;
            }
            else
            {
                totalGirlBirths += numBorn;
                girlNameCount++;
            }
        }
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total boy births: " + totalBoyBirths);
        System.out.println("Total girl births: " + totalGirlBirths);
        System.out.println("Total names: " + totalNames);
        System.out.println("Total boy names: " + boyNameCount);
        System.out.println("Total girl names: " + girlNameCount);
    }

    public int getRank(int year, String name, String gender)
    {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
                rank++;
                if (rec.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender)
    {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
                currentRank++;
                if (currentRank == rank)
                {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender)
    {
        int bestYear = -1;
        int bestRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7)); // filename looks like "yob1880.csv"
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1 && (bestYear == -1 || currentRank < bestRank))
            {
                bestYear = currentYear;
                bestRank = currentRank;
            }
        }
        return bestYear;
    }

    public double getAverageRank(String name, String gender)
    {
        double totalRank = 0.0;
        int countFiles = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1)
            {
                countFiles++;
                totalRank += currentRank;
            }
        }
        if (countFiles == 0)
        {
            return -1.0;
        }
        return totalRank / countFiles;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int targetRank = getRank(year, name, gender);
        int totalBirthsRankedHigher = 0;
        int currentRank = 0;

        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
                currentRank++;
                if (currentRank < targetRank)
                {
                    totalBirthsRankedHigher += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirthsRankedHigher;
    }

    // Tests

    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public void testGetRank()
    {
        int rank = getRank(1971, "Frank", "M");
        if (rank == -1)
        {
            System.out.println("Rank not found, -1 returned.");
        }
        else
        {
            System.out.println("Rank for Frank in 1971: " + rank);
        }
    }

    public void testGetName()
    {
        System.out.println("Name at rank 450 (1982, M): " + getName(1982, 450, "M"));
    }

    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public void testYearOfHighestRank()
    {
        System.out.println("Year of highest rank for Mich (M): " + yearOfHighestRank("Mich", "M"));
    }

    public void testGetAverageRank()
    {
        System.out.println("Average rank for Susan (F): " + getAverageRank("Susan", "F"));
        System.out.println("Average rank for Robert (M): " + getAverageRank("Robert", "M"));
    }

    public void testGetTotalBirthsRankedHigher()
    {
        System.out.println("Births ranked higher than Emily (1990, F): " + getTotalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println("Births ranked higher than Drew (1990, M): " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }

    public static void main(String[] args)
    {
        BabyBirths bb = new BabyBirths();
        bb.testGetTotalBirthsRankedHigher();
        // Other available tests: printNames(), testTotalBirths(), testGetRank(),
        // testGetName(), testWhatIsNameInYear(), testYearOfHighestRank(), testGetAverageRank()
    }
}