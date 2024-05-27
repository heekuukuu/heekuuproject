import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WifiNearbyFinder {

    private static final String WIFI_API_URL = "http://openapi.seoul.go.kr:8088/747a584b6f6a6130383057694a6d6f/json/TbPublicWifiInfo/1/100/";

    public static void main(String[] args) {
        // 입력된 위치 정보 (예: 위도(latitude)와 경도(longitude))
        double latitude = 37.5665;
        double longitude = 126.9780;

        // 가까운 와이파이 정보 조회
        JsonObject wifiData = fetchWifiData();
        if (wifiData != null) {
            displayNearbyWifi(wifiData, latitude, longitude);
        } else {
            System.out.println("Failed to fetch WiFi data.");
        }
    }

    private static JsonObject fetchWifiData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(WIFI_API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonData = response.body().string();
            return JsonParser.parseString(jsonData).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void displayNearbyWifi(JsonObject wifiData, double latitude, double longitude) {
        if (wifiData == null) {
            System.out.println("WiFi data is null.");
            return;
        }

        JsonArray wifiList = wifiData.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
        for (int i = 0; i < wifiList.size(); i++) {
            JsonObject wifi = wifiList.get(i).getAsJsonObject();
            double wifiLat = wifi.get("LAT").getAsDouble();
            JsonElement lonElement = wifi.get("LON");
            double wifiLon = 0;
            if (lonElement != null && !lonElement.isJsonNull()) {
                wifiLon = lonElement.getAsDouble();
                System.out.println("Longitude: " + wifiLon);
                // 이후에 wifiLon 변수 사용
            } else {
                // "LON" 키에 대한 값이 없는 경우 처리
            }


            double distance = calculateDistance(latitude, longitude, wifiLat, wifiLon);

            // 가까운 와이파이 정보 출력 (예시로 거리 1km 이내인 것만 출력)
            if (distance <= 1.0) {
                System.out.println("Name: " + wifi.get("NAME").getAsString());
                System.out.println("Distance: " + distance + " km");
                // 추가 정보 출력 (필요에 따라)
            }
        }
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 위도와 경도를 기반으로 거리를 계산하는 코드 (예시로 단순 계산)
        // 실제로는 더 정확한 거리 계산 알고리즘이 필요할 수 있습니다.
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // 킬로미터로 변환
        return dist;
    }
}
