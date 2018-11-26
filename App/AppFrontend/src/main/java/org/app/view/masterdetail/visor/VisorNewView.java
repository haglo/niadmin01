package org.app.view.masterdetail.visor;

import java.util.Locale;

import javax.inject.Inject;

import org.app.controler.VisorService;
import org.app.model.entity.Visor;
import org.app.view.V18;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VisorNewView extends Dialog {
	
	@Inject
	V18 v18;
	
	private static final long serialVersionUID = 1L;
	

	private TextField txfValue;
	private TextArea txaComment;
	private Button saveButton;
	private VisorService service;
	private Visor newEntry;
	private Locale loc;

	@SuppressWarnings("static-access")
	public VisorNewView(VisorView parentView) {
		
		loc = new Locale("de", "DE");
		this.service = parentView.getService();
		this.newEntry = new Visor();

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		try {
			txfValue = new TextField(v18.getTranslation("title.value", loc));
			subContent.add(txfValue);

			txaComment = new TextArea(v18.getTranslation("basic.comment", loc));
			subContent.add(txaComment);

			saveButton = new Button(v18.getTranslation("basic.save", loc));
			saveButton.setEnabled(true);
			subContent.add(saveButton);

			saveButton.addClickListener(event -> {
				newEntry.setListPrio(parentView.getMaxListPrio() + 1);
				newEntry.setMdValue(txfValue.getValue());
				newEntry.setComment(txaComment.getValue());
				service.getDAO().create(newEntry);
				parentView.refreshGrid();
				this.close();
			});

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
