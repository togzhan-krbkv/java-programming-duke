/**
 * DepthFilter is a filter that accepts earthquakes whose depth falls within
 * an inclusive [minDepth, maxDepth] range.
 *
 * @author Togzhan K.
 */
public class DepthFilter implements Filter
{
    private double minDepth;
    private double maxDepth;
    private String filterName;

    public DepthFilter(double minDepth, double maxDepth, String filterName)
    {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }

    public String getName()
    {
        return filterName;
    }
}
