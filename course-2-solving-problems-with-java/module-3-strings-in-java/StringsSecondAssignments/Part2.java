public class Part2
{
    public int howMany(String stringA, String stringB)
    {
        int count = 0;
        int currIndex = stringB.indexOf(stringA);
        while (currIndex != -1)
        {
            count++;
            currIndex = stringB.indexOf(stringA, currIndex + stringA.length());
        }
        return count;
    }

    public void testHowMany()
    {
        String stringA = "GAA";
        String stringB = "ATGAACGAATTGAATC";
        System.out.println(howMany(stringA, stringB));

        stringA = "AA";
        stringB = "ATAAAATAAA";
        System.out.println(howMany(stringA, stringB));
    }

    public static void main(String[] args)
    {
        Part2 p = new Part2();
        p.testHowMany();
    }
}