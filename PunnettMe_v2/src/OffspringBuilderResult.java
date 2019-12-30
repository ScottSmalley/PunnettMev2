import java.util.ArrayList;

public class OffspringBuilderResult implements OffspringBuilder {
    @Override
    public ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
        ArrayList<String> parentOneCombinations = parentOneGenes.buildSingleParentGeneCombination();
        ArrayList<String> parentTwoCombination = parentTwoGenes.buildSingleParentGeneCombination();
        ArrayList<String> offspringResults = new ArrayList<>();
        for (String parentOne : parentOneCombinations){
                String[] parentOneSplit = parentOne.split("");
            for (String parentTwo : parentTwoCombination){
                String[] parentTwoSplit = parentTwo.split("");
                offspringResults.add(generateSingleOffspringResult(parentOneSplit, parentTwoSplit));
            }
        }
        return offspringResults;
    }


    private String generateSingleOffspringResult(String[] parentOne, String[] parentTwo){
        String offspringResult = "";
        for (int idx = 0; idx < parentOne.length; idx++){
            /*
            Punnett Square convention says in each pair of genes, if
            one is the dominant (Uppercase) gene, it should come first.
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
