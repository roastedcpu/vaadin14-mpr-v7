package org.vaadin.example.bookstore;

import com.vaadin.flow.component.internal.UIInternals;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.LoggerFactory;
import org.vaadin.example.bookstore.authentication.AccessControl;
import org.vaadin.example.bookstore.authentication.AccessControlFactory;
import org.vaadin.example.bookstore.ui.login.LoginScreen;

/**
 * This class is used to listen to BeforeEnter event of all UIs in order to
 * check whether a user is signed in or not before allowing entering any page.
 * It is registered in a file named
 * com.vaadin.flow.server.VaadinServiceInitListener in META-INF/services.
 */
public class BookstoreInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent initEvent) {
        final AccessControl accessControl = AccessControlFactory.getInstance()
                .createAccessControl();

        LoggerFactory.getLogger(UIInternals.class.getName()).info(" > A new Service has been initialized!");

        initEvent.getSource().addSessionInitListener(sessionInitEvent -> {
            VaadinSession session = sessionInitEvent.getSession();
            session.getSession().setMaxInactiveInterval(60);
            LoggerFactory.getLogger(UIInternals.class.getName()).info(String.format(" > A new Session has been initialized! (%d)", session.hashCode()));
        });
        initEvent.getSource().addSessionDestroyListener(sessionDestroyEvent -> {
            LoggerFactory.getLogger(UIInternals.class.getName()).info(String.format(" > Session (%d) has been destroyed!", sessionDestroyEvent.getSession().hashCode()));
        });

        initEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
                if (!accessControl.isUserSignedIn() && !LoginScreen.class
                        .equals(enterEvent.getNavigationTarget()))
                    enterEvent.rerouteTo(LoginScreen.class);
            });
        });
    }
}
