/**
 * Represents the behaviors required by
 * a class that deals with building
 * Offspring results.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public interface OffspringBuildBehavior {
    /**
     * Take two GeneBuilder parents as parameters.
     * Work through the combinations of each parent
     * to produce offspring. Return the raw result.
     * @param parentOneGenes GeneBuilder
     * @param parentTwoGenes GeneBuilder
     * @return ArrayList<String>
     */
    ArrayList<String> buildResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes);
}
