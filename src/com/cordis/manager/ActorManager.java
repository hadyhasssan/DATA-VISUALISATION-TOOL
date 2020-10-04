package com.cordis.manager;

import com.cordis.model.Actor;
import sample.ChartData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorManager {

    public static List<Actor> searchActor(String criteriaField, String criteria){
        List<Actor> actors = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            actors = new ArrayList<Actor>();
            String sql = "SELECT actor_id, short_name, name, organization_url FROM Actor WHERE "+criteriaField+" LIKE '%"+ criteria +"%'";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Actor resultActor = new Actor();
                resultActor.setActorId(result.getLong(1));
                resultActor.setShortName(result.getString(2));
                resultActor.setName(result.getString(3));
                resultActor.setOrganizationUrl(result.getString(4));
                resultActor.setParticipations(ParticipationManager.searchParticipationByActor(resultActor.getActorId()));
                actors.add(resultActor);
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return actors;
    }

    public static List<ChartData> findActors(String actorAcronym, String activityType, String country, String topic){
        List<ChartData> data = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            data = new ArrayList<ChartData>();
            String sql = "SELECT " +
                            "at.activity_type_title, " +
                            "part.ec_contribution, " +
                            "pr.topic_code, " +
                            "pr.project_id, " +
                            "c.country_name, " +
                            "year(p.start_date) as year " +
                    "FROM actor act , " +
                        "participant part, " +
                        "project_topic pr, " +
                        "country c, " +
                        "project p, " +
                        "activity_type at " +
                    "WHERE " +
                            "part.actor_id=act.actor_id " +
                    "AND c.country_code = act.country_code " +
                    "AND p.project_id = pr.project_id " +
                    "AND at.activity_type_code = act.activity_type " +
                    "AND part.project_id= pr.project_id "
                            +(topic==null || topic.isEmpty()?"":"and pr.topic_code='"+topic+"' ")
                            +(country==null || country.isEmpty()?"":" and  c.country_name='"+country+"' ")
                            +(activityType==null || activityType.isEmpty()?"":" and at.activity_type_title='"+activityType+"' ")
                            +(actorAcronym==null || actorAcronym.isEmpty()?"":"and act.short_name='"+actorAcronym+"';");
            System.out.println(sql);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                ChartData chartData = new ChartData();
                chartData.setActivityType(result.getString(1));
                chartData.setContribution(result.getDouble(2));
                chartData.setTopic(result.getString(3));
                chartData.setProjectId(result.getLong(4));
                chartData.setCountry(result.getString(5));
                chartData.setYear(result.getInt(6));
                data.add(chartData);
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return data;
    }

    public static List<String> getActorShortNames(){
        List<String> shortNames = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            shortNames = new ArrayList<String>();
            String sql = "SELECT short_name FROM ACTOR ORDER BY short_name";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                shortNames.add(result.getString(1));
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return shortNames;
    }
}
