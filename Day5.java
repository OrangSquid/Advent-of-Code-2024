import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day5 {

    public static void main(String[] args) throws IOException {
        String[] lines = Utils.readFile("inputs/day5.txt");
        Map<Integer, Set<Integer>> precedences = new HashMap<>();
        int sum = 0;
        int sumPart2 = 0;

        int i = 0;
        for (; !lines[i].equals(""); i++) {
            String[] parts = lines[i].split("\\|");
            int after = Integer.parseInt(parts[0]);
            int before = Integer.parseInt(parts[1]);

            Set<Integer> precedencesForBefore = precedences.get(before);
            if (precedencesForBefore == null) {
                precedencesForBefore = new HashSet<>();
                precedences.put(before, precedencesForBefore);
            }

            precedencesForBefore.add(after);
        }

        i++;

        for (; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            boolean addLine = true;

            for (int j = 0; j < parts.length && addLine; j++) {
                int page = Integer.parseInt(parts[j]);
                Set<Integer> mustBeBefore = precedences.get(page);
                for (int k = j + 1; k < parts.length; k++) {
                    if (mustBeBefore != null && mustBeBefore.contains(Integer.parseInt(parts[k]))) {
                        addLine = false;
                    }
                }
            }
            if (addLine) {
                sum += Integer.parseInt(parts[(parts.length - 1) / 2]);
            } else {
                sumPart2 += sort(parts, precedences);
            }
        }

        System.out.println(sum);
        System.out.println(sumPart2);
    }

    public static int sort(String[] parts, Map<Integer, Set<Integer>> precedences) {
        int[] corrected = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            corrected[i] = Integer.parseInt(parts[i]);
        }

        for (int i = 0; i < parts.length; i++) {
            int candidate = i;
            Set<Integer> candidatePrecedences = precedences.get(corrected[candidate]);
            for (int j = i + 1; j < parts.length; j++) {
                if (candidatePrecedences != null && candidatePrecedences.contains(corrected[j])) {
                    candidate = j;
                    candidatePrecedences = precedences.get(corrected[candidate]);
                }
            }
            int temp = corrected[i];
            corrected[i] = corrected[candidate];
            corrected[candidate] = temp;
        }

        return corrected[(corrected.length - 1) / 2];
    }
}
