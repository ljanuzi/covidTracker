package com.example.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class CovidController {

    private final DB_Manager db_manager;

    public CovidController(DB_Manager db_manager) {
        this.db_manager = db_manager;
    }

    @GetMapping("/getData")
    @ResponseBody
    public ArrayList<CovidData>  getData(String startDate, String endDate) throws SQLException {
        ArrayList<CovidData> data = db_manager.displayData(startDate, endDate);
        return data;
    }

    @GetMapping(value = "/getAllData", produces = "application/json")
    @ResponseBody
    public ArrayList<CovidData> getAllData() throws SQLException {

        String startDate = db_manager.getFirstDate();
        String endDate = db_manager.getLastDate();

        ArrayList<CovidData> data = db_manager.displayData(startDate,endDate);
        return data;
    }

    @GetMapping("/getMean")
    @ResponseBody
    public CovidData getMean(String startDate, String endDate) throws SQLException {
        CovidData data = db_manager.meanOfData(startDate, endDate);
        return data;
    }

    @GetMapping("/getAllMeans")
    @ResponseBody
    public CovidData getAllMeans() throws SQLException {
        String startDate = db_manager.getFirstDate();
        String endDate = db_manager.getLastDate();
        CovidData data = db_manager.meanOfData(startDate, endDate);
        return data;
    }

    @GetMapping("/getMedian")
    @ResponseBody
    public CovidData getMedian(String startDate, String endDate) throws SQLException {
        CovidData data = db_manager.medianOfData(startDate, endDate);
        return data;
    }

    @GetMapping("/getAllMedian")
    @ResponseBody
    public CovidData getAllMedian() throws SQLException {

        String startDate = db_manager.getFirstDate();
        String endDate = db_manager.getLastDate();

        CovidData data = db_manager.medianOfData(startDate, endDate);
        return data;
    }

    @PostMapping("/insertData")
    @ResponseBody
    public void insertData(@RequestBody CovidData covidData) throws SQLException {
        db_manager.addEntry(covidData);
    }

    @PostMapping("/alterData")
    @ResponseBody
    public void alterData(@RequestBody CovidData covidData) throws SQLException {
        db_manager.editEntry(covidData);
    }

    @DeleteMapping("/deleteData")
    @ResponseBody
    public void deleteData(String timestamp) throws SQLException {
        db_manager.deleteEntry(timestamp);
    }
}
