import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalTest {
    public static void main(String[] args) {
        String [] array = {"Ivan", "Henry", "Peter", "James", "Oliver", "Benjamin", "Liam", "William"};
        String [] arrayString = {"1, 2, 0", "4, 5"};

        task1(Arrays.asList(array));
        task2(Arrays.asList(array));
        task3(arrayString);
        task4();
        task5();
    }

    static void task1(List <String> names){
        System.out.println("\nTASK 1.\nНачальный список:\n" + names);
        System.out.println("\nТолько нечетные:");
        String oddArray = names.stream().filter(name -> names.indexOf(name)%2 == 0)
                .map(name -> names.indexOf(name) + 1 + ". " + name)
                .collect(Collectors.joining(", "));
        System.out.println(oddArray);
    }

    private static void task2(List <String> names) {
        System.out.println("\nTASK 2.\nНачальный список:\n" + names);
        System.out.println("\nСписок в UpperCase и отсортирован по убыванию:");
        List<String> result = names.stream().map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(result);
    }

    private static void task3(String[] arrayString) {
        System.out.println("\nTASK 3.\nПреобразованный и отсортированный массив:");
        String result = Arrays.stream(arrayString).flatMap(n->Arrays.stream(n.split(", ")))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }

    static void task4(){
        Stream<Long> gen = generator(25214903917L, 11L, (long) Math.pow(2, 48), 44L, 13);
        System.out.println("\nTASK 4.\nСгенерированный стрим из 13-ти чисел:");
        System.out.println(gen.collect(Collectors.toList())+"\n");
    }

    private static Stream <Long> generator(long a, long c, long m, long seed, int limit){
        return Stream.iterate(seed, х -> (a * х + c) % m).limit(limit);
    }

    static void task5(){
        System.out.println("\nTASK 5.\nДва друга составляли слова из букв слова \"КОНГРУЭНТНЫЙ\"");
        String [] first = {"РЫНОК", "ОКРУГ", "УТРО", "ГНОЙ", "КРУГ", "КОРТ"};
        String [] second = {"КРУТО", "ГРУНТ", "УРОН", "РУНО"};
        System.out.println("\nПервый составил:");
        System.out.println(Arrays.toString(first));
        System.out.println("Второй составил:");
        System.out.println(Arrays.toString(second));
        System.out.println("\nДрузья озвучили слова по очереди:");
        Stream <String> quieWordsStream = zip(Arrays.stream(first), Arrays.stream(second));
        System.out.println(quieWordsStream.collect(Collectors.joining(", ")));
        if (first.length>second.length) {
            System.out.println("\nПервый победил)))");
        } else {
            System.out.println("\nВторой победил)))");
        }
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        Iterator <T> firstIterator = first.iterator();
        Iterator <T> secondIterator = second.iterator();

        Stream<T> quie = Stream.empty();

        while (firstIterator.hasNext() && secondIterator.hasNext()){
            quie = Stream.concat(quie, Stream.of(firstIterator.next(), secondIterator.next()));
        }
        return quie;
    }
}
