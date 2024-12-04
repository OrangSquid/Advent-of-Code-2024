import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        String[] lines = Utils.readFile("inputs/day4.txt");
        char[][] wordSearch = new char[lines.length][lines[0].length()];
        int sum = 0;
        int sumPart2 = 0;

        for (int i = 0; i < lines.length; i++) {
            wordSearch[i] = lines[i].toCharArray();
        }

        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch.length; j++) {
                if (wordSearch[i][j] == 'X') {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (dx == 0 && dy == 0)
                                continue;

                            if (findXmasInDirection(wordSearch, i, j, dx, dy))
                                sum++;
                        }
                    }
                } else if (wordSearch[i][j] == 'A') {
                    if (findX_Mas(wordSearch, i, j))
                        sumPart2++;
                }
            }
        }

        System.out.println(sum);
        System.out.println(sumPart2);
    }

    public static boolean findXmasInDirection(char[][] matrix, int x, int y, int dx, int dy) {
        try {
            return matrix[x + dx][y + dy] == 'M' && matrix[x + 2 * dx][y + 2 * dy] == 'A'
                    && matrix[x + 3 * dx][y + 3 * dy] == 'S';
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean findX_Mas(char[][] matrix, int x, int y) {
        try {
            boolean diagonal1 = matrix[x + 1][y + 1] == 'S' && matrix[x - 1][y - 1] == 'M';
            boolean diagonal2 = matrix[x + 1][y + 1] == 'M' && matrix[x - 1][y - 1] == 'S';
            boolean diagonal3 = matrix[x + 1][y - 1] == 'S' && matrix[x - 1][y + 1] == 'M';
            boolean diagonal4 = matrix[x + 1][y - 1] == 'M' && matrix[x - 1][y + 1] == 'S';
            return (diagonal1 || diagonal2) && (diagonal3 || diagonal4);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
