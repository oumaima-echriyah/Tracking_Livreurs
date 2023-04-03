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

public class ProductsController  implements Initializable {



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
    private TextField tFP;

    @FXML
    private TableColumn<Product, Integer> colid;

    @FXML
    private TableColumn<Product, String> colname;

    @FXML
    private TableColumn<Product, String> colp;


    @FXML
    private TableView<Product> table;
    int id=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        show();
    }
    public ObservableList<Product> getProducts(){
        ObservableList<Product> products= FXCollections.observableArrayList();
        String query = "select * from products ";

        con =DBconnexion.getCon();
        try {
            st=con.prepareStatement(query);
            rs= st.executeQuery();
            while (rs.next()) {
                Product pro=new Product();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getString("price"));
                products.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
@FXML
    public void show ( ){
        ObservableList<Product> list = getProducts();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));

        colname.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));

        colp.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));


    }







    @FXML
    void clearField(ActionEvent event){
        clear();
    }

    @FXML
    void delete(ActionEvent event) {
        String delete = "delete from products where id= ? ";
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
    void save(ActionEvent event) {
        String insert = "insert into products(name,price) values (?,?)";
        con = DBconnexion.getCon();
        try {
            st = con.prepareStatement(insert);
            st.setString(1,tFName.getText());
            st.setString(2,tFP.getText());
            st.executeUpdate();
            clear();
            show();
        } catch ( SQLException e){
            throw new RuntimeException(e);

        }


    }

    @FXML
    void getDATA(MouseEvent event) {
        Product product = table.getSelectionModel().getSelectedItem();
        id = product.getId();
        tFName.setText(product.getName());
        tFP.setText(product.getPrice());
        btnSave.setDisable(true);

    }

    @FXML
    void update(ActionEvent event) {
        String update = "update products set name=?, price=? where id =?";
        con=DBconnexion.getCon();
        try {
            st = con.prepareStatement(update);
            st.setString(1,tFName.getText());
            st.setString(2,tFP.getText());
            st.setInt(3,id);
            st.executeUpdate();
            clear();
            show();
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }


    }
    @FXML
    void clear (){
        tFP.setText(null);
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
