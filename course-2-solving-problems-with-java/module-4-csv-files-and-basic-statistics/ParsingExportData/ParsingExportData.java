import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData
{
    public String countryInfo(CSVParser parser, String country)
    {
        for (CSVRecord record : parser)
        {
            if (record.get("Country").equals(country))
            {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        for (CSVRecord record : parser)
        {
            if (record.get("Exports").contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount)
    {
        for (CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length())
            {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println("Country info for Nauru: " + countryInfo(parser, "Nauru"));

        parser = fr.getCSVParser();
        System.out.println("Countries that export gold and diamonds:");
        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();
        System.out.println("Total countries that export sugar: " + numberOfExporters(parser, "sugar"));

        parser = fr.getCSVParser();
        System.out.println("Big exporters:");
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args)
    {
        ParsingExportData csv = new ParsingExportData();
        csv.tester();
    }
}
