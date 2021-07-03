package aplencler.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Component
public class ExchangeRates {

    public HashMap<String, Double> findExchangeRates(String currencyCode) {

        HashMap<String, ArrayList<LinkedHashMap<String, Double>>> exchangeRatesMap;
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<String, Double> exchangeRatesList = null;

        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/C/" + currencyCode + "/?format=json");
            exchangeRatesMap = mapper.readValue(url, HashMap.class);
            exchangeRatesList = exchangeRatesMap.get("rates").get(0);

        } catch (IOException e) {
            System.err.println("Exchange rate retrieve fail");

        }
        return exchangeRatesList;
    }
}
