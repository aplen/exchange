package aplencler.exchange.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {


    @Autowired
    TransactionService transactionService;
    LinkedHashMap<String, Double> mockExchangeRatesMap;

    @Before
    public void initialize() {
        mockExchangeRatesMap = new LinkedHashMap<>();
        mockExchangeRatesMap.put("bid", 10.00);
        mockExchangeRatesMap.put("ask", 11.00);

    }

    @Test
    public void shouldReturnCorrectValueWhenAsk() throws Exception {
        assertThat(transactionService.countValue(10.0, "ask", mockExchangeRatesMap)).isEqualTo(112.20);
    }
    @Test
    public void shouldReturnCorrectValueWhenBid() throws Exception {
        assertThat(transactionService.countValue(10.0, "bid", mockExchangeRatesMap)).isEqualTo(98.00);
    }
    @Test
    public void shouldReturnZeroWhenAskZeroAmount() throws Exception {
        assertThat(transactionService.countValue(0.0, "ask", mockExchangeRatesMap)).isEqualTo(0.00);
    }
    @Test
    public void shouldReturnZeroWhenBidZeroAmount() throws Exception {
        assertThat(transactionService.countValue(0.0, "bid", mockExchangeRatesMap)).isEqualTo(0.00);
    }

}