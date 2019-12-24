import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private final int[] numOfGenesPossible = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final double DISPLAYSIDECOLUMNWIDTH = 0.3;
//    private final double DISPLAYCENTERCOLUMNWIDTH = 0.7;
//    private final int TEXTFIELDMAXWIDTH = 40;
//    private final int DEFAULTNUMOFGENES = 1;
    private ScrollPane centerDisplay;
    private BorderPane layout;
    private BuildGeneSelectorGUI buildGeneSelector;
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
//        numOfGenesComboBox.
        VBox.setMargin(numOfGenesComboBox, baselineInsets);
        Label numofGenesLabel = new Label("Number of Genes");
        numofGenesLabel.setLabelFor(numOfGenesComboBox);
        VBox.setMargin(numofGenesLabel, baselineInsets);
        leftDisplay.getChildren().addAll(numofGenesLabel, numOfGenesComboBox);

        //Left Button Display
        HBox buttonDisplay = new HBox();
        Button runButton = new Button("Run");
        runButton.setOnAction(event -> {
//            System.out.println("You hit run.");
            buildProcessingScreen();
            generateGeneResults();
        });
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
//            System.out.println("You hit reset.");
            numOfGenesComboBox.getSelectionModel().select(0);
            resetCenterDisplay();
        });
        buttonDisplay.getChildren().addAll(runButton, resetButton);
        VBox.setMargin(buttonDisplay, baselineInsets);
        leftDisplay.getChildren().add(buttonDisplay);

        //Center Display
        centerDisplay = buildGeneSelector.buildGeneSelector(1);

        layout.setLeft(leftDisplay);
        layout.setCenter(centerDisplay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void resetCenterDisplay(){
        centerDisplay = buildGeneSelector.buildGeneSelector(1);
        layout.setCenter(centerDisplay);
    }

    private void buildProcessingScreen(){
        TextArea resultsTextArea = new TextArea();
        resultsTextArea.setEditable(false);
        resultsTextArea.setWrapText(true);
        resultsTextArea.setText("Processing...");
        centerDisplay.setContent(resultsTextArea);
    }

    private void generateGeneResults(){
        BuildGeneResultsGUI buildGeneResults = new BuildGeneResultsGUI();
        ArrayList<String> parentOneGeneNames = new ArrayList<>();
        for (TextField textField : buildGeneSelector.getParentOneTextField()){
            parentOneGeneNames.add(textField.getText());
        }
        ArrayList<String> parentTwoGeneNames = new ArrayList<>();
        for (TextField textField : buildGeneSelector.getParentTwoTextField()){
            parentTwoGeneNames.add(textField.getText());
        }
        centerDisplay.setContent(buildGeneResults.buildGeneResults(parentOneGeneNames, parentTwoGeneNames));
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