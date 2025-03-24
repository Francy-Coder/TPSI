public class ProbabilityGenerator {
    public static boolean eventOccurs(double probability) {
        return Math.random() < probability;
    }
}
