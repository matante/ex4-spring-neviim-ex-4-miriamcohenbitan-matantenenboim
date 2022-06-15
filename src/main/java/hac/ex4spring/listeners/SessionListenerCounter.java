package hac.ex4spring.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    private final AtomicInteger activeSessions;
    private final String SESSION_CREATED_MSG = "SessionListenerCounter +++ Total active session are ";
    private final String SESSION_DESTROYED_MSG = "SessionListenerCounter --- Total active session are ";


    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println(SESSION_CREATED_MSG + activeSessions.get());

    }
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println(SESSION_DESTROYED_MSG + activeSessions.get());
    }
}
