import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GenerateOffspringThread implements Callable<ArrayList<String>> {
    private String[] parentOne;
    private ArrayList<String> parentTwoCombination;

    public GenerateOffspringThread(String[] parentOne, ArrayList<String> parentTwoCombination){
        this.parentOne = parentOne;
        this.parentTwoCombination = parentTwoCombination;
    }

    private ArrayList<String> generateOffspringResult(){
        ArrayList<String> offspringResult = new ArrayList<>();
        String singleOffspringResult = "";
        for (String parentTwo : parentTwoCombination){
            String[] parentTwoSplit = parentTwo.split("");
            for (int idx = 0; idx < parentOne.length; idx++){
                /*
                Punnett Square convention says in each pair of genes, if
                one is the dominant (Uppercase) gene, it should come first.
                for example aA--a from parentOne, A from parentTwo, A should come first.
                 */
                if (parentOne[idx].equals(parentOne[idx].toLowerCase()) && parentTwoSplit[idx].equals(parentTwoSplit[idx].toUpperCase())){
                    singleOffspringResult += parentTwoSplit[idx];
                    singleOffspringResult += parentOne[idx];
                }
                else{
                    singleOffspringResult += parentOne[idx];
                    singleOffspringResult += parentTwoSplit[idx];
                }
            }
            offspringResult.add(singleOffspringResult);
        }
        return offspringResult;
    }

    @Override
    public ArrayList<String> call() throws Exception {
        return generateOffspringResult();
    }
}
