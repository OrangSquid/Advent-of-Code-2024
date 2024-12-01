import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {
    public static void main(String[] args) throws Exception {
        String[] lines = Utils.readFile("inputs/day1.txt");
        int[] leftColumn = new int[lines.length];
        int[] rightColumn = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("   ");
            leftColumn[i] = Integer.parseInt(parts[0]);
            rightColumn[i] = Integer.parseInt(parts[1]);
        }

        Arrays.sort(leftColumn);
        Arrays.sort(rightColumn);

        int sum = 0;

        for (int i = 0; i < lines.length; i++) {
            sum += Math.abs(leftColumn[i] - rightColumn[i]);
        }

        System.out.println(sum);

        sum = 0;
        Map<Integer, AtomicInteger> occurrences = countOcurrences(rightColumn);

        for (int placeId : leftColumn) {
            AtomicInteger times = occurrences.get(placeId);
            if (times != null)
                sum += placeId * times.get();
        }

        System.out.println(sum);
    }

    public static Map<Integer, AtomicInteger> countOcurrences(int[] array) {
        Map<Integer, AtomicInteger> occurrences = new HashMap<>();

        for (int placeId : array) {
            AtomicInteger times = occurrences.get(placeId);
            if (times == null) {
                times = new AtomicInteger(0);
                occurrences.put(placeId, times);
            }
            times.incrementAndGet();
        }
        return occurrences;
    }
}