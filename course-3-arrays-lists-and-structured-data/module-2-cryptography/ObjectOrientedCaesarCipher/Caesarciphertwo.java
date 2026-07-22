public class CaesarCipherTwo
{
    private int mainKey1;
    private int mainKey2;
    private String uppercaseAlphabet;
    private String lowercaseAlphabet;
    private String uppercaseShifted1;
    private String lowercaseShifted1;
    private String uppercaseShifted2;
    private String lowercaseShifted2;

    public CaesarCipherTwo(int key1, int key2)
    {
        mainKey1 = key1;
        mainKey2 = key2;
        uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowercaseAlphabet = uppercaseAlphabet.toLowerCase();
        uppercaseShifted1 = uppercaseAlphabet.substring(key1) + uppercaseAlphabet.substring(0, key1);
        lowercaseShifted1 = lowercaseAlphabet.substring(key1) + lowercaseAlphabet.substring(0, key1);
        uppercaseShifted2 = uppercaseAlphabet.substring(key2) + uppercaseAlphabet.substring(0, key2);
        lowercaseShifted2 = lowercaseAlphabet.substring(key2) + lowercaseAlphabet.substring(0, key2);
    }

    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i += 2)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted1.charAt(idx));
                }
            }
            else if (Character.isUpperCase(currChar))
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted1.charAt(idx));
                }
            }
        }

        for (int i = 1; i < encrypted.length(); i += 2)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted2.charAt(idx));
                }
            }
            else if (Character.isUpperCase(currChar))
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted2.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String encrypted)
    {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(encrypted);
    }
}
