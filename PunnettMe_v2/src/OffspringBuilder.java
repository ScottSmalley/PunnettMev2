/**
 * Builds the raw results of two GeneBuilder parents and
 * their genes reproducing through a Punnett Square methodology.
 * @author Scott Smalley
 */
import java.util.ArrayList;

public class OffspringBuilder implements OffspringBuildBehavior {

    /**
     * Works through each parent's gene combinations to build
     * a result, identical to a Punnett Square cell. Each
     * parent's genetic combinations are combined with the
     * other parent's genetic combinations. The result is
     * a list of each cell of a Punnett Square.
     * @param parentOneGenes GeneBuilder
     * @param parentTwoGenes GeneBuilder
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> buildResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
        ArrayList<String> parentOneCombinations = parentOneGenes.buildCombination();
        ArrayList<String> parentTwoCombination = parentTwoGenes.buildCombination();
        ArrayList<String> offspringResults = new ArrayList<>();
        //Generate Results
        for (String parentOne : parentOneCombinations){
                String[] parentOneSplit = parentOne.split("");
            for (String parentTwo : parentTwoCombination){
                String[] parentTwoSplit = parentTwo.split("");
                offspringResults.add(buildSingleResult(parentOneSplit, parentTwoSplit));
            }
        }
        return offspringResults;
    }

    /**
     * Helper method for buildResults. Takes in a combination from
     * both parents and interweaves their genetic traits to create a
     * potential genetic offspring result.
     * @param parentOne
     * @param parentTwo
     * @return String
     */
    private String buildSingleResult(String[] parentOne, String[] parentTwo){
        String offspringResult = "";
        for (int idx = 0; idx < parentOne.length; idx++){
            /*
            Punnett Square convention says in each pair of genes, if
            one is dominant (Uppercase) gene, it should come first.
            for example aA--a from parentOne, A from parentTwo, A should come first.
             */
            if (parentOne[idx].equals(parentOne[idx].toLowerCase()) && parentTwo[idx].equals(parentTwo[idx].toUpperCase())){
                offspringResult += parentTwo[idx];
                offspringResult += parentOne[idx];
            }
            else{
                offspringResult += parentOne[idx];
                offspringResult += parentTwo[idx];
            }
        }
        return offspringResult;
    }
}
