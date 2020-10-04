package com.cordis.manager;

import com.cordis.model.ActionType;
import com.cordis.model.ActivityType;
import com.cordis.model.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityTypeManager {

    public static List<String> getActivityTypes(){
        List<String> activityTypes = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            activityTypes = new ArrayList<String>();
            String sql = "SELECT activity_type_title FROM ACTIVITY_TYPE ORDER BY activity_type_title ASC";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                activityTypes.add(result.getString(1));
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return activityTypes;
    }

}
