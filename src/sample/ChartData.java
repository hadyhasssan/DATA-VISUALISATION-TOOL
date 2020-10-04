package sample;

public class ChartData {

    private String activityType;
    private String topic;
    private String country;
    private double contribution;
    private long projectId;
    private int year;

    @Override
    public String toString() {
        return "ChartData{" +
                "activityType='" + activityType + '\'' +
                ", topic='" + topic + '\'' +
                ", country='" + country + '\'' +
                ", contribution=" + contribution +
                ", projectId=" + projectId +
                ", year=" + year +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

}
