import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for PathUtils.
 *
 * Uses a tiny self-contained test framework (run + assertEquals)
 * so the project has zero external dependencies - giao vien
 * chi can chay ./run.sh la du.
 *
 * On failure System.exit(1) is called so the shell script (and
 * therefore the GitHub Actions job) returns a non-zero exit code.
 */
public class PathUtilsTest {

    private static final List<String> failures = new ArrayList<>();
    private static int total = 0;

    public static void runAll() {
        run("buildLogFilePath uses OS-correct separator",
            PathUtilsTest::testBuildLogFilePath);

        run("join with multiple segments uses OS-correct separator",
            PathUtilsTest::testJoinMulti);

        run("getFileName extracts only the file name",
            PathUtilsTest::testGetFileName);

        printSummary();
    }

    // ---------- tests ----------

    private static void testBuildLogFilePath() {
        String result = PathUtils.buildLogFilePath("var", "app.log");
        String expected =
            "var" + File.separator + "logs" + File.separator + "app.log";
        assertEquals(expected, result);
    }

    private static void testJoinMulti() {
        String result = PathUtils.join("data", "input", "users.csv");
        String expected =
            "data" + File.separator + "input" + File.separator + "users.csv";
        assertEquals(expected, result);
    }

    private static void testGetFileName() {
        String input =
            "data" + File.separator + "input" + File.separator + "users.csv";
        assertEquals("users.csv", PathUtils.getFileName(input));
    }

    // ---------- mini test framework ----------

    private static void run(String name, Runnable test) {
        total++;
        try {
            test.run();
            System.out.println("[PASS] " + name);
        } catch (AssertionError e) {
            System.out.println("[FAIL] " + name + " -> " + e.getMessage());
            failures.add(name);
        } catch (Exception e) {
            System.out.println("[ERROR] " + name + " -> " + e);
            failures.add(name);
        }
    }

    private static void assertEquals(Object expected, Object actual) {
        boolean equal = (expected == null)
            ? actual == null
            : expected.equals(actual);
        if (!equal) {
            throw new AssertionError(
                "expected=<" + expected + "> actual=<" + actual + ">");
        }
    }

    private static void printSummary() {
        int passed = total - failures.size();
        System.out.println();
        System.out.println("=== Test Summary ===");
        System.out.println("Total : " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failures.size());

        if (!failures.isEmpty()) {
            System.out.println();
            System.out.println("Failed tests:");
            for (String f : failures) {
                System.out.println("  - " + f);
            }
            System.exit(1);
        }
    }
}
