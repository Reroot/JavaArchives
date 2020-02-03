package Lamdas;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambdas {
    public static void main(String[] args) {
        question1();

        // question 2
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(question2(l));

        // question 3
        List<String> ls = Arrays.asList("a", "boy", "arc", "corvette", "ant", "apple", "asterisk", "effective", "tea",
                "and", "etc", "adonis");
        System.out.println(question3(ls));

        datetimeQuestion1();
        datetimeQuestion2();
        //datetimeQuestion4();
        datetimeQuestion5(2005);
    }

    private static void question1() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("mice");
        items.add("rabid");
        items.add("upbeat");
        items.add("aware");
        items.add("partner");
        items.add("earth");
        items.add("equal");
        items.add("unaccountable");
        items.add("past");
        items.add("detect");
        items.add("broad");

        System.out.println("Shortest to longest");
        items.sort((String item1, String item2) -> item1.length() - item2.length());
        System.out.println(items);

        System.out.println("Longest to shortest");
        items.sort((String item1, String item2) -> item2.length() - item1.length());
        System.out.println(items);

        System.out.println("Alphabetically by first letter only");
        items.sort((String item1, String item2) -> item1.substring(0, 1).compareTo(item2.substring(0, 1)));
        System.out.println(items);

        System.out.println("Strings beginning with 'e' first, everything else next");
        items.sort((String item1, String item2) -> {
            if (item1.substring(0, 1).equalsIgnoreCase("e") && !item2.substring(0, 1).equalsIgnoreCase("e")) {
                return -1;
            } else if (!item1.substring(0, 1).equalsIgnoreCase("e") && item2.substring(0, 1).equalsIgnoreCase("e")) {
                return 1;
            } else {
                return 0;
            }
        });
//        System.out.println(items);
//        System.out.println("Shortest to longest");
//        items.sort(SortShortestToLongest());
//        System.out.println(items);
//
//        System.out.println("Longest to shortest");
//        items.sort(()=> {new SortLongestToShortest()};);
//        System.out.println(items);
//
//        System.out.println("Alphabetically by first letter only");
//        items.sort(new SortAlphabeticallyByFirstLetter());
//        System.out.println(items);
//
//        System.out.println("Strings beginning with 'e' first, everything else next");
//        items.sort(new SortBeginsWithEFirst());
//        System.out.println(items);
    }

    public static String question2(List<Integer> list) {
        return String.join(",", list.stream().map((num) -> {
            String item = "";
            if (num % 2 == 0) {
                item += "e";
            } else {
                item += "o";
            }
            item += num;
            return item;
        }).collect(Collectors.toList()));
    }

    public static List<String> question3(List<String> list) {
        return list.stream().filter(s -> s.substring(0, 1).equals("a")).filter(s -> s.length() == 3)
                .collect(Collectors.toList());
    }

    public static void datetimeQuestion1() {
        LocalDateTime myBirthday = LocalDateTime.of(1982, Month.JULY, 18, 4, 24, 30, 457);
        System.out.println("I was born " + myBirthday);

    }

    public static void datetimeQuestion2() {
        LocalDate date = LocalDate.now();
        LocalDate previousThursday = date.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY));
        System.out.println("\nPrevious Thursday: " + previousThursday);
    }

    public static void datetimeQuestion4a() {
        Instant instant = Instant.now();
        ZonedDateTime westCoastTime = instant.atZone(ZoneId.of("America/Los_Angelas"));
        System.out.println("ZonedDateTime : " + westCoastTime);
    }

    public static void datetimeQuestion4b() {
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime westCoastTime = dateTime.atZone(ZoneId.of("America/Los_Angelas"));
        Instant instant = westCoastTime.toInstant();
        System.out.println("Instant : " + instant);
    }

    public static void datetimeQuestion5(int year) {
        for (Month month : Month.values()) {
            YearMonth yearMonth = YearMonth.of(year, month);
            int daysInMonth = yearMonth.lengthOfMonth();
            System.out.println(daysInMonth + " days hath " + month);
        }
    }

    public static void datetimeQuestion6(int year) {

    }

    class SortShortestToLongest implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    class SortLongestToShortest implements Comparator<String> {
        public int compare(String a, String b) {
            return b.length() - a.length();
        }
    }

    class SortAlphabeticallyByFirstLetter implements Comparator<String> {
        public int compare(String a, String b) {
            return a.substring(0, 1).compareTo(b.substring(0, 1));
        }
    }

class SortBeginsWithEFirst implements Comparator<String> {
    public int compare(String a, String b) {
        if (a.substring(0, 1).equalsIgnoreCase("e") && !b.substring(0, 1).equalsIgnoreCase("e")) {
            return -1;
        } else if (!a.substring(0, 1).equalsIgnoreCase("e") && b.substring(0, 1).equalsIgnoreCase("e")) {
            return 1;
        } else {
            return 0;
        }
    }
}
}