/**
 * Represents an additional gene from the base gene.
 * This class gets used to wrap other
 * GeneBuilder-inherited genes.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public class Gene extends GeneWrapper {
    public Gene(String gene, GeneBuilder internalGene){
        super(gene, internalGene);
    }

    /**
     * Take the composed GeneBuilder item and
     * call their buildSingleParentGene().
     * Concatenate my gene onto the end of their
     * result.
     * @return String
     */
    @Override
    public String buildSingleParentGene() {
        return internalGene.buildSingleParentGene() + gene;
    }

    /**
     * Generate combinations with the composed gene
     * to my gene. For each character in my gene String,
     * we create a combination for each character in the
     * composed gene String.
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> buildSingleParentGeneCombination() {
        //Results of adding this Gene to the combinations.
        ArrayList<String> resultingCombination = new ArrayList<>();
        //Split the String that represents the genes, and add each
        //single character to the ArrayList of the internal combinations.
        for (String singleGene : gene.split("")){
            //Add the concatenation of the internal combinations to the current single gene.
            for (String internalCombo : internalGene.buildSingleParentGeneCombination()){
                resultingCombination.add(internalCombo + singleGene);
            }
        }
        return resultingCombination;
    }
}