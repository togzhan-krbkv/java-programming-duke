public class CaesarCipher
{
    private String uppercaseAlphabet;
    private String lowercaseAlphabet;
    private String uppercaseShifted;
    private String lowercaseShifted;
    private int mainKey;

    public CaesarCipher(int key)
    {
        mainKey = key;
        uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowercaseAlphabet = uppercaseAlphabet.toLowerCase();
        uppercaseShifted = uppercaseAlphabet.substring(key) + uppercaseAlphabet.substring(0, key);
        lowercaseShifted = lowercaseAlphabet.substring(key) + lowercaseAlphabet.substring(0, key);
    }

    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar))
            {
                int idx = lowercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, lowercaseShifted.charAt(idx));
                }
            }
            else
            {
                int idx = uppercaseAlphabet.indexOf(currChar);
                if (idx != -1)
                {
                    encrypted.setCharAt(i, uppercaseShifted.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input)
    {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
