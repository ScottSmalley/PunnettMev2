/**
 * Formats the raw results given into data. Specifically
 * outputs the % chance of each unique offspring combination.
 * @author Scott Smalley
 */
import java.util.ArrayList;
import java.util.TreeMap;

public class OffspringFormatter implements OffspringFormatDataBehavior {
    //Counter for how many total offspring were produced.
    private int totalDataPoints;

    /**
     * Takes each row in the ArrayList and stores it into the TreeMap.
     * It checks every row to see if there was a duplicate, if so
     * it will increment a counter. Once completed, it will then
     * work through the TreeMap to replace the counter for each
     * offspring combination with a % chance of this combination
     * appearing during reproduction.
     * @param offspring ArrayList<String>
     * @return TreeMap<String, Double>
     */
    @Override
    public TreeMap<String, Double> buildData(ArrayList<String> offspring) {
        totalDataPoints = 0;
        TreeMap<String, Double> dataResults = new TreeMap<>();
        //Works through the ArrayList, creating k,v pairs in the
        //TreeMap if one doesn't already exist. If it does exist,
        //increment the value.
        for (String singleOffspring : offspring){
            if (dataResults.containsKey(singleOffspring)){
                double currData = dataResults.get(singleOffspring);
                currData += 1.0;
                //Replace the value with the updated counter.
                dataResults.replace(singleOffspring, currData);
            }
            else{
                dataResults.put(singleOffspring, 1.0);
            }
            totalDataPoints++;
        }
        //Works through the TreeMap, changing each value from
        //an increment value into a % of the total offspring count.
        for (String geneKey : dataResults.keySet()){
            double totalNumOfThisGeneSequence = dataResults.get(geneKey);
            dataResults.replace(geneKey, (totalNumOfThisGeneSequence / offspring.size()));
        }
        return dataResults;
    }

    /**
     * Returns the total offspring counter.
     * @return int
     */
    public int getTotalDataPoints(){
        return totalDataPoints;
    }
}
