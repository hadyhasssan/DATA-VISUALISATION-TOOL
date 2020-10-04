package com.cordis.manager;

import com.cordis.model.ActivityType;
import com.cordis.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryManager {

    public static List<String> getCountries(){
        List<String> countries = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            countries = new ArrayList<String>();
            String sql = "SELECT country_name FROM COUNTRY ORDER BY country_name ASC";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                countries.add(result.getString(1));
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return countries;
    }

}
