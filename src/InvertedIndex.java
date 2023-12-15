import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvertedIndex {
    private final Map<String, Set<String>> termToFileMapping = new ConcurrentHashMap<>();
    private static final Logger LOGGER = Logger.getLogger(InvertedIndex.class.getName());
    public void addToMapping(String word, String file) {
        if (word == null || file == null) {
            LOGGER.log(Level.WARNING, "Null word or file.");
            return;
        }
        termToFileMapping.computeIfAbsent(word, k -> new HashSet<>()).add(file);
    }

    public Set<String> getFilesForKey(String word) {
        return termToFileMapping.getOrDefault(word, Collections.emptySet());
    }

    public Map<String, Set<String>> getMapping() {
        return termToFileMapping;
    }
}

