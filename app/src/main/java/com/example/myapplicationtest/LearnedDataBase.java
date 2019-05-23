//package com.example.myapplicationtest;
//
//import com.example.myapplicationtest.LearnedData.Pair;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class LearnedDataBase {
//
//
//    public LearnedDataBase(Map<String,List<String>> map) {
//       /* Map<String, List<String>> map = new HashMap<>();
//        List<String> list = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        list.add("rap");
//        list.add("hip hop");
//        list.add("rock");
//
//        list2.add("rock");
//        list2.add("hip hop");
//        map.put("1",list);
//        map.put("2",list2);*/
//       // map.put("1","kk");
//        CreatePairFromMap(map);
//    }
//
//    public List<List<String>> CreatePairFromMap(Map<String,List<String>> map){
//        List<List<String>> pairs=new ArrayList<>();
//
//        for ( Map.Entry<String, List<String>> entry : map.entrySet()) {
//            String key = entry.getKey();
//            List<String> values = entry.getValue();
//            if (values.size()>1){
//
//                for (int i = 0; i < values.size(); i++)
//                    for (int j = i+1; j < values.size(); j++)
//
//                        // finding the index with different
//                        // value and different index.
//                        if (values.get(i) != values.get(j)){
//                           // Pair pair = new Pair(values.get(i), values.get(j));
//                            pairs.add(pair);
//                        }
//            }
//        }
//
//       // List<Pair> noDuplicataPairs = removeDuplicates(pairs);
//        //noDuplicataPairs=noDuplicataPairs;
//
//    }
//
//    //TODO need to check 2 genreSinger and not one element
//    public <T> ArrayList<T> removeDuplicates(List<T> list)
//    {
//
//        // Create a new ArrayList
//        ArrayList<T> newList = new ArrayList<T>();
//
//        // Traverse through the first list
//        for (T element : list) {
//
//            // If this element is not present in newList
//            // then add it
//            if (!newList.contains(element)) {
//
//                newList.add(element);
//            }
//        }
//
//        // return the new list
//        return newList;
//    }
//}
