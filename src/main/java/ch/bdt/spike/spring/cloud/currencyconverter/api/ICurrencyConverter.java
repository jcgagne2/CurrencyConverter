package ch.bdt.spike.spring.cloud.currencyconverter.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface ICurrencyConverter {
    @GetMapping(path = "/convert")
    PriceWithCurrency convert(double amount, String fromCurrency, String toCurrency);

}
