package aplencler.exchange.transaction;

import aplencler.exchange.ExchangeRates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction")
class TransactionController {

    private ExchangeRates exchangeRates;
    private TransactionService service;

    TransactionController(ExchangeRates exchangeRates, TransactionService service) {
        this.exchangeRates = exchangeRates;
        this.service = service;

    }

    @GetMapping(value = "ask", params = {"amount", "code"})
    ResponseEntity<Double> responseToAsk(@RequestParam("amount") String amount, @RequestParam("code") String currencyCode) {
        double amountValidated = validateAmount(amount);
        String currencyCodeValidated = validateCurrencyCode(currencyCode);
        String bidOrAsk = "ask";

        return responseEntity(currencyCodeValidated, bidOrAsk, amountValidated);

    }


    @GetMapping(value = "bid", params = {"amount", "code"})
    ResponseEntity<Double> responseToBid(@RequestParam("amount") String amount, @RequestParam("code") String currencyCode) {
        double amountValidated = validateAmount(amount);
        String currencyCodeValidated = validateCurrencyCode(currencyCode);
        String bidOrAsk = "bid";

        return responseEntity(currencyCodeValidated, bidOrAsk, amountValidated);
    }

    double validateAmount(String amount) {
        double amountValidated;
        try {
            amountValidated = Double.parseDouble(amount.replace(",", "."));

        } catch (NumberFormatException e) {
            amountValidated = 0.00;
        }

        return amountValidated;
    }

    String validateCurrencyCode(String currencyCode) {

        String currencyCodeValidated = currencyCode.toUpperCase();


        return currencyCodeValidated;
    }

    ResponseEntity<Double> responseEntity(String currencyCodeValidated, String bidOrAsk, double amountValidated) {
        ResponseEntity<Double> responseEntity;
        if (currencyCodeValidated.equals("EUR") || currencyCodeValidated.equals("USD") || currencyCodeValidated.equals("GBP") && amountValidated > 0.00) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(service.countValue(amountValidated, bidOrAsk, exchangeRates.findExchangeRates(currencyCodeValidated)));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0.00);
        }
        return responseEntity;
    }
}
