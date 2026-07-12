public class Part3
{
    public boolean twoOccurrences(String stringA, String stringB)
    {
        int firstOccur = stringB.indexOf(stringA);
        if (firstOccur == -1)
        {
            return false;
        }
        return stringB.indexOf(stringA, firstOccur + 1) != -1;
    }

    public String lastPart(String stringA, String stringB)
    {
        int firstOccur = stringB.indexOf(stringA);
        if (firstOccur == -1)
        {
            return stringB;
        }
        return stringB.substring(firstOccur + stringA.length());
    }

    public void testing()
    {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println("The part of the string after an in banana is " + lastPart("an", "banana"));
        System.out.println("The part of the string after zoo in forest is " + lastPart("zoo", "forest"));
    }

    public static void main(String[] args)
    {
        Part3 p = new Part3();
        p.testing();
    }
}
