package org.app.view.dbaccount;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import org.app.controler.DbAccountService;
import org.app.model.entity.DbAccount;
import org.app.view.V18;
import org.app.view.V18Cdi;
import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class DbAccountNewView extends Dialog {

	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfUsername;
	private TextField txfMailaddress;
	private TextField txfPassword;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private BCryptPasswordEncoder encoder;
	private DbAccountService service;
	private DbAccount newEntry;
	

	@SuppressWarnings("static-access")
	public DbAccountNewView(DbAccountView parentView) {
		v18 = new V18();
		encoder = new BCryptPasswordEncoder();
		service = parentView.getAccountService();
		newEntry = new DbAccount();
		saveButton = new Button(v18.getTranslation("basic.save"));
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		saveButton.setEnabled(true);

		VerticalLayout subContent = new VerticalLayout();
		add(subContent);

		try {
			txfUsername = new TextField(v18.getTranslation("account.username"));
			subContent.add(txfUsername);

			txfPassword = new TextField(v18.getTranslation("account.password"));
			subContent.add(txfPassword);

			txfMailaddress = new TextField(v18.getTranslation("basic.email"));
			subContent.add(txfMailaddress);

			txaComment = new TextArea(v18.getTranslation("basic.comment"));
			subContent.add(txaComment);

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);

			saveButton.addClickListener(event -> {
				if ((txfUsername.getValue()).isEmpty()) {
					this.close();
				}
				newEntry.setUsername(txfUsername.getValue());
				newEntry.setMailaddress(txfMailaddress.getValue());
				newEntry.setPassword(encode(txfPassword.getValue()));
				newEntry.setComment(txaComment.getValue());
				service.create(newEntry);
				parentView.refreshGrid();
				close();
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
