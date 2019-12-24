import java.util.ArrayList;

public class Gene extends GeneWrapper {
    public Gene(String gene, GeneBuilder internalGene){
        super(gene, internalGene);
    }

    @Override
    public String buildSingleParentGene() {
        return internalGene.buildSingleParentGene() + gene;
    }

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