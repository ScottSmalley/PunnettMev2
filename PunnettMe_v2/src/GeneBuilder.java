/**
 * Represent behaviors that every Gene needs to be able to perform.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public interface GeneBuilder {
    /**
     * Generate a String that represents the raw genetic traits.
     * @return String
     */
    String build();

    /**
     * Generate the combinations between all the genes to prepare for
     * being used in a Punnett Square.
     * @return ArrayList<String>
     */
    ArrayList<String> buildCombination();
}
