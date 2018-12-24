package org.app.view.person;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ProfileView", layout = MainLayout.class)
@PageTitle("ProfileView")
public class ProfileView extends VerticalLayout {

	@Inject
	V18Cdi v18;
	
	@Inject
	PersonView personView;

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "ProfileView";

	private VerticalLayout topHolder;
	private VerticalLayout leftHolder;
	private VerticalLayout rightHolder;
	private SplitLayout verticalSplit;
	private SplitLayout horizontalSplit;

	public ProfileView() {
		setSizeFull();
	}

	@PostConstruct
	void init() {
		topHolder = new VerticalLayout();
		leftHolder = new VerticalLayout();
		rightHolder = new VerticalLayout();
		Label demo2 = new Label("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Label demo3 = new Label("gggggggggggggggggggggggggggggggggg");

		topHolder.add(personView);
		
		leftHolder.add(demo2);
		leftHolder.add(demo2);
		leftHolder.add(demo2);
		
		rightHolder.add(demo3);
		rightHolder.add(demo3);
		rightHolder.add(demo3);
		

		horizontalSplit = new SplitLayout();
		horizontalSplit.setOrientation(Orientation.HORIZONTAL);
		horizontalSplit.addToPrimary(leftHolder);
		horizontalSplit.addToSecondary(rightHolder);
		horizontalSplit.setSplitterPosition(50);

		verticalSplit = new SplitLayout();
		verticalSplit.setOrientation(Orientation.VERTICAL);
		verticalSplit.addToPrimary(topHolder);
		verticalSplit.addToSecondary(horizontalSplit);
		verticalSplit.setSplitterPosition(60);
		verticalSplit.setSizeFull();

		add(verticalSplit);

	}

}
