package model;

import roles.User;

import java.util.HashMap;
import java.util.Map;

public class Model {
    public Map<String, User> userSession;
    private static Model instance;

    private Model() {
        userSession = new HashMap<>();
    }

    public static Model getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }

    public void addSession(String sessionId, User user) {
        userSession.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        userSession.remove(sessionId);
    }

    public User getUserBySessionId(String sessionId) {
        return userSession.get(sessionId);
    }

}
