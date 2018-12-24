package org.app.mail.smtp;

import org.app.view.V18;
import org.app.model.audit.LoggedInUser;
import org.app.model.entity.ElytronMailSetting;
import org.app.service.ElytronMailSettingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

public class MailSetting extends FormLayout {

	private static final long serialVersionUID = 1L;
	private String smtpUsername;
	private String smtpPassword;
	private String smtpReplyTo;
	private String smtpHost;
	private Integer smtpPort;
	private boolean isSmtpSSL = true;
	private boolean isSmtpStartTls = true;
	private boolean isSmtpAuthentication = true;

	private String imapReplyTo;
	private String imapUsername;
	private String imapPassword;
	private String imapHost;
	private Integer imapPort;
	private boolean isImapSSL = true;

	private Button saveButton;
	private V18 v18;
	private ElytronMailSettingService service;
	private ElytronMailSetting selectedEntry;

	public MailSetting(ElytronMailSettingService srv, LoggedInUser loggedInUser) {
		v18 = new V18();
		service = srv;
		saveButton = new Button(v18.getTranslation("basic.save"));
		selectedEntry = service.getDAO().findByElytronUser(loggedInUser.getElytronUser());

		Label head = new Label("Mail-Settings for User");
		add(head);
		createView();
	}

	private void createView() {

		TextField smtpHostField = new TextField();
		smtpHostField.setValue(selectedEntry.getSmtpHost() != null ? selectedEntry.getSmtpHost() : "bla1");
		addFormItem(smtpHostField, "SMTP-Server");

		TextField smtpUsernameField = new TextField();
		smtpUsernameField.setValue(selectedEntry.getSmtpUsername() != null ? selectedEntry.getSmtpUsername() : "bla2");
		addFormItem(smtpUsernameField, v18.getTranslation("account.username"));

		TextField smtpPasswordField = new TextField();
		smtpPasswordField.setValue(selectedEntry.getSmtpPassword() != null ? selectedEntry.getSmtpPassword() : "bla3");
		addFormItem(smtpPasswordField, v18.getTranslation("account.password"));

		TextField smtpReplyToField = new TextField();
		smtpReplyToField.setValue(selectedEntry.getSmtpReplyTo() != null ? selectedEntry.getSmtpReplyTo() : "bla4");
		addFormItem(smtpReplyToField, v18.getTranslation("mail.answer"));

		TextField smtpPortField = new TextField();
		smtpPortField.setValue(selectedEntry.getSmtpPort() != null ? selectedEntry.getSmtpPort().toString() : "bla4");
		addFormItem(smtpPortField, v18.getTranslation("basic.port"));

		Checkbox isSmtpSSLCheckBox = new Checkbox();
		isSmtpSSLCheckBox.setEnabled(selectedEntry.isSmtpSSL());
		addFormItem(isSmtpSSLCheckBox, v18.getTranslation("basic.withssl"));

		Checkbox isSmtpStartTlsCheckBox = new Checkbox();
		isSmtpStartTlsCheckBox.setEnabled(selectedEntry.isSmtpStartTls());
		addFormItem(isSmtpStartTlsCheckBox, v18.getTranslation("basic.withstarttls"));

		Checkbox isSmtpAuthenticationCheckBox = new Checkbox();
		isSmtpAuthenticationCheckBox.setEnabled(selectedEntry.isSmtpAuthentication());
		addFormItem(isSmtpAuthenticationCheckBox, "SMTP-" + v18.getTranslation("mail.withauthentication"));

		TextField imapHostField = new TextField();
		imapHostField.setValue(selectedEntry.getImapHost() != null ? selectedEntry.getImapHost() : "bla4");
		addFormItem(imapHostField, "IMAP-Server");

		TextField imapUsernameField = new TextField();
		imapUsernameField.setValue(selectedEntry.getImapUsername() != null ? selectedEntry.getImapUsername() : "bla4");
		addFormItem(imapUsernameField, v18.getTranslation("account.username"));

		TextField imapPasswordField = new TextField();
		imapPasswordField.setValue(selectedEntry.getImapPassword() != null ? selectedEntry.getImapPassword() : "bla4");
		addFormItem(imapPasswordField, v18.getTranslation("account.password"));

		TextField imapReplyToField = new TextField();
		imapReplyToField.setValue(selectedEntry.getImapReplyTo() != null ? selectedEntry.getImapReplyTo() : "bla4");
		addFormItem(imapReplyToField, v18.getTranslation("mail.answer"));

		TextField imapPortField = new TextField();
		imapPortField.setValue(selectedEntry.getImapPort() != null ? selectedEntry.getImapPort().toString() : "bla4");
		addFormItem(imapPortField, v18.getTranslation("basic.port"));

		Checkbox isImapSSLCheckBox = new Checkbox();
		isImapSSLCheckBox.setEnabled(selectedEntry.isImapSSL());
		addFormItem(isImapSSLCheckBox, v18.getTranslation("basic.withssl"));

		saveButton.addClickListener(event -> {
			selectedEntry.setSmtpUsername(smtpUsernameField.getValue());
			selectedEntry.setSmtpPassword(smtpPasswordField.getValue());
			selectedEntry.setSmtpHost(smtpHostField.getValue());
			selectedEntry.setSmtpReplyTo(smtpReplyToField.getValue());
			selectedEntry.setSmtpPort(Integer.parseInt(smtpPortField.getValue()));
			selectedEntry.setSmtpSSL(isSmtpSSLCheckBox.isEnabled());
			selectedEntry.setSmtpStartTls(isSmtpStartTlsCheckBox.isEnabled());
			selectedEntry.setSmtpAuthentication(isSmtpAuthenticationCheckBox.isEnabled());
			
			selectedEntry.setImapHost(imapHostField.getValue());
			selectedEntry.setImapUsername(imapUsernameField.getValue());
			selectedEntry.setImapPassword(imapPasswordField.getValue());
			selectedEntry.setImapReplyTo(imapReplyToField.getValue());
			selectedEntry.setImapPort(Integer.parseInt(imapPortField.getValue()));
			selectedEntry.setImapSSL(isImapSSLCheckBox.isEnabled());

			selectedEntry = service.getDAO().update(selectedEntry);
			Notification.show(v18.getTranslation("notification.saveSuccess"));
		});
		add(saveButton);
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSmtpReplyTo() {
		return smtpReplyTo;
	}

	public void setSmtpReplyTo(String smtpReplyTo) {
		this.smtpReplyTo = smtpReplyTo;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public boolean isSmtpSSL() {
		return isSmtpSSL;
	}

	public void setSmtpSSL(boolean isSmtpSSL) {
		this.isSmtpSSL = isSmtpSSL;
	}

	public boolean isSmtpStartTls() {
		return isSmtpStartTls;
	}

	public void setSmtpStartTls(boolean isSmtpStartTls) {
		this.isSmtpStartTls = isSmtpStartTls;
	}

	public boolean isSmtpAuthentication() {
		return isSmtpAuthentication;
	}

	public void setSmtpAuthentication(boolean isSmtpAuthentication) {
		this.isSmtpAuthentication = isSmtpAuthentication;
	}

	public String getImapReplyTo() {
		return imapReplyTo;
	}

	public void setImapReplyTo(String imapReplyTo) {
		this.imapReplyTo = imapReplyTo;
	}

	public String getImapUsername() {
		return imapUsername;
	}

	public void setImapUsername(String imapUsername) {
		this.imapUsername = imapUsername;
	}

	public String getImapPassword() {
		return imapPassword;
	}

	public void setImapPassword(String imapPassword) {
		this.imapPassword = imapPassword;
	}

	public String getImapHost() {
		return imapHost;
	}

	public void setImapHost(String imapHost) {
		this.imapHost = imapHost;
	}

	public Integer getImapPort() {
		return imapPort;
	}

	public void setImapPort(Integer imapPort) {
		this.imapPort = imapPort;
	}

	public boolean isImapSSL() {
		return isImapSSL;
	}

	public void setImapSSL(boolean isImapSSL) {
		this.isImapSSL = isImapSSL;
	}
}
