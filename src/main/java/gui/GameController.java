package gui;

import Game.Panel;
import gui.panel.IPanel;
import gui.panel.PanelButtonControl;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class GameController implements Initializable {

    @FXML private GridPane gridPane;

    private GameView view;
    private Runnable runnable;
    PanelFactory panelFactory;


    public void initialize(URL location, ResourceBundle resources) {
        panelFactory = new PanelFactory();
        fillGridWithPanels();
    }

    public void setView(GameView gameView) {
        view = gameView;
    }
    public void fillGridWithPanels()
    {
        //Alleen als voorbeeld, wordt dalijk met een forloop door de lijst van panels van een player gegaan
        Panel panel = new Panel(1,0,"testpanel",0,0);
        Panel panel1 = new Panel(2,1,"testpanel",1,5);
        Panel panel2 = new Panel(3,2,"testpanel",1,5);
        IPanel iPanel = panelFactory.getPanel(panel);
        IPanel iPanel1 = panelFactory.getPanel(panel1);
        IPanel iPanel2 = panelFactory.getPanel(panel2);
        gridPane.add((Node) iPanel, 0, 0);
        gridPane.add((Node) iPanel1, 0, 1);
        gridPane.add((Node) iPanel2, 0, 2);
    }
    //get gamestatus update
    /*
        Huidige tijd *optional*
        Hoeveel team levens?
        nieuwe instructie
     */


}
