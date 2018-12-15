package org.app.view.masterdetail.desk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.app.controler.DeskService;
import org.app.controler.RoomService;
import org.app.model.entity.Desk;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;

public class DeskView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "DeskView";

	private V18 v18;
	private DeskService service;
	private RoomService roomService;
	private ListDataProvider<Desk> dataProvider;
	private Desk selectedEntry;
	private Set<Desk> selectedEntries;
	private Grid<Desk> grid;
	private TextField txfRoom;
	private FlexLayout bottomMenuBar;

	public DeskView(DeskService dService, RoomService rService) {
		service = dService;
		roomService = rService;
		v18 = new V18();

		List<Desk> list = service.getDAO().findAllExpanded();
		selectedEntries = new HashSet<>();
		dataProvider = DataProvider.ofCollection(list);
		dataProvider.setSortOrder(Desk::getDeskNumber, SortDirection.ASCENDING);

		grid = new Grid<>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(event -> {
			selectedEntries = event.getAllSelectedItems();
			if (selectedEntries.size() == 1) {
				for (Desk entry : selectedEntries) {
					selectedEntry = entry;
				}
			} else {
				selectedEntry = null;
			}
		});

		grid.setDataProvider(dataProvider);
		grid.addColumn(Desk::getDeskNumber).setHeader(v18.getTranslation("md.desk"));

		grid.addComponentColumn(desk -> {
			txfRoom = new TextField();
			txfRoom.setValue(desk.getRoom().getEntityValue());
			return txfRoom;
		}).setHeader(v18.getTranslation("md.room"));

		grid.addColumn(Desk::getComment).setHeader(v18.getTranslation("basic.comment"));

		Button add = new Button("+");
		add.addClickListener(event -> {
			DeskNewView dialog = new DeskNewView(this);
			dialog.open();
		});

		Button delete = new Button("-");
		delete.addClickListener(event -> deleteRow());

		Button detail = new Button("", VaadinIcon.PENCIL.create());
		detail.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Desk entry : selectedEntries) {
					selectedEntry = entry;
					DeskDetailView detailView = new DeskDetailView(this);
					detailView.open();
				}
				refreshGrid();
			}
		});

		Button aud = new Button("", VaadinIcon.ELLIPSIS_V.create());
		aud.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Desk entry : selectedEntries) {
					selectedEntry = entry;
					DeskAuditView detailView = new DeskAuditView(this);
					detailView.open();
				}
				refreshGrid();
			}
		});

		bottomMenuBar = new FlexLayout(add, delete, detail, aud);

		add(grid);
		add(bottomMenuBar);
		setSizeFull();
		expand(grid);

	}

	private void deleteRow() {
		if (selectedEntries.size() == 0) {
			Notification.show(v18.getTranslation("notification.noItem"));
			return;
		}
		for (Desk entry : selectedEntries) {
			service.getDAO().remove(entry.getId());
		}
		refreshGrid();
	}

	public void updateRow(Desk entry) {
		service.getDAO().update(entry);
		refreshGrid();
	}

	public void refreshGrid() {
		List<Desk> list = service.getDAO().findAllExpanded();

		dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);

	}

	private boolean onlyOneSelected(Set<Desk> selected) {
		boolean isCorrect = true;
		if (selected.size() > 1) {
			Notification.show(v18.getTranslation("notification.exactOneItem"));
			isCorrect = false;
		}
		if (selected.size() < 1) {
			Notification.show(v18.getTranslation("notification.minimumOneItem"));
			isCorrect = false;
		}
		return isCorrect;

	}

	public DeskService getDeskService() {
		return service;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public Desk getSelectedEntry() {
		return selectedEntry;
	}

}