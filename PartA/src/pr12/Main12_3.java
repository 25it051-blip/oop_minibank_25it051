import java.util.*;

public class Main12_3 {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Aesha", 95);
        scores.put("Rutvi", 88);
        scores.put("Rajvi", 95);
        scores.put("Tanu", 78);
        scores.put("Tanisha", 88);

        List<Map.Entry<String, Integer>> players =
            new ArrayList<>(scores.entrySet());

        players.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b) {
                int result =
                    b.getValue().compareTo(a.getValue());

                if (result == 0) {
                    return a.getKey().compareTo(b.getKey());
                }

                return result;
            }
        });

        int topN = 3;

        System.out.println("Top " + topN + " Players:");

        for (int i = 0; i < Math.min(topN, players.size()); i++) {
            Map.Entry<String, Integer> player = players.get(i);

            System.out.println(player.getKey() + " - "
                + player.getValue());
        }
    }
}