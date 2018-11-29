package org.app.view.dbaccount;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.app.controler.DbAccountService;

import org.app.model.entity.DbAccount;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;

import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "DbAccountView", layout = MainLayout.class)
@PageTitle("DbAccountView")
public class DbAccountView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "DbAccountView";

	@Inject
	V18Cdi v18;

	@Inject
	DbAccountService accountService;

	private DbAccount account;
	private DbAccount selectedAccount;
	private Set<DbAccount> selectedAccounts;
	private TextField firstEntryField = new TextField();
	private TextField txfPassword = new TextField();
	private Grid<DbAccount> grid;

	public DbAccountView() {

	}

	@PostConstruct
	void init() {
		setSizeFull();

		VerticalLayout content = new VerticalLayout();
		selectedAccounts = new HashSet<>();
		List<DbAccount> accountList = accountService.findAll();

		DataProvider<DbAccount, ?> dataProvider = DataProvider.ofCollection(accountList);
		grid = new Grid<DbAccount>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(event -> {
			selectedAccounts = event.getAllSelectedItems();
		});

		grid.getEditor().addSaveListener(event -> {
			account = event.getItem();
			updateRow(account);
		});

		grid.setDataProvider(dataProvider);

//		grid.addColumn(Account::getUsername).setHeader(v18.getTranslation("account.username"))
//				.setEditorComponent(firstEntryField, Account::setUsername).setId(v18.getTranslation("account.username"));
//		grid.addColumn(Account::getPassword).setHeader(v18.getTranslation("account.password")).setEditorComponent(txfPassword,
//				Account::setPassword);

		grid.addColumn(DbAccount::getUsername).setHeader(v18.getTranslation("account.username"))
				.setEditorComponent(firstEntryField).setId(v18.getTranslation("account.username"));
		grid.addColumn(DbAccount::getPassword).setHeader(v18.getTranslation("account.password"))
				.setEditorComponent(txfPassword);

		Button add = new Button("+");
		add.addClickListener(event -> {
			DbAccountNewView dialog = new DbAccountNewView(this);
			dialog.open();
		});

		Button delete = new Button("-");
		delete.addClickListener(event -> deleteRow());

		Button detail = new Button("", ev -> {
			if (onlyOneSelected(selectedAccounts)) {
				for (DbAccount entry : selectedAccounts) {
					selectedAccount = entry;
					DbAccountDetailView dialog = new DbAccountDetailView(this, selectedAccount);
					dialog.open();
				}
			}
		});
		detail.setIcon(VaadinIcon.PENCIL.create());

		FlexLayout bottomMenuBar = new FlexLayout(add, delete, detail);
		content.add(grid);
		content.add(bottomMenuBar);
		content.setFlexGrow(1, grid);
		content.setFlexGrow(0, bottomMenuBar);
		content.setSizeFull();
		content.expand(grid);
		add(content);

	}

	private void deleteRow() {
		if (selectedAccounts.size() == 0) {
			return;
		}
		for (DbAccount entry : selectedAccounts) {
			accountService.remove(entry.getId());
		}
		refreshGrid();
	}

	public void updateRow(DbAccount account) {
		accountService.update(account);
		refreshGrid();
	}

	public void refreshGrid() {
		List<DbAccount> list = accountService.findAll();
//		grid.sort(i18n.ACCOUNT_USERNAME, SortDirection.ASCENDING);
		grid.setItems(list);
	}

	public DbAccountService getAccountService() {
		return accountService;
	}

	private boolean onlyOneSelected(Set<DbAccount> selected) {
		boolean isCorrect = true;
		if (selected.size() > 1) {
			Notification.show(v18.getTranslation("notification.onlyOneItem"));
			isCorrect = false;
		}
		if (selected.size() < 1) {
			Notification.show(v18.getTranslation("notification.exactOneItem"));
			isCorrect = false;
		}
		return isCorrect;

	}
}