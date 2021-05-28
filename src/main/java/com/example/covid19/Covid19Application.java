package com.example.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;

@Configuration
@SpringBootApplication
public class Covid19Application {

    public static void main(String[] args) {
        SpringApplication.run(Covid19Application.class, args);


    }

//    public static ArrayList<Float> caclulateMedian(){
//        if ()
//    }

}
