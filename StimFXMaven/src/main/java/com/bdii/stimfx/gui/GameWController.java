package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.DLC;
import com.bdii.stimfx.aplicacion.Videojuego;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GameWController implements Controller {

    FachadaGUI fg;
    Application app;
    Videojuego game;

    //PANE
    @FXML
    AnchorPane rightPane;

    @FXML
    Hyperlink linkRef;
    @FXML
    ImageView bannerImage;
    @FXML
    Label nameLabel;
    @FXML
    Label dateLabel;
    @FXML
    HBox buyHbox;
    @FXML
    Label priceLabel;
    @FXML
    TextArea descrpArea;
    @FXML
    Label creatorLabel;
    @FXML
    Label downloadsLabel;
    @FXML
    VBox catVbox;
    @FXML
    VBox checkVbox;
    @FXML
    HBox reviewsHbox;
    @FXML
    HBox addHbox;


    public void setVideojuego(Videojuego v) {this.game = v;}

    public void initializeWindow()
    {
        bannerImage.setImage(game.getBanner());
        nameLabel.setText(game.getNombre());
        dateLabel.setText("Fecha de publicacion: "+game.getFechaSubida().toString());
        if (game.getFechaSubida().toLocalDate().isAfter(LocalDate.now())) buyHbox.setDisable(true);
        priceLabel.setText(game.getPrecio()+"€");
        descrpArea.setText(game.getDescripcion());
        creatorLabel.setText("Creador: "+game.getEditor().getId());
        downloadsLabel.setText("Descargas: " + game.getNumDescargas());
        catVbox.getChildren().clear();
        List<String> cats = fg.fa.consultarCategoriasVideojuego(game);
        for(String s : cats){
            Label l = new Label(s);
            catVbox.getChildren().add(l);
        }

        checkVbox.getChildren().clear();
        List<DLC> dlcList = fg.fa.consultarDLCsVideojuego(game);

        try {
            for (DLC d : dlcList) { // Add 10 instances as an example
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/dlcCheckItem.fxml"));
                checkVbox.getChildren().add(loader.load());

                DLCCheckItemController controller = loader.getController();
                controller.setMainApp(fg);

                controller.getCheckBox().setText(d.getNombre());
                controller.getPriceLabel().setText(d.getPrecio()+"€");
            }
            if (dlcList.isEmpty()) addHbox.setDisable(true);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openTrailer(ActionEvent event) {
        app.getHostServices().showDocument(game.getTrailer());
    }

    @FXML
    public void buyGame(MouseEvent event)
    {
        fg.fa.insertarCompra(game.getId());
        buyHbox.setDisable(true);
    }

    @FXML
    public void addDLCs(MouseEvent event)
    {
        //fg.fa.();
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
    }

    @FXML
    public void loadGameWindow(MouseEvent event) {
        fg.loadGameWindow();
    }

    @FXML
    public void showProfileScene(MouseEvent event)
    {
        fg.showProfileScene();
    }

    @FXML
    public void showLibraryScene(MouseEvent event)
    {
        fg.showLibraryScene();
    }

    @FXML
    public void showSocialScene(MouseEvent event)
    {
        fg.showSocialScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event)
    {
        fg.showCommunityScene();
    }

    @FXML
    public void showEditScene(MouseEvent event)
    {

    }

    @FXML
    public void showAdminScene(MouseEvent event)
    {

    }

    @FXML
    public void showSettingsScene(MouseEvent event)
    {
        fg.showSettingsScene();
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }

    public void setApp(Application app) {
        this.app = app;
    }
}