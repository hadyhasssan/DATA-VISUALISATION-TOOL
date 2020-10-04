package com.cordis.manager;

import com.cordis.model.ActionType;
import com.cordis.model.Project;
import com.cordis.model.User;
import com.cordis.model.UserAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryManager {

    public static void logAction(User user, String action, String parameter) {
            try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
                Long actionId = getActionId(action);
                if (actionId!=0) {
                    String sql = "INSERT INTO User_Action (user_id, action_id, action_parameters) VALUES (?, ?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.setLong(1, user.getId());
                    statement.setLong(2, actionId);
                    statement.setString(3, parameter);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new user action was inserted successfully!");
                    } else {
                        System.out.println(actionId + " - " +statement.getParameterMetaData());
                    }

                    conn.close();
                } else {
                    System.out.println("Action id not found");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
    }

    public static List<UserAction> searchUserActionByUser(String username){
        List<UserAction> userActions = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            userActions = new ArrayList<UserAction>();
            String sql = "SELECT u.username, a.action_name, ua.action_parameters, ua.action_date, u.is_admin FROM Action a, User u, User_action ua WHERE u.username LIKE '%"+ username +"%' and ua.user_id = u.user_id and ua.action_id=a.action_id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                UserAction resultUserAction = new UserAction();
                resultUserAction.setActionDate(result.getDate(4));
                resultUserAction.setActionParameters(result.getString(3));
                User u = new User();
                u.setUsername(result.getString(1));
                resultUserAction.setUser(u);
                ActionType at = new ActionType();
                at.setActionName(result.getString(2));
                resultUserAction.setActionType(at);
                userActions.add(resultUserAction);
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userActions;
    }

    public static List<UserAction> searchUserActionByActionName(String actionName){
        List<UserAction> userActions = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            userActions = new ArrayList<UserAction>();
            String sql = "SELECT u.username, a.action_name, ua.action_parameters, ua.action_date, u.is_admin FROM Action a, User u, User_action ua WHERE a.action_name like '%"+ actionName +"%' and ua.user_id = u.user_id and ua.action_id=a.action_id";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                UserAction resultUserAction = new UserAction();
                resultUserAction.setActionDate(result.getDate(4));
                resultUserAction.setActionParameters(result.getString(3));
                User u = new User();
                u.setUsername(result.getString(1));
                resultUserAction.setUser(u);
                ActionType at = new ActionType();
                at.setActionName(result.getString(2));
                resultUserAction.setActionType(at);
                userActions.add(resultUserAction);
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userActions;
    }

    private static long getActionId(String action){
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {

            String sql = "SELECT action_id FROM Action WHERE action_name='"+action+"'";

            Statement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return result.getLong(1);
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }
}
