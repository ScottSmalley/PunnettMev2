/**
 * Represents the behaviors anything a GeneWrapper would inherit.
 * The Wrapper comes from the Decorator Design Pattern.
 * To build genes, we use the Decorator pattern to create
 * Base Genes and additional genes. This allows for modularity
 * and separation of concerns between genes.
 * @author Scott Smalley
 */
import java.util.ArrayList;

abstract class GeneWrapper implements GeneBuilder{
    protected String gene;
    //The wrapped gene that this abstract class would have.
    protected GeneBuilder internalGene;
    public GeneWrapper(String gene, GeneBuilder internalGene){
        this.gene = gene;
        this.internalGene = internalGene;
    }

    /**
     * Generate a String that represents the raw genetic traits.
     * @return String
     */
    abstract public String build();

    /**
     * Generate the combinations between all the genes to prepare for
     * being used in a Punnett Square.
     * @return ArrayList<String>
     */
    abstract public ArrayList<String> buildCombination();
}
