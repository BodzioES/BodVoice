package com.bodex.bodvoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
    private Connection connection;


    public UserLogin(Connection connection){
        this.connection = connection;
    }

    public boolean verificationUser(String username, String password) throws SQLException {
        try{
            String sql = "SELECT * FROM users WHERE username=?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1,username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    String storedPassword = resultSet.getString("password");

                    return checkPassword(password,storedPassword);
                }
            }
        }catch (SQLException err){
            err.printStackTrace();
        }
        return false;
    }

    private boolean checkPassword(String enteredPassword, String storedPassword){
        return new String(enteredPassword).equals(storedPassword);
    }
}
