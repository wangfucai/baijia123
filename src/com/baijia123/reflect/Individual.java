package com.baijia123.reflect;

public class Individual implements Comparable<Individual> {

    private static long counter = 0;
    private final long id = counter++;
    private String name; // name is optional

    public Individual(String name) {
        this.name = name;
    }

    public String toString() {
        return getClass().getSimpleName() + (name == null ? "" : "" + name);
    }

    public Individual() {
    }

    public long id() {
        return id;
    }

    public boolean equals(Object o) {
        return o instanceof Individual && id == ((Individual) o).id;
    }

    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 37 * result + name.hashCode();
        }
        result = 37 * result + (int) id;
        return result;
    }

    @Override
    public int compareTo(Individual arg) {
        // Compare by class name first:
        String first = getClass().getSimpleName();
        String argFirst = arg.getClass().getSimpleName();
        int firstCompare = first.compareTo(argFirst);
        if (firstCompare != 0) {
            return firstCompare;
        }
        if (name != null && arg.name != null) {
            int secendCompare = name.compareTo(arg.name);
            if (secendCompare != 0) {
                return secendCompare;
            }
        }
        return (arg.id < id ? -1 : (arg.id == id ? 0 : 1));
    }

}
