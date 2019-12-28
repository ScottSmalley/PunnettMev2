import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.TreeMap;

public class BuildGeneResultsGUI {
    public TextArea buildGeneResults(TreeMap<String, Double> results){
        TextArea resultsTextArea = new TextArea();
        resultsTextArea.setEditable(false);
        resultsTextArea.setWrapText(false);
        DecimalFormat formatPercent = new DecimalFormat("0.0000%");
        resultsTextArea.setText("Gene:\t% Chance:");
        for (String gene : results.keySet()){
            resultsTextArea.appendText("\n" + gene + "\t" + formatPercent.format(results.get(gene)));
        }
        resultsTextArea.appendText("\nTotal unique gene combinations:\t" + results.size());
        return resultsTextArea;
    }
}
