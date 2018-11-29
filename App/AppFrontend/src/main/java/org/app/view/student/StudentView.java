package org.app.view.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.app.controler.SgiGroupService;
import org.app.controler.StudentService;
import org.app.controler.VisorService;
import org.app.model.entity.ElytronUser;
import org.app.model.entity.Student;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import org.app.view.masterdetail.MasterDetail;

import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.icons.VaadinIcons;

@Route(value = "StudentView", layout = MainLayout.class)
@PageTitle("StudentView")
public class StudentView extends VerticalLayout {

	@Inject
	V18Cdi v18;

	@Inject
	StudentService service;

	@Inject
	VisorService visorService;

	@Inject
	SgiGroupService sgiGroupService;

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "StudentView";

	private ListDataProvider<Student> dataProvider;
	private Student selectedEntry;
	private Set<Student> selectedEntries;
	private TextField newValueField = new TextField();
	private TextField newCommentField = new TextField();

	private Grid<Student> grid;
	private FlexLayout bottomMenuBar;

	public StudentView() {

	}

	@PostConstruct
	void init() {
		VerticalLayout content = new VerticalLayout();

		selectedEntries = new HashSet<>();
		List<Student> list = new ArrayList<Student>();

		if (service.getDAO().findAll() != null) {
			list = service.getDAO().findAll();
		} else {
			Notification.show("No Student available");
			Student tmp = new Student();
			tmp.setFirstName("<unknown>");
			tmp.setLastName("<unknown>");
			tmp.setAccountName("<unknown>");
			list.add(tmp);
		}
		list.sort(Comparator.comparing(Student::getId));

		dataProvider = DataProvider.ofCollection(list);

		grid = new Grid<Student>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(event -> {
			selectedEntries = event.getAllSelectedItems();
		});

		grid.getEditor().addSaveListener(event -> {
			selectedEntry = event.getItem();
			updateRow(selectedEntry);
		});
		grid.getEditor().addCancelListener(event -> {
			refreshGrid();
		});
		
		Binder<Student> binder = new Binder<>(Student.class);
		Editor<Student> editor = grid.getEditor();
		editor.setBinder(binder);
		editor.setBuffered(true);
		
		TextField txfFirstName = new TextField();
		binder.forField(txfFirstName).bind(Student::getFirstName, Student::setFirstName);
		txfFirstName.getElement()
        .addEventListener("keydown", event -> grid.getEditor().cancel())
        .setFilter("event.key === 'Tab' && event.shiftKey");

		TextField txfLastName = new TextField();
		binder.forField(txfLastName).bind(Student::getLastName, Student::setLastName);

		TextField txfAccountName = new TextField();
		binder.forField(txfAccountName).bind(Student::getAccountName, Student::setAccountName);
		
		grid.addItemDoubleClickListener(
		        event -> grid.getEditor().editItem(event.getItem()));
		
		Button save = new Button("Save", e -> editor.save());
		save.addClassName("save");

		Button cancel = new Button("Cancel", e -> editor.cancel());
		cancel.addClassName("cancel");

		grid.setDataProvider(dataProvider);
		grid.addColumn(Student::getFirstName).setHeader(v18.getTranslation("person.firstname")).setEditorComponent(txfFirstName);
		grid.addColumn(Student::getLastName).setHeader(v18.getTranslation("person.lastname")).setEditorComponent(txfLastName);
		grid.addColumn(Student::getAccountName).setHeader(v18.getTranslation("account.username")).setEditorComponent(txfAccountName);
		grid.addColumn(Student::getInitialPassword).setHeader(v18.getTranslation("account.password"));
		grid.addColumn(Student::getMailaddress).setHeader(v18.getTranslation("basic.email"));
		grid.addColumn(Student::getStartDate).setHeader("Start " + v18.getTranslation("basic.date"));
		grid.addColumn(Student::getEndDate).setHeader("End " + v18.getTranslation("basic.date"));
		grid.addColumn(Student::getComment).setHeader(v18.getTranslation("basic.comment"));

		Button add = new Button("+");
		add.addClickListener(event -> {
			StudentNewView dialog = new StudentNewView(this);
			dialog.open();
		});

		Button delete = new Button("-");
		delete.addClickListener(event -> deleteRow());

		Button detail = new Button("", VaadinIcon.PENCIL.create());
		detail.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Student entry : selectedEntries) {
					selectedEntry = entry;
					StudentDetailView detailView = new StudentDetailView(this, selectedEntry);
					detailView.open();
				}
				refreshGrid();
			}
		});

		Button aud = new Button("", VaadinIcon.ELLIPSIS_V.create());
		aud.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Student entry : selectedEntries) {
					selectedEntry = entry;
					StudentAuditView detailView = new StudentAuditView(selectedEntry, service);
					detailView.open();
				}
				refreshGrid();
			}
		});

		bottomMenuBar = new FlexLayout(add, delete, detail, aud);

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
			Notification.show(v18.getTranslation("notification.noItem"));
			return;
		}
		for (Student entry : selectedEntries) {
			service.getDAO().remove(entry.getId());
		}
		refreshGrid();
	}

	public void updateRow(Student entry) {
		service.getDAO().update(entry);
		refreshGrid();
	}

	public void refreshGrid() {
		List<Student> list = service.getDAO().findAll();
//		grid.setSortProperty(v18.getTranslation("basic.listprio", loc));
//		grid.sort(v18.getTranslation("basic.listprio", loc), SortDirection.ASCENDING);
		grid.setItems(list);
	}

	private boolean onlyOneSelected(Set<Student> selected) {
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

	public StudentService getService() {
		return service;
	}

	public VisorService getVisorService() {
		return visorService;
	}

	public SgiGroupService getSgiGroupService() {
		return sgiGroupService;
	}

}