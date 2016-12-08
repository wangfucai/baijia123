package com.baijia123.guava.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;

public class GuavaUtils {
    
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        
        List<String> test1 = Lists.newArrayList();
        
        List<String> test2 = Lists.newArrayList("alpha", "beta", "gama");
        
        List<Type> exact100 = Lists.newArrayListWithExpectedSize(100);
        
        Iterable<Integer> concatend = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6) );
        
        
        List countUp = Ints.asList(1, 2, 3, 4, 5);
        System.out.println(countUp);
        List countDown = Lists.reverse(countUp);
        List<List> parts = Lists.partition(countUp, 2);
        System.out.println(parts);
        
        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruites = ImmutableSet.of("apple", "orange", "banana");
        
        // {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},{"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
        Set<List<String>> product = Sets.cartesianProduct(animals, fruites);
        
        // {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
        Set<Set<String>> animalSets = Sets.powerSet(animals);
        
        List<String> strings = Lists.newArrayList();
        
        ImmutableMap<Integer, String> stringByIndex = Maps.uniqueIndex(strings,  new Function<String, Integer>(){

            @Override
            public Integer apply(String input) {
                // TODO Auto-generated method stub
                return null;
            }
            
        });
        
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("d", 2, "b", 2, "c", 4);
        
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        diff.entriesInCommon();
        diff.entriesOnlyOnLeft();
        diff.entriesOnlyOnRight();
        for(Map.Entry<String, Integer> entry : diff.entriesOnlyOnRight().entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        
        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>(){

            @Override
            public Integer apply(String input) {
                // TODO Auto-generated method stub
                return input.length();
            }
            
        };
        
        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits,  lengthFunction); 
        for(Map.Entry<Integer, java.util.Collection<String>> entry : digitsByLength.asMap().entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));
        
        //注意我们选择的实现，因为选了TreeMultimap，得到的反转结果是有序的
        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(inverse);
        
        //Multimaps.invertFrom结合使用，可以把多对一的Map反转为一对多的Multimap
        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 3);
        SetMultimap<String, Integer> multimap1 = Multimaps.forMap(map);
        // multimap：["a" => {1}, "b" => {1}, "c" => {2}]
        System.out.println(multimap1);
        HashMultimap<Integer, String> inverse1 = Multimaps.invertFrom(multimap1, HashMultimap.create());
        System.out.println(inverse1);
        
        
    }
}
