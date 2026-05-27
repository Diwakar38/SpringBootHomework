package com.homework.cakeBakery.impl.frosting;

import com.homework.cakeBakery.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "frosting", havingValue = "chocolate")
public class ChocolateFrosting implements Frosting {
    @Override
    public String getFrostingType() {
        return "Chocolate Frosting";
    }
}
