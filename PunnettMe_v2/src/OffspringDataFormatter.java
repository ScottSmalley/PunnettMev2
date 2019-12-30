import java.util.ArrayList;
import java.util.TreeMap;

public class OffspringDataFormatter implements OffspringDataBuilder {
    private int totalDataPoints;
    @Override
    public TreeMap<String, Double> buildOffspringData(ArrayList<String> offspring) {
        totalDataPoints = 0;
        TreeMap<String, Double> dataResults = new TreeMap<>();
        for (String singleOffspring : offspring){
            if (dataResults.containsKey(singleOffspring)){
                double currData = dataResults.get(singleOffspring);
                currData += 1.0;
                dataResults.replace(singleOffspring, currData);
            }
            else{
                dataResults.put(singleOffspring, 1.0);
            }
            totalDataPoints++;
        }
        for (String key : dataResults.keySet()){
            double totalNumOfThisGeneSequence = dataResults.get(key);
//            totalDataPoints += (int)totalNumOfThisGeneSequence;
            dataResults.replace(key, (totalNumOfThisGeneSequence / offspring.size()));
        }
        return dataResults;
    }

    public int getTotalDataPoints(){
        return totalDataPoints;
    }
}
