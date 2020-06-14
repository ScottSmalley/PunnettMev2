/**
 * Formats the offspring data into output that can be
 * put into a GUI element.
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
     * Builds a line by line list of data that can
     * be used in GUI elements. Each line contains the
     * genetic String, in addition to the % chance of the
     * gene appearing. The % chance is formatted to 4 decimal
     * places. Additionally, at the end it outputs the total
     * offspring and the amount of unique offspring combinations that
     * were found.
     * @param results
     * @param totalSize
     * @return ObservableList<String>
     */
    public ObservableList<String> buildResults(TreeMap<String, Double> results, int totalSize){
        DecimalFormat formatPercent = new DecimalFormat("0.0000%");
        //Tried using \t, which worked great in console--but had mixed results
        //in GUI elements. It would intermittently ignore \t.
        data.add("Gene:      % Chance:");
        for (String geneKey : results.keySet()){
            data.add("\n" + geneKey + "     " + formatPercent.format(results.get(geneKey)));
        }
        data.add("\nTotal unique offspring combinations:      " + results.size());
        data.add("\nTotal offspring:      " + totalSize);
        return data;
    }

    /**
     * Returns the GUI-formatted data.
     * @return ObservableList<String>
     */
    public ObservableList<String> getData(){
        return data;
    }
}
