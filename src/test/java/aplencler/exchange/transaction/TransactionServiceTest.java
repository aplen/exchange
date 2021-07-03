package aplencler.exchange.transaction;

import org.junit.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;


public class TransactionServiceTest {


    TransactionService transactionService = new TransactionService();
    LinkedHashMap<String, Double> mockExchangeRatesMap = new LinkedHashMap() {
        {
            put("bid", 10.00);
            put("ask", 11.00);
        }
    };




    @Test
    public void shouldReturnCorrectValueWhenAsk()  {
        assertThat(transactionService.countValue(10.0, "ask", mockExchangeRatesMap)).isEqualTo(112.20);
    }

    @Test
    public void shouldReturnCorrectValueWhenBid()  {
        assertThat(transactionService.countValue(10.0, "bid", mockExchangeRatesMap)).isEqualTo(98.00);
    }

    @Test
    public void shouldReturnZeroWhenAskZeroAmount() {
        assertThat(transactionService.countValue(0.0, "ask", mockExchangeRatesMap)).isEqualTo(0.00);
    }

    @Test
    public void shouldReturnZeroWhenBidZeroAmount() {
        assertThat(transactionService.countValue(0.0, "bid", mockExchangeRatesMap)).isEqualTo(0.00);
    }

}