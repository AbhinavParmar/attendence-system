package com.shivam.hrms.attendance.attendancesystem;

import org.apache.http.HttpServerConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class FaceDetectionPythonCall {
    public static void main(String args[]) throws IOException {

        String       postUrl       = "https://192.168.5.26:3000/detect_final2";// put in your url
        Gson         gson          = new Gson();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost     httpPost          = new HttpPost(postUrl);

        //create a POJO

        //gson.tojson() converts your pojo to json

        //currently Hard coding the request data for httpPost Call directly from method to python script AND
        StringEntity postingString = new StringEntity(gson.toJson(
                new EmployeeAttendance("Unknown Person",
                        "Unknown Dept",
                        "2023-12-28T09:45:21",
                        "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")
                ));

        httpPost.setEntity(postingString);
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        System.out.println("POST Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // print result
        System.out.println(response.toString());
        httpClient.close();
    }
}
