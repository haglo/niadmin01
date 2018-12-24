package org.app.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ExtendedDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private HorizontalLayout topBar;
	private VerticalLayout contentTextOnTop;
	private FormLayout contentTextOnLeft;

	public ExtendedDialog(String title) {
		getElement().getClassList().add("extended-dialog");
		Div captionText = new Div();
		captionText.setText(title);

		Button cancelButton = new Button(" X ");
		cancelButton.addClickListener(event -> {
			close();
		});
		cancelButton.setClassName("alignright");

		topBar = new HorizontalLayout(captionText, cancelButton);
		topBar.setHeight("7%");
		topBar.setWidth("100%");
		topBar.setClassName("extended-dialog-top-bar");

		contentTextOnTop = new VerticalLayout();
		contentTextOnTop.setHeight("93%");
		contentTextOnTop.setWidth("100%");
		contentTextOnTop.setClassName("email-write");
		
		contentTextOnLeft = new FormLayout();
		contentTextOnLeft.setHeight("93%");
		contentTextOnLeft.setWidth("100%");
		contentTextOnLeft.setClassName("email-write");

		add(topBar);
	}

	public void addWithTextOnTop(Component component) {
		contentTextOnTop.add(component);
		add(contentTextOnTop);
	}

	public void addWithTextOnLeft(Component component, String text) {
		contentTextOnLeft.addFormItem(component, text);
		add(contentTextOnLeft);
	}

	enum TextAlignment {
		TOP, LEFT;
	}

}
