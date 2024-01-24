package com.bodex.bodvoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInformation {
    private Connection connection;

    public DatabaseInformation(Connection connection) {
        this.connection = connection;
    }

    public String getNickNameByUsername(String username) throws SQLException{
        String sql = "SELECT nickName FROM users WHERE username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getString("nickName");
            }
        }
        return null;
    }

    public List<String> getFriendsList(String username){
        List<String> friendsList = new ArrayList<>();
        try {
            String sql = "SELECT f.friend_id FROM friends f JOIN users u ON f.user_id = u.username WHERE u.username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                friendsList.add(resultSet.getString("friend_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return friendsList;
    }
}
