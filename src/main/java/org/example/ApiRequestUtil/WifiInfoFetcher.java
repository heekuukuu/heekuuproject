import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class WifiInfoFetcher {
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/YOUR_API_KEY/json/TbPublicWifiInfo/1/20/";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                System.out.println(jsonData);
            } else {
                System.err.println("API 요청 실패: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}