package hac.ex4spring.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    private final AtomicInteger activeSessions;

    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("SessionListenerCounter +++ Total active session are " + activeSessions.get());

    }
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
