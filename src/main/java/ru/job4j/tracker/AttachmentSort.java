package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AttachmentSort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 1", 100),
                new Attachment("image 2", 34),
                new Attachment("image 3", 13)
        );
        Comparator comparator =  new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment left = (Attachment) o1;
                Attachment right = (Attachment) o2;
                return left.getSize() - right.getSize();
            }
        };
        attachments.sort(comparator);
        System.out.println(attachments);
        // Здесь создайте компаратор на основании анонимного класса.
        Comparator<Attachment> compareNamesAsc  =  new Comparator<Attachment>() {
            @Override
            public int compare(Attachment a1, Attachment a2) {
                return a1.getName().compareTo(a2.getName());
            }
        };
        attachments.sort(compareNamesAsc);
        System.out.println(attachments);
    }
}