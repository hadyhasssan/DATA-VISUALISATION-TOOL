package com.cordis.manager;

import com.cordis.model.*;

import java.sql.*;
import java.util.*;

public class ProjectManager {

    public static List<Project> searchProject(String criteriaField, String criteria){
        List<Project> projects = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            projects = new ArrayList<Project>();
            String sql = "SELECT project_id, acronym, title, total_cost FROM Project WHERE "+criteriaField+" LIKE '%"+ criteria +"%'";
            System.out.println(Calendar.getInstance().getTimeInMillis() + " " +sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Project resultProject = new Project();
                resultProject.setProjectId(result.getLong(1));
                resultProject.setAcronym(result.getString(2));
                resultProject.setTitle(result.getString(3));
                resultProject.setTotalCost(result.getDouble(4));
                projects.add(resultProject);
            }
            System.out.println(Calendar.getInstance().getTimeInMillis() + " End search");
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return projects;
    }

}
