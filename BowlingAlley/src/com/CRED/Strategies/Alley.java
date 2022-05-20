package com.CRED.Strategies;

import java.util.HashMap;
import java.util.Map;

public class Alley {
    private Integer totalLanes;
    private Map<Integer, Lane> lanes;

    public Alley() {
        totalLanes = 0;
        lanes = new HashMap<>();
    }

    public Alley(Integer totalLanes) {
        this.totalLanes = totalLanes;
        lanes = new HashMap<>();
        for(int i = 0; i< totalLanes; i++) {
            lanes.put(i,new Lane());
        }
    }

    public Integer getTotalLanes() {
        return totalLanes;
    }

    public Map<Integer, Lane> getLanes() {
        return lanes;
    }
}
