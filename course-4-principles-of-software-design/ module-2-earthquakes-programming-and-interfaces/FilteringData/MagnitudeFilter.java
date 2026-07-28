/**
 * MagnitudeFilter — a Filter that accepts earthquakes whose magnitude falls
 * within an inclusive [minMagnitude, maxMagnitude] range.
 *
 * @author Togzhan K.
 */
public class MagnitudeFilter implements Filter
{
    private double minMagnitude;
    private double maxMagnitude;
    private String filterName;

    public MagnitudeFilter(double minMagnitude, double maxMagnitude, String filterName)
    {
        this.minMagnitude = minMagnitude;
        this.maxMagnitude = maxMagnitude;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getMagnitude() >= minMagnitude && qe.getMagnitude() <= maxMagnitude;
    }

    public String getName()
    {
        return filterName;
    }
}
