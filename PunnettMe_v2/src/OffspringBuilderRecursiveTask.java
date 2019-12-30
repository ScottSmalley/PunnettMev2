import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class OffspringBuilderRecursiveTask extends RecursiveTask<ArrayList<String>> {
    private ArrayList<String> parentOneCombinations;
    private ArrayList<String> parentTwoCombinations;
    private List<String> results;
    private int threshold;

    public OffspringBuilderRecursiveTask(List<String> results, ArrayList<String> parentOneCombinations, ArrayList<String> parentTwoCombinations, int threshold){
        this.parentOneCombinations = parentOneCombinations;
        this.parentTwoCombinations = parentTwoCombinations;
        this.results = results;
        this.threshold = threshold;
    }
    @Override
    protected ArrayList<String> compute() {
        if (parentOneCombinations.size() <= threshold){
            for (String parentOne : parentOneCombinations){
                String[] parentOneSplit = parentOne.split("");
                for (String parentTwo : parentTwoCombinations){
                    String[] parentTwoSplit = parentTwo.split("");
                    String singleOffspringResult = "";
                    for (int letterIdx = 0; letterIdx < parentOneSplit.length; letterIdx++){
                        if (parentOneSplit[letterIdx].equals(parentOneSplit[letterIdx].toLowerCase()) && parentTwoSplit[letterIdx].equals(parentTwoSplit[letterIdx].toUpperCase())){
                            singleOffspringResult += parentTwoSplit[letterIdx];
                            singleOffspringResult += parentOneSplit[letterIdx];
                        }
                        else{
                            singleOffspringResult += parentOneSplit[letterIdx];
                            singleOffspringResult += parentTwoSplit[letterIdx];
                        }
                    }
                    results.add(singleOffspringResult);
                }
            }
            //do computation
//            private ArrayList<String> generateOffspringResult(){
//                ArrayList<String> offspringResult = new ArrayList<>();
//                String singleOffspringResult = "";
//                for (String parentTwo : parentTwoCombination){
//                    String[] parentTwoSplit = parentTwo.split("");
//                    for (int idx = 0; idx < parentOne.length; idx++){
//                /*
//                Punnett Square convention says in each pair of genes, if
//                one is the dominant (Uppercase) gene, it should come first.
//                for example aA--a from parentOne, A from parentTwo, A should come first.
//                 */
//                        if (parentOne[idx].equals(parentOne[idx].toLowerCase()) && parentTwoSplit[idx].equals(parentTwoSplit[idx].toUpperCase())){
//                            singleOffspringResult += parentTwoSplit[idx];
//                            singleOffspringResult += parentOne[idx];
//                        }
//                        else{
//                            singleOffspringResult += parentOne[idx];
//                            singleOffspringResult += parentTwoSplit[idx];
//                        }
//                    }
//                    offspringResult.add(singleOffspringResult);
//                }
//                return offspringResult;
//            }
        }
        else{
            ArrayList<String> parentOneSubList = new ArrayList<>(parentOneCombinations.subList(0, parentOneCombinations.size()/2));
            ArrayList<String> parentTwoSubList = new ArrayList<>(parentTwoCombinations.subList(0, parentTwoCombinations.size()/2));
            OffspringBuilderRecursiveTask leftTask = new OffspringBuilderRecursiveTask(results, parentOneSubList, parentTwoSubList, threshold);
            leftTask.fork();
            parentOneSubList = new ArrayList<>(parentOneCombinations.subList(parentOneCombinations.size()/2, parentOneCombinations.size()-1));
            parentTwoSubList = new ArrayList<>(parentTwoCombinations.subList(parentTwoCombinations.size()/2, parentTwoCombinations.size()-1));
            OffspringBuilderRecursiveTask rightTask = new OffspringBuilderRecursiveTask(results, parentOneSubList, parentTwoSubList, threshold);

            results.addAll(rightTask.compute());
            results.addAll(leftTask.join());
        }
        ArrayList<String> resultsConverted = new ArrayList<>();
        resultsConverted.addAll(results);
        System.out.println("In recursion: " + resultsConverted.size());
        return resultsConverted;
    }
}
