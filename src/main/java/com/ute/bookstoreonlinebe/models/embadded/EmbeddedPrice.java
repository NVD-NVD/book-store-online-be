package com.ute.bookstoreonlinebe.models.embadded;

import com.ute.bookstoreonlinebe.utils.EnumCurrency;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmbeddedPrice {
    private float price;

    private EnumCurrency currency;
}
