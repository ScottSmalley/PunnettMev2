import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.TreeMap;

public class BuildGeneResultsGUI {
    private ObservableList<String> outputs;
    public BuildGeneResultsGUI(){
        outputs = FXCollections.observableArrayList();
    }
    public ObservableList<String> buildGeneResults(TreeMap<String, Double> results, int totalSize){
        DecimalFormat formatPercent = new DecimalFormat("0.0000%");
//        ObservableList<String> outputs = FXCollections.observableArrayList();
        outputs.add("Gene:      % Chance:");
        for (String gene : results.keySet()){
            outputs.add("\n" + gene + "     " + formatPercent.format(results.get(gene)));
        }
        outputs.add("\nTotal unique offspring combinations:      " + results.size());
        outputs.add("\nTotal offspring:      " + totalSize);
        return outputs;
    }

    public ObservableList<String> getOutputs(){
        return outputs;
    }
}
