package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Comunidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CommunityWController implements Controller{
    FachadaGUI fg;

    @FXML
    ComboBox<String> filter;
    @FXML
    TextField searchBar;
    @FXML
    VBox comSearchVbox;


    @FXML
    public void showSearchResults(ActionEvent event)
    {
        String selection = filter.getSelectionModel().getSelectedItem();
        if(selection == null) selection = "showAll";

        switch(selection)
        {
            case "Comunidad":
                showCommunitySearch();
                break;
            case "Usuario":
                showUserSearch();
                break;
            default:
                showCommunitySearch();
                showUserSearch();
                break;
        }
    }

    private void showCommunitySearch()
    {
        List<Comunidad> comList = fg.fa.consultarComunidades(searchBar.getText());
        comSearchVbox.getChildren().clear();

        try {
            for (Comunidad c : comList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/communitySearchItem.fxml"));
                comSearchVbox.getChildren().add(loader.load());

                CommunitySearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUserSearch()
    {

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
    public void showSettingsScene(MouseEvent event)
    {
        fg.showSettingsScene();
    }

    public void setMainApp(FachadaGUI mainApp)
    {
        this.fg = mainApp;
    }

}
