import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;

public class ParsingWeatherData
{
    // Hottest temperature

    public CSVRecord hottestInManyDays()
    {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestHourInFile(CSVParser parser)
    {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar)
    {
        if (largestSoFar == null)
        {
            return currentRow;
        }
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
        return currentTemp > largestTemp ? currentRow : largestSoFar;
    }

    // coldest temperature

    public String fileWithColdestTemperature()
    {
        String nameOfFile = "";
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null)
            {
                smallestSoFar = currentRow;
                nameOfFile = f.getAbsolutePath();
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                // ignoring bogus values: lowest temp ever recorded on Earth was -89.2
                if (currentTemp < smallestTemp && currentTemp > -90)
                {
                    smallestSoFar = currentRow;
                    nameOfFile = f.getAbsolutePath();
                }
            }
        }
        return nameOfFile;
    }

    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar)
    {
        if (smallestSoFar == null)
        {
            return currentRow;
        }
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
        if (currentTemp < smallestTemp && currentTemp > -90)
        {
            return currentRow;
        }
        return smallestSoFar;
    }

    // humidity 

    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            smallestSoFar = getSmallestOfTwoHumidities(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwoHumidities(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwoHumidities(CSVRecord currentRow, CSVRecord smallestSoFar)
    {
        if (smallestSoFar == null)
        {
            return currentRow;
        }
        // some humidity values are malformed (e.g. "N/A") skip anything not a clean number
        if (currentRow.get("Humidity").length() == 3)
        {
            return smallestSoFar;
        }
        double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
        double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
        if (currentHumidity < smallestHumidity && currentHumidity > -90)
        {
            return currentRow;
        }
        return smallestSoFar;
    }

    // Average temperature 

    public double averageTemperatureInFile(CSVParser parser)
    {
        double count = 0.0;
        double total = 0.0;
        for (CSVRecord currentRow : parser)
        {
            if (Double.parseDouble(currentRow.get("TemperatureF")) > -90)
            {
                total += Double.parseDouble(currentRow.get("TemperatureF"));
                count++;
            }
        }
        return total / count;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double count = 0.0;
        double total = 0.0;
        for (CSVRecord currentRow : parser)
        {
            double temp = Double.parseDouble(currentRow.get("TemperatureF"));
            double humidity = Double.parseDouble(currentRow.get("Humidity"));
            if (temp > -90 && humidity >= value)
            {
                total += temp;
                count++;
            }
        }
        if (count == 0.0)
        {
            return -1;
        }
        return total / count;
    }

    // Tests

    public void testHottestInManyDays()
    {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temp over many days was " + largest.get("TemperatureF")
                + " on " + largest.get("DateUTC") + " at " + largest.get("TimeEST"));
    }

    public void testHottestInDay()
    {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temp was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp in the file was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    public void testFileWithColdestTemperature()
    {
        String nameOfFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(nameOfFile);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + nameOfFile);
        System.out.println("Coldest temp on that day was " + smallest.get("TemperatureF"));
        System.out.println("All temps on that coldest day:");
        for (CSVRecord record : fr.getCSVParser())
        {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public void testLowestHumidityInManyFiles()
    {
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity in file was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is: " + averageTemperatureInFile(fr.getCSVParser()));
    }

    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        double result = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (result == -1)
        {
            System.out.println("No temps with that humidity.");
        }
        else
        {
            System.out.println("Average temperature with high humidity is: " + result);
        }
    }

    public static void main(String[] args)
    {
        ParsingWeatherData csv = new ParsingWeatherData();
        csv.testFileWithColdestTemperature();
        // Other available tests: testHottestInDay(), testHottestInManyDays(),
        // testColdestHourInFile(), testLowestHumidityInFile(),
        // testLowestHumidityInManyFiles(), testAverageTemperatureInFile(),
        // testAverageTemperatureWithHighHumidityInFile()
    }
}
