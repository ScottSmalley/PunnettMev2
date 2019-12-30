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
    private ObservableList<String> formattedData;
    public BuildGeneResultsGUI(){
        formattedData = FXCollections.observableArrayList();
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
    public ObservableList<String> buildGeneResults(TreeMap<String, Double> results, int totalSize){
        DecimalFormat formatPercent = new DecimalFormat("0.0000%");
        //Tried using \t, which worked great in console--but had mixed results
        //in GUI elements. It would intermittently ignore \t.
        formattedData.add("Gene:      % Chance:");
        for (String gene : results.keySet()){
            formattedData.add("\n" + gene + "     " + formatPercent.format(results.get(gene)));
        }
        formattedData.add("\nTotal unique offspring combinations:      " + results.size());
        formattedData.add("\nTotal offspring:      " + totalSize);
        return formattedData;
    }

    /**
     * Returns the GUI-formatted data.
     * @return ObservableList<String>
     */
    public ObservableList<String> getFormattedData(){
        return formattedData;
    }
}
