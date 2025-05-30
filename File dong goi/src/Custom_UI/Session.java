package Custom_UI;
import java.util.HashMap;

public class Session {

    private static Session instance;

    private HashMap<String, Object> data = new HashMap<>();

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void clear() {
        data.clear();
    }
}

