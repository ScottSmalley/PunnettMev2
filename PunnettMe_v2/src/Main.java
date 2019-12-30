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
    private final double DISPLAYSIDECOLUMNWIDTH = 0.3;
    private final double CENTERDISPLAYRATIO = 0.95;
    private final int[] numOfGenesPossible = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private Stage primaryStage;
    private ScrollPane centerDisplay;
    private BorderPane layout;
    private BuildGeneSelectorGUI buildGeneSelector;
    private BuildGeneResultsGUI buildGeneResults;

    public static void main (String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        buildGeneSelector = new BuildGeneSelectorGUI();
        primaryStage.setTitle("PunnettMe v2 - Scott Smalley");
        layout = new BorderPane();
        Scene scene = new Scene(layout, 800, 600);
        Insets baselineInsets = new Insets(5, 5, 5, 5);

        Menu moreMenu = new Menu("More");

        MenuItem generateCsvFileMenuItem = new MenuItem("Generate CSV File");
        generateCsvFileMenuItem.setOnAction(event -> buildCSVFile());
        generateCsvFileMenuItem.setDisable(true);
        moreMenu.getItems().add(generateCsvFileMenuItem);

        MenuItem aboutMeMenuItem = new MenuItem("About Me");
        aboutMeMenuItem.setOnAction(event -> showAboutMeWindow());
        moreMenu.getItems().add(aboutMeMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(moreMenu);
        layout.setTop(menuBar);

        //Left Display
        VBox leftDisplay = new VBox();
        leftDisplay.setMaxWidth(scene.getWidth() * DISPLAYSIDECOLUMNWIDTH);
        ComboBox numOfGenesComboBox = new ComboBox();
        for (int size : numOfGenesPossible){
            numOfGenesComboBox.getItems().add(size);
        }
        numOfGenesComboBox.getSelectionModel().select(0);
        numOfGenesComboBox.setOnAction(event -> {
            //Get the number from the ComboBox, and convert it into an int to be sent to the BuildGeneSelectorGUI object.
            int numberOfGeneSelectorsToDisplay = Integer.parseInt(numOfGenesComboBox.getSelectionModel().getSelectedItem().toString());
            //Get the GridPane filled with gene selector GUI elements and set it as the content of the center pane.
            centerDisplay.setContent(buildGeneSelector.buildGeneSelector(numberOfGeneSelectorsToDisplay));
        });
        VBox.setMargin(numOfGenesComboBox, baselineInsets);
        Label numofGenesLabel = new Label("Number of Genes");
        numofGenesLabel.setLabelFor(numOfGenesComboBox);
        VBox.setMargin(numofGenesLabel, baselineInsets);
        leftDisplay.getChildren().addAll(numofGenesLabel, numOfGenesComboBox);

        //Left Button Display
        HBox buttonDisplay = new HBox();
        Button runButton = new Button("Run");
        runButton.setOnAction(event -> {
            generateCsvFileMenuItem.setDisable(false);
            runButton.setDisable(true);
            numOfGenesComboBox.setDisable(true);
            generateGeneResults();
        });
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            generateCsvFileMenuItem.setDisable(true);
            runButton.setDisable(false);
            numOfGenesComboBox.setDisable(false);
            numOfGenesComboBox.getSelectionModel().select(0);
            resetCenterDisplay();
        });
        buttonDisplay.getChildren().addAll(runButton, resetButton);
        VBox.setMargin(buttonDisplay, baselineInsets);
        leftDisplay.getChildren().add(buttonDisplay);

        //Center Display
        centerDisplay = new ScrollPane();
        centerDisplay.setContent(buildGeneSelector.buildGeneSelector(1));
        centerDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        centerDisplay.setPadding(new Insets(10, 10, 10, 10));

        layout.setLeft(leftDisplay);
        layout.setCenter(centerDisplay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetCenterDisplay(){
        centerDisplay.setContent(buildGeneSelector.buildGeneSelector(1));
    }

    private void generateGeneResults(){
//GET GENES FROM GUI
        //Get each TextField from parent one and store it into an ArrayList.
        ArrayList<TextField> geneNameTextFields = buildGeneSelector.getParentOneTextField();
        //Make our base gene of our gene decorator.
        GeneBuilder parentOneGeneBuilder = new BaseGene(geneNameTextFields.get(0).getText());
        //Generate as many Gene objects that compose it's predecessors.
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentOneGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentOneGeneBuilder);
        }
        //Get each TextField from parent two and store it into an ArrayList.
        geneNameTextFields = buildGeneSelector.getParentTwoTextField();
        //Make our base gene of our gene decorator.
        GeneBuilder parentTwoGeneBuilder = new BaseGene(geneNameTextFields.get(0).getText());
        //Generate as many Gene objects that compose it's predecessors.
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentTwoGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentTwoGeneBuilder);
        }
//GENERATE RAW OFFSPRING RESULTS
        OffspringBuilderResult offspringBuilderResultGenerator = new OffspringBuilderResult();
        ArrayList<String> offspringResults = offspringBuilderResultGenerator.buildOffspringResults(parentOneGeneBuilder, parentTwoGeneBuilder);
//FORMAT THOSE RAW RESULTS INTO USEFUL DATA
        OffspringDataFormatter offspringDataFormatterGenerator = new OffspringDataFormatter();
        TreeMap<String, Double> formattedResults = offspringDataFormatterGenerator.buildOffspringData(offspringResults);
//GRAPHICALLY FORMAT THE DATA INTO GUI ELEMENTS
        buildGeneResults = new BuildGeneResultsGUI();
        ObservableList<String> results = buildGeneResults.buildGeneResults(formattedResults, offspringDataFormatterGenerator.getTotalDataPoints());
        ListView<String> resultsContent = new ListView<>(results);
//DISPLAY THE DATA
        centerDisplay.setContent(resultsContent);
        resultsContent.setPrefWidth(centerDisplay.getWidth()*CENTERDISPLAYRATIO);
        resultsContent.setPrefHeight(centerDisplay.getHeight()*CENTERDISPLAYRATIO);

    }
    private void showAboutMeWindow(){
        Stage modalStage = new Stage();
        BorderPane modalPane = new BorderPane();
        Scene modalScene = new Scene(modalPane, primaryStage.getWidth()*0.5, primaryStage.getHeight()*0.5);
        VBox dialog = new VBox();
        dialog.setPadding(new Insets(5, 5, 5, 5));
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
        aboutMeTextArea.setPrefHeight(modalScene.getHeight()*0.85);
        dialog.getChildren().add(aboutMeTextArea);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> modalStage.close());
        closeBtn.setPrefHeight(primaryStage.getHeight()*0.05);
        closeBtn.setPrefWidth(primaryStage.getWidth()*0.5);
        dialog.getChildren().add(closeBtn);
        modalPane.setCenter(dialog);

        modalStage.setScene(modalScene);
        modalStage.setTitle("About Me");
        modalStage.initOwner(primaryStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }

    private void buildCSVFile(){
        FileChooser createFile = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv");
        createFile.getExtensionFilters().add(extensionFilter);
        createFile.setTitle("Save CSV File");
        File savingFile = createFile.showSaveDialog(primaryStage);
        if (savingFile != null){
            System.out.println(savingFile.getName());
            try{
                FileWriter fileWriter = new FileWriter(savingFile.getAbsolutePath());
                for (String offspring : buildGeneResults.getFormattedData()){
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
        else{
            System.out.println("No file name.");
        }
    }
}