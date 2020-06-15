/**
 * Generates a list of genetic combinations and
 * their percent-chance of appearing in offspring.
 * Used specifically for the GUI element.
 * @author Scott Smalley
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.DecimalFormat;
import java.util.TreeMap;

public class BuildGeneResultsGUI {
    private ObservableList<String> data;
    public BuildGeneResultsGUI(){
        data = FXCollections.observableArrayList();
    }

    /**
     * Builds a list of offspring results that can be
     * used in GUI elements. Each line contains the
     * genetic String and the percent-chance of said
     * gene appearing. The percent-chance is formatted
     * to 4 decimal places. The last 2 lines are the total
     * offspring and the number of unique offspring
     * combinations found.
     * @param results
     * @param totalSize
     * @return ObservableList<String>
     */
    public ObservableList<String> buildResults(TreeMap<String, Double> results, int totalSize){
        DecimalFormat formatPercent = new DecimalFormat("0.0000%");
        data.add("Gene:      % Chance:");
        for (String geneKey : results.keySet()){
            data.add("\n" + geneKey + "     " + formatPercent.format(results.get(geneKey)));
        }
        data.add("\nTotal unique offspring combinations:      " + results.size());
        data.add("\nTotal offspring:      " + totalSize);
        return data;
    }

    /**
     * Returns the list of data.
     * @return ObservableList<String>
     */
    public ObservableList<String> getData(){
        return data;
    }
}
