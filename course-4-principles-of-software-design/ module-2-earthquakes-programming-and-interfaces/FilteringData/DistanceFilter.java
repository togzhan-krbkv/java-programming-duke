/**
 * a Filter that accepts earthquakes within a maximum
 * distance from a given Location.
 *
 * @author Togzhan K.
 */
public class DistanceFilter implements Filter
{
    private Location location;
    private double maxDistance;
    private String filterName;

    public DistanceFilter(Location location, double maxDistance, String filterName)
    {
        this.location = location;
        this.maxDistance = maxDistance;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

    public String getName()
    {
        return filterName;
    }
}
