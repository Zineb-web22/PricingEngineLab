public class PricingEngine {

    // الدالة الرئيسية أصبحت نظيفة وتقرأ كقصة
    public double calculate(double[] prices, int[] quantities, String customerType, String discountCode) {
        double subtotal = calculateSubtotal(prices, quantities);
        double discount = getDiscountAmount(subtotal, customerType, discountCode);
        double tax = calculateTax(subtotal - discount);
        
        return (subtotal - discount) + tax;
    }

    // فصل حساب المجموع الفرعي
    private double calculateSubtotal(double[] prices, int[] quantities) {
        double sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i] * quantities[i];
        }
        return sum;
    }

    // فصل منطق الخصومات لتسهيل تعديله مستقبلاً
    private double getDiscountAmount(double subtotal, String customerType, String code) {
        double rate = 0;
        if ("VIP".equals(customerType)) {
            rate = 0.15;
            if ("SAVE20".equals(code)) rate += 0.20;
        } else if ("REGULAR".equals(customerType) && "SAVE10".equals(code)) {
            rate = 0.10;
        }
        return subtotal * rate;
    }

    // فصل حساب الضريبة
    private double calculateTax(double amount) {
        return amount * 0.19;
    }
}