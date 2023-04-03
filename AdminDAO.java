package com.example.tracking_l;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO  {
    protected Connection connection ;
    protected Statement statement ;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet ;

    // connexion avec bdd

    private String url = "jdbc:mysql://127.0.0.1:3306/glovo";
    private String login = "root";
    private String password = "";



    AdminDAO() throws SQLException {
        this.connection = DriverManager.getConnection(url , login ,password );
    }


    public void save(Admin object) throws SQLException {


    }







// fonction teste la validité

    public boolean validate(Admin object) throws SQLException{


        String request = "SELECT * FROM admins WHERE USERNAME =? AND PASSWORD = ?";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getUserName());

        this.preparedStatement.setString(2 , object.getPassword());

        ResultSet rs = this.preparedStatement.executeQuery();

        if (rs.next()){
            return true;
        }else {
            return false;
        }
    }
}
