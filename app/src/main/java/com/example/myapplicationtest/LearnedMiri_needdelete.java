package com.example.myapplicationtest;

import com.example.myapplicationtest.LearnedData.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearnedMiri_needdelete {


    public LearnedMiri_needdelete() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("rap");
        list.add("hip hop");
        list.add("rock");

        list2.add("rock");
        list2.add("hip hop");
        map.put("1",list);
        map.put("2",list2);
       // map.put("1","kk");
        CreatePairFromMap(map);
    }

    public <K, V> void CreatePairFromMap(Map<K,List<V>> map){
        List<Pair> pairs=new ArrayList<>();

        for ( Map.Entry<K, List<V>> entry : map.entrySet()) {
            K key = entry.getKey();
            List<V> values = entry.getValue();
            if (values.size()>1){
                for(int i=0;i<values.size();i++){
                    if(i+1<values.size()) { //the first to all
                        Pair pair = new Pair(values.get(i), values.get(i + 1));
                        pairs.add(pair);
                    }else/* if()*/{

                    }
                }
            }


            //if (value.s)
            // do something with key and/or tab
        }


        for (K key : map.keySet()) {
            for(V val: map.get(key)){

            }

        }
    }
}
