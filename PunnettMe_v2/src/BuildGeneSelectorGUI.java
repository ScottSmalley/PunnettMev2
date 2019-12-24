import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.TreeMap;

public class BuildGeneSelectorGUI {
    private final String[] defaultGenes = {"AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ"};
    private final int TEXTFIELDMAXWIDTH = 40;

    public ScrollPane buildGeneSelector(int numOfGenes){
        GridPane newGridPane = new GridPane();
        newGridPane.setHgap(10.0);
        newGridPane.setVgap(10.0);

        Label parentOneLabel = new Label("Parent One");
        newGridPane.add(parentOneLabel, 0, 0, 2, 1);

        Label parentTwoLabel = new Label("Parent Two");
        newGridPane.add(parentTwoLabel, 2, 0, 2, 1);

        int parentOneGeneCol = 0;
        int parentOneGeneRow = 1;
        int parentOneButtonsCol = 1;
        int parentOneButtonsRow = 2;
        int parentTwoGeneCol = 2;
        int parentTwoGeneRow = 1;
        int parentTwoButtonsCol = 3;
        int parentTwoButtonsRow = 2;
        for (int geneToPull = 0; geneToPull < numOfGenes; geneToPull++){
            //Parent 1 Gene
            TextField parentOneGeneOneTextField = new TextField(defaultGenes[geneToPull]);
            parentOneGeneOneTextField.setEditable(false);
            parentOneGeneOneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
            Label parentOneGeneOneLabel = new Label("Gene " + (geneToPull+1));
            parentOneGeneOneLabel.setLabelFor(parentOneGeneOneTextField);
            newGridPane.add(parentOneGeneOneLabel, parentOneGeneCol, parentOneGeneRow++);
            newGridPane.add(parentOneGeneOneTextField, parentOneGeneCol, parentOneGeneRow++);

            String[] heteroConvert = defaultGenes[geneToPull].split("");
            //Parent 1 Gene Radio buttons
            VBox parentOneGeneOneTypeRadioButtonGroup = new VBox();
            ToggleGroup parentOneGeneOneGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant Radiobutton
            RadioButton parentOneGeneOneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + defaultGenes[geneToPull] +")");
            parentOneGeneOneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneOneTextField.setText(parentOneGeneOneTextField.getText().toUpperCase());
                }
            });
            parentOneGeneOneHomozygousDomRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentOneGeneOneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase() + ")");
            parentOneGeneOneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneOneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentOneGeneOneHeterozygousRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentOneGeneOneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + defaultGenes[geneToPull].toLowerCase() + ")");
            parentOneGeneOneHomozygousRecRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneOneTextField.setText(parentOneGeneOneTextField.getText().toLowerCase());
                }
            });
            parentOneGeneOneHomozygousRecRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
            parentOneGeneOneGeneTypeToggleGroup.selectToggle(parentOneGeneOneHomozygousDomRadioButton);
            //Add RadioButtons to ToggleGroup
            parentOneGeneOneTypeRadioButtonGroup.getChildren().addAll(parentOneGeneOneHomozygousDomRadioButton, parentOneGeneOneHeterozygousRadioButton, parentOneGeneOneHomozygousRecRadioButton);
            //Add ToggleGroup to GridPane.
            newGridPane.add(parentOneGeneOneTypeRadioButtonGroup, parentOneButtonsCol, parentOneButtonsRow);
            parentOneButtonsRow += 2;

            //Parent 2 Gene
            TextField parentTwoGeneOneTextField = new TextField(defaultGenes[geneToPull]);
            parentTwoGeneOneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
            parentTwoGeneOneTextField.setEditable(false);
            Label parentTwoGeneOneLabel = new Label("Gene " + (geneToPull+1));
            parentOneGeneOneLabel.setLabelFor(parentTwoGeneOneTextField);
            newGridPane.add(parentTwoGeneOneLabel, parentTwoGeneCol, parentTwoGeneRow++);
            newGridPane.add(parentTwoGeneOneTextField, parentTwoGeneCol, parentTwoGeneRow++);

            //Parent 2 Gene  Radio buttons
            VBox parentTwoGeneOneTypeRadioButtonGroup = new VBox();
            ToggleGroup parentTwoGeneOneGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant RadioButton
            RadioButton parentTwoGeneOneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + defaultGenes[geneToPull] + ")");
            parentTwoGeneOneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneOneTextField.setText(parentTwoGeneOneTextField.getText().toUpperCase());
                }
            });
            parentTwoGeneOneHomozygousDomRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentTwoGeneOneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0] + heteroConvert[1].toLowerCase() + ")");
            parentTwoGeneOneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneOneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentTwoGeneOneHeterozygousRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentTwoGeneOneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + defaultGenes[geneToPull].toLowerCase() + ")");
            parentTwoGeneOneHomozygousRecRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneOneTextField.setText(parentTwoGeneOneTextField.getText().toLowerCase());
                }
            });
            parentTwoGeneOneHomozygousRecRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
            parentTwoGeneOneGeneTypeToggleGroup.selectToggle(parentTwoGeneOneHomozygousDomRadioButton);
            //Add RadioButtons to ToggleGroup
            parentTwoGeneOneTypeRadioButtonGroup.getChildren().addAll(parentTwoGeneOneHomozygousDomRadioButton, parentTwoGeneOneHeterozygousRadioButton, parentTwoGeneOneHomozygousRecRadioButton);
            //Add ToggleGroup to GridPane
            newGridPane.add(parentTwoGeneOneTypeRadioButtonGroup, parentTwoButtonsCol, parentTwoButtonsRow);
            parentTwoButtonsRow += 2;
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(newGridPane);
        return scrollPane;
    }
}
