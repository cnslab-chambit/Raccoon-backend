package raccoon.raccoonchatting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import raccoon.raccoonchatting.dto.ChatMessage;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;

    private final Map<Long, List<WebSocketSession>> sessions = new HashMap<>();
    private final ApplicationEventPublisher eventPublisher;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        System.out.println(sessions.get(chatMessage.getRoomId()).size()+"사이즈입니다.");
        for (WebSocketSession e : sessions.get(chatMessage.getRoomId())){
            e.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
        }
        eventPublisher.publishEvent(ChatEvent.of(chatMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        final String json = qs2json(URLDecoder.decode(session.getUri().getQuery(), "UTF-8"));

        Map<String,String> map = objectMapper.readValue(json, Map.class);

        Long roomId = Long.parseLong(map.get("roomId"));
        Long userId = Long.parseLong(map.get("userId"));

        sessions.get(roomId).remove(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final String json = qs2json(URLDecoder.decode(session.getUri().getQuery(), "UTF-8"));

        Map<String,String> map = objectMapper.readValue(json, Map.class);

        Long roomId = Long.parseLong(map.get("roomId"));
        Long userId = Long.parseLong(map.get("userId"));

        sessions.computeIfAbsent(roomId, k -> new ArrayList<>());

        sessions.get(roomId).add(session);
    }

    private String qs2json(String a) {
        String res = "{\"";

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '=') {
                res += "\"" + ":" + "\"";
            } else if (a.charAt(i) == '&') {
                res += "\"" + "," + "\"";
            } else {
                res += a.charAt(i);
            }
        }
        res += "\"" + "}";
        return res;
    }
}