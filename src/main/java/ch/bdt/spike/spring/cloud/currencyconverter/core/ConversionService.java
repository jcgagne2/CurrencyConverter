package ch.bdt.spike.spring.cloud.currencyconverter.core;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@Component
public class ConversionService {
    @Getter
    private Map<ConversionKey, Double> conversionRates;

    public double convert(double amount, String fromCurrency, String toCurrency) {
        ConversionKey key = new ConversionKey(fromCurrency, toCurrency);
        Double rate = conversionRates.get(key);
        if (rate == null) {
            throw new IllegalArgumentException("Conversion rate not found for " + key);
        }
        double vConverted = amount * rate;
        // On arrondi pour conserver 2 décimales
        vConverted = Math.round(vConverted * 100.0) / 100.0;
        return vConverted;

    }

    @PostConstruct
    public void init() {
        String[] currencies = {"USD", "EUR", "GBP", "CHF", "JPY", "CAD"};
        // On initialise les taux de conversion avec des valeurs random comprises entre 0.5 et 200
        Random random = new Random();
        Map<ConversionKey, Double> vRates = new TreeMap<>();
        for (int i = 0; i < currencies.length; i++) {
            for (int j = i + 1; j < currencies.length; j++) {
                double rate = 0.1 + random.nextDouble() * 2;
                vRates.put(new ConversionKey(currencies[i], currencies[j]), rate);
                // On inverse pour la conversion dans l'autre sens
                vRates.put(new ConversionKey(currencies[j], currencies[i]), 1 / rate);
            }
        }
        conversionRates = vRates;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ConversionKey implements Comparable<ConversionKey> {
        private String fromCurrency;
        private String toCurrency;

        @Override
        public int compareTo(ConversionKey aOther) {
            String vMyKey = fromCurrency + "§" + toCurrency;
            String vOtherKey = aOther.fromCurrency + "§" + aOther.toCurrency;
            return vMyKey.compareTo(vOtherKey);
        }
    }
}
