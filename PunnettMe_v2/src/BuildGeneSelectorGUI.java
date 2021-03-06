/**
 * Generates the GUI elements used to select
 * the genes to create combinations for.
 * @author Scott Smalley
 */
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class BuildGeneSelectorGUI {
    //Maximum genes available currently. Can be easily added to later.
    private final String[] DEFAULT_GENES = {"AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ"};
    private final int TEXTFIELD_MAX_WIDTH = 40;
    //Used to return a list of all the TextFields that
    //are generated by this class.
    private ArrayList<TextField> parentOneTextField;
    private ArrayList<TextField> parentTwoTextField;

    /**
     * Creates the GUI elements for parent one
     * and parent two.
     * @param numOfGenes
     * @return GridPane
     */
    public GridPane buildGeneSelector(int numOfGenes){
        parentOneTextField = new ArrayList<>();
        parentTwoTextField = new ArrayList<>();
        //The overall GridPane that gets returned.
        GridPane geneSelectorGridPane = new GridPane();
        geneSelectorGridPane.setHgap(10.0);
        geneSelectorGridPane.setVgap(10.0);

        Label parentOneLabel = new Label("Parent One");
        geneSelectorGridPane.add(parentOneLabel, 0, 0, 2, 1);

        Label parentTwoLabel = new Label("Parent Two");
        geneSelectorGridPane.add(parentTwoLabel, 2, 0, 2, 1);

        //GridPane positions, incremented each loop for
        //placing additional gene GUI elements.
        int parentOneGeneGridCol = 0;
        int parentOneGeneGridRow = 1;
        int parentOneButtonsGridCol = 1;
        int parentOneButtonsGridRow = 2;

        int parentTwoGeneGridCol = 2;
        int parentTwoGeneGridRow = 1;
        int parentTwoButtonsGridCol = 3;
        int parentTwoButtonsGridRow = 2;

        //Loop to build each gene selector GUI group. Increments
        //the grid columns and rows as it iterates.
        for (int geneToPull = 0; geneToPull < numOfGenes; geneToPull++){
//PARENT 1 GUI ELEMENTS
            //TextField
            TextField parentOneGeneTextField = new TextField(DEFAULT_GENES[geneToPull]);
            parentOneGeneTextField.setEditable(false);
            parentOneGeneTextField.setMaxWidth(TEXTFIELD_MAX_WIDTH);
            parentOneTextField.add(parentOneGeneTextField);

            //Label
            Label parentOneGeneLabel = new Label("Gene " + (geneToPull+1));
            parentOneGeneLabel.setLabelFor(parentOneGeneTextField);
            geneSelectorGridPane.add(parentOneGeneLabel, parentOneGeneGridCol, parentOneGeneGridRow++);
            geneSelectorGridPane.add(parentOneGeneTextField, parentOneGeneGridCol, parentOneGeneGridRow++);

            //Split the two gene characters so we can make the first char uppercase and the second lowercase to
            //represent heterozygous.
            String[] heteroConvert = DEFAULT_GENES[geneToPull].split("");
            //Radio buttons and ToggleGroup
            VBox parentOneGeneTypeRadioButtonGroupPane = new VBox();
            ToggleGroup parentOneGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant Radiobutton
            RadioButton parentOneGeneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + DEFAULT_GENES[geneToPull] +")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
            parentOneGeneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneTextField.setText(parentOneGeneTextField.getText().toUpperCase());
                }
            });
            parentOneGeneHomozygousDomRadioButton.setToggleGroup(parentOneGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentOneGeneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase() + ")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
            parentOneGeneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentOneGeneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentOneGeneHeterozygousRadioButton.setToggleGroup(parentOneGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentOneGeneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + DEFAULT_GENES[geneToPull].toLowerCase() + ")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
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
            geneSelectorGridPane.add(parentOneGeneTypeRadioButtonGroupPane, parentOneButtonsGridCol, parentOneButtonsGridRow);
            parentOneButtonsGridRow += 2;

//PARENT 2 GUI ELEMENTS
            //TextField
            TextField parentTwoGeneTextField = new TextField(DEFAULT_GENES[geneToPull]);
            parentTwoGeneTextField.setEditable(false);
            parentTwoGeneTextField.setMaxWidth(TEXTFIELD_MAX_WIDTH);
            parentTwoTextField.add(parentTwoGeneTextField);

            //Label
            Label parentTwoGeneLabel = new Label("Gene " + (geneToPull+1));
            parentTwoGeneLabel.setLabelFor(parentTwoGeneTextField);
            geneSelectorGridPane.add(parentTwoGeneLabel, parentTwoGeneGridCol, parentTwoGeneGridRow++);
            geneSelectorGridPane.add(parentTwoGeneTextField, parentTwoGeneGridCol, parentTwoGeneGridRow++);

            //Radio buttons and ToggleGroup
            VBox parentTwoGeneTypeRadioButtonGroupPane = new VBox();
            ToggleGroup parentTwoGeneTypeToggleGroup = new ToggleGroup();
            //Homozygous Dominant RadioButton
            RadioButton parentTwoGeneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (" + DEFAULT_GENES[geneToPull] + ")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
            parentTwoGeneHomozygousDomRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneTextField.setText(parentTwoGeneTextField.getText().toUpperCase());
                }
            });
            parentTwoGeneHomozygousDomRadioButton.setToggleGroup(parentTwoGeneTypeToggleGroup);
            //Heterozygous RadioButton
            RadioButton parentTwoGeneHeterozygousRadioButton = new RadioButton("Heterozygous (" + heteroConvert[0] + heteroConvert[1].toLowerCase() + ")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
            parentTwoGeneHeterozygousRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    parentTwoGeneTextField.setText(heteroConvert[0].toUpperCase() + heteroConvert[1].toLowerCase());
                }
            });
            parentTwoGeneHeterozygousRadioButton.setToggleGroup(parentTwoGeneTypeToggleGroup);
            //Homozygous Recessive RadioButton
            RadioButton parentTwoGeneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (" + DEFAULT_GENES[geneToPull].toLowerCase() + ")");
            //Change the TextField data to reflect the gene combination that represents the selected RadioButton.
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
            geneSelectorGridPane.add(parentTwoGeneTypeRadioButtonGroupPane, parentTwoButtonsGridCol, parentTwoButtonsGridRow);
            parentTwoButtonsGridRow += 2;
        }
        return  geneSelectorGridPane;
    }

    /**
     * Return the TextFields generated for parent one.
     * @return ArrayList<TextField>
     */
    public ArrayList<TextField> getParentOneTextField(){
        return parentOneTextField;
    }

    /**
     * Return the TextFields generated for parent two.
     * @return ArrayList<TextField>
     */
    public ArrayList<TextField> getParentTwoTextField(){
        return parentTwoTextField;
    }
}
