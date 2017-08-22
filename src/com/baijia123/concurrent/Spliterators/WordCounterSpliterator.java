package com.baijia123.concurrent.Spliterators;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // TODO Auto-generated method stub
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        // TODO Auto-generated method stub
        int currentSize = string.length() - currentChar;
        if(currentSize < 10)
            return null;
        for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos ++) {
            if(Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        // TODO Auto-generated method stub
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        // TODO Auto-generated method stub
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

}
