import java.util.ArrayList;
import java.util.TreeMap;

public class OffspringDataFormatter implements OffspringDataBuilder {
    @Override
    public TreeMap<String, Double> buildOffspringData(ArrayList<String> offspring) {
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
        }
        for (String key : dataResults.keySet()){
            double totalNumOfThisGeneSequence = dataResults.get(key);
            dataResults.replace(key, (totalNumOfThisGeneSequence / offspring.size()));
        }
        return dataResults;
    }
}
