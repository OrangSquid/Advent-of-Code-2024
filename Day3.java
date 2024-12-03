import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String[] lines = Utils.readFile("inputs/day3.txt");
        int sum = 0;
        int sumPart2 = 0;
        boolean part2Summing = true;

        for (String line : lines) {
            Pattern regex = Pattern.compile("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))");
            Matcher matcher = regex.matcher(line);

            while (matcher.find()) {
                String mulInstructior = matcher.group(1);
                boolean doInstruction = matcher.group(2) != null;
                boolean dontInstruction = matcher.group(3) != null;
                if (mulInstructior != null) {
                    String numbers = mulInstructior.substring(4, mulInstructior.length() - 1);
                    String[] numberLeftRight = numbers.split(",");

                    int left = Integer.parseInt(numberLeftRight[0]);
                    int right = Integer.parseInt(numberLeftRight[1]);
                    if (part2Summing)
                        sumPart2 += left * right;
                    sum += left * right;
                }

                if (part2Summing && dontInstruction)
                    part2Summing = false;
                else if (!part2Summing && doInstruction)
                    part2Summing = true;
            }
        }

        System.out.println(sum);
        System.out.println(sumPart2);
    }
}
