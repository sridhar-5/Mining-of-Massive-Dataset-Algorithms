package com.amrita.MomdsProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Apriori {
    private HashMap<Integer, ArrayList<Integer>> itemSets;
    private int minimumSupportCount;
    public Apriori(HashMap<Integer, ArrayList<Integer>> itemSets){
        this.itemSets = itemSets;
    }

}
