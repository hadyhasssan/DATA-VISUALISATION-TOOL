package sample;

import com.cordis.manager.*;
import com.cordis.model.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class Controller {

    @FXML
    private BorderPane borderPaneLogin;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private BorderPane borderPaneSignUp;

    @FXML
    private TextField usernameSignText;

    @FXML
    private PasswordField passwordSignText;

    @FXML
    private PasswordField confirmPasswordSignText;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Label signUpErrorLabel;

    @FXML
    private VBox signUpVBox;

    @FXML
    private VBox loginVBox;

    @FXML
    private SplitPane dashboardSplitPane;

    @FXML
    private TitledPane actorTiteledPane;

    @FXML
    private Button searchActorButton;

    @FXML
    private TitledPane projectTiteledPane;

    @FXML
    private Button searchProjectButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label dashboardLabel;

    @FXML
    private TitledPane userTiteledPane;

    @FXML
    private VBox searchActorVBox;

    @FXML
    private GridPane searchActorGrid;

    @FXML
    private ComboBox<String> choiceActorCombo;

    @FXML
    private TextField searchActorText;

    @FXML
    private TableView<Actor> searchActorTable;

    private User connectedUser;

    @FXML
    private TableColumn<?, ?> shortNameColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<String, Double> totalColumn;

    @FXML
    private TableColumn<?, ?> urlColumn;

    @FXML
    private BorderPane projectPane;

    @FXML
    private Label dashboardProjectLabel;

    @FXML
    private VBox searchProjectVBox;

    @FXML
    private GridPane searchProjectGrid;

    @FXML
    private TextField searchProjectText;

    @FXML
    private ComboBox<String> choiceProjectCombo;

    @FXML
    private TableView<Project> searchProjectTable;

    @FXML
    private TableColumn<?, ?> projectColumn;

    @FXML
    private TableColumn<?, ?> acronymColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<String, Double> totalCostColumn;

    @FXML
    private BorderPane userPane;

    @FXML
    private Label dashboardUserLabel;

    @FXML
    private VBox searchActorVBox11;

    @FXML
    private GridPane searchUserGrid;

    @FXML
    private TextField searchUserText;

    @FXML
    private Button searchUserButton;

    @FXML
    private ComboBox<String> choiceUserCombo;

    @FXML
    private TableView<UserAction> searchUserTable;

    @FXML
    private TableColumn<?, ?> userNameColumn;

    @FXML
    private TableColumn<?, ?> actionColumn;

    @FXML
    private TableColumn<?, ?> parameterColumn;

    @FXML
    private TableColumn<?, ?> actionDateColumn;

    @FXML
    private BorderPane actorPane;

    @FXML
    private ComboBox<String> shortNameCombo;

    @FXML
    private ComboBox<String> activityTypeCombo;

    @FXML
    private ComboBox<String> countryCombo;

    @FXML
    private ComboBox<String> topicCombo;

    @FXML
    private BorderPane dashboardPane;

    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis barCategoryAxis;

    @FXML
    private NumberAxis barNumberAxis;


    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private CategoryAxis lineCategoryAxis;

    @FXML
    private NumberAxis lineNumberAxis;

    @FXML
    private PieChart pieChart;

    @FXML
    private TitledPane moreGraphsPane;

    @FXML
    private Button graphs1Button;

    @FXML
    private Button moreGraph1Button;

    @FXML
    private Button moreGraph2Button;

    @FXML
    private Button moreGraph3Button;

    @FXML
    private Button moreGraph4Button;

    @FXML
    private Button moreGraph5Button;

    @FXML
    private Button moreGraph6Button;

    @FXML
    private Button moreGraph7Button;

    @FXML
    private Button moreGraph8Button;

    @FXML
    private BorderPane moreDashboardPane;

    @FXML
    private BorderPane moreGraphsInternPane;

    private static final Map<String,String> criteriaMap = new HashMap<String,String>(){{
        put("Short Name","short_name");
        put("Name","name");
        put("Acronym","acronym");
        put("Title","title");
        put("User Name","name");
        put("Action","action_name");};};

    private ChangeListener<? super String> listener = (observable, oldValue, newValue) -> {
        searchForCharts();
    };


    @FXML
    public void initialize() {
        List<String> shortNameList = ActorManager.getActorShortNames();
        List<String> countryList = CountryManager.getCountries();
        List<String> topicList = TopicManager.getTopics();
        List<String> activityList = ActivityTypeManager.getActivityTypes();

        shortNameCombo.setItems(FXCollections.observableArrayList(shortNameList));
        new AutoCompleteBox(shortNameCombo);

        countryCombo.setItems(FXCollections.observableArrayList(countryList));
        new AutoCompleteBox(countryCombo);

        topicCombo.setItems(FXCollections.observableArrayList(topicList));
        new AutoCompleteBox(topicCombo);

        activityTypeCombo.setItems(FXCollections.observableArrayList(activityList));
        new AutoCompleteBox(activityTypeCombo);

        shortNameCombo.getSelectionModel().selectedItemProperty().addListener(listener);
        countryCombo.getSelectionModel().selectedItemProperty().addListener(listener);
        topicCombo.getSelectionModel().selectedItemProperty().addListener(listener);
        activityTypeCombo.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void goToSignUp(){
        loginVBox.setVisible(false);
        signUpVBox.setVisible(true);
    }

    public void goToLogin(){
        loginVBox.setVisible(true);
        signUpVBox.setVisible(false);
    }

    public void showActorSearch(){
        dashboardLabel.setText("Search Actor");
        actorPane.setVisible(true);
        projectPane.setVisible(false);
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        moreDashboardPane.setVisible(false);
        List<String> options = new ArrayList<String>(Arrays.asList(new String[]{"Short Name","Name" }));
        choiceActorCombo.setItems(FXCollections.observableArrayList(options));
        searchActorVBox.setVisible(true);
    }

    public void showMoreGraphs(){
        actorPane.setVisible(false);
        projectPane.setVisible(false);
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        moreDashboardPane.setVisible(true);
    }

    public void showProjectSearch(){
        dashboardProjectLabel.setText("Search Project");
        actorPane.setVisible(false);
        projectPane.setVisible(true);
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        moreDashboardPane.setVisible(false);
        List<String> options = new ArrayList<String>(Arrays.asList(new String[]{"Acronym","Title" }));
        choiceProjectCombo.setItems(FXCollections.observableArrayList(options));
    }

    public void showDashboard(){
        actorPane.setVisible(false);
        projectPane.setVisible(false);
        dashboardPane.setVisible(true);
        userPane.setVisible(false);
        moreDashboardPane.setVisible(false);
        clearFilter();
    }

    public void clearFilter(){
        shortNameCombo.setValue(null);
        activityTypeCombo.setValue(null);
        countryCombo.setValue(null);
        topicCombo.setValue(null);
        barChart.getData().clear();
        pieChart.getData().clear();
        lineChart.getData().clear();
    }

    public void showUserSearch(){
        actorPane.setVisible(false);
        projectPane.setVisible(false);
        dashboardPane.setVisible(false);
        moreDashboardPane.setVisible(false);
        userPane.setVisible(true);
        List<String> options = new ArrayList<String>(Arrays.asList(new String[]{"User Name","Action" }));
        choiceUserCombo.setItems(FXCollections.observableArrayList(options));
    }

    private void displayAppScreen() {
        dashboardSplitPane.setVisible(true);
        dashboardPane.setVisible(true);
        actorPane.setVisible(false);
        projectPane.setVisible(false);
        moreDashboardPane.setVisible(false);
        userPane.setVisible(false);
        userLabel.setText(connectedUser.getUsername() + "\n" + ((connectedUser.isAdmin())?"Admin":"Normal User"));
        if (connectedUser.isAdmin()){
            userTiteledPane.setVisible(true);
        } else {
            userTiteledPane.setVisible(false);
        }
    }


    public void searchActor(){
        boolean choice = validateChoiceField(choiceActorCombo);
        boolean parameter= validateStringField(searchActorText);
        if (choice && parameter) {
            HistoryManager.logAction(connectedUser, ActionType.SEARCH_ACTOR, choiceActorCombo.getValue()+":"+searchActorText.getText());
            List<Actor> resultSearch = ActorManager.searchActor(criteriaMap.get(choiceActorCombo.getValue()), searchActorText.getText());
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
            totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalEcParticipation"));
            totalColumn.setCellFactory(col ->
                    new TableCell<String, Double>() {
                        @Override
                        protected void updateItem(Double cost, boolean empty) {
                            StringBuilder sb = new StringBuilder();
                            Formatter formatter = new Formatter(sb, Locale.UK);
                            formatter.format("€ %(,.2f", cost);
                            setText(sb.toString());
                        }
                    });
            urlColumn.setCellValueFactory(new PropertyValueFactory<>("organizationUrl"));
            searchActorTable.setItems(FXCollections.observableArrayList(resultSearch));
        }
    }

    public void searchProject(){
        boolean choice = validateChoiceField(choiceProjectCombo);
        boolean parameter= validateStringField(searchProjectText);
        if (choice && parameter) {
            HistoryManager.logAction(connectedUser, ActionType.SEARCH_PROJECT, choiceProjectCombo.getValue()+":"+searchProjectText.getText());
            List<Project> resultSearch = ProjectManager.searchProject(criteriaMap.get(choiceProjectCombo.getValue()), searchProjectText.getText());
            projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
            acronymColumn.setCellValueFactory(new PropertyValueFactory<>("acronym"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
            totalCostColumn.setCellFactory(col ->
                    new TableCell<String, Double>() {
                        @Override
                        protected void updateItem(Double cost, boolean empty) {
                            StringBuilder sb = new StringBuilder();
                            Formatter formatter = new Formatter(sb, Locale.UK);
                            formatter.format("€ %(,.2f", cost);
                            setText(sb.toString());
                        }
                    });
            searchProjectTable.setItems(FXCollections.observableArrayList(resultSearch));



        }
    }

    public void searchUser(){
        boolean choice = validateChoiceField(choiceUserCombo);
        boolean parameter= validateStringField(searchUserText);
        if (choice && parameter) {
            List<UserAction> resultSearch = new ArrayList<UserAction>();
            HistoryManager.logAction(connectedUser, ActionType.SEARCH_USER_ACTION, choiceUserCombo.getValue()+":"+searchUserText.getText());
            if (choiceUserCombo.getValue().equals("Action")) {
                resultSearch = HistoryManager.searchUserActionByActionName(searchUserText.getText());
            } else {
                resultSearch = HistoryManager.searchUserActionByUser(searchUserText.getText());
            }
            userNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionName"));
            actionDateColumn.setCellValueFactory(new PropertyValueFactory<>("actionDate"));
            parameterColumn.setCellValueFactory(new PropertyValueFactory<>("actionParameters"));
            searchUserTable.setItems(FXCollections.observableArrayList(resultSearch));
        }
    }

    public void logout() {
        dashboardSplitPane.setVisible(false);
        HistoryManager.logAction(connectedUser, ActionType.LOGOUT, "Logout OK");
        connectedUser = null;
        loginVBox.setVisible(true);
    }

    public void signUp(){
        signUpErrorLabel.setText("");
        boolean usernameOk = validateStringField(usernameSignText);
        boolean passwordOk = validateStringField(passwordSignText);
        boolean confirmPasswordOk = validateStringField(confirmPasswordSignText);

        if (usernameOk && passwordOk && confirmPasswordOk) {
            if (!passwordSignText.getText().equals(confirmPasswordSignText.getText())){
                signUpErrorLabel.setText("Password and confirm password are different");
            } else {
                User userSignUp = new User(usernameSignText.getText(), passwordSignText.getText());
                try {
                    connectedUser = UserManager.addUser(userSignUp);
                    HistoryManager.logAction(connectedUser, ActionType.SIGNUP, "SignUp OK");
                    signUpVBox.setVisible(false);
                    usernameSignText.setText("");
                    passwordSignText.setText("");
                    confirmPasswordSignText.setText("");
                    displayAppScreen();
                } catch (SQLException e) {
                    signUpErrorLabel.setText(e.getMessage());
                }
            }
        } else {
            List<String> textErrors = new ArrayList<String>();
            if (!usernameOk){
                textErrors.add("Username");
            }
            if (!passwordOk){
                textErrors.add("Password");
            }
            if (!confirmPasswordOk){
                textErrors.add("Confirm Password");
            }
            String errorMessage = String.join(" and ", textErrors) + ((textErrors.size()>1)? " are required":" is required");
            signUpErrorLabel.setText(errorMessage);
        }
    }

    public void login(){
        loginErrorLabel.setText("");
        boolean usernameOk = validateStringField(usernameText);
        boolean passwordOk = validateStringField(passwordText);

        if (usernameOk && passwordOk) {
            User userLogin = new User(usernameText.getText(), passwordText.getText());
            userLogin = UserManager.loginUser(userLogin);
            System.out.println(userLogin);
            if (userLogin==null){
                loginErrorLabel.setText("The username/password combination is not correct");
            } else {
                connectedUser = userLogin;
                HistoryManager.logAction(connectedUser, ActionType.LOGIN, "Login OK");
                loginVBox.setVisible(false);
                usernameText.setText("");
                passwordText.setText("");
                displayAppScreen();
            }
        } else {
            String errorMessage = (!usernameOk)?((!passwordOk)? "Username and Password are required":"Username is required"):"Password is required";
            loginErrorLabel.setText(errorMessage);
        }
    }

    public void searchForCharts(){
        String shortName = shortNameCombo.getValue();
        String country = countryCombo.getValue();
        String activityType = activityTypeCombo.getValue();
        String topic = topicCombo.getValue();
        HistoryManager.logAction(connectedUser, ActionType.SEARCH_DASHBOARD, "shortName:"+shortName+";country:"+country+";activityType:"+activityType+";topic:"+topic);
        List<ChartData> chartDataList = ActorManager.findActors(shortName, activityType, country, topic);

        Map<String, Double> countriesChart = new HashMap<String, Double>();

        Map<String, Double> activityTypeChart = new HashMap<String, Double>();

        Map<Integer, Double> yearChart = new HashMap<Integer, Double>();

        chartDataList.forEach(chartData -> {
                    countriesChart.put(chartData.getCountry(), (countriesChart.containsKey(chartData.getCountry())?countriesChart.get(chartData.getCountry()):0) + chartData.getContribution());
                    activityTypeChart.put(chartData.getActivityType(), (activityTypeChart.containsKey(chartData.getActivityType())?activityTypeChart.get(chartData.getActivityType()):0) + chartData.getContribution());
                    yearChart.put(chartData.getYear(), (yearChart.containsKey(chartData.getYear())?yearChart.get(chartData.getYear()):0) + chartData.getContribution());
                }
        );

        barChart.getData().clear();
        barChart.layout();
        barCategoryAxis.setLabel("Countries");
        barNumberAxis.setLabel("Contribution");

        XYChart.Series<String, Double> countryDataSeries = new XYChart.Series();
        countryDataSeries.setName("Contributions by Country");

        countriesChart.keySet().forEach(countryName -> {
            countryDataSeries.getData().add(new XYChart.Data<String, Double>(countryName, countriesChart.get(countryName)));
        });

        barChart.getData().add(countryDataSeries);

        for (XYChart.Data<String, Double> entry : countryDataSeries.getData()) {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.UK);
            formatter.format("€ %(,.2f", entry.getYValue());
            Tooltip t = new Tooltip(sb.toString());
            Tooltip.install(entry.getNode(), t);
        }


        pieChart.getData().clear();
        pieChart.setTitle("Contributions by Activity Type");
        activityTypeChart.keySet().forEach(activity ->
                pieChart.getData().add(new PieChart.Data(activity, activityTypeChart.get(activity)))
        );

        pieChart.getData().stream().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.UK);
            formatter.format("€ %(,.2f", data.getPieValue());
            tooltip.setText(sb.toString());
            Tooltip.install(data.getNode(), tooltip);
        });

        lineChart.getData().clear();
        lineCategoryAxis.setLabel("Years");
        lineNumberAxis.setLabel("Contribution");

        XYChart.Series<String, Double> yearsDataSeries = new XYChart.Series<String, Double>();
        yearsDataSeries.setName("Contributions per year");

        List<Integer> listYears = new ArrayList<Integer>(yearChart.keySet());
        Collections.sort(listYears);
        listYears.forEach(year ->
                yearsDataSeries.getData().add(new XYChart.Data<String, Double>(year+"", yearChart.get(year)))
        );

        lineChart.getData().add(yearsDataSeries);

        for (XYChart.Data<String, Double> entry : yearsDataSeries.getData()) {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.UK);
            formatter.format("€ %(,.2f", entry.getYValue());
            Tooltip t = new Tooltip(sb.toString());
            Tooltip.install(entry.getNode(), t);
        }
    }

    private boolean validateStringField(TextField field){
        if (field.getText().equals("")){
            field.setStyle("-fx-border-color: red;-fx-text-color: red;");
            return false;
        } else {
            field.setStyle("-fx-border-color: black;-fx-text-color: black;");
            return true;
        }
    }

    private boolean validateChoiceField(ComboBox<String> field){
        if (field.getValue()==null || field.getValue().equals("")){
            field.setStyle("-fx-border-color: red;-fx-text-color: red;");
            return false;
        } else {
            field.setStyle("-fx-border-color: black;-fx-text-color: black;");
            return true;
        }
    }

    public void showBarGraph1() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Signed",19014),
                new PieChart.Data("Closed",6568),
                new PieChart.Data("TERMINATED",358)

        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Project Status");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        moreGraphsInternPane.setCenter(pieChart);

    }

    public void showBarGraph2() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Funding Scheme Categories");

        NumberAxis yAxis =  new NumberAxis();
        yAxis.setLabel("Number of Projects ");

        BarChart barChart = new BarChart(xAxis,yAxis);

        XYChart.Series data =  new XYChart.Series();
        data.setName("Number of Projects in each category");

        data.getData().add(new XYChart.Data("IMI2-RIA", 79));
        data.getData().add(new XYChart.Data("ECSEL-IA", 30));
        data.getData().add(new XYChart.Data("ERC-POC", 760));
        data.getData().add(new XYChart.Data("BBI-RIA", 52));
        data.getData().add(new XYChart.Data("FCH2-RIA", 62));
        data.getData().add(new XYChart.Data("ERC-ADG", 1177));
        data.getData().add(new XYChart.Data("IA", 1201));
        data.getData().add(new XYChart.Data("CSA", 1761));
        data.getData().add(new XYChart.Data("RIA", 2788));
        data.getData().add(new XYChart.Data("MSCA-RISE", 445));
        data.getData().add(new XYChart.Data("ERC-STG", 2080));
        data.getData().add(new XYChart.Data("MSCA-IF-EF-CAR", 221));
        data.getData().add(new XYChart.Data("MSCA-ITN-EJD", 61));
        data.getData().add(new XYChart.Data("ERC-LVG", 3));
        data.getData().add(new XYChart.Data("MSCA-ITN-EID", 127));
        data.getData().add(new XYChart.Data("ERC-SyG", 27));
        data.getData().add(new XYChart.Data("SME-2", 1136));
        data.getData().add(new XYChart.Data("SME-1", 3945));
        data.getData().add(new XYChart.Data("BBI-IA-FLAG", 9));
        data.getData().add(new XYChart.Data("COFUND-EJP", 6));
        data.getData().add(new XYChart.Data("MSCA-IF-EF-ST", 5032));
        data.getData().add(new XYChart.Data("ECSEL-RIA", 34));
        data.getData().add(new XYChart.Data("BBI-IA-DEMO", 29));
        data.getData().add(new XYChart.Data("MSCA-IF-EF-RI", 430));
        data.getData().add(new XYChart.Data("ERC-COG", 1604));
        data.getData().add(new XYChart.Data("MSCA-COFUND-FP", 74));
        data.getData().add(new XYChart.Data("SGA-CSA", 45));
        data.getData().add(new XYChart.Data("MSCA-IF-GF", 717));
        data.getData().add(new XYChart.Data("ERA-NET-Cofund", 64));
        data.getData().add(new XYChart.Data("MSCA-ITN-ETN", 646));
        data.getData().add(new XYChart.Data("MSCA-COFUND-DP", 68));
        data.getData().add(new XYChart.Data("MC-IA", 1227));


        barChart.getData().add(data);

        moreGraphsInternPane.setCenter(barChart);


    }

    public void showBarGraph3() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Coordinator",128),
                new PieChart.Data("Partner",56),
                new PieChart.Data("Participant",873)

        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Organization Roles");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        moreGraphsInternPane.setCenter(pieChart);
    }

    public void showBarGraph4() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Countries");


        NumberAxis yAxis =  new NumberAxis();
        yAxis.setLabel("Number of Projects ");

        BarChart barChart = new BarChart(xAxis,yAxis);


        XYChart.Series data =  new XYChart.Series();
        data.setName("Number of Projects Participation in each Country");

        data.getData().add(new XYChart.Data("Armenia", 2));
        data.getData().add(new XYChart.Data("Austria", 26));
        data.getData().add(new XYChart.Data("Belgium", 52));
        data.getData().add(new XYChart.Data("Bulgaria", 1));
        data.getData().add(new XYChart.Data("Brazil", 1));
        data.getData().add(new XYChart.Data("Canada", 5));
        data.getData().add(new XYChart.Data("Switzerland", 29));
        data.getData().add(new XYChart.Data("Chile", 1));
        data.getData().add(new XYChart.Data("Cyprus", 7));
        data.getData().add(new XYChart.Data("Czechia", 8));
        data.getData().add(new XYChart.Data("Germany", 141));
        data.getData().add(new XYChart.Data("Denmark", 23));
        data.getData().add(new XYChart.Data("Estonia", 4));
        data.getData().add(new XYChart.Data("Greece", 27));
        data.getData().add(new XYChart.Data("Spain", 83));
        data.getData().add(new XYChart.Data("Finland", 32));
        data.getData().add(new XYChart.Data("Faroe Islands", 2));
        data.getData().add(new XYChart.Data("France", 92));
        data.getData().add(new XYChart.Data("Georgia", 1));
        data.getData().add(new XYChart.Data("Croatia", 6));
        data.getData().add(new XYChart.Data("Hungary", 9));
        data.getData().add(new XYChart.Data("Ireland", 31));
        data.getData().add(new XYChart.Data("Israel", 12));
        data.getData().add(new XYChart.Data("Iceland", 2));
        data.getData().add(new XYChart.Data("Italy", 99));
        data.getData().add(new XYChart.Data("Kenya", 1));
        data.getData().add(new XYChart.Data("Lithuania", 4));
        data.getData().add(new XYChart.Data("Luxembourg", 4));
        data.getData().add(new XYChart.Data("Latvia", 3));
        data.getData().add(new XYChart.Data("Moldova", 1));
        data.getData().add(new XYChart.Data("Netherlands", 74));
        data.getData().add(new XYChart.Data("Norway", 21));
        data.getData().add(new XYChart.Data("Poland", 22));
        data.getData().add(new XYChart.Data("Portugal", 24));
        data.getData().add(new XYChart.Data("Romania", 13));
        data.getData().add(new XYChart.Data("Serbia", 4));
        data.getData().add(new XYChart.Data("Sweden", 23));
        data.getData().add(new XYChart.Data("Slovenia", 13));
        data.getData().add(new XYChart.Data("Slovakia", 3));
        data.getData().add(new XYChart.Data("Turkey", 7));
        data.getData().add(new XYChart.Data("Ukraine", 3));
        data.getData().add(new XYChart.Data("United Kingdom", 126));
        data.getData().add(new XYChart.Data("United States", 3));
        data.getData().add(new XYChart.Data("South Africa", 3));


        barChart.getData().add(data);

        moreGraphsInternPane.setCenter(barChart);

    }

    public void showBarGraph5() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Higher or Secondary Education Establishments",317),
                new PieChart.Data("Other",70),
                new PieChart.Data("Private for-profit entities",363),
                new PieChart.Data("Public bodies ",61),
                new PieChart.Data("Research Organisations",246)

        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Organization Activity Types");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        moreGraphsInternPane.setCenter(pieChart);
    }

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:8889/cordisDB?zeroDateTimeBehavior=convertToNull";

    public void showBarGraph6() {
        int signed=0, closed =0;
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected Sccuessfully");

            String query = "SELECT * FROM project";

            Statement stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("status").equalsIgnoreCase("SIGNED")){
                    signed++;
                }else{
                    closed++;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }


            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(signed, "Signed", "Signed Projects");
            dataset.addValue(closed, "Closed", "Closed Projects");

            JFreeChart chart = ChartFactory.createBarChart("Project's Filtered on basis of Status", "Projects Status", "Total Number of Projects", dataset, PlotOrientation.VERTICAL,true, true, true);

            CategoryPlot barchart = new CategoryPlot();
            barchart.setRangeGridlinePaint(Color.ORANGE);

            ChartPanel barpabel = new ChartPanel(chart);

            chart.getPlot().setBackgroundPaint(Color.WHITE);

        final SwingNode chartSwingNode = new SwingNode();

        chartSwingNode.setContent(barpabel);

        moreGraphsInternPane.setCenter(chartSwingNode);
    }

    public void showBarGraph7() {
        long p_start = 0;
        long p_end = 0;
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected Sccuessfully");

            String query = "SELECT * FROM project";

            Statement stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getDate("start_date") !=null && rs.getDate("start_date").toString().equals("2019-03-01")){
                    p_start++;
                    System.out.println(p_end);
                }else if(rs.getDate("end_date") !=null && rs.getDate("end_date").toString().equals("2019-03-01")){
                    p_end++;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Project's Strated in March 2019", p_start);
        data.setValue("Project's Ended in March 2019", p_end);

        JFreeChart pchart = ChartFactory.createPieChart("Project's Started & Ended in March 2019", data, true, true, true);
        CategoryPlot piechart = new CategoryPlot();
        pchart.getPlot().setBackgroundPaint(Color.WHITE);

        ChartPanel piepanel = new ChartPanel(pchart);

        final SwingNode chartSwingNode = new SwingNode();

        chartSwingNode.setContent(piepanel);

        moreGraphsInternPane.setCenter(chartSwingNode);
    }

    public void showBarGraph8() {
        int first=0, second=0, third=0, fourth=0, fifth =0;
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected Sccuessfully");

            String query = "SELECT * FROM project";

            Statement stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString("funding_scheme_category").equals("49")){
                    first++;
                }else if(rs.getString("funding_scheme_category").equals("75")){
                    second++;
                }else if(rs.getString("funding_scheme_category").equals("107")){
                    third++;
                }else if(rs.getString("funding_scheme_category").equals("94")){
                    fourth++;
                }else if(rs.getString("funding_scheme_category").equals("137")){
                    fifth++;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(first, "Top", "1st");
        dataset.addValue(second, "Second", "2nd");
        dataset.addValue(third, "Third", "3rd");
        dataset.addValue(fourth, "Fourth", "4th");
        dataset.addValue(fifth, "Fifth", "5th");

        JFreeChart chart = ChartFactory.createBarChart("Top five Funding Scheme Category", "Top 5 Funding Scheme's", "Total Project's Associated with Schemes", dataset, PlotOrientation.HORIZONTAL, true, true, true);

        CategoryPlot barchart = new CategoryPlot();
        barchart.setRangeGridlinePaint(Color.ORANGE);

        ChartPanel barpabel = new ChartPanel(chart);

        final SwingNode chartSwingNode = new SwingNode();

        chartSwingNode.setContent(barpabel);

        moreGraphsInternPane.setCenter(chartSwingNode);
    }
}
