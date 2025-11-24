package ch.bdt.spike.spring.cloud.currencyconverter.web;

import ch.bdt.spike.spring.cloud.currencyconverter.core.ConversionService;
import ch.bdt.spike.spring.cloud.currencyconverter.api.PriceWithCurrency;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class CurrencyConverterController {
    @Resource
    private ConversionService conversionService;

    @GetMapping(path = "/convertx")
    public PriceWithCurrency convertx(double a, String f, String t) {
        return convert(a, f, t);
    }

    @GetMapping(path = "/convert")
    public PriceWithCurrency convert(double amount, String fromCurrency, String toCurrency) {
        log.info("convert {} {} {}", amount, fromCurrency, toCurrency);
        double vConverted = conversionService.convert(amount, fromCurrency, toCurrency);
        return new PriceWithCurrency(vConverted, toCurrency);
    }

    // On expose les conversion rates
    @GetMapping(path = "/rates")
    public Map<ConversionService.ConversionKey, Double> getRates() {
        log.info("getRates");
        return conversionService.getConversionRates();
    }

    // On expose init
    @GetMapping(path = "/init")
    public void init() {
        log.info("init");
        conversionService.init();
    }
}
