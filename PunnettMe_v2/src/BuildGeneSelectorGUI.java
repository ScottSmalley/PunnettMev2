import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BuildGeneSelectorGUI {
    private final String[] defaultGenes = {"AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ"};
    private final int TEXTFIELDMAXWIDTH = 40;
    private ArrayList<TextField> parentOneTextField;
    private ArrayList<TextField> parentTwoTextField;

    public ScrollPane buildGeneSelector(int numOfGenes){
        parentOneTextField = new ArrayList<>();
        parentTwoTextField = new ArrayList<>();
        GridPane newGridPane = new GridPane();
        newGridPane.setHgap(10.0);
        newGridPane.setVgap(10.0);

        Label parentOneLabel = new Label("Parent One");
        newGridPane.add(parentOneLabel, 0, 0, 2, 1);

        Label parentTwoLabel = new Label("Parent Two");
        newGridPane.add(parentTwoLabel, 2, 0, 2, 1);

        //GridPane positions, incremented each loop for
        //placing additional gene elements.
        int parentOneGeneGridCol = 0;
        int parentOneGeneGridRow = 1;
        int parentOneButtonsGridCol = 1;
        int parentOneButtonsGridRow = 2;
        int parentTwoGeneGridCol = 2;
        int parentTwoGeneGridRow = 1;
        int parentTwoButtonsGridCol = 3;
        int parentTwoButtonsGridRow = 2;
        for (int geneToPull = 0; geneToPull < numOfGenes; geneToPull++){
            //Parent 1 Gene
            TextField parentOneGeneTextField = new TextField(defaultGenes[geneToPull]);
            parentOneGeneTextField.setEditable(false);
            parentOneGeneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
            parentOneTextField.add(parentOneGeneTextField);
            Label parentOneGeneLabel = new Label("Gene " + (geneToPull+1));
            parentOneGeneLabel.setLabelFor(parentOneGeneTextField);
            newGridPane.add(parentOneGeneLabel, parentOneGeneGridCol, parentOneGeneGridRow++);
            newGridPane.add(parentOneGeneTextField, parentOneGeneGridCol, parentOneGeneGridRow++);

            //Split the gene so we can make the first char uppercase and the second lowercase.
            String[] heteroConvert = defaultGenes[geneToPull].split("");
            //Parent 1 Gene Radio buttons
            VBox parentOneGeneTypeRadioButtonGroupPane = new VBox();
            ToggleGroup parentOneGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant Radiobutton
            RadioButton parentOneGeneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + defaultGenes[geneToPull] +")");
            parentOneGeneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneTextField.setText(parentOneGeneTextField.getText().toUpperCase());
                }
            });
            parentOneGeneHomozygousDomRadioButton.setToggleGroup(parentOneGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentOneGeneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase() + ")");
            parentOneGeneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentOneGeneHeterozygousRadioButton.setToggleGroup(parentOneGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentOneGeneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + defaultGenes[geneToPull].toLowerCase() + ")");
            parentOneGeneHomozygousRecRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneTextField.setText(parentOneGeneTextField.getText().toLowerCase());
                }
            });
            parentOneGeneHomozygousRecRadioButton.setToggleGroup(parentOneGeneTypeToggleGroup);
            parentOneGeneTypeToggleGroup.selectToggle(parentOneGeneHomozygousDomRadioButton);
            //Add RadioButtons to ToggleGroup
            parentOneGeneTypeRadioButtonGroupPane.getChildren().addAll(parentOneGeneHomozygousDomRadioButton, parentOneGeneHeterozygousRadioButton, parentOneGeneHomozygousRecRadioButton);
            //Add ToggleGroup to GridPane.
            newGridPane.add(parentOneGeneTypeRadioButtonGroupPane, parentOneButtonsGridCol, parentOneButtonsGridRow);
            parentOneButtonsGridRow += 2;

            //Parent 2 Gene
            TextField parentTwoGeneTextField = new TextField(defaultGenes[geneToPull]);
            parentTwoGeneTextField.setEditable(false);
            parentTwoGeneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
            parentTwoTextField.add(parentTwoGeneTextField);
            Label parentTwoGeneLabel = new Label("Gene " + (geneToPull+1));
            parentTwoGeneLabel.setLabelFor(parentTwoGeneTextField);
            newGridPane.add(parentTwoGeneLabel, parentTwoGeneGridCol, parentTwoGeneGridRow++);
            newGridPane.add(parentTwoGeneTextField, parentTwoGeneGridCol, parentTwoGeneGridRow++);

            //Parent 2 Gene  Radio buttons
            VBox parentTwoGeneTypeRadioButtonGroupPane = new VBox();
            ToggleGroup parentTwoGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant RadioButton
            RadioButton parentTwoGeneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + defaultGenes[geneToPull] + ")");
            parentTwoGeneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneTextField.setText(parentTwoGeneTextField.getText().toUpperCase());
                }
            });
            parentTwoGeneHomozygousDomRadioButton.setToggleGroup(parentTwoGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentTwoGeneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0] + heteroConvert[1].toLowerCase() + ")");
            parentTwoGeneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentTwoGeneHeterozygousRadioButton.setToggleGroup(parentTwoGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentTwoGeneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + defaultGenes[geneToPull].toLowerCase() + ")");
            parentTwoGeneHomozygousRecRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneTextField.setText(parentTwoGeneTextField.getText().toLowerCase());
                }
            });
            parentTwoGeneHomozygousRecRadioButton.setToggleGroup(parentTwoGeneTypeToggleGroup);
            parentTwoGeneTypeToggleGroup.selectToggle(parentTwoGeneHomozygousDomRadioButton);
            //Add RadioButtons to ToggleGroup
            parentTwoGeneTypeRadioButtonGroupPane.getChildren().addAll(parentTwoGeneHomozygousDomRadioButton, parentTwoGeneHeterozygousRadioButton, parentTwoGeneHomozygousRecRadioButton);
            //Add ToggleGroup to GridPane
            newGridPane.add(parentTwoGeneTypeRadioButtonGroupPane, parentTwoButtonsGridCol, parentTwoButtonsGridRow);
            parentTwoButtonsGridRow += 2;
        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(newGridPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPadding(new Insets(10, 10, 10, 10));
        return scrollPane;
    }

    public ArrayList<TextField> getParentOneTextField(){
        return parentOneTextField;
    }

    public ArrayList<TextField> getParentTwoTextField(){
        return parentTwoTextField;
    }
}
