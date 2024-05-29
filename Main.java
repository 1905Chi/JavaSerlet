import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Tạo danh sách các bản đồ
        List<Map<String, Object>> list = Arrays.asList(
            new HashMap<String, Object>() {{
                put("id", 1);
                put("sex", "Male");
                put("name", "John Doe");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 2);
                put("sex", "Female");
                put("name", "Jane Smith");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 3);
                put("sex", "Male");
                put("name", "Bob Johnson");
                put("checkbox", false);
            }},
            new HashMap<String, Object>() {{
                put("id", 4);
                put("sex", "Male");
                put("name", "john pham");
                put("checkbox", false);
            }}
        );
System.out.print(list);
        // Sử dụng Stream API để nhóm các bản đồ theo thuộc tính 'sex' và chuyển đổi thành mảng Object[]
        Map<String, Object[]> groupedBySex = list.stream()
            .collect(Collectors.groupingBy(
                map -> (String) map.get("sex"), // Nhóm theo thuộc tính 'sex'
                Collectors.collectingAndThen(Collectors.toList(), l -> l.toArray(new Map[0])) // Chuyển đổi danh sách thành mảng
            ));

        // In ra kết quả
        groupedBySex.forEach((sex, maps) -> {
            System.out.println("Sex: " + sex);
            for (Object map : maps) {
                System.out.println(map);
            }
        });
    }
}
