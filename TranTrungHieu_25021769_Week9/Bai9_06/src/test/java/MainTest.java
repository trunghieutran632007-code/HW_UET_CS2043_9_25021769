import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Cac test don gian de mvn test co viec lam trong CI pipeline.
 * Muc tieu chinh cua bai 9_06 la dependency caching, khong phai testing.
 */
class MainTest {

    @Test
    void commonsLangReverseShouldWork() {
        assertEquals("cba", StringUtils.reverse("abc"));
    }

    @Test
    void commonsLangShouldDetectBlankString() {
        assertTrue(StringUtils.isBlank("   "));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank(null));
    }
}
