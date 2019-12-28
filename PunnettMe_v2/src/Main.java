import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main extends Application {
    private final int[] numOfGenesPossible = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final double DISPLAYSIDECOLUMNWIDTH = 0.3;
//    private final double DISPLAYCENTERCOLUMNWIDTH = 0.7;
//    private final int TEXTFIELDMAXWIDTH = 40;
//    private final int DEFAULTNUMOFGENES = 1;
    private ScrollPane centerDisplay;
    private BorderPane layout;
    private BuildGeneSelectorGUI buildGeneSelector;
    private OffspringDataTree offspringTree;
    public static void main (String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        buildGeneSelector = new BuildGeneSelectorGUI();
        primaryStage.setTitle("PunnettMe v2 - Scott Smalley");
        layout = new BorderPane();
        Scene scene = new Scene(layout, 800, 600);
        Insets baselineInsets = new Insets(5, 5, 5, 5);

        Menu infoMenu = new Menu("Info");
        MenuItem aboutMeMenuItem = new MenuItem("About Me");
        infoMenu.getItems().add(aboutMeMenuItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(infoMenu);
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
            buildProcessingScreen();
            generateGeneResults();
        });
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
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

    private void buildProcessingScreen(){
        TextArea resultsTextArea = new TextArea();
        resultsTextArea.setEditable(false);
        resultsTextArea.setWrapText(true);
        resultsTextArea.setText("Processing...");
        centerDisplay.setContent(resultsTextArea);
    }

    private void generateGeneResults(){
//        ArrayList<String> parentOneGeneNames = new ArrayList<>();
//        for (TextField textField : buildGeneSelector.getParentOneTextField()){
//            parentOneGeneNames.add(textField.getText());
//        }
//        ArrayList<String> parentTwoGeneNames = new ArrayList<>();
//        for (TextField textField : buildGeneSelector.getParentTwoTextField()){
//            parentTwoGeneNames.add(textField.getText());
//        }

        //BUILD GENES & RESULTS
//        offspringTree = new OffspringDataTree();
        ArrayList<TextField> geneNameTextFields = buildGeneSelector.getParentOneTextField();
        GeneBuilder parentOneGeneBuilder = new BaseGene(geneNameTextFields.get(0).getText());
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentOneGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentOneGeneBuilder);
        }
        geneNameTextFields = buildGeneSelector.getParentTwoTextField();
        GeneBuilder parentTwoGeneBuilder = new BaseGene(geneNameTextFields.get(0).getText());
        for (int idx = 1; idx < geneNameTextFields.size(); idx++){
            parentTwoGeneBuilder = new Gene(geneNameTextFields.get(idx).getText(), parentTwoGeneBuilder);
        }
        OffspringBuilderResult offspringBuilderResultGenerator = new OffspringBuilderResult();
        ArrayList<String> offspringResults = offspringBuilderResultGenerator.buildOffspringResults(parentOneGeneBuilder, parentTwoGeneBuilder);

//        offspringTree = offspringBuilderResultGenerator.buildOffspringResults(parentOneGeneBuilder, parentTwoGeneBuilder);
//        long endTime = System.currentTimeMillis();
//        System.out.println("Time: " + (endTime - startTime) + " ms");
        OffspringDataFormatter offspringDataFormatterGenerator = new OffspringDataFormatter();
        TreeMap<String, Double> formattedResults = offspringDataFormatterGenerator.buildOffspringData(offspringResults);
        long startTime = System.currentTimeMillis();
        BuildGeneResultsGUI buildGeneResults = new BuildGeneResultsGUI();
        TextArea content = buildGeneResults.buildGeneResults(formattedResults);
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + " ms");
        centerDisplay.setContent(content);
////        TreeMap<String, Integer> resultsTree = offspringTree.getOffspringTreeMap();
//        for (String key : formattedResults.keySet()){
////        for (String key : resultsTree.keySet()){
//            System.out.println(key + "\t" + formattedResults.get(key));
////            System.out.println(key + "\t" + resultsTree.get(key));
//        }
//        centerDisplay.setContent(buildGeneResults.buildGeneResults(offspringTree));
//        System.out.println("Total number of unique nodes: " + offspringTree.getCountOfUniqueNodes());
//        System.out.println("Total number of total elements: " + offspringTree.getCountOfTotalDataPoints());
    }
}


/*
Building Genes stuff for later:

//        GeneBuilder test1 = new BaseGene("Aa"); //1
//        test1 = new Gene("Bb", test1); //2
//        test1 = new Gene("Cc", test1); //3
//        test1 = new Gene("Dd", test1); //4
//        test1 = new Gene("Ee", test1); //5
//        test1 = new Gene("Ff", test1); //6
//        test1 = new Gene("Gg", test1); //7
//        test1 = new Gene("Hh", test1); //8
//        test1 = new Gene("Ii", test1); //9
//        test1 = new Gene("Jj", test1); //10
//        OffspringResult gor = new OffspringResult();
//        long startTime = System.currentTimeMillis();
//        ArrayList<String> results = gor.buildOffspringResults(test1, test1);
//        long endTime = System.currentTimeMillis();
//        OffspringData od = new OffspringData();
//        TreeMap<String, Double> dataResults = od.buildOffspringResultData(results);
//        DecimalFormat decimalFormat = new DecimalFormat("#.####%");
//        System.out.println("Offspring:\tPercent Chance");
//        for (String s : dataResults.keySet()){
//            System.out.println(s + "\t" + decimalFormat.format(dataResults.get(s)));
//        }
//        System.out.println("Single Threaded Execution Time: " + (endTime - startTime) + " milliseconds or " + ((endTime - startTime)/1000.0) + " seconds.");
//        System.out.println("Number of total offspring: " + results.size());

//        GenerateOffspringResultMultiThread gormt = new GenerateOffspringResultMultiThread();
//        long startTime2 = System.currentTimeMillis();
//        ArrayList<String> results2 = gormt.buildOffspringResults(test1, test1);
//        long endTime2 = System.currentTimeMillis();
//        System.out.println("Multi Threaded Execution Time: " + (endTime2 - startTime2) + " milliseconds or " + ((endTime2 - startTime2)/1000.0) + " seconds.");
//        System.out.println("Number of total offspring: " + results2.size());
 */