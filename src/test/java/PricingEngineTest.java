import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * ملف اختبارات محرك التسعير بعد التحسين (Refactored)
 * تم تحسين مسميات الدوال لتكون أكثر وضوحاً وفقاً لمبادئ هندسة البرمجيات.
 */
public class PricingEngineTest {

    @Test
    void shouldCalculatePriceForRegularCustomerWithNoDiscount() {
        PricingEngine engine = new PricingEngine();
        double[] prices = {100.0, 200.0};
        int[] quantities = {1, 1};
        
        // الحساب: (300 مجموع) - (0 خصم) + (300 * 0.19 ضريبة) = 357.0
        double result = engine.calculate(prices, quantities, "REGULAR", null);
        
        assertEquals(357.0, result, "The final price for regular customer should be 357.0");
    }

    @Test
    void shouldApplyVipDiscountWithPromoCodeCorrectly() {
        PricingEngine engine = new PricingEngine();
        double[] prices = {100.0};
        int[] quantities = {1};
        
        // الحساب: (100 مجموع) - (100 * 0.35 خصم VIP+SAVE20) + (65 * 0.19 ضريبة) = 77.35
        double result = engine.calculate(prices, quantities, "VIP", "SAVE20");
        
        assertEquals(77.35, result, 0.01, "VIP with SAVE20 should get 35% total discount rate");
    }

    @Test
    void shouldApplyRegularDiscountWhenSave10CodeIsUsed() {
        PricingEngine engine = new PricingEngine();
        double[] prices = {200.0};
        int[] quantities = {1};
        
        // الحساب: (200 مجموع) - (200 * 0.10 خصم) + (180 * 0.19 ضريبة) = 214.2
        double result = engine.calculate(prices, quantities, "REGULAR", "SAVE10");
        
        assertEquals(214.2, result, 0.01, "Regular customer with SAVE10 should get 10% discount");
    }
}