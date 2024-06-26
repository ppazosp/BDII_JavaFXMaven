package com.bdii.stimfx.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsWController implements Controller {
    FachadaGUI fg;

    //MENU BAR
    @FXML
    HBox comMenu;
    @FXML
    HBox editMenu;
    @FXML
    HBox adminMenu;


    @FXML
    VBox beEditorVbox;
    @FXML
    Label editorPreLabel;


    @FXML
    VBox bePlayerVbox;
    @FXML
    Label playerPreLabel;

    @FXML
    Label purseLabel;
    @FXML
    TextField purseField;
    @FXML
    HBox purseHbox;

    public void initializeWindow()
    {
        if(!(fg.fa.usuario.isCompetitivePlayer())) comMenu.setVisible(false);
        else
        {
            playerPreLabel.setText("Ya eres PLAYER");
            bePlayerVbox.setDisable(true);
        }
        if(!(fg.fa.usuario.isEditor())) editMenu.setVisible(false);
        else
        {
            editorPreLabel.setText("Ya eres EDITOR");
            beEditorVbox.setDisable(true);
        }
        if(!(fg.fa.usuario.isAdmin())) adminMenu.setVisible(false);

        purseLabel.setText(String.valueOf(fg.fa.usuario.getDinero()));
    }

    @FXML
    public void beEditor(MouseEvent event)
    {
        fg.loading();

        new Thread(() -> {

            fg.fa.hacerEditor(fg.fa.usuario);
            fg.fa.usuario.setEditor(true);

            Platform.runLater(() ->{
                fg.showSettingsScene();

                fg.loaded();
            });
        }).start();
    }

    @FXML
    public void bePlayer(MouseEvent event)
    {
        fg.loading();

        new Thread(() -> {

            fg.fa.hacerJugadorCompetitivo(fg.fa.usuario);
            fg.fa.usuario.setCompetitivePlayer(true);

            Platform.runLater(() ->{
                fg.showSettingsScene();

                fg.loaded();
            });
        }).start();
    }

    @FXML
    public void addFounds(MouseEvent event)
    {
        fg.loading();

        new Thread(() -> {

            double value;
            try{
                value = Double.parseDouble(purseField.getText());
            }catch (NumberFormatException e)
            {
                value = 0;
            }

            fg.fa.insertarFondos(fg.fa.usuario, value);

            Platform.runLater(() ->{

                fg.showSettingsScene();

                fg.loaded();
            });
        }).start();
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
        fg.showEditScene();
    }

    @FXML
    public void showAdminScene(MouseEvent event)
    {
        fg.showAdminScene();
    }

    @FXML
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }
}
