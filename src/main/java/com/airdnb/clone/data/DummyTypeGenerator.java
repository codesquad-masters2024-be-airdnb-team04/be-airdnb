package com.airdnb.clone.data;

import com.airdnb.clone.domain.stay.entity.Stay.Type;
import java.util.Random;

public class DummyTypeGenerator {

    public static Type generate() {
        int first = new Random().nextInt(0, 6);

        return Type.values()[first];
    }
}
