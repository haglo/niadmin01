package org.app.view.masterdetail.visor;

import org.app.model.entity.Visor;
import org.app.service.VisorService;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class VisorNewView extends Dialog {
	

	private static final long serialVersionUID = 1L;
	
	private V18 v18;
	private TextField txfValue;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private VisorService service;
	private Visor newEntry;

	public VisorNewView(VisorView parentView) {
		v18 = new V18();
		service = parentView.getService();
		newEntry = new Visor();
		saveButton = new Button(v18.getTranslation("basic.save"));
		saveButton.setEnabled(true);
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		cancelButton.setEnabled(true);

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		try {
			txfValue = new TextField(v18.getTranslation("md.visor"));
			subContent.add(txfValue);

			txaComment = new TextArea(v18.getTranslation("basic.comment"));
			subContent.add(txaComment);

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);

			saveButton.addClickListener(event -> {
				newEntry.setListPrio(parentView.getMaxListPrio() + 1);
				newEntry.setEntityValue(txfValue.getValue());
				newEntry.setComment(txaComment.getValue());
				newEntry = service.getDAO().create(newEntry);
				parentView.refreshGrid();
				close();
			});
			
			cancelButton.addClickListener(event -> {
				close();
			});
			
			addOpenedChangeListener(event -> txfValue.focus());


		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
