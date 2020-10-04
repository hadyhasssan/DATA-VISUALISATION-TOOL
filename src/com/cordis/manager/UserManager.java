package com.cordis.manager;

import com.cordis.model.User;
import com.cordis.model.UserAction;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Map;

public class UserManager {

    public static User addUser(User user) throws SQLException {
        if (UserManager.userExists(user.getUsername())==-1) {
            try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {

                String sql = "INSERT INTO User (username, password, is_admin) VALUES (?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getUsername());
                statement.setString(2, encrypt(user.getPassword()));
                statement.setBoolean(3, user.isAdmin());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                System.out.println(user);
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                throw ex;
            }
        } else {
            throw new SQLException("User already exists");
        }
        return user;
    }

    private static long userExists(String username){
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {

            String sql = "SELECT user_id FROM User WHERE username='"+username+"'";

            Statement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return result.getLong(1);
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return -1;
    }

    public static User loginUser(User user){
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL,Constants.USERNAME, Constants.PASSWORD)) {

            String sql = "SELECT user_id, username, is_admin FROM User WHERE username = '"+ user.getUsername() +"' and password = '"+encrypt(user.getPassword())+"'";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return new User(result.getLong(1), result.getString(2),result.getBoolean(3));
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    private static String encrypt(String password){
            try {
                MessageDigest sha1 = MessageDigest.getInstance("SHA1");
                byte[] encoded = password.getBytes(StandardCharsets.UTF_8);
                byte[] digest = sha1.digest(encoded);
                return DatatypeConverter.printHexBinary(digest);
            } catch (UnsupportedOperationException | NoSuchAlgorithmException e) {
                return password;
            }
        }


}
