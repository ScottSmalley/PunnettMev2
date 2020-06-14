/**
 * Represents the behaviors required of a class
 * that will generate data from the raw offspring results.
 * @author Scott Smalley
 */
import java.util.ArrayList;
import java.util.TreeMap;

public interface OffspringFormatDataBehavior {
    /**
     * Take the raw results and find duplicates.
     * Generate a list of unique offspring gene
     * combinations and tally how many of each.
     * Then return the percentage of how many
     * of the children have each unique gene.
     * @param offspring
     * @return TreeMap<String, Double>
     */
    TreeMap<String, Double> buildData(ArrayList<String> offspring);
}
