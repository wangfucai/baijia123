package com.baijia123.guava.basicutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class ObjectTest {

    public enum EnumTest {
        StringEnum, IntEnum, DateFormatEnum;
    }

    public class Foo {
        private String aString;
        private int anInt;
        private EnumTest anEnum;

        public int compareTo(Foo that) {
            return ComparisonChain.start()
                    .compare(anInt, that.anInt)
                    .compare(aString, that.aString)
                    .compare(anEnum, that.anEnum)
                    .result();
        }
    }

    public class Person implements Comparable<Person> {
        private String firstName;
        private String lastName;
        private int zipCode;

        @Override
        public int compareTo(Person o) {
            // TODO Auto-generated method stub
            int cmp = lastName.compareTo(o.lastName);
            if (cmp != 0)
                return cmp;
            cmp = firstName.compareTo(o.firstName);
            if (cmp != 0)
                return cmp;
            return Integer.compare(zipCode, o.zipCode);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTest.class);

    public static void main(String[] args) {
        String a = "test";
        Objects.equal("a", "a");
        LOGGER.debug("result = " + Objects.equal(null, "a"));
    }
}
