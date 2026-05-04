import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Demo su dung dependency da duoc cache.
 *
 * <p>Class nay co tinh "cham" vao moi dependency trong pom.xml de chac chan
 * rang neu Maven khong tai duoc dep tu cache, build se fail.</p>
 */
// tôi sửa
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("=== Bai9_06: CI/CD Pipeline Optimization & Caching ===");

        // 1. Gson - JSON serialization
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> info = Map.of(
                "exercise", "Bai9_06",
                "topic", "Maven dependency caching",
                "jdk", System.getProperty("java.version")
        );
        log.info("Gson output:\n{}", gson.toJson(info));

        // 2. Apache Commons Lang
        String reversed = StringUtils.reverse("Caching saves time");
        log.info("commons-lang3 reverse: {}", reversed);

        // 3. Apache Commons Collections
        List<Integer> a = List.of(1, 2, 3, 4);
        List<Integer> b = List.of(3, 4, 5, 6);
        log.info("commons-collections4 intersection: {}",
                CollectionUtils.intersection(a, b));

        // 4. Guava
        List<List<Integer>> partitions = Lists.partition(List.of(1, 2, 3, 4, 5, 6, 7), 3);
        log.info("Guava Lists.partition: {}", partitions);

        log.info("All dependencies loaded successfully -> cache hoat dong dung.");
    }
}
