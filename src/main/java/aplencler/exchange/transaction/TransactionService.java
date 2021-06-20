package aplencler.exchange.transaction;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class TransactionService {

    /**
     * @param amount           - ilość waluty, jaką chcemy kupić lub sprzedać,
     * @param bidOrAsk         - rodzaj transakcji (kupno/sprzedaż),
     * @param exchangeRatesMap - mapa zawierająca aktualne kursy kupna/sprzedaży waluty
     * @return ilość PLN potrzebnych do zakupu danej waluty lub ilość PLN, jaką otrzymamy za posiadaną walutę z uwzględnieniem 2% prowizji
     */
    public double countValue(double amount, String bidOrAsk, LinkedHashMap<String, Double> exchangeRatesMap) {

        double commissionAmount = exchangeRatesMap.get(bidOrAsk) * 0.02;
        double exchangeRate;

        if (bidOrAsk.equals("bid")) {
            exchangeRate = exchangeRatesMap.get("bid") - commissionAmount;

        } else if (bidOrAsk.equals("ask")) {

            exchangeRate = exchangeRatesMap.get("ask") + commissionAmount;
        } else exchangeRate = 0.00;

        return Math.round(amount * exchangeRate * 100.00) / 100.00;
    }

}