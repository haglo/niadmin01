package org.app.view.dbaccount;

import java.util.EnumSet;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.app.model.entity.DbAccount;
import org.app.service.DbAccountService;
import org.app.view.V18;
import org.app.view.V18Cdi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.base.Preconditions;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

@Any
public class DbAccountDetailView extends Dialog {

	private static final long serialVersionUID = 1L;
	
	private V18 v18;
	private TextField txfUsername;
	private TextField txfMailaddress;
	private TextField txfPassword;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;
	private Button cancelButton;
	private BCryptPasswordEncoder encoder;

	private DbAccountService service;
	
	@SuppressWarnings("static-access")
	public DbAccountDetailView(DbAccountView accountView, DbAccount selectedAccount) {
		v18 = new V18();
		encoder = new BCryptPasswordEncoder();
		service = accountView.getAccountService();
		saveButton = new Button(v18.getTranslation("basic.save"));
		cancelButton = new Button(v18.getTranslation("basic.cancel"));

		VerticalLayout subContent = new VerticalLayout();
		add(subContent);

		try {
			service.setEditing(false);
			saveButton.setEnabled(service.getEditing());

			TextField txfID = new TextField("ID", "" + selectedAccount.getId());
			txfID.setReadOnly(true);
			subContent.add(txfID);

			txfUsername = new TextField(v18.getTranslation("account.username"));
			subContent.add(txfUsername);
			txfUsername.setValue(selectedAccount.getUsername());

			txfPassword = new TextField(v18.getTranslation("account.password"));
			subContent.add(txfPassword);
			txfPassword.setValue(selectedAccount.getPassword());

			txfMailaddress = new TextField(v18.getTranslation("basic.mail"));
			subContent.add(txfMailaddress);
			if (selectedAccount.getMailaddress()==null) {
				txfMailaddress.setValue(" ");
			} else  {
				txfMailaddress.setValue(selectedAccount.getMailaddress());
			}
				
			txaComment = new TextArea(v18.getTranslation("basic.comment"));
			subContent.add(txaComment);
			if (selectedAccount.getComment()==null) {
				txaComment.setValue(" ");
			} else  {
				txaComment.setValue(selectedAccount.getComment());
			}

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

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);

			saveButton.addClickListener(event -> {
				selectedAccount.setUsername(txfUsername.getValue());
				selectedAccount.setMailaddress(txfMailaddress.getValue());
				selectedAccount.setPassword(encode(txfPassword.getValue()));
				selectedAccount.setComment(txaComment.getValue());
				accountView.updateRow(selectedAccount);
				accountView.refreshGrid();
				this.close();
			});
			
			cancelButton.addClickListener(event -> {
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
	
	private String encode(String password) {
		return encoder.encode(password);
	}

}