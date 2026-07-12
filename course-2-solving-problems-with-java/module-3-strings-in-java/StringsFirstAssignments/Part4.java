import edu.duke.*;

public class Part4
{
    public void printUrl(String url)
    {
        URLResource ur = new URLResource(url);
        for (String word : ur.words())
        {
            if (word.toLowerCase().contains("youtube.com"))
            {
                int startIndex = word.indexOf("\"");
                int endIndex = word.lastIndexOf("\"");
                System.out.println(word.substring(startIndex + 1, endIndex));
            }
        }
    }

    public void testUrl()
    {
        printUrl("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }

    public static void main(String[] args)
    {
        Part4 p = new Part4();
        p.testUrl();
    }
}
