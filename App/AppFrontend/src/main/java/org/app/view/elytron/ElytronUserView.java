package org.app.view.elytron;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.app.controler.ElytronRoleService;
import org.app.controler.ElytronUserService;
import org.app.model.entity.ElytronRole;
import org.app.model.entity.ElytronUser;
import org.app.model.entity.enums.DefaultLanguage;
import org.app.model.entity.enums.DefaultTheme;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ElytronUserView", layout = MainLayout.class)
@PageTitle("ElytronUserView")
public class ElytronUserView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "ElytronUserView";

	@Inject
	V18Cdi v18;

	@Inject
	ElytronUserService elytronUserService;

	@Inject
	ElytronRoleService elytronRoleService;

	private ElytronUser selectedEntry;
	private Set<ElytronUser> selectedEntries;
	private TextField txfRolename = new TextField();
	private TextField txfComment = new TextField();
	private ComboBox<ElytronRole> cbxRole = new ComboBox<>();
	private ComboBox<DefaultLanguage> cbxLanguage = new ComboBox<>();
	private ComboBox<DefaultTheme> cbxTheme = new ComboBox<>();
	private Grid<ElytronUser> grid;

	public ElytronUserView() {
	}

	@PostConstruct
	void init() {
		setSizeFull();

		VerticalLayout content = new VerticalLayout();
		selectedEntries = new HashSet<>();
		List<ElytronUser> list = elytronUserService.getDAO().findAllExpanded();
		list.sort(Comparator.comparing(ElytronUser::getId));

		List<ElytronRole> elytronRoleList = elytronRoleService.getDAO().findAll();

		DataProvider<ElytronUser, ?> dataProvider = DataProvider.ofCollection(list);

		grid = new Grid<ElytronUser>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(event -> {
			selectedEntries = event.getAllSelectedItems();
		});

//		grid.getEditor().setEnabled(true);
		grid.getEditor().addSaveListener(event -> {
			selectedEntry = event.getItem();
			updateRow(selectedEntry);
		});

		cbxRole.setPageSize(8);
//		cbxRole.setNullSelectionAllowed(false);
		cbxRole.setItems(elytronRoleList);
		cbxRole.setItemLabelGenerator(ElytronRole::getRolename);

		cbxLanguage.setPageSize(8);
//		cbxLanguage.setRequired(required);
		cbxLanguage.setItems(EnumSet.allOf(DefaultLanguage.class));

		cbxTheme.setPageSize(8);
//		cbxTheme.setEmptySelectionAllowed(false);
		cbxTheme.setItems(EnumSet.allOf(DefaultTheme.class));

		grid.setDataProvider(dataProvider);
//		grid.addColumn(ElytronUser::getUsername).setHeader(v18.getTranslation("account.username")).
//				.setEditorComponent(txfRolename, ElytronUser::setUsername).setId(v18.getTranslation("account.username"));
//		grid.getColumnByKey(v18.getTranslation("account.username")).getEditorComponent().set
//		grid.addColumn(ElytronUser::getElytronRole).setHeader(v18.getTranslation("account.group")).
//				.setRenderer(role -> role != null ? role.getRolename() : null, new TextRenderer())
//				.setEditorComponent(cbxRole, ElytronUser::setElytronRole);
//
//		grid.addColumn(ElytronUser::getDefaultLanguage).setHeader(v18.getTranslation("basic.language")).setEditorComponent(cbxLanguage,
//				ElytronUser::setDefaultLanguage);
//
//		grid.addColumn(ElytronUser::getDefaultTheme).setHeader(v18.getTranslation("basic.theme")).setEditorComponent(cbxTheme,
//				ElytronUser::setDefaultTheme);
//
//		grid.addColumn(ElytronUser::getComment).setHeader(v18.getTranslation("basic.comment"))
//				.setEditorComponent(txfComment, ElytronUser::setComment).setId(v18.getTranslation("basic.comment"));

		grid.addColumn(ElytronUser::getUsername).setHeader(v18.getTranslation("account.username"))
				.setEditorComponent(txfRolename).setId(v18.getTranslation("account.username"));
		grid.addColumn(ElytronUser::getElytronRole).setHeader(v18.getTranslation("account.group"))
				.setEditorComponent(cbxRole);

		grid.addColumn(ElytronUser::getDefaultLanguage).setHeader(v18.getTranslation("basic.language"))
				.setEditorComponent(cbxLanguage);

		grid.addColumn(ElytronUser::getDefaultTheme).setHeader(v18.getTranslation("basic.theme"))
				.setEditorComponent(cbxTheme);

		grid.addColumn(ElytronUser::getComment).setHeader(v18.getTranslation("basic.comment"))
				.setEditorComponent(txfComment).setId(v18.getTranslation("basic.comment"));

		Button delete = new Button("-");
		delete.addClickListener(event -> deleteRow());

		FlexLayout bottomMenuBar = new FlexLayout(delete);
		content.add(grid);
		content.add(bottomMenuBar);
		content.setFlexGrow(1, grid);
		content.setFlexGrow(0, bottomMenuBar);
		content.setSizeFull();
		content.expand(grid);
		add(content);

	}

	private void deleteRow() {
		if (selectedEntries.size() == 0) {
			return;
		}
		for (ElytronUser entry : selectedEntries) {
			if (entry.getId() == 1) {
				Notification.show("Can not delete User NUT - New User Template");
				continue;
			}
			elytronUserService.getDAO().remove(entry.getId());
		}
		refreshGrid();
	}

	public void updateRow(ElytronUser puser) {
		elytronUserService.getDAO().update(puser);
		refreshGrid();
	}

	public void refreshGrid() {
		List<ElytronUser> list = elytronUserService.getDAO().findAllExpanded();
//		grid.getColumnByKey(v18.getTranslation("account.username")).setSortProperty(properties)
//		grid.sort(v18.getTranslation("account.username"), SortDirection.ASCENDING);

		grid.setItems(list);
	}

	public ElytronUserService getElytronUserService() {
		return elytronUserService;
	}

}