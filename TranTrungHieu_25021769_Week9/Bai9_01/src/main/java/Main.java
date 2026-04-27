import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo nhỏ chứng minh ba dependency mới đã được resolve thành công:
 *  - Logback Classic (qua API của SLF4J)
 *  - Hibernate Core (chỉ import để ép classpath resolve, chưa kết nối DB)
 *  - JUnit Jupiter sẽ được kích hoạt khi chạy mvn test
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("MathUtils started on Java {}", System.getProperty("java.version"));

        int a = 48;
        int b = 18;
        int g = gcd(a, b);
        logger.info("GCD({}, {}) = {}", a, b, g);

        // Chi import de classpath co Hibernate, khong khoi tao session that.
        Class<?> hbm = org.hibernate.Session.class;
        logger.debug("Hibernate Session class loaded: {}", hbm.getName());

        logger.info("MathUtils finished successfully");
    }

    private static int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }
}