package com.cordis.manager;

import com.cordis.model.Actor;
import com.cordis.model.Participation;
import com.cordis.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipationManager {

    public static List<Participation> searchParticipationByActor(Long actorId){
        List<Participation> participations = null;
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD)) {
            participations = new ArrayList<Participation>();
            String sql = "SELECT project_id, actor_id, ec_contribution, end_of_participation, url FROM Participant WHERE actor_id = "+ actorId ;

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Participation resultParticipation = new Participation();
                resultParticipation.setEndOfParticipation(result.getBoolean(4));
                resultParticipation.setEcContribution(result.getDouble(3));
                resultParticipation.setUrl(result.getString(5));
                Actor actor = new Actor();
                actor.setActorId(result.getLong(2));
                resultParticipation.setActor(actor);
                Project project = new Project();
                project.setProjectId(result.getLong(1));
                resultParticipation.setProject(project);
                participations.add(resultParticipation);
            }

            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return participations;
    }
}
