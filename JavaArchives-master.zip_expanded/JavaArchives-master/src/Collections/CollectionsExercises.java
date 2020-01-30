package Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionsExercises {
    public static void main(String[] args) throws IOException {
        exercise1();
        exercise2();
        exercise3();
    }

    private static void exercise1() {
        // Create a set called mySet
        Set<String> mySet = new HashSet<>();
        // Ensure that this set contains an interesting selection of fruit
        String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine", fruit4 = "strawberry", fruit5 = "blackberry";
        mySet.add(fruit1);
        mySet.add(fruit2);
        mySet.add(fruit3);
        mySet.add(fruit2);
        mySet.add(fruit4);
        mySet.add(fruit5);
        // Display contents of mySet
        System.out.println("mySet now contains:");
        System.out.println(mySet);
        System.out.println("Cardinality of the set: " + mySet.size());
        System.out.println("Removing 'blackberry' and 'strawberry' from set...");
        mySet.remove("blackberry");
        mySet.remove("strawberry");
        System.out.println("The set now contains:");
        System.out.println(mySet);
        System.out.println("Removing remaining items...");
        mySet.removeAll(mySet);
        System.out.println("The set now contains:");
        System.out.println(mySet);
        System.out.println("The set is now empty. <--This statement is: " + mySet.isEmpty());
    }

    private static void separator() {
        System.out.println("--------------------------------------------------------------------------------");
    }

    private static void exercise2() {
        List<String> list = new ArrayList<String>();
        list.add("pear");
        list.add("banana");
        list.add("tangerine");
        list.add("strawberry");
        list.add("blackberry");

        separator();

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        separator();

        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        list.add(list.indexOf("strawberry"), "banana");
        System.out.println(list);
    }

    private static void exercise3() throws IOException {
        ArrayList<String> items = readFile();

        // Convert to list of dicts
        ArrayList<Map<String, Integer>> data = new ArrayList<>(
                items.stream().map(s -> stringToMap(s)).collect(Collectors.toList()));

        System.out.println(data);

        // Set<String> studentNamesSorted = new TreeSet<>();

        StudentCollection studentCollection = new StudentCollection();

        for (Map<String, Integer> datum : data) {
            String name = datum.keySet().iterator().next();
            int value = datum.values().iterator().next();

            studentCollection.addScore(name, value);
        }

        studentCollection.displayAlphabetically();
        studentCollection.displayByMerit();
        studentCollection.displayCollectionStatistics();

    }

    // private static Map<String, Integer> reducer(Map<String, Integer> x) {

    // }

    private static Map<String, Integer> stringToMap(String s) {
        String[] arr = s.split(" ");
        String studentName = arr[0];
        int score = Integer.parseInt(arr[1]);
        Map<String, Integer> map = Collections.singletonMap(studentName, score);
        return map;
    }

    private static ArrayList<String> readFile() throws IOException {
        // Read file to ArrayList
        String inputDir = "/Users/guts/Desktop/InterviewPrep/SmoothStack/Week1/input.txt";
        String filePath = inputDir + "input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> items = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            items.add(line);
            line = reader.readLine();
        }
        reader.close();
        return items;
    }
}

class StudentCollection {
    HashMap<String, Student> students;

    public StudentCollection() {
        students = new HashMap<>();
    }

    public ArrayList<Student> getList() {
        return new ArrayList<>(students.values());
    }

    public void addScore(String name, int value) {
        if (students.containsKey(name)) {
            students.get(name).addScore(value);
        } else {
            Student student = new Student(name);
            student.addScore(value);
            students.put(name, student);
        }
    }

    public int getStudentCount() {
        return students.size();
    }

    public double[] getAverageScores() {
        double scores[] = new double[getStudentCount()];
        ArrayList<Student> studentList = getList();

        for (int i = 0; i < getStudentCount(); i++) {
            scores[i] = studentList.get(i).getAverageScore();
        }
        return scores;
    }

    public double getOverallAverageScore() {
        ArrayList<Double> averageScores = new ArrayList<>();
        for (Student student : students.values()) {
            averageScores.add(student.getAverageScore());
        }
        return averageScores.stream().mapToDouble(a -> a).sum() / getStudentCount();
    }

    public double getPopulationStandardDeviation() {
        double[] averages = getAverageScores();
        return Utility.calculateSD(averages);
    }

    public void displayAlphabetically() {
        ArrayList<Student> studentList = getList();
        Collections.sort(studentList, Student.compareByName);

        System.out.println("\nAlpha order");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void displayByMerit() {
        ArrayList<Student> studentList = getList();
        Collections.sort(studentList, Student.compareByAverageScore);

        System.out.println("\nMerit order");
        int counter = 1;
        for (Student student : studentList) {
            System.out.println(counter + " " + student);
            counter++;
        }
    }

    public void displayCollectionStatistics() {
        System.out.println();
        System.out.println("Number of students: " + getStudentCount());
        System.out.println("Average student mark: " + String.format("%.1f", getOverallAverageScore()));
        System.out.println("Standard deviation: " + String.format("%.1f", getPopulationStandardDeviation()));
    }
}

class Student {
    String name;
    ArrayList<Integer> scores;

    public Student(String name) {
        this.name = name;
        scores = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public int getScoreCount() {
        return scores.size();
    }

    public double getAverageScore() {
        return scores.stream().mapToDouble(a -> a).sum() / getScoreCount();
    }

    public static Comparator<Student> compareByName = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            String StudentName1 = s1.getName().toUpperCase();
            String StudentName2 = s2.getName().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }
    };

    public static Comparator<Student> compareByAverageScore = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            double average1 = s1.getAverageScore();
            double average2 = s2.getAverageScore();
            return (int) (average2 - average1);
        }
    };

    @Override
    public String toString() {
        return name + " " + getScoreCount() + " " + String.format("%.1f", getAverageScore());
    }
}
