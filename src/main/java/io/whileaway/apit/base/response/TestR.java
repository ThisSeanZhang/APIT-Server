package io.whileaway.apit.base.response;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestR {

    public static void main(String [] args) {
        List<String> strs1 = new ArrayList<>();
        strs1.add("a");
        strs1.add("b");
        strs1.add("c");
        strs1.add("d");
        strs1.add("e");

        List<String> strs = Arrays.asList("a","b","c","d","e");
    }
}
