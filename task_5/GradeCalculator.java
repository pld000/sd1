import java.util.List;

public class GradeCalculator {
    public double calculateAverage(List<Integer> grades) {
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int sum = 0;
        for (int grade : grades) {
            if (grade <= 0) {
                throw new IllegalArgumentException("Grade must be greater than zero");
            }

            if (grade > 5) {
                throw new IllegalArgumentException("Grade must be lower than five");
            }

            sum += grade;
        }

        return (double) sum / grades.size();
    }
}
