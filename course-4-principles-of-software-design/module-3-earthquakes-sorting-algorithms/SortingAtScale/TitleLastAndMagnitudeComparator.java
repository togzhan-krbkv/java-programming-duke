/**
 * TitleLastAndMagnitudeComparator, orders QuakeEntry objects by the last
 * word in their title, alphabetically, breaking ties by magnitude from
 * smallest to largest.
 *
 * @author Togzhan K.
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>
{
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        int spaceIndex1 = q1.getInfo().lastIndexOf(" ");
        String lastWord1 = q1.getInfo().substring(spaceIndex1 + 1);

        int spaceIndex2 = q2.getInfo().lastIndexOf(" ");
        String lastWord2 = q2.getInfo().substring(spaceIndex2 + 1);

        int lastWordCompare = lastWord1.compareTo(lastWord2);
        if (lastWordCompare != 0)
        {
            return lastWordCompare;
        }
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());
    }
}
