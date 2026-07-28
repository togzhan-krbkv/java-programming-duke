/**
 * PhraseFilter — a Filter that accepts earthquakes whose title starts with,
 * ends with, or contains a given phrase.
 *
 * @author Togzhan K.
 */
public class PhraseFilter implements Filter
{
    private String where;
    private String phrase;
    private String name;

    public PhraseFilter(String cWhere, String cPhrase, String filterName)
    {
        where = cWhere;
        phrase = cPhrase;
        name = filterName;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        if (where.equals("start"))
        {
            return qe.getInfo().startsWith(phrase);
        }
        else if (where.equals("end"))
        {
            return qe.getInfo().endsWith(phrase);
        }
        else if (where.equals("any"))
        {
            return qe.getInfo().contains(phrase);
        }
        else
        {
            return false;
        }
    }

    public String getName()
    {
        return name;
    }
}
