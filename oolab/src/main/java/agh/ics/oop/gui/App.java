package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Map;

public class App extends Application{
    IEngine engine;
    IWorldMap map;
    private Label label;
    GridPane grid=new GridPane();
    public void start(Stage primaryStage){
        try {
            engine.run();
            drawMap(primaryStage);
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
//    @Override
//    public void positionChanged(Vector2d prevPosition, Vector2d nextPosition) {
//        Vector2d[] newBounds = new Vector2d[]{new Vector2d(0, 0), new Vector2d(0, 0)};
//        if (this.map instanceof GrassField) {
//            newBounds = ((GrassField) this.map).mapBoundary.getDim();
//        }
//        if (this.map instanceof RectangularMap) {
//            newBounds = ((RectangularMap) this.map).mapBoundary.getDim();
//        }
//        this.pane.min_position=newBounds[0];
//        this.pane.max_position=((Vector2d) newBounds[1].add(new Vector2d(1,1)));
//        Platform.runLater(this.pane);
//    }

    @Override
    public void init(){
        try {
            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine(directions, this.map, positions);
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
    }
    public void drawMap(Stage stage){
        GrassField myMap = (GrassField) this.map;
        Vector2d max_position=myMap.get_max_position();
        Vector2d min_position=myMap.get_min_position();
        for (int i = 0; i <= max_position.y - min_position.y; i++) {
            label = new Label(max_position.y - i + "");
            grid.getColumnConstraints().add(new ColumnConstraints(25));
            grid.add(label, 0, i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i <= max_position.x - min_position.x; i++) {
            grid.getRowConstraints().add(new RowConstraints(25));
            label = new Label(min_position.x + i + "");
            grid.add(label, i+1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        Map<Vector2d, IMapElement> elements = myMap.getElements();
        for (Vector2d pozycja: elements.keySet()){
            label = new Label(map.objectAt(pozycja).toString());
            grid.add(label, pozycja.x-min_position.x+1, max_position.y - pozycja.y+1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        label = new Label("y\\x");
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, (max_position.x - min_position.x+2)*26, (max_position.y - min_position.y+2)*26);
        stage.setScene(scene);
        stage.setTitle("Habitat");
        stage.show();
    }
}
