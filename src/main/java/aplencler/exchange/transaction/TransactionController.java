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

    private final ExchangeRates exchangeRates;
    private final TransactionService service;
    private double amountValidated;
    private String currencyCodeValidated;

    TransactionController(ExchangeRates exchangeRates, TransactionService service) {
        this.exchangeRates = exchangeRates;
        this.service = service;
    }

    @GetMapping(value = "ask", params = {"amount", "code"})
    ResponseEntity<String> responseToAsk(@RequestParam("amount") String amount, @RequestParam("code") String currencyCode) {
        ResponseEntity<String> responseEntity;
        currencyCode = currencyCode.toUpperCase();
        try {
            amountValidated = Double.parseDouble(amount.replace(",", "."));

        } catch (NumberFormatException e) {
            amountValidated = 0.00;
        }

        if (currencyCode.equals("EUR") | currencyCode.equals("USD") | currencyCode.equals("GBP") && amountValidated > 0.00) {

            currencyCodeValidated = currencyCode;
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("Kupujesz " + amountValidated + currencyCodeValidated + ", potrzebujesz " + service.countValue(amountValidated, "ask", exchangeRates.findExchangeRates(currencyCodeValidated)) + "PLN");
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błędne dane wejściowe");
        }
        return responseEntity;
    }


    @GetMapping(value = "bid", params = {"amount", "code"})
    ResponseEntity<String> responseToBid(@RequestParam("amount") String amount, @RequestParam("code") String currencyCode) {
        ResponseEntity<String> responseEntity;
        currencyCode = currencyCode.toUpperCase();
        try {
            amountValidated = Double.parseDouble(amount.replace(",", "."));

        } catch (NumberFormatException e) {
            amountValidated = 0.00;
        }

        if (currencyCode.equals("EUR") | currencyCode.equals("USD") | currencyCode.equals("GBP") && amountValidated > 0.00) {

            currencyCodeValidated = currencyCode;
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("Sprzedajesz " + amountValidated + currencyCodeValidated + ", otrzymujesz " + service.countValue(amountValidated, "bid", exchangeRates.findExchangeRates(currencyCodeValidated)) + "PLN");
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Błędne dane wejściowe");
        }
        return responseEntity;
    }

    @GetMapping(value = "ask")
    ResponseEntity<String> responseToAsk() {
        return ResponseEntity.status(HttpStatus.OK).body("Podaj ilość i rodzaj waluty");
    }

    @GetMapping(value = "bid")
    ResponseEntity<String> responseToBid() {
        return ResponseEntity.status(HttpStatus.OK).body("Podaj ilość i rodzaj waluty");
    }
}