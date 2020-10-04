package com.cordis.manager;

import com.cordis.model.ActivityType;
import com.cordis.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicManager {

    public static List<String> getTopics(){
        List<String> topics = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            topics = new ArrayList<String>();
            String sql = "SELECT topic_code FROM TOPIC ORDER BY topic_code ASC";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                topics.add(result.getString(1));
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return topics;
    }

}
