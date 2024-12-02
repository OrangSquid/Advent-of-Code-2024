import java.io.IOException;

public class Day2 {
    public static void main(String[] args) throws IOException {
        String[] lines = Utils.readFile("inputs/day2.txt");
        int sumPart1 = 0;
        int sumPart2 = 0;

        for (String line : lines) {
            String[] parts = line.split(" ");
            int maxDifference = Integer.MIN_VALUE;
            int minDifference = Integer.MAX_VALUE;
            int before = Integer.parseInt(parts[0]);
            boolean noIncrease = false;

            for (int i = 1; i < parts.length; i++) {
                int after = Integer.parseInt(parts[i]);
                int difference = after - before;

                maxDifference = Math.max(difference, maxDifference);
                minDifference = Math.min(difference, minDifference);
                noIncrease = noIncrease | difference == 0;
                before = after;
            }

            if (((maxDifference < 0 && minDifference < 0 && minDifference >= -3 && maxDifference <= -1) ||
                    (maxDifference > 0 && minDifference > 0 && maxDifference <= 3 && minDifference >= 1))
                    && !noIncrease) {

                sumPart1++;
            } else {
                for (int toSkip = 0; toSkip < parts.length; toSkip++) {
                    int firstIdx = toSkip == 0 ? 1 : 0;
                    before = Integer.parseInt(parts[firstIdx]);
                    maxDifference = Integer.MIN_VALUE;
                    minDifference = Integer.MAX_VALUE;
                    noIncrease = false;
                    for (int i = firstIdx + 1; i < parts.length; i++) {
                        if (i == toSkip)
                            continue;
                        int after = Integer.parseInt(parts[i]);
                        int difference = after - before;

                        maxDifference = Math.max(difference, maxDifference);
                        minDifference = Math.min(difference, minDifference);
                        noIncrease = noIncrease | difference == 0;
                        before = after;
                    }

                    if (((maxDifference < 0 && minDifference < 0 && minDifference >= -3 && maxDifference <= -1) ||
                            (maxDifference > 0 && minDifference > 0 && maxDifference <= 3 && minDifference >= 1))
                            && !noIncrease) {

                        sumPart2++;
                        break;
                    }
                }
            }
        }

        System.out.println(sumPart1);
        System.out.println(sumPart1 + sumPart2);
    }
}
