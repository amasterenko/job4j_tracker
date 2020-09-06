package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class SearchAtt {

    private static List<Attachment> filter(List<Attachment> list,
                                           Predicate<Attachment> checkAttachment) {
        List<Attachment> resultList = new ArrayList<>();
        for (Attachment att : list) {
            if (checkAttachment.test(att)) {
                resultList.add(att);
            }
        }
        return resultList;
    }

    public static List<Attachment> filterSize(List<Attachment> list) {
        Predicate<Attachment> checkForSize = new Predicate<>() {
            @Override
            public boolean test(Attachment att) {
                return att.getSize() > 100;
            }
        };
        return filter(list, checkForSize);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        Predicate<Attachment> checkForName = new Predicate<>() {
            @Override
            public boolean test(Attachment att) {
                return att.getName().contains("bug");
            }
        };
        return filter(list, checkForName);
    }

    public static void main(String[] args) {
        List<Attachment> list = new ArrayList<>();
        list.add(new Attachment("bug1", 10));
        list.add(new Attachment("bug2", 20));
        list.add(new Attachment("Attachment1", 120));
        list.add(new Attachment("bug", 150));
        System.out.println(filterName(list));
        System.out.println(filterSize(list));
    }
}
