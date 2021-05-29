package com.example.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
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
    public CovidData getMean( String startDate, String endDate) throws SQLException {
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
    public String insertData(@RequestBody CovidData covidData) throws SQLException {
        try{
            db_manager.addEntry(covidData);
            return "Data added to the database";
        }catch(Exception e){
            return "Error" + e;
        }

    }

    @PutMapping("/alterData/{timestamp}")
    @ResponseBody
    public void alterData(@PathVariable String timestamp, @RequestBody CovidData covidData) throws SQLException {
        db_manager.editEntry(timestamp, covidData);
    }

    @DeleteMapping("{timestamp}")
    @ResponseBody
    public void deleteData(@PathVariable String timestamp ) throws SQLException {
        db_manager.deleteEntry(timestamp);
    }
}
