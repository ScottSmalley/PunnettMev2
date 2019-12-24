import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main extends Application {
//    private Stage primaryStage;
    private final int[] numOfGenesPossible = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final double DISPLAYSIDECOLUMNWIDTH = 0.3;
    private final double DISPLAYCENTERCOLUMNWIDTH = 0.7;
    private final int TEXTFIELDMAXWIDTH = 40;
    private final int DEFAULTNUMOFGENES = 1;
    public static void main (String [] args){
        launch(args);
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
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PunnettMe v2");
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 800, 600);

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
        VBox.setMargin(numOfGenesComboBox, new Insets(5, 5, 5, 5));
        Label numofGenesLabel = new Label("Number of Genes");
        numofGenesLabel.setLabelFor(numOfGenesComboBox);
        VBox.setMargin(numofGenesLabel, new Insets(5, 5, 5, 5));
        leftDisplay.getChildren().addAll(numofGenesLabel, numOfGenesComboBox);

        //Button Display
        HBox buttonDisplay = new HBox();
        Button runButton = new Button("Run");
        runButton.setOnAction(event -> System.out.println("You hit run."));
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> System.out.println("You hit reset."));
        buttonDisplay.getChildren().addAll(runButton, resetButton);
        VBox.setMargin(buttonDisplay, new Insets(5, 5, 5, 5));
        leftDisplay.getChildren().add(buttonDisplay);

        //Center Display
        BuildGeneSelectorGUI buildGeneSelectors = new BuildGeneSelectorGUI();
//        GridPane centerDisplay = buildGeneSelectors.buildGeneSelector(DEFAULTNUMOFGENES);
//        Region result = buildGeneSelectors.buildGeneSelector(10);
//        result instanceof GridPane ?
        ScrollPane centerDisplay = buildGeneSelectors.buildGeneSelector(10);
//        ScrollPane centerDisplay = buildGeneSelectors.buildGeneSelector(10);
//        centerDisplay.setMaxWidth(scene.getWidth() * DISPLAYCENTERCOLUMNWIDTH);
        centerDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        centerDisplay.setPadding(new Insets(10, 10, 10, 10));

//        centerDisplay.setHgap(5.0);
//        centerDisplay.setVgap(5.0);

//        Label parentOneLabel = new Label("Parent One");
//        centerDisplay.add(parentOneLabel, 0, 0, 2, 1);
//
//        Label parentTwoLabel = new Label("Parent Two");
//        centerDisplay.add(parentTwoLabel, 2, 0, 2, 1);
//
//        //Parent 1 Gene 1
//        TextField parentOneGeneOneTextField = new TextField("AA");
//        parentOneGeneOneTextField.setEditable(false);
//        parentOneGeneOneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
////        parentOneGeneOneTextField.setPadding(geneInsets);
//        Label parentOneGeneOneLabel = new Label("Gene 1");
//        parentOneGeneOneLabel.setLabelFor(parentOneGeneOneTextField);
////        parentOneGeneOneLabel.setPadding(geneInsets);
//        centerDisplay.add(parentOneGeneOneLabel, 0, 1);
//        centerDisplay.add(parentOneGeneOneTextField, 0, 2);
//        //Parent 1 Gene 1 Radio buttons
//        VBox parentOneGeneOneTypeRadioButtonGroup = new VBox();
////        parentOneGeneOneTypeRadioButtonGroup.setPadding(new Insets(1, 5, 1, 1));
////        parentOneGeneOneTypeRadioButtonGroup.setPadding(geneInsets);
//        ToggleGroup parentOneGeneOneGeneTypeToggleGroup = new ToggleGroup();
//        RadioButton parentOneGeneOneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (AA)");
//        parentOneGeneOneHomozygousDomRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
//        RadioButton parentOneGeneOneHeterozygousRadioButton = new RadioButton("Heterozygous (Aa)");
//        parentOneGeneOneHeterozygousRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
//        RadioButton parentOneGeneOneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (aa)");
//        parentOneGeneOneHomozygousRecRadioButton.setToggleGroup(parentOneGeneOneGeneTypeToggleGroup);
//        parentOneGeneOneGeneTypeToggleGroup.selectToggle(parentOneGeneOneHomozygousDomRadioButton);
//        parentOneGeneOneTypeRadioButtonGroup.getChildren().addAll(parentOneGeneOneHomozygousDomRadioButton, parentOneGeneOneHeterozygousRadioButton, parentOneGeneOneHomozygousRecRadioButton);
////        VBox.setMargin(parentOneGeneOneTypeRadioButtonGroup, new Insets(5, 5, 5, 5));
//        centerDisplay.add(parentOneGeneOneTypeRadioButtonGroup, 1, 2);
//
//        //Parent 2 Gene 1
//        TextField parentTwoGeneOneTextField = new TextField("AA");
//        parentTwoGeneOneTextField.setMaxWidth(TEXTFIELDMAXWIDTH);
//        parentTwoGeneOneTextField.setEditable(false);
//        Label parentTwoGeneOneLabel = new Label("Gene 1");
//        parentOneGeneOneLabel.setLabelFor(parentTwoGeneOneTextField);
//        centerDisplay.add(parentTwoGeneOneLabel, 2, 1);
//        centerDisplay.add(parentTwoGeneOneTextField, 2, 2);
//        //Gene 1 Radio buttons
//        VBox parentTwoGeneOneTypeRadioButtonGroup = new VBox();
//        ToggleGroup parentTwoGeneOneGeneTypeToggleGroup = new ToggleGroup();
//        RadioButton parentTwoGeneOneHomozygousDomRadioButton = new RadioButton("Homozygous Dominant (AA)");
//        parentTwoGeneOneHomozygousDomRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
//        RadioButton parentTwoGeneOneHeterozygousRadioButton = new RadioButton("Heterozygous (Aa)");
//        parentTwoGeneOneHeterozygousRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
//        RadioButton parentTwoGeneOneHomozygousRecRadioButton = new RadioButton("Homozygous Recessive (aa)");
//        parentTwoGeneOneHomozygousRecRadioButton.setToggleGroup(parentTwoGeneOneGeneTypeToggleGroup);
//        parentTwoGeneOneTypeRadioButtonGroup.getChildren().addAll(parentTwoGeneOneHomozygousDomRadioButton, parentTwoGeneOneHeterozygousRadioButton, parentTwoGeneOneHomozygousRecRadioButton);
//        centerDisplay.add(parentTwoGeneOneTypeRadioButtonGroup, 3, 2);

        //MORE GENES



//        //Right Display
//        VBox rightDisplay = new VBox();
//        rightDisplay.setMaxWidth(scene.getWidth() * DISPLAYSIDECOLUMNWIDTH);
//        TextArea projectDescription = new TextArea("PunnettMe is a Punnett Square generator. " +
//                "It is designed to show the percent chance of each combination of genes up to 10 " +
//                "genes at a time. For more information, please check out my portfolio " +
//                "website at scottsmalley.net\n\n" +
//                "Designed and created by: \n" +
//                "Scott Smalley\n" +
//                "scottsmalley90@gmail.com");
//        projectDescription.setWrapText(true);
//        projectDescription.setEditable(false);
//        rightDisplay.getChildren().add(projectDescription);



        layout.setLeft(leftDisplay);
        layout.setCenter(centerDisplay);
//        layout.setRight(rightDisplay);
//        layout.setBottom(buttonDisplay);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
