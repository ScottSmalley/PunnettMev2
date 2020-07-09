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
        //2 character length is the ONLY valid gene input.
        if (gene.length() != 2){
            throw new Error("Gene alleles only contain 2 characters.");
        }
        /*
        Inputs that look like "AA", "Aa", and "aa"
        are valid, but we need to make sure if we
        get "aA" that we correct it.
        */
        String[] geneSplit = gene.split("");
        if (geneSplit[0].equals(geneSplit[0].toLowerCase()) && geneSplit[1].equals(geneSplit[1].toUpperCase())){
            this.gene = geneSplit[1] + geneSplit[0];
        }
        else{
            this.gene = gene;
        }
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
