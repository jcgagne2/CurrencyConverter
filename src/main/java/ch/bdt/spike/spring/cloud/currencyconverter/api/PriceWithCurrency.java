package ch.bdt.spike.spring.cloud.currencyconverter.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PriceWithCurrency {
    private double price;
    private String currency;
}
