package com.bdii.stimfx.gui;

import com.bdii.stimfx.aplicacion.Comunidad;
import com.bdii.stimfx.aplicacion.Torneo;
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
import java.time.LocalDate;
import java.util.List;

public class CommunityWController implements Controller {
    FachadaGUI fg;
    Comunidad userCom;
    @FXML
    ComboBox<String> filter;
    @FXML
    TextField searchBar;

    // COMMUNITY
    @FXML
    VBox comSearchVbox;
    @FXML
    HBox myComHbox;
    @FXML
    ImageView myComIcon;
    @FXML
    Label myComName;
    @FXML
    Label myComMembers;


    // TOURNAMENTS
    @FXML
    HBox currTHbox;
    @FXML
    HBox oldTHbox;

    public void load()
    {
        loadCom();
        loadComp();
    }

    public void loadCom() {
        loadMyCom();
        showCommunitySearch();
    }

    public void loadComp()
    {
        showTournamentSearch();
    }

    public void loadMyCom()
    {
        userCom = fg.fa.consultarEquipoJugador(fg.fa.usuario.getId());
        if (userCom != null){
            myComIcon.setImage(userCom.getEscudo());
            myComName.setText(userCom.getNombre());
            myComMembers.setText("Miembros: " + fg.fa.contarMiembrosEquipo(userCom));
            myComHbox.setVisible(true);
        }else  myComHbox.setVisible(false);
    }

    @FXML
    public void exitCommunity(MouseEvent event)
    {
        fg.fa.salirJugadorEquipo(fg.fa.usuario.getId());

        load();
    }


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
            case "Torneo":
                showTournamentSearch();
                break;
            default:
                showCommunitySearch();
                showTournamentSearch();
                break;
        }
    }

    private void showCommunitySearch()
    {
        List<Comunidad> comList = fg.fa.consultarComunidades(searchBar.getText());
        comSearchVbox.getChildren().clear();

        try {
            for (Comunidad c : comList) {
                if(userCom != null && userCom.getNombre().equals(c.getNombre())) continue;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/communitySearchItem.fxml"));
                comSearchVbox.getChildren().add(loader.load());

                CommunitySearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(c, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTournamentSearch()
    {
        List<Torneo> tList = fg.fa.consultarTorneos(searchBar.getText());
        currTHbox.getChildren().clear();
        oldTHbox.getChildren().clear();
        int opt;

        try {
            for (Torneo t : tList) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdii/stimfx/gui/compSearchItem.fxml"));

                if(t.getFecha_final().toLocalDate().isBefore(LocalDate.now())){
                    if (t.getGanador() == null) fg.fa.setGanador(t);
                    oldTHbox.getChildren().add(loader.load());
                    opt = 1;
                }
                else {
                    currTHbox.getChildren().add(loader.load());
                    opt = 0;
                }

                CompSearchItemController controller = loader.getController();
                controller.setMainApp(fg);
                controller.initializeWindow(t, opt, this);

                if(opt==1)
                {
                    controller.partHbox.setDisable(true);
                    if(fg.fa.isParticipante(fg.fa.usuario, t))
                    {
                        controller.hboxLabel.setText("Jugado");
                        if (t.getGanador().equals(fg.fa.usuario.getId())) controller.hboxLabel.setText("Ganado");
                    }else controller.hboxLabel.setText("Finalizado");
                }else
                {
                    if (fg.fa.isParticipante(fg.fa.usuario, t)) {
                        if(fg.fa.puedeRetirarse(t))
                        {
                            controller.hboxLabel.setText("Retirarse");
                        }else
                        {
                            controller.hboxLabel.setText("Jugando");
                            controller.partHbox.setDisable(true);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showSocialScene(MouseEvent event)
    {
        fg.showSocialScene();
    }

    @FXML
    public void showCommunityScene(MouseEvent event)
    {

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
