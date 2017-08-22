package com.baijia123.concurrent.Spliterators;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterTest {

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combile);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final String SENTENCE = " Nel    mezzo del cammmin    di  nostra  vita" + " mi ritrovai in una selva oscura"
                + " che la   dritta via era smarrita";
        //Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + WordCounterTest.countWords(stream) + " words");//Found 19 words
        //Found 21 words，出现原因是由于原始String是任意拆分，有时把一个词拆成2个词，然后数了2次
        //System.out.println("Found " + WordCounterTest.countWords(stream.parallel()) + " words");
        
        
        
    }

}
