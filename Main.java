import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Giả lập dữ liệu từ Hibernate
        List<Map<String, Object>> list = Arrays.asList(
            new HashMap<String, Object>() {{
                put("id", 1.0);
                put("sex", "Male");
                put("name", "John Doe");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 2.0);
                put("sex", "Female");
                put("name", "Jane Smith");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 3.0);
                put("sex", "Male");
                put("name", "Bob Johnson");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 4.0);
                put("sex", "Male");
                put("name", "john pham");
                put("checkbox", false);
            }}
        );

        // Sử dụng Stream API để nhóm các bản đồ theo thuộc tính 'sex'
        Map<String, List<Map<String, Object>>> groupedBySex = list.stream()
            .collect(Collectors.groupingBy(map -> (String) map.get("sex")));

        // In ra kết quả chi tiết từng giá trị và tính tổng id cho mỗi nhóm
        groupedBySex.forEach((sex, maps) -> {
            System.out.println("Sex: " + sex);
            double totalId = maps.stream()
                                 .mapToDouble(map -> (Double) map.get("id"))
                                 .sum();
            System.out.println("Total ID: " + totalId);
            for (Map<String, Object> map : maps) {
                map.forEach((key, value) -> {
                    System.out.println(key + ": " + value);
                });
                System.out.println("-----");
            }
        });
    }
}
