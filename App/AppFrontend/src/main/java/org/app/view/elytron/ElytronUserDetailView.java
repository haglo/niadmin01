package org.app.view.elytron;

import java.util.EnumSet;
import java.util.List;

import org.app.model.entity.ElytronRole;
import org.app.model.entity.ElytronUser;
import org.app.model.entity.enums.DefaultLanguage;
import org.app.model.entity.enums.DefaultTheme;
import org.app.service.ElytronRoleService;
import org.app.service.ElytronUserService;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class ElytronUserDetailView extends Dialog {
	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfUserName;
	private ComboBox<ElytronRole> cbxRole;
	private ComboBox<DefaultLanguage> cbxLanguage;
	private ComboBox<DefaultTheme> cbxTheme;
	private Checkbox ckbEdit;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private ElytronUser selectedEntry;
	private ElytronUserService service;
	private ElytronRoleService elytronRoleService;

	public ElytronUserDetailView(ElytronUserView parentView) {
		v18 = new V18();
		selectedEntry = parentView.getSelectedEntry();
		service = parentView.getService();
		elytronRoleService = parentView.getElytronRoleService();
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

			txfUserName = new TextField();
			txfUserName.setValue(selectedEntry.getUsername() != null ? selectedEntry.getUsername() : "");
			subContent.addFormItem(txfUserName, v18.getTranslation("account.username"));
			
			
			List<ElytronRole> elytronRoleList = elytronRoleService.getDAO().findAll();
			cbxRole = new ComboBox<>();
			cbxRole.setItems(elytronRoleList);
			cbxRole.setItemLabelGenerator(ElytronRole::getRolename);
			cbxRole.setValue(selectedEntry.getElytronRole());
			subContent.addFormItem(cbxRole, v18.getTranslation("account.group"));

			cbxLanguage = new ComboBox<>();
			cbxLanguage.setItems(EnumSet.allOf(DefaultLanguage.class));
			cbxLanguage.setValue(selectedEntry.getDefaultLanguage());
			subContent.addFormItem(cbxLanguage, v18.getTranslation("basic.language"));

			cbxTheme = new ComboBox<>();
			cbxTheme.setItems(EnumSet.allOf(DefaultTheme.class));
			cbxTheme.setValue(selectedEntry.getDefaultTheme());
			subContent.addFormItem(cbxTheme, v18.getTranslation("basic.theme"));

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

			saveButton.addClickListener(event -> {
				selectedEntry.setUsername(txfUserName.getValue());
				selectedEntry.setElytronRole(cbxRole.getValue());
				selectedEntry.setDefaultLanguage(cbxLanguage.getValue());
				selectedEntry.setDefaultTheme(cbxTheme.getValue());
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
