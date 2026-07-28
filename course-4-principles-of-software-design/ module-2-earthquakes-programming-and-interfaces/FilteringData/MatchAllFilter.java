/**
 * MatchAllFilter — a composite Filter that accepts a QuakeEntry only if it
 * satisfies every filter added to it (logical AND of multiple filters).
 *
 * @author Togzhan K.
 */
import java.util.*;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filterList;

    public MatchAllFilter()
    {
        filterList = new ArrayList<Filter>();
    }

    public void addFilter(Filter f)
    {
        filterList.add(f);
    }

    public boolean satisfies(QuakeEntry qe)
    {
        for (Filter f : filterList)
        {
            if (!f.satisfies(qe))
            {
                return false;
            }
        }
        return true;
    }

    public String getName()
    {
        String filterName = "";
        for (Filter f : filterList)
        {
            filterName += f.getName() + " ";
        }
        return filterName;
    }
}
