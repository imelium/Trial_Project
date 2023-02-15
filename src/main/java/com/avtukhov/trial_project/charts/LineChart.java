package com.avtukhov.trial_project.charts;

import com.avtukhov.trial_project.DTO.VoteResponse;
import com.avtukhov.trial_project.charts.LineRequest.Chart;
import com.avtukhov.trial_project.charts.LineRequest.Data;
import com.avtukhov.trial_project.charts.LineRequest.Dataset;
import com.avtukhov.trial_project.charts.LineRequest.LineChartRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineChart {

   public String getLineChart(List<VoteResponse> responseList){
       List<Integer> score = responseList.stream().map(VoteResponse::getScore).toList();
       List<Integer> date = responseList.stream().map(x->x.getDateOdVote().getDayOfMonth()).collect(Collectors.toList());
       RestTemplate restTemplate = new RestTemplate();
       String url = "https://quickchart.io/chart/create";
       Data data = new Data();
       data.setLabels(date);
       Dataset dataset = new Dataset();
       dataset.setLabel("Vote");
       dataset.setData(score);
       List<Dataset> list = new ArrayList<>();
       list.add(dataset);
       data.setDatasets(list);
       Chart chart = new Chart(data);
       LineChartRequest lineChartRequest = new LineChartRequest(chart);

       HttpHeaders headers = new HttpHeaders();
       headers.set("Content-Type", "application/json");


       HttpEntity<LineChartRequest> request = new HttpEntity<>(lineChartRequest, headers);
       return restTemplate.postForObject(url, request, String.class);


   }








}
