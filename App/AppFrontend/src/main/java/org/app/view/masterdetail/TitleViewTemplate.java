package org.app.view.masterdetail;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "Title", layout = MasterDetail.class)
@PageTitle("Title")
public class TitleViewTemplate extends HorizontalLayout {


	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "Title";

    public TitleViewTemplate() {
    	Label label = new Label("Title, das ist der Text zum anzeigen, danach kommt ein Logo");
        add(label);
        add(VaadinIcon.INFO_CIRCLE.create());

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }
}
