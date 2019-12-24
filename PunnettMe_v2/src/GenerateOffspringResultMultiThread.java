import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GenerateOffspringResultMultiThread implements OffspringBuilder {
    @Override
    public ArrayList<String> buildOffspringResults(GeneBuilder parentOneGenes, GeneBuilder parentTwoGenes) {
        ArrayList<String> parentOneCombination = parentOneGenes.buildSingleParentGeneCombination();
        ArrayList<String> parentTwoCombination = parentTwoGenes.buildSingleParentGeneCombination();
        ArrayList<String> offspringResults = new ArrayList<>();
        Future<ArrayList<String>>[] threadResults = new Future[parentOneCombination.size()];
        System.out.println("avail processors: " + Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        ExecutorService executorService = Executors.newFixedThreadPool(parentOneCombination.size());
        int threadCounter = 0;
        for (String parentOne : parentOneCombination){
            String[] parentOneSplit = parentOne.split("");
                GenerateOffspringThread newThread = new GenerateOffspringThread(parentOneSplit, parentTwoCombination);
                threadResults[threadCounter++] = executorService.submit(newThread);
//            for (String parentTwo : parentTwoCombination){
//                String[] parentTwoSplit = parentTwo.split("");
//                GenerateOffspringThread newThread = new GenerateOffspringThread(parentOneSplit, parentTwoSplit);
//                threadResults[threadCounter++] = executorService.submit(newThread);
//            }
        }
        try{
            executorService.shutdown();
        }
        catch (Exception e){
            System.out.println("Shutdown error caught");
        }
        for (Future<ArrayList<String>> result : threadResults){
            try{
                offspringResults.addAll(result.get());
            }
            catch(ExecutionException ee){
                System.out.println("ExecutionException caught");
                ee.printStackTrace();
            }
            catch(InterruptedException ie){
                System.out.println("InterruptedException caught");
            }
        }
        return offspringResults;
    }
}
