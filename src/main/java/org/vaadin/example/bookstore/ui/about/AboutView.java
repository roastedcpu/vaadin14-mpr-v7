package org.vaadin.example.bookstore.ui.about;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.Version;
import com.vaadin.mpr.LegacyWrapper;
import com.vaadin.ui.Button;
import org.slf4j.LoggerFactory;
import org.vaadin.example.bookstore.ui.MainLayout;

@Route(value = "About", layout = MainLayout.class)
@PageTitle("About")
public class AboutView extends HorizontalLayout {
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        VaadinSession session = VaadinSession.getCurrent();
        if(session == null) {
            LoggerFactory.getLogger(getClass().getName()).error(" > SESSION IS EXPIRED");
        } else {
            LoggerFactory.getLogger(getClass().getName()).error(" > SESSION IS VALID");
        }
    }

    public static final String VIEW_NAME = "About";

    public AboutView() {
        add(VaadinIcon.INFO_CIRCLE.create());
        add(new Span(" This application is using Vaadin version "
                + Version.getFullVersion() + "."));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        Button button = new Button("Legacy button");
        button.setHtmlContentAllowed(true);
        button.setCaptionAsHtml(true);
        button.setCaption("<span stype=\"color:red;\">AAAAAAAA</span>");
        add(new LegacyWrapper(button));
    }
}
