import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class OffspringBuilderResult implements OffspringBuilder {
    private final int RECURSIVETHRESHOLD = 512;
    private OffspringDataTree tree;

    public OffspringBuilderResult(){
        tree = new OffspringDataTree();
    }

    @Override
    public ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
        ArrayList<String> parentOneCombination = parentOneGenes.buildSingleParentGeneCombination();
        ArrayList<String> parentTwoCombination = parentTwoGenes.buildSingleParentGeneCombination();
        List<String> offspringResults = Collections.synchronizedList(new ArrayList<>());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        OffspringBuilderRecursiveTask recursiveTask = new OffspringBuilderRecursiveTask(offspringResults,
                parentOneCombination,
                parentTwoCombination,
                RECURSIVETHRESHOLD);
        forkJoinPool.invoke(recursiveTask);
        ArrayList<String> offspringResultsConverted = new ArrayList<>();
        offspringResultsConverted.addAll(offspringResults);
        return offspringResultsConverted;
    }
//    @Override
////    public OffspringDataTree buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
//    public ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
//        ArrayList<String> parentOneCombinations = parentOneGenes.buildSingleParentGeneCombination();
//        ArrayList<String> parentTwoCombination = parentTwoGenes.buildSingleParentGeneCombination();
//        ArrayList<String> offspringResults = new ArrayList<>();
//        for (String parentOne : parentOneCombinations){
//                String[] parentOneSplit = parentOne.split("");
//            for (String parentTwo : parentTwoCombination){
//                String[] parentTwoSplit = parentTwo.split("");
////                tree.storeInTree(generateSingleOffspringResult(parentOneSplit, parentTwoSplit));
//                offspringResults.add(generateSingleOffspringResult(parentOneSplit, parentTwoSplit));
//            }
//        }
////        return tree;
//        return offspringResults;
//    }


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
