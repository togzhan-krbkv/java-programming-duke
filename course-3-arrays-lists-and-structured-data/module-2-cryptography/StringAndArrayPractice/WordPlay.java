public class WordPlay
{
    public boolean isVowel(char ch)
    {
        char lower = Character.toLowerCase(ch);
        return lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u' || lower == 'y';
    }

    public boolean isVowelA(char ch)
    {
        return ch == 'a' || ch == 'A';
    }

    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++)
        {
            char currChar = result.charAt(i);
            if (isVowel(currChar))
            {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }

    public String emphasize(String phrase, char ch)
    {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++)
        {
            char currChar = result.charAt(i);
            if (currChar == ch)
            {
                result.setCharAt(i, i % 2 == 0 ? '*' : '+');
            }
        }
        return result.toString();
    }

    public void testWordPlay()
    {
        System.out.println("isVowel('F') = " + isVowel('F'));
        System.out.println("isVowel('a') = " + isVowel('a'));
        System.out.println("replaceVowels: " + replaceVowels("Hello World", '*'));
        System.out.println("emphasize example 1: " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("emphasize example 2: " + emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main(String[] args)
    {
        WordPlay wp = new WordPlay();
        wp.testWordPlay();
    }
}
