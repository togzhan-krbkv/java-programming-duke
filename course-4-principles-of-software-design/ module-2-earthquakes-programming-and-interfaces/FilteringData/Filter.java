/**
 * Filter is a strategy interface for testing whether a QuakeEntry satisfies
 * some criterion (e.g. magnitude, depth, distance, phrase). Implemented by
 * MagnitudeFilter, MinMagFilter, DepthFilter, DistanceFilter, PhraseFilter,
 * and MatchAllFilter, which combines several filters together.
 *
 * @author Togzhan K.
 */
public interface Filter
{
    boolean satisfies(QuakeEntry qe);
    String getName();
}
