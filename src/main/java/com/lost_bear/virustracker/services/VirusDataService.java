package com.lost_bear.virustracker.services;

import com.lost_bear.virustracker.models.Location;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class VirusDataService {
    private static String VIRUS_DATASOURCE_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private List<Location> allStats = new ArrayList<>();

    public List<Location> getAllStats() {
        return allStats;
    }
    private long total_deaths;

    public long getTotal_deaths() {
        return total_deaths;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDataVirus() throws IOException, InterruptedException {
        List<Location> newStats = new ArrayList<>();
        total_deaths = 0;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATASOURCE_URL)).build();
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csv_reader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv_reader);
        for(CSVRecord record : records){
            Location location = new Location();
            location.setState(record.get("Province/State"));
            location.setCountry(record.get("Country/Region"));
            location.setLatest_total_cases(Integer.parseInt(record.get(record.size()-1)));
            total_deaths += location.getLatest_total_cases();
            newStats.add(location);
        }
        allStats = newStats;
    }
}
