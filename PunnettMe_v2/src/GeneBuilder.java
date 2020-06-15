/**
 * Represent behaviors that every Gene needs to be able to perform.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public interface GeneBuilder {
    /**
     * Builds a String of the genetic traits to
     * be used later to build offspring combinations.
     * @return String
     */
    String build();

    /**
     * Generate the combinations of traits the
     * parent could pass on, using the
     * combinations made from the wrapped gene
     * and the current gene.
     * @return ArrayList<String>
     */
    ArrayList<String> buildCombination();
}
