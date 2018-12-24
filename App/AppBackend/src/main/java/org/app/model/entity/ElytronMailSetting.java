package org.app.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = ElytronMailSetting.QUERY_FIND_ALL, query = "SELECT c FROM ElytronMailSetting c"),
		@NamedQuery(name = ElytronMailSetting.QUERY_FIND_ALL_EXPANDED, query = "SELECT c FROM ElytronMailSetting c join fetch c.elytronUser"),
		@NamedQuery(name = ElytronMailSetting.QUERY_FIND_BY_ELYTRONUSER, query = "SELECT c FROM ElytronMailSetting c WHERE c.elytronUser =  :elytronUser") })
public class ElytronMailSetting extends Superclass implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "ElytronMailSetting.FindAll";
	public static final String QUERY_FIND_ALL_EXPANDED = "ElytronMailSetting.FindAllExpanded";
	public static final String QUERY_FIND_BY_ELYTRONUSER = "ElytronMailSetting.FindByElytronUser";

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

	@OneToOne
	private ElytronUser elytronUser;

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

	public ElytronUser getElytronUser() {
		return elytronUser;
	}

	public void setElytronUser(ElytronUser elytronUser) {
		this.elytronUser = elytronUser;
	}
}
