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

public class LivreurController implements Initializable {
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
    private TextField tFName;

    @FXML
    private TextField tFPhone;

    @FXML
    private TableColumn<Livreur, Integer> colid;

    @FXML
    private TableColumn<Livreur, String> colname;

    @FXML
    private TableColumn<Livreur, String> colp;


    @FXML
    private TableView<Livreur> table;
    int id=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showLivreurs();
    }
    public ObservableList<Livreur> getLivreurs(){
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        String query = "select * from livreur ";

        con =DBconnexion.getCon();
        try {
            st=con.prepareStatement(query);
            rs= st.executeQuery();
            while (rs.next()) {
                Livreur liv=new Livreur();
                liv.setId(rs.getInt("id"));
                liv.setName(rs.getString("name"));
                liv.setPhone(rs.getString("phone"));
                livreurs.add(liv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livreurs;
    }

    public void showLivreurs ( ){
        ObservableList<Livreur> list = getLivreurs();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Livreur,Integer>("id"));

        colname.setCellValueFactory(new PropertyValueFactory<Livreur,String>("name"));

        colp.setCellValueFactory(new PropertyValueFactory<Livreur,String>("phone"));


    }







    @FXML
    void clearField(ActionEvent event){
        clear();
    }

    @FXML
    void delete(ActionEvent event) {
        String delete = "delete from livreur where id= ? ";
        con=DBconnexion.getCon();
        try{
            st= con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            clear();
            showLivreurs();
        }
        catch (SQLException e){
            throw new RuntimeException();

        }

    }

    @FXML
    void save(ActionEvent event) {
        String insert = "insert into livreur(name,phone) values (?,?)";
        con = DBconnexion.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tFName.getText());
            st.setString(2,tFPhone.getText());
            st.executeUpdate();
            clear();
            showLivreurs();
        } catch ( SQLException e){
            throw new RuntimeException(e);

        }


    }

    @FXML
    void getDATA(MouseEvent event) {
        Livreur livreur = table.getSelectionModel().getSelectedItem();
        id = livreur.getId();
        tFName.setText(livreur.getName());
        tFPhone.setText(livreur.getPhone());
        btnSave.setDisable(true);

    }

    @FXML
    void update(ActionEvent event) {
        String update = "update livreur set name=?, phone=? where id =?";
        con=DBconnexion.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tFName.getText());
            st.setString(2,tFPhone.getText());
            st.setInt(3,id);
             st.executeUpdate();
             clear();
             showLivreurs();
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }


    }
    @FXML
    void clear (){
        tFPhone.setText(null);
        tFName.setText(null);
        btnSave.setDisable(false);
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
