public class PricingEngine {
    // استبدال Magic Numbers بثوابت (Constants) لتحسين القابلية للصيانة
    private static final double TAX_RATE = 0.19;
    private static final double VIP_BASE_RATE = 0.15;
    private static final double SAVE20_BONUS = 0.20;
    private static final double REGULAR_DISCOUNT = 0.10;

    public double calculate(double[] prices, int[] quantities, String customerType, String discountCode) {
        double subtotal = calculateSubtotal(prices, quantities);
        double discount = getDiscountAmount(subtotal, customerType, discountCode);
        double tax = calculateTax(subtotal - discount);
        
        return (subtotal - discount) + tax;
    }

    private double calculateSubtotal(double[] prices, int[] quantities) {
        double total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * quantities[i];
        }
        return total;
    }

    // تطبيق تقنية Guard Clauses لتبسيط الشروط المتداخلة
    private double getDiscountAmount(double subtotal, String customerType, String code) {
        if ("VIP".equals(customerType)) {
            double rate = VIP_BASE_RATE;
            if ("SAVE20".equals(code)) rate += SAVE20_BONUS;
            return subtotal * rate;
        }
        
        if ("REGULAR".equals(customerType) && "SAVE10".equals(code)) {
            return subtotal * REGULAR_DISCOUNT;
        }
        
        return 0; // التعامل مع الحالة الافتراضية بوضوح
    }

    private double calculateTax(double amount) {
        return amount * TAX_RATE;
    }
}