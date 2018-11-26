package org.app.view.masterdetail.visor;

import java.util.Locale;
import javax.inject.Inject;
import org.app.controler.VisorService;
import org.app.model.entity.Visor;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class VisorDetailView extends Dialog {
	@Inject
	V18 v18;

	private static final long serialVersionUID = 1L;

	private TextField txfListPrio;
	private TextField txfValue;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;
	private Locale loc;

	@SuppressWarnings("static-access")
	public VisorDetailView(VisorView parentView, Visor selectedEntry) {
		loc = new Locale("de", "DE");
		VisorService service = parentView.getService();
		saveButton = new Button(v18.getTranslation("basic.save", loc));

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		try {
			service.setEditing(false);

			TextField txfID = new TextField("ID" + selectedEntry.getId());
			subContent.add(txfID);

			txfListPrio = new TextField(v18.getTranslation("basic.listprio", loc), "" + selectedEntry.getListPrio());
			subContent.add(txfListPrio);

			txfValue = new TextField(v18.getTranslation("title.value", loc), "" + selectedEntry.getMdValue());
			subContent.add(txfValue);

			txaComment = new TextArea(v18.getTranslation("basic.comment", loc), "" + selectedEntry.getComment());
			subContent.add(txaComment);

			ckbEdit = new Checkbox(v18.getTranslation("basic.edit", loc));
			ckbEdit.addValueChangeListener(event -> {
				service.toggleEditing();
				if (event.getValue()) {
					saveButton.setEnabled(true);
				} else {
					saveButton.setEnabled(false);
				}
			});

			subContent.add(ckbEdit);

			saveButton.setEnabled(service.getEditing());
			subContent.add(saveButton);

			saveButton.addClickListener(event -> {
				selectedEntry.setListPrio(Integer.valueOf(txfListPrio.getValue()));
				selectedEntry.setMdValue(txfValue.getValue());
				selectedEntry.setComment(txaComment.getValue());
				parentView.updateRow(selectedEntry);
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
