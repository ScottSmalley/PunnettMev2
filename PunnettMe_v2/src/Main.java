/**
 * PunnettMe v2
 * This project is a Punnett Square calculator.
 * It can compare up to 10 genes between two reproducing partners.
 * It outputs the unique genetic combination possibilities for
 * reproduction. The application also has functionality to
 * generate a CSV (Comma-Separated Values) file to make
 * data manipulation much easier in a more powerful application.
 * @author Scott Smalley
 *
 * Contact Info:
 * scottsmalley@gmail.com
 * scottsmalley.net
 *
 * Software Engineering Senior
 * Graduation Fall 2020
 * Utah Valley University
 */
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main extends Application {
    private final double DISPLAY_SIDE_COLUMN_WIDTH = 0.3;
    private final double CENTER_DISPLAY_RATIO = 0.95;
    private final double MODAL_DISPLAY_WIDTH = 0.5;
    private final double MODAL_DISPLAY_HEIGHT = 0.5;
    private final double MODAL_BUTTON_HEIGHT = 0.05;
    private final int FIRST_ELEMENT_POS = 0;
    private final int[] NUM_OF_GENES_POSSIBLE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final Insets MAIN_INSETS = new Insets(5, 5, 5, 5);
    private Stage primaryStage;
    private ScrollPane centerDisplay;
    private BorderPane sceneLayout;
    private BuildGeneSelectorGUI buildGeneSelector;
    private BuildGeneResultsGUI buildGeneResults;

    public static void main (String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        //For generating the gene selecting GUI elements dynamically.
        buildGeneSelector = new BuildGeneSelectorGUI();
        primaryStage.setTitle("PunnettMe v2 - Scott Smalley");
        sceneLayout = new BorderPane();
        Scene scene = new Scene(sceneLayout, 800, 600);
//MENUBAR
        Menu moreMenu = new Menu("More");
        //CSV MenuItem
        MenuItem generateCsvFileMenuItem = new MenuItem("Generate CSV File");
        generateCsvFileMenuItem.setOnAction(event -> buildCSVFile());
        generateCsvFileMenuItem.setDisable(true);
        moreMenu.getItems().add(generateCsvFileMenuItem);
        //About Me MenuItem
        MenuItem aboutMeMenuItem = new MenuItem("About Me");
        aboutMeMenuItem.setOnAction(event -> showAboutMeWindow());
        moreMenu.getItems().add(aboutMeMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(moreMenu);
        sceneLayout.setTop(menuBar);
//LEFT SIDE DISPLAY
        VBox leftDisplay = new VBox();
        leftDisplay.setMaxWidth(scene.getWidth() * DISPLAY_SIDE_COLUMN_WIDTH);
        ComboBox numOfGenesComboBox = new ComboBox();
        for (int size : NUM_OF_GENES_POSSIBLE){
            numOfGenesComboBox.getItems().add(size);
        }
        //Select the first item in the ComboBox as default.
        numOfGenesComboBox.getSelectionModel().select(FIRST_ELEMENT_POS);
        numOfGenesComboBox.setOnAction(event -> {
            //Get the number from the ComboBox, and convert it into an int to be sent to the BuildGeneSelectorGUI object.
            int numberOfGeneSelectorsToDisplay = Integer.parseInt(numOfGenesComboBox.getSelectionModel().getSelectedItem().toString());
            //Get the GridPane filled with gene selector GUI elements and set it as the content of the center pane.
            centerDisplay.setContent(buildGeneSelector.buildGeneSelector(numberOfGeneSelectorsToDisplay));
        });
        VBox.setMargin(numOfGenesComboBox, MAIN_INSETS);
        Label numofGenesLabel = new Label("Number of Genes");
        numofGenesLabel.setLabelFor(numOfGenesComboBox);
        VBox.setMargin(numofGenesLabel, MAIN_INSETS);
        leftDisplay.getChildren().addAll(numofGenesLabel, numOfGenesComboBox);

        //Left Side Button Display
        HBox buttonDisplay = new HBox();
        //Run Button
        Button runButton = new Button("Run");
        runButton.setOnAction(event -> {
            generateCsvFileMenuItem.setDisable(false);
            runButton.setDisable(true);
            numOfGenesComboBox.setDisable(true);
            generateGeneResults();
        });
        //Reset Button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            generateCsvFileMenuItem.setDisable(true);
            runButton.setDisable(false);
            numOfGenesComboBox.setDisable(false);
            numOfGenesComboBox.getSelectionModel().select(FIRST_ELEMENT_POS);
            resetCenterDisplay();
        });
        buttonDisplay.getChildren().addAll(runButton, resetButton);
        VBox.setMargin(buttonDisplay, MAIN_INSETS);
        leftDisplay.getChildren().add(buttonDisplay);

//CENTER DISPLAY
        centerDisplay = new ScrollPane();
        //Default gene selection should be 1 gene.
        centerDisplay.setContent(buildGeneSelector.buildGeneSelector(1));
        centerDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        centerDisplay.setPadding(new Insets(10, 10, 10, 10));

        sceneLayout.setLeft(leftDisplay);
        sceneLayout.setCenter(centerDisplay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Reset the gene selector GUI elements back to 1 gene with default settings.
     */
    private void resetCenterDisplay(){
        centerDisplay.setContent(buildGeneSelector.buildGeneSelector(1));
    }

    /**
     * Starts the computation phase.
     * Generates the Gene Decorator for each parent,
     * Instantiates and runs offspring builder.
     * Instantiates and runs offspring formatter.
     * Instantiates and runs GUI offspring formatter.
     * Displays the data in the center display.
     */
    private void generateGeneResults(){
//GET GENES FROM GUI
        //Get each TextField from parent one and store it into an ArrayList.
        ArrayList<TextField> geneNameTextFields = buildGeneSelector.getParentOneTextField();
        //Make our base gene of our gene decorator.
        GeneBuilder parentOneGeneBuilder = new BaseGene(geneNameTextFields.get(FIRST_ELEMENT_POS).getText());
        //Generate as many Gene objects that compose it's predecessors.
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentOneGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentOneGeneBuilder);
        }
        //Get each TextField from parent two and store it into an ArrayList.
        geneNameTextFields = buildGeneSelector.getParentTwoTextField();
        //Make our base gene of our gene decorator.
        GeneBuilder parentTwoGeneBuilder = new BaseGene(geneNameTextFields.get(FIRST_ELEMENT_POS).getText());
        //Generate as many Gene objects that compose it's predecessors.
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentTwoGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentTwoGeneBuilder);
        }
//GENERATE RAW OFFSPRING RESULTS
        OffspringBuilder offspringBuilderResultGenerator = new OffspringBuilder();
        ArrayList<String> offspringResults = offspringBuilderResultGenerator.buildResults(parentOneGeneBuilder, parentTwoGeneBuilder);
//FORMAT THOSE RAW RESULTS INTO USEFUL DATA
        OffspringFormatter offspringDataFormatterGenerator = new OffspringFormatter();
        TreeMap<String, Double> formattedResults = offspringDataFormatterGenerator.buildData(offspringResults);
//GRAPHICALLY FORMAT THE DATA INTO GUI ELEMENTS
        buildGeneResults = new BuildGeneResultsGUI();
        ObservableList<String> results = buildGeneResults.buildResults(formattedResults, offspringDataFormatterGenerator.getTotalDataPoints());
        ListView<String> resultsContent = new ListView<>(results);
//DISPLAY THE DATA
        centerDisplay.setContent(resultsContent);
        resultsContent.setPrefWidth(centerDisplay.getWidth() * CENTER_DISPLAY_RATIO);
        resultsContent.setPrefHeight(centerDisplay.getHeight() * CENTER_DISPLAY_RATIO);

    }

    /**
     * Generates and shows a new Stage with 'about me' information.
     */
    private void showAboutMeWindow(){
        Stage modalStage = new Stage();
        BorderPane modalPane = new BorderPane();
        Scene modalScene = new Scene(modalPane, primaryStage.getWidth() * MODAL_DISPLAY_WIDTH, primaryStage.getHeight() * MODAL_DISPLAY_HEIGHT);
        VBox dialog = new VBox();
        dialog.setPadding(MAIN_INSETS);
        TextArea aboutMeTextArea = new TextArea("Hello, my name is Scott Smalley. My project is to help " +
                "those interested in seeing the results of a Punnett Square between two reproductive partners. " +
                "When I took Biology, I really could’ve used a user-friendly, results oriented calculator. Most " +
                "Punnett Square calculators I find on the internet are very limited. The highest calculator I could " +
                "find could compare 7 genes. Additionally, they do not give user-friendly output—hard to copy and " +
                "paste or output to a data file. This software allows you to compare up to 10 genes, view the " +
                "percent chance of each combination appearing, the number of unique gene combinations, the number of " +
                "total offspring considered. Lastly, I added functionality to generate a CSV file so it can be " +
                "imported to more advanced tools like Excel.\n\nIf you'd like to know more about me, please check out " +
                "my website at www.scottsmalley.net or email me at scottsmalley90@gmail.com.");
        aboutMeTextArea.setEditable(false);
        aboutMeTextArea.setWrapText(true);
        aboutMeTextArea.setPrefWidth(modalScene.getWidth());
        aboutMeTextArea.setPrefHeight(modalScene.getHeight() * 0.85);
        dialog.getChildren().add(aboutMeTextArea);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> modalStage.close());
        closeBtn.setPrefHeight(primaryStage.getHeight() * MODAL_BUTTON_HEIGHT);
        closeBtn.setPrefWidth(primaryStage.getWidth() * MODAL_DISPLAY_WIDTH);
        dialog.getChildren().add(closeBtn);
        modalPane.setCenter(dialog);

        modalStage.setScene(modalScene);
        modalStage.setTitle("About Me");
        modalStage.initOwner(primaryStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }

    /**
     * Displays a FileChooser for the user to create a filename and
     * in the directory of their choice. Generates a CSV data file that
     * can be opened in more powerful applications like Excel.
     */
    private void buildCSVFile(){
        FileChooser createFile = new FileChooser();
        //Only available to CSV files for the moment.
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv");
        createFile.getExtensionFilters().add(extensionFilter);
        createFile.setTitle("Save CSV File");
        File savingFile = createFile.showSaveDialog(primaryStage);
        if (savingFile != null){
            System.out.println(savingFile.getName());
            try{
                FileWriter fileWriter = new FileWriter(savingFile.getAbsolutePath());
                for (String offspring : buildGeneResults.getData()){
                    String[] offspringSplit = offspring.split("     ");
                    fileWriter.append(offspringSplit[0]);
                    fileWriter.append(",");
                    fileWriter.append(offspringSplit[1]);
                }
                fileWriter.flush();
                fileWriter.close();
            }
            catch (ArrayIndexOutOfBoundsException aio){
                System.out.println("There was a problem with array sizing.");
            }
            catch(IOException e){
                System.out.println("There was a problem generating the file.");
            }
        }
    }
}