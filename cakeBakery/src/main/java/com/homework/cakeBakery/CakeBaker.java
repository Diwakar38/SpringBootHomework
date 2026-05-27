package com.homework.cakeBakery;

import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    final Frosting frosting;
    final Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.syrup = syrup;
        this.frosting = frosting;
    }

    public String bakeCake() {
        return "Baking a cake with " + frosting.getFrostingType() +
                " and " + syrup.getSyrupType() + ".";
    }
}
