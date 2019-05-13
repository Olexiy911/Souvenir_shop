package souvenir_shop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class CurrencyRepository {
    public double findConversionRate(String from, String to, Date date) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String d = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String fooResourceUrl =
                "http://data.fixer.io/api/" + d + "?access_key=66a01dee1bc6fdb3634a6f3b96d4b02a&base="
                        + from + "&symbols=" + to;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("rates").path(to);
        return name.asDouble();
    }
}
