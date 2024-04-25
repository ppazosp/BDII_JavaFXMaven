package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Comunidad;
import com.bdii.stimfx.aplicacion.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class SocialWController implements Controller{

    FachadaGUI fg;

    @FXML
    TextField searchBar;

    @FXML
    Label resultsLabel;

    @FXML
    VBox followersVbox;
    @FXML
    VBox resultsVbox;


    public void load()
    {
        loadFollows();
        loadSearch();
    }

    public void loadFollows()
    {
        List<Usuario> followers = fg.fa.consultarSeguidos(fg.fa.usuario);
        followersVbox.getChildren().clear();

        try {
            for (Usuario u : followers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/SocialSearchItem.fxml"));
                followersVbox.getChildren().add(loader.load());

                SocialSearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(u, 1, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSearch()
    {
        resultsLabel.setText("Resultados para \""+searchBar.getText()+"\"");

        List<Usuario> followers = fg.fa.consultarUsuariosNoSeguidos(fg.fa.usuario, searchBar.getText() );
        resultsVbox.getChildren().clear();

        try {
            for (Usuario u : followers) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/SocialSearchItem.fxml"));
                resultsVbox.getChildren().add(loader.load());

                SocialSearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(u, 0, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showSearchResults(ActionEvent event)
    {
        loadSearch();
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
    public void showMainScene(MouseEvent event)
    {
        fg.showMainWindow(false);
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

}
