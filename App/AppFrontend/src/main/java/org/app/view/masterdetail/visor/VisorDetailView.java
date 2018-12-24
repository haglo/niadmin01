package org.app.view.masterdetail.visor;

import org.app.model.entity.Visor;
import org.app.service.VisorService;
import org.app.view.V18;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class VisorDetailView extends Dialog {

	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfListPrio;
	private TextField txfValue;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;
	private Button cancelButton;
	private Visor selectedEntry;
	private VisorService service;

	public VisorDetailView(VisorView parentView) {
		v18 = new V18();
		selectedEntry = parentView.getSelectedEntry();
		service = parentView.getService();
		saveButton = new Button(v18.getTranslation("basic.save"));
		saveButton.setEnabled(false);
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		cancelButton.setEnabled(true);

		FormLayout subContent = new FormLayout();

		try {
			service.setEditing(false);

			TextField txfID = new TextField();
			txfID.setValue("" + selectedEntry.getId());
			txfID.setReadOnly(true);
			subContent.addFormItem(txfID, "ID");

			TextField txfUUID = new TextField();
			txfUUID.setValue("" + selectedEntry.getUuidEntry());
			txfUUID.setReadOnly(true);
			subContent.addFormItem(txfUUID, "UUID");

			txfListPrio = new TextField();
			txfListPrio.setValue("" + selectedEntry.getListPrio() != null ? "" + selectedEntry.getListPrio() : "");
			subContent.addFormItem(txfListPrio, v18.getTranslation("basic.listprio"));

			txfValue = new TextField();
			txfValue.setValue(selectedEntry.getEntityValue() != null ? selectedEntry.getEntityValue() : "");
			subContent.addFormItem(txfValue, v18.getTranslation("md.visor"));

			txaComment = new TextArea();
			txaComment.setValue(selectedEntry.getComment() != null ? selectedEntry.getComment() : "");
			subContent.addFormItem(txaComment, v18.getTranslation("basic.comment"));

			ckbEdit = new Checkbox(v18.getTranslation("basic.edit"));
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
				selectedEntry.setEntityValue(txfValue.getValue());
				selectedEntry.setComment(txaComment.getValue());
				// important
				selectedEntry = service.getDAO().update(selectedEntry);

				parentView.updateRow(selectedEntry);
				parentView.refreshGrid();
				close();
			});

			cancelButton.addClickListener(event -> {
				close();
			});

			addOpenedChangeListener(event -> txfValue.focus());

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);
			add(subContent);
			setWidth("400px");

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
