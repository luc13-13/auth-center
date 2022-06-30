package com.lc.auth.center.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

/**
 * @author: lucheng
 * @data: 2022/5/1 3:39
 * @version: 1.0
 */
@Data
public class ShiroSessionListener implements SessionListener {
    private final AtomicInteger sessionCount = new AtomicInteger(0);
    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
    }
}
