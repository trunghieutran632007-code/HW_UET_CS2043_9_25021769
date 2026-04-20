package Bai8_07.src;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("=== DISCOUNT CALCULATOR TEST SUITE ===")
public class DiscountCalculatorTest {

    // ========================================================================
    // PHAN 1 & 2: EQUIVALENCE PARTITIONING (EP)
    // ========================================================================

    @Nested
    @DisplayName("EP - Equivalence Partitioning")
    class EquivalencePartitioningTests {

        @Test
        @DisplayName("TC1 | EC1 Invalid: price am (-10, MEMBER) -> Exception")
        void tc1_negativePriceMember() {
            assertThrows(IllegalArgumentException.class,
                () -> DiscountCalculator.calculateDiscount(-10, "MEMBER"));
        }

        @Test
        @DisplayName("TC2 | EC2 Valid: GUEST, gia thap (50) -> chiet khau 0%")
        void tc2_guestLowPrice() {
            assertEquals(0, DiscountCalculator.calculateDiscount(50, "GUEST"));
        }

        @Test
        @DisplayName("TC3 | EC3 Valid: GUEST, gia cao (200) -> chiet khau 0%")
        void tc3_guestHighPrice() {
            assertEquals(0, DiscountCalculator.calculateDiscount(200, "GUEST"));
        }

        @Test
        @DisplayName("TC4 | EC2 Valid: MEMBER, gia thap (50) -> chiet khau 5% = 2.5")
        void tc4_memberLowPrice() {
            assertEquals(2.5, DiscountCalculator.calculateDiscount(50, "MEMBER"), 0.001);
        }

        @Test
        @DisplayName("TC5 | EC3 Valid: MEMBER, gia cao (200) -> chiet khau 10% = 20.0")
        void tc5_memberHighPrice() {
            assertEquals(20.0, DiscountCalculator.calculateDiscount(200, "MEMBER"), 0.001);
        }

        @Test
        @DisplayName("TC6 | EC2 Valid: VIP, gia thap (50) -> chiet khau 15% = 7.5")
        void tc6_vipLowPrice() {
            assertEquals(7.5, DiscountCalculator.calculateDiscount(50, "VIP"), 0.001);
        }

        @Test
        @DisplayName("TC7 | EC3 Valid: VIP, gia cao (200) -> chiet khau 20% = 40.0")
        void tc7_vipHighPrice() {
            assertEquals(40.0, DiscountCalculator.calculateDiscount(200, "VIP"), 0.001);
        }

        @Test
        @DisplayName("TC8 | Invalid memberType (GOLD) -> Exception")
        void tc8_invalidMemberType() {
            assertThrows(IllegalArgumentException.class,
                () -> DiscountCalculator.calculateDiscount(50, "GOLD"));
        }
    }

    // ========================================================================
    // PHAN 3: BOUNDARY VALUE ANALYSIS (BVA)
    // ========================================================================

    @Nested
    @DisplayName("BVA - Boundary Value Analysis")
    class BoundaryValueTests {

        @Nested
        @DisplayName("Ranh gioi price = 0 (Invalid / Valid)")
        class BoundaryZero {

            @Test
            @DisplayName("min-: price = -0.01 -> Exception (ngay duoi ranh gioi)")
            void minMinus_negativeJustBelow() {
                assertThrows(IllegalArgumentException.class,
                    () -> DiscountCalculator.calculateDiscount(-0.01, "MEMBER"));
            }

            @Test
            @DisplayName("min: price = 0 -> hop le, MEMBER chiet khau 5% = 0.0")
            void min_exactBoundary() {
                assertEquals(0.0, DiscountCalculator.calculateDiscount(0, "MEMBER"), 0.001);
            }

            @Test
            @DisplayName("min+: price = 0.01 -> hop le, MEMBER chiet khau 5% = 0.0005")
            void minPlus_justAbove() {
                assertEquals(0.0005, DiscountCalculator.calculateDiscount(0.01, "MEMBER"), 0.0001);
            }
        }

        @Nested
        @DisplayName("Ranh gioi price = 100 (muc chiet khau thap / cao)")
        class BoundaryHundred {

            @Test
            @DisplayName("max-: price = 99.99 -> MEMBER chiet khau 5% = 4.9995")
            void maxMinus_justBelow() {
                assertEquals(99.99 * 0.05,
                    DiscountCalculator.calculateDiscount(99.99, "MEMBER"), 0.001);
            }

            @Test
            @DisplayName("max: price = 100 -> MEMBER chiet khau 10% = 10.0")
            void max_exactBoundary() {
                assertEquals(10.0, DiscountCalculator.calculateDiscount(100, "MEMBER"), 0.001);
            }

            @Test
            @DisplayName("max+: price = 100.01 -> MEMBER chiet khau 10% = 10.001")
            void maxPlus_justAbove() {
                assertEquals(100.01 * 0.10,
                    DiscountCalculator.calculateDiscount(100.01, "MEMBER"), 0.001);
            }
        }
    }

    // ========================================================================
    // PHAN 4: 2-WAY COMBINATORIAL TESTING
    // ========================================================================

    @Nested
    @DisplayName("2-Way Combinatorial (price x memberType)")
    class CombinatorialTests {

        // --- price = -10 (Invalid) x tat ca memberType ---

        @ParameterizedTest(name = "price=-10, memberType={0} -> Exception")
        @ValueSource(strings = {"GUEST", "MEMBER", "VIP", "OTHER"})
        @DisplayName("Negative price x All memberTypes -> Exception")
        void negativePrice_allMemberTypes(String memberType) {
            assertThrows(IllegalArgumentException.class,
                () -> DiscountCalculator.calculateDiscount(-10, memberType));
        }

        // --- price = 50 (low valid) x tat ca memberType ---

        @Test
        @DisplayName("price=50, GUEST -> 0")
        void lowPrice_guest() {
            assertEquals(0, DiscountCalculator.calculateDiscount(50, "GUEST"));
        }

        @Test
        @DisplayName("price=50, MEMBER -> 2.5 (5%)")
        void lowPrice_member() {
            assertEquals(2.5, DiscountCalculator.calculateDiscount(50, "MEMBER"), 0.001);
        }

        @Test
        @DisplayName("price=50, VIP -> 7.5 (15%)")
        void lowPrice_vip() {
            assertEquals(7.5, DiscountCalculator.calculateDiscount(50, "VIP"), 0.001);
        }

        @Test
        @DisplayName("price=50, OTHER -> Exception")
        void lowPrice_other() {
            assertThrows(IllegalArgumentException.class,
                () -> DiscountCalculator.calculateDiscount(50, "OTHER"));
        }

        // --- price = 200 (high valid) x tat ca memberType ---

        @Test
        @DisplayName("price=200, GUEST -> 0")
        void highPrice_guest() {
            assertEquals(0, DiscountCalculator.calculateDiscount(200, "GUEST"));
        }

        @Test
        @DisplayName("price=200, MEMBER -> 20.0 (10%)")
        void highPrice_member() {
            assertEquals(20.0, DiscountCalculator.calculateDiscount(200, "MEMBER"), 0.001);
        }

        @Test
        @DisplayName("price=200, VIP -> 40.0 (20%)")
        void highPrice_vip() {
            assertEquals(40.0, DiscountCalculator.calculateDiscount(200, "VIP"), 0.001);
        }

        @Test
        @DisplayName("price=200, OTHER -> Exception")
        void highPrice_other() {
            assertThrows(IllegalArgumentException.class,
                () -> DiscountCalculator.calculateDiscount(200, "OTHER"));
        }
    }
}
