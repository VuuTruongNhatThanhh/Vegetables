package vn.edu.hcmuaf.fit.services.API_LOGISTIC;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Ward_API {
    public static List<Ward> convert(String key, int id) {
        String responseData = null;
        String apiUrl = "http://140.238.54.136/api/ward?districtID=" + id;
        String accessToken = key;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                responseData = String.valueOf(response);
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
//CONVERT
        List<Ward> wardList = new ArrayList<>();

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(responseData).getAsJsonObject();

        if (jsonObject.has("original")) {
            JsonObject originalJson = jsonObject.getAsJsonObject("original");
            if (originalJson.has("data") && originalJson.get("data").isJsonArray()) {
                JsonArray jsonArray = originalJson.getAsJsonArray("data");
                for (JsonElement jsonElement : jsonArray) {
                    JsonObject provinceJson = jsonElement.getAsJsonObject();
                    int wardCode = provinceJson.get("WardCode").getAsInt();
                    int districtID = provinceJson.get("DistrictID").getAsInt();
                    String wardName = provinceJson.get("WardName").getAsString();
                    Ward ward = new Ward( wardCode,  districtID,  wardName);
                    wardList.add(ward);
                }
            }
        }

        return wardList;
    }

    public static void main(String[] args) {
        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTQwLjIzOC41NC4xMzYvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE2ODY0OTg1NjAsImV4cCI6MTY4NjQ5OTE2MCwibmJmIjoxNjg2NDk4NTYwLCJqdGkiOiJSZnBzSmtEWHhISHh5eEwzIiwic3ViIjoiMzhlNTljOGM4MzA1NGM5MTgzM2ViZTkzMTdhNzE0OTEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.lJyGm5BLpz02gj-e4mreSxBfAZU1ElwCSLiiBBW7iHY";
        int id = 2264;
        List<Ward> districts = Ward_API.convert(key, id);
        for (Ward district : districts) {
            System.out.println(district.getWardCode() + ": " + district.getDistrictID() + ": " + district.getWardName());
        }
    }
}
