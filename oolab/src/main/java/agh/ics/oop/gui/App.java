package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class App extends Application{
    private IWorldMap map;
    private Label label;
    private GridPane grid=new GridPane();
    public Stage primaryStage;
    private Thread tengine;
    public void start(Stage primaryStage) throws Exception{
        try {
            GrassField map = new GrassField(10);
            this.map = map;
            this.primaryStage = primaryStage;
            Vector2d[] positions = {new Vector2d(3,2), new Vector2d(1,4)};
            SimulationEngine engine = new SimulationEngine(map, positions, this, 2000);
            Button button = new Button("Start");
            TextField text = new TextField("Wpisz kierunki");
            HBox hbox = new HBox(text, button);
            hbox.setAlignment(Pos.TOP_LEFT);
            button.setOnAction((e) -> {
                try{
                    MoveDirection[] directions = new OptionsParser().parse(text.getText().split(" "));
                    engine.setKierunki(directions);
                    tengine = new Thread(engine);
                    tengine.start();
                } catch(IllegalArgumentException ex){
                    System.out.println("ex: " + ex);
                    Platform.exit();
                    System.exit(0);
                }
            });
            Scene scene = new Scene(hbox, 400, 400);

            primaryStage.setScene(scene);
            primaryStage.setTitle("habitat");
            primaryStage.show();


        } catch (RuntimeException exception) {

            System.out.println(exception.getMessage());

        }

    }
    public void drawMap(){
        System.out.println(this.map.toString());
        System.out.println();
        GrassField myMap = (GrassField) this.map;
        Vector2d max_position=myMap.get_max_position();
        Vector2d min_position=myMap.get_min_position();
        for (int i = 0; i <= max_position.y - min_position.y; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(45));
            label = new Label(max_position.y - i + "");
            grid.add(label, 0, i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i <= max_position.x - min_position.x; i++) {
            grid.getRowConstraints().add(new RowConstraints(45));
            label = new Label(min_position.x + i + "");
            grid.add(label, i+1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        Map<Vector2d, IMapElement> elements = myMap.getElements();
        for (Vector2d pozycja: elements.keySet()){
            VBox vbox = drawObject(pozycja);
            grid.add(vbox, pozycja.x-min_position.x+1, max_position.y - pozycja.y+1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        label = new Label("y\\x");
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, (max_position.x - min_position.x+2)*47, (max_position.y - min_position.y+2)*47);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Habitat");
    }
    private VBox drawObject(Vector2d position){
        GuiElementBox elementbox=new GuiElementBox((IMapElement) this.map.objectAt(position));
        return elementbox.getVbox();
    }
    public void updateMap(){
        grid.getChildren().clear();
        grid = new GridPane();
        drawMap();
    }
}
