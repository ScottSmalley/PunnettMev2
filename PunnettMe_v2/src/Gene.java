/**
 * Represents an additional gene from the base gene.
 * Used as a Wrapper class.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public class Gene extends GeneWrapper {
    public Gene(String gene, GeneBuilder internalGene){
        super(gene, internalGene);
    }

    /**
     * Builds a String of the genetic traits to
     * be used later to build offspring combinations.
     * @return String
     */
    @Override
    public String build() {
        return internalGene.build() + gene;
    }

    /**
     * Generate the combinations of traits the
     * parent could pass on, using the
     * combinations made from the wrapped gene
     * and the current gene.
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> buildCombination() {
        //Results of adding this Gene to the combinations.
        ArrayList<String> resultingCombination = new ArrayList<>();
        //Split the String that represents the genes, and add each
        //single character to the ArrayList of the internal combinations.
        for (String singleGene : gene.split("")){
            //Add the concatenation of the internal combinations to the current single gene.
            for (String internalCombo : internalGene.buildCombination()){
                resultingCombination.add(internalCombo + singleGene);
            }
        }
        return resultingCombination;
    }
}