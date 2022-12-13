package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox{
    private Image image;
    private VBox vbox = new VBox(-5);
    private Label label;
    private ImageView view;

    public GuiElementBox(IMapElement element){
        try {
            this.image=new Image(new FileInputStream(element.getLink()));
        }catch (FileNotFoundException ex){
            throw new RuntimeException(ex + "Nie znaleziono pliku");
        }
        this.view=new ImageView(this.image);
        this.view.setFitHeight(35);
        this.view.setFitWidth(35);
        if (element.toString().equals("*")) this.label=new Label("Trawa");
        else this.label=new Label("Z " + element.getPosition().toString());
        this.vbox.getChildren().addAll((Node) this.view, this.label);
        this.vbox.setPadding(new Insets(1,0,1,4));
        this.vbox.setMaxHeight(45);
        this.vbox.setMaxWidth(45);
    }

    public VBox getVbox() {
        return this.vbox;
    }
}
