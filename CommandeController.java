package com.example.tracking_l;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    Connection con = null;
    PreparedStatement st =null;
    ResultSet rs =null;
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Commande, Integer> colid;

    @FXML
    private TableColumn<Commande, String> colname;

    @FXML
    private TableColumn<Commande, Integer> colpd;


    @FXML
    private TableColumn<Commande, String> cols;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tFPhone;

    @FXML
    private TextField tFPhone1;


    @FXML
    private TableView<Commande> table;
    int id=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        show();
    }


    @FXML
    public ObservableList<Commande> getCommandes(){
        ObservableList<Commande> commandes= FXCollections.observableArrayList();
        String query = "select * from orders ";

        con =DBconnexion.getCon();
        try {
            st=con.prepareStatement(query);
            rs= st.executeQuery();
            while (rs.next()) {
                Commande pro=new Commande();
                pro.setId(rs.getInt("id"));
                pro.setClient_name(rs.getString("client_name"));
                pro.setStatus(rs.getString("status"));
                pro.setDelivery_man_id(rs.getInt("delivery_man_id"));

                commandes.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }




    @FXML
    public void show ( ){
        ObservableList<Commande> list = getCommandes();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));

        colname.setCellValueFactory(new PropertyValueFactory<Commande,String>("client_name"));

        cols.setCellValueFactory(new PropertyValueFactory<Commande,String>("status"));
        colpd.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("delivery_man_id"));




    }
    @FXML
    void clearField(ActionEvent event){
        clear();
    }
    @FXML
    void clear() {
        tFPhone1.setText(null);
        tFPhone.setText(null);
        tFName.setText(null);

        btnSave.setDisable(false);


    }

    @FXML
    void delete(ActionEvent event) {
        String delete = "delete from orders where id= ? ";
        con=DBconnexion.getCon();
        try{
            st= con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            show();
        }
        catch (SQLException e){
            throw new RuntimeException();

        }

    }

    @FXML
    void getDATAA(MouseEvent event) {
        Commande product = table.getSelectionModel().getSelectedItem();
        id = product.getId();
        tFName.setText(product.getClient_name());
        tFPhone.setText(product.getStatus());
        tFPhone1.setText(String.valueOf(product.getDelivery_man_id()));

        btnSave.setDisable(true);

    }

    @FXML
    void save(ActionEvent event) {
        String insert = "insert into orders(client_name,status,delivery_man_id,product_id) values (?,?,?,?)";
        con = DBconnexion.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tFName.getText());
            st.setString(2,tFPhone.getText());
            st.setString(3,tFPhone1.getText());

            st.executeUpdate();
            clear();
            show();
        } catch ( SQLException e){
            throw new RuntimeException(e);

        }


    }

    @FXML
    void getDATA(MouseEvent event) {
        Commande product = table.getSelectionModel().getSelectedItem();
        id = product.getId();
        tFName.setText(product.getClient_name());
        tFPhone.setText(product.getStatus());
        tFPhone1.setText(String.valueOf(product.getDelivery_man_id()));

        btnSave.setDisable(true);

    }

    @FXML
    void update(ActionEvent event) {
        String update = "update orders set client_name=?, status=? ,delivery_man_id=?,where id =?";
        con=DBconnexion.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tFName.getText());
            st.setString(2,tFPhone.getText());
            st.setString(3,tFPhone1.getText());

            st.setInt(5,id);
            st.executeUpdate();
            clear();
            show();
        }catch (SQLException e ){
            throw new RuntimeException(e);

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

            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println("y"+ex.getMessage());
        }
    }
}
