package com.example.covid19;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class DB_Manager {

    Connection connection;

    public DB_Manager() {
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/mydb", "postgres","340uzi540");
        } catch (SQLException e) {
            e.printStackTrace();
            //return "Connection Failed! Check output console";
        }
        System.out.println(connection);
    }

    public String DB_Read() throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM covidData");
        String result="";
        while (rs.next()) {
            result += rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3)
                    + " " +rs.getString(4) + " " + System.lineSeparator();
        }
        return result;
    }

    public String getLastDate() throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();
        rs = st.executeQuery("SELECT timestamp FROM covidData ORDER BY timestamp DESC LIMIT 1");
        String result="";
        while (rs.next()) {
            result += rs.getString(1) + System.lineSeparator();
        }
        return result;
    }

    public String getFirstDate() throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();
        rs = st.executeQuery("SELECT timestamp FROM covidData ORDER BY timestamp ASC LIMIT 1");
        String result="";
        while (rs.next()) {
            result += rs.getString(1) + System.lineSeparator();
        }
        return result;
    }

//    public String DisplayAllData() throws SQLException {
//        Statement st = null;
//        ResultSet rs = null;
//
//        st = connection.createStatement();
//        rs = st.executeQuery("SELECT * from covidData;");
//
//        String result="";
//        while (rs.next()) {
//            result += rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3)
//                    + " " +rs.getString(4) + " " + System.lineSeparator();
//        }
//        return result;
//    }

    public ArrayList<CovidData> displayData(String startDate, String endDate) throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();
        rs = st.executeQuery("SELECT * from covidData where timestamp>='" + startDate + "' and timestamp<='"+ endDate+"';");

        CovidData covidData = null;
        ArrayList<CovidData> dataList = new ArrayList<CovidData>();;
        String result="";
        while (rs.next()) {
            covidData = new CovidData(
                    rs.getString(1),
                    Float.parseFloat(rs.getString(2)),
                    Float.parseFloat(rs.getString(3)),
                    Float.parseFloat(rs.getString(4)));
            dataList.add(covidData);
            result += rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3)
                    + " " +rs.getString(4) + " " + System.lineSeparator();
        }
        return dataList;
    }

    public CovidData meanOfData(String startDate, String endDate) throws SQLException {
        Statement st = null;
        ResultSet rs = null;

        st = connection.createStatement();
        rs = st.executeQuery("SELECT avg(cases),avg(admissions),avg(deaths) from covidData where timestamp>='" + startDate + "' and timestamp<='"+ endDate+"';");

        CovidData covidData = null;
        String result="";
        while (rs.next()) {
            covidData = new CovidData(
                    Float.parseFloat(rs.getString(1)),
                    Float.parseFloat(rs.getString(2)),
                    Float.parseFloat(rs.getString(3)));
            result += rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3) + System.lineSeparator();
        }
        return covidData;
    }

    public CovidData medianOfData(String startDate, String endDate) throws SQLException {
        Statement stCases = null;
        ResultSet rsCases = null;
        Statement stAdmissions = null;
        ResultSet rsAdmissions = null;
        Statement stDeaths = null;
        ResultSet rsDeaths = null;

        stCases = connection.createStatement();
        rsCases = stCases.executeQuery("SELECT cases from covidData where timestamp>='" + startDate + "' and timestamp<='"+ endDate+"' ORDER BY cases ASC;");
        stAdmissions = connection.createStatement();
        rsAdmissions = stAdmissions.executeQuery("SELECT admissions from covidData where timestamp>='" + startDate + "' and timestamp<='"+ endDate+"' ORDER BY admissions ASC;");
        stDeaths = connection.createStatement();
        rsDeaths = stDeaths.executeQuery("SELECT deaths from covidData where timestamp>='" + startDate + "' and timestamp<='"+ endDate+"' ORDER BY deaths ASC;");

        CovidData covidData = null;
        ArrayList<Float> cases = new ArrayList<>();
        ArrayList<Float> admissions = new ArrayList<>();;
        ArrayList<Float> deaths = new ArrayList<>();;

        String result="";//rs.getString(1);
        while (rsCases.next() && rsAdmissions.next() && rsDeaths.next()) {
            cases.add(Float.parseFloat(rsCases.getString(1)));
            admissions.add(Float.parseFloat(rsAdmissions.getString(1)));
            deaths.add(Float.parseFloat(rsDeaths.getString(1)));
            result += rsCases.getString(1) + " " +  rsAdmissions.getString(1) + " " +  rsDeaths.getString(1) + System.lineSeparator();
        }

        float medianOfCases = findMedian(cases);
        System.out.println("median of cases");
        System.out.println(result);
        float medianOfAdmissions = findMedian(admissions);
        float medianOfDeaths = findMedian(deaths);

        covidData = new CovidData(medianOfCases, medianOfAdmissions, medianOfDeaths);


        return covidData;

    }

    private float findMedian (ArrayList <Float> array){
        if(array.size() % 2 == 1){
            System.out.println("IF: avg median");
            System.out.println(array.get(array.size()/2));
            return array.get(array.size()/2);
//            return medianOfCases;
        } else{
            int tmp = array.size()/2;
//            float avgmedian = (array.get(tmp - 1) + array.get(tmp))/2;
            System.out.println("Else: avg median");
            System.out.println((array.get(tmp - 1) + array.get(tmp))/2);
            return (array.get(tmp - 1) + array.get(tmp))/2;
        }
    }
}
