import java.util.*;

public class InMemorySearchEngine {

    private final Map<String, String> documents = new HashMap<>();
    private final Map<String, Set<String>> invertedIndex = new HashMap<>();

    public void addDocument(String docId, String text) {
        documents.put(docId, text);
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(docId);
        }
    }

    public void removeDocument(String docId) {
        String text = documents.remove(docId);
        if (text != null) {
            String[] words = text.toLowerCase().split("\\W+");
            for (String word : words) {
                Set<String> docSet = invertedIndex.get(word);
                if (docSet != null) {
                    docSet.remove(docId);
                    if (docSet.isEmpty()) {
                        invertedIndex.remove(word);
                    }
                }
            }
        }
    }

    public List<String> search(String query) {
        query = query.toLowerCase();
        Set<String> resultDocIds = invertedIndex.getOrDefault(query, new HashSet<>());

        // Sort results based on occurrence count
        String query1 = query;
        return resultDocIds.stream()
            .sorted((d1, d2) -> countOccurrences(documents.get(d2), query1) - countOccurrences(documents.get(d1), query1))
            .toList();
    }

    private int countOccurrences(String text, String word) {
        if (text == null) return 0;
        return text.toLowerCase().split("\\b" + word + "\\b").length - 1;
    }

    public static void main(String[] args) {
        InMemorySearchEngine engine = new InMemorySearchEngine();
        
        engine.addDocument("Doc1", "apple is a fruit");
        engine.addDocument("Doc2", "apple, apple come on!");
        engine.addDocument("Doc3", "oranges are sour");
        engine.addDocument("Doc4", "apple-pie is sweet");

        System.out.println(engine.search("apple"));
    }
}
