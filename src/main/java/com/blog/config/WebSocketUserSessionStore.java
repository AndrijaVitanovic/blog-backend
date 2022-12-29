package com.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebSocketUserSessionStore {

    private final Map<String, String> userSessions = new ConcurrentHashMap<>();

    public Set<Map.Entry<String, String>> getUserSessions() {
        return userSessions.entrySet();
    }

    public void add(String sessionId, String username) {
        userSessions.put(sessionId, username);
    }

    public void removeSessionId(String sessionId) {
        userSessions.remove(sessionId);
    }

    public List<String> getSessionIds(String username) {
        return userSessions.entrySet().stream()
                .filter(entry -> entry.getValue().equals(username))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void remove(String username) {
        userSessions.values().remove(username);
    }
}
