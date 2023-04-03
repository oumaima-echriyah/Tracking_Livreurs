package com.example.tracking_l;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    Connection con = null;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
@FXML
private TextField nbrD;
    @FXML
    private TextField nbrO;
    @FXML
    private TextField nbrP;
    @FXML
    private Button btnL;
    @FXML
    private TextField del;
    @FXML
    private TextField pro;



    @FXML



    public void openDeliverymen(Event e)
    {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource( "/Fxml/Livreur.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println("y"+ex.getMessage());
        }
    }


    @FXML



    public void openProducts(Event e)
    {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource( "/Fxml/Products.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println("y"+ex.getMessage());
        }
    }


@FXML

    public void openOrders(Event e)
    {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource( "/Fxml/Commandes.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println("y"+ex.getMessage());
        }
    }

    @FXML
    public void back(Event e)
    {
        try {
            //add you loading or delays - ;-)
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println("y"+ex.getMessage());
        }
    }

@FXML

public void homeTotalL() {

    String sql = "SELECT COUNT(id) FROM livreur";

    connect = DBconnexion.getCon();
    int countData = 0;
    try {

        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        while (result.next()) {
            countData = result.getInt("COUNT(id)");
        }

        nbrD.setText(String.valueOf(countData));

    } catch (Exception e) {
        e.printStackTrace();
    }

}



@FXML
    public void homeTotalO() {

        String sql = "SELECT COUNT(id) FROM orders";

        connect = DBconnexion.getCon();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            nbrO.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


@FXML
    public void homeTotalP() {

        String sql = "SELECT COUNT(id) FROM products";

        connect = DBconnexion.getCon();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            nbrP.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void homeDel() {

        String sql = "SELECT COUNT(id) FROM orders where  status ='Delivered'";

        connect = DBconnexion.getCon();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            del.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void homePro() {

        String sql = "SELECT COUNT(id) FROM orders where  status ='Processing'";

        connect = DBconnexion.getCon();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            pro.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeTotalL();
        homeTotalO();
        homeTotalP();
        homeDel();
        homePro();
    }
}
