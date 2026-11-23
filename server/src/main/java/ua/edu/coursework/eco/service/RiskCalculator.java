package ua.edu.coursework.eco.service;

public final class RiskCalculator {

    private static final double W1 = 0.40;
    private static final double W2 = 0.35;
    private static final double W3 = 0.25;

    private RiskCalculator() {
    }

    public static void validateFactor(double value, String factorName) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0.0 || value > 100.0) {
            throw new IllegalArgumentException(
                    factorName + " must be in range [0..100], got: " + value
            );
        }
    }

    public static double calculateRiskIndex(double f1, double f2, double f3) {
        validateFactor(f1, "F1");
        validateFactor(f2, "F2");
        validateFactor(f3, "F3");

        double raw = W1 * f1 + W2 * f2 + W3 * f3;
        return Math.round(raw * 100.0) / 100.0;
    }
}
