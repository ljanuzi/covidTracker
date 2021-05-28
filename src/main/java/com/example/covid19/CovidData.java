package com.example.covid19;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CovidData {

    private float cases;
    private float admissions;
    private float deaths;
    private String date;

    public CovidData() {

    }

    public CovidData(float cases, float admissions, float deaths) {
        this.cases = cases;
        this.admissions = admissions;
        this.deaths = deaths;
    }

    public CovidData(String date, float cases, float admissions, float deaths) {
        this.date = date;
        this.cases = cases;
        this.admissions = admissions;
        this.deaths = deaths;
    }

    public float getCases() {
        return cases;
    }

    public float getAdmissions() {
        return admissions;
    }

    public float getDeaths() {
        return deaths;
    }

    public String getDate() {
        return date;
    }

    public void setCases(float cases) {
        this.cases = cases;
    }

    public void setAdmissions(float admissions) {
        this.admissions = admissions;
    }

    public void setDeaths(float deaths) {
        this.deaths = deaths;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "cases=" + cases +
                ", admissions=" + admissions +
                ", deaths=" + deaths +
                ", date='" + date + '\'' +
                '}';
    }
}
