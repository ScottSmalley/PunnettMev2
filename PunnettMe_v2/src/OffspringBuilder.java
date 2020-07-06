/**
 * Builds the raw results of two GeneBuilder parents and
 * their genes reproducing through a Punnett Square methodology.
 * @author Scott Smalley
 */
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class OffspringBuilder extends RecursiveTask<ArrayList<String>> implements OffspringBuildBehavior {
    //Parallel Processing threshold--the number of
    //combinations to process per parent.
    private int threshold = 512;
    private ArrayList<String> parentOneCombination;
    private ArrayList<String> parentTwoCombination;

    public OffspringBuilder(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes){
        this.parentOneCombination = parentOneGenes.buildCombination();
        this.parentTwoCombination = parentTwoGenes.buildCombination();
    }

    public OffspringBuilder(ArrayList<String> parentOneGeneCombination, ArrayList<String> parentTwoGeneCombination){
        this.parentOneCombination = parentOneGeneCombination;
        this.parentTwoCombination = parentTwoGeneCombination;
    }

    /**
     * Returns the offspring data.
     * @return ArrayList<String>
     */
    public ArrayList<String> buildResults() {
        return compute();
    }

    /**
     * Works through each parent's gene combinations to build
     * a genetic offspring outcome, identical to a Punnett
     * Square cell. Each parent's genetic combinations are
     * combined with the other parent's genetic combinations.
     * The result is a list of each cell of a Punnett Square.
     * Uses Parallel Processing to handle large computations.
     * @return ArrayList<String>
     */
    @Override
    protected ArrayList<String> compute() {
        //Stopping condition for recursion.
        ArrayList<String> offspringResults = new ArrayList<>();
        if (parentOneCombination.size() <= threshold){
            //Generate Results
            for (String parentOne : parentOneCombination){
                String[] parentOneSplit = parentOne.split("");
                for (String parentTwo : parentTwoCombination){
                    String[] parentTwoSplit = parentTwo.split("");
                    offspringResults.add(buildSingleResult(parentOneSplit, parentTwoSplit));
                }
            }
        }
        else{
            int sizeOne = parentOneCombination.size();
            int sizeTwo = parentTwoCombination.size();

            //Using four forks because punnett squares are in multiples of 4.
            //This way we can make sure each half of each parent's combinations is matched up to
            //each half of the opposite parent's combinations.
            //Punnett Square -- fork top left values
            ArrayList<String> parentOneSubList = new ArrayList<>(parentOneCombination.subList(0, sizeOne/2));
            ArrayList<String> parentTwoSubList = new ArrayList<>(parentTwoCombination.subList(0, sizeTwo/2));
            OffspringBuilder firstTask = new OffspringBuilder(parentOneSubList, parentTwoSubList);
            firstTask.fork();

            //Punnett Square -- fork top right values
            parentOneSubList = new ArrayList<>(parentOneCombination.subList(0, sizeOne/2));
            parentTwoSubList = new ArrayList<>(parentTwoCombination.subList(sizeTwo/2, sizeTwo));
            OffspringBuilder secondTask = new OffspringBuilder(parentOneSubList, parentTwoSubList);
            secondTask.fork();

            //Punnett Square -- fork bottom left values
            parentOneSubList = new ArrayList<>(parentOneCombination.subList(sizeOne/2, sizeOne));
            parentTwoSubList = new ArrayList<>(parentTwoCombination.subList(0, sizeTwo/2));
            OffspringBuilder thirdTask = new OffspringBuilder(parentOneSubList, parentTwoSubList);
            thirdTask.fork();

            //Punnett Square -- fork bottom right values
            parentOneSubList = new ArrayList<>(parentOneCombination.subList(sizeOne/2, sizeOne));
            parentTwoSubList = new ArrayList<>(parentTwoCombination.subList(sizeTwo/2, sizeTwo));
            OffspringBuilder fourthTask = new OffspringBuilder(parentOneSubList, parentTwoSubList);
            fourthTask.fork();

            //Nested order due to recommendations by oracle's
            //javadocs and guide on ForkJoin, they appear
            //to have less issues if in nested order.
            offspringResults.addAll(fourthTask.join());
            offspringResults.addAll(thirdTask.join());
            offspringResults.addAll(secondTask.join());
            offspringResults.addAll(firstTask.join());
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
            if (parentOne[idx].equals(parentOne[idx].toLowerCase())
                    && parentTwo[idx].equals(parentTwo[idx].toUpperCase())){
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
