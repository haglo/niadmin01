package org.app.view.masterdetail.visor;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.app.model.entity.Desk;
import org.app.model.entity.Student;
import org.app.model.entity.Visor;
import org.app.service.VisorService;
import org.app.view.V18;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;

public class VisorView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "VisorView";

	private V18 v18;
	private VisorService service;
	private ListDataProvider<Visor> dataProvider;
	private Visor selectedEntry;
	private Visor prevEntry;
	private Visor nextEntry;
	private Set<Visor> selectedEntries;
	private Grid<Visor> grid;
	private FlexLayout bottomMenuBar;

	public VisorView(VisorService srv) {
		service = srv;
		v18 = new V18();

		List<Visor> list = service.getDAO().findAll();
		selectedEntries = new HashSet<>();
		dataProvider = DataProvider.ofCollection(list);
		dataProvider.setSortOrder(Visor::getListPrio, SortDirection.ASCENDING);

		grid = new Grid<>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addSelectionListener(event -> {
			selectedEntries = event.getAllSelectedItems();
			if (selectedEntries.size() == 1) {
				for (Visor entry : selectedEntries) {
					selectedEntry = entry;
				}
			} else {
				selectedEntry = null;
			}
		});

		grid.setDataProvider(dataProvider);
		grid.addColumn(Visor::getListPrio).setHeader(v18.getTranslation("basic.listprio"));
		grid.addColumn(Visor::getEntityValue).setHeader(v18.getTranslation("md.visor"));
		grid.addColumn(Visor::getComment).setHeader(v18.getTranslation("basic.comment"));

		Button add = new Button("+");
		add.addClickListener(event -> {
			VisorNewView dialog = new VisorNewView(this);
			dialog.open();
		});

		Button delete = new Button("-");
		delete.addClickListener(event -> deleteRow());

		Button up = new Button("", VaadinIcon.ARROW_UP.create());
		up.addClickListener(event -> upRow());

		Button top = new Button("", VaadinIcon.UPLOAD_ALT.create());
		top.addClickListener(event -> topRow());

		Button down = new Button("", VaadinIcon.ARROW_DOWN.create());
		down.addClickListener(event -> downRow());

		Button bottom = new Button("", VaadinIcon.DOWNLOAD_ALT.create());
		bottom.addClickListener(event -> bottomRow());

		Button detail = new Button("", VaadinIcon.PENCIL.create());
		detail.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Visor entry : selectedEntries) {
					selectedEntry = entry;
					VisorDetailView detailView = new VisorDetailView(this);
					detailView.open();
				}
				refreshGrid();
			}
		});

		Button aud = new Button("", VaadinIcon.ELLIPSIS_V.create());
		aud.addClickListener(event -> {
			if (onlyOneSelected(selectedEntries)) {
				for (Visor entry : selectedEntries) {
					selectedEntry = entry;
					VisorAuditView detailView = new VisorAuditView(this);
					detailView.open();
				}
				refreshGrid();
			}
		});

		bottomMenuBar = new FlexLayout(add, delete, up, top, down, bottom, detail, aud);

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
		for (Visor entry : selectedEntries) {
			service.getDAO().remove(entry.getId());
		}
		refreshGrid();
	}

	private void upRow() {
		prevEntry = new Visor();

		if (!onlyOneSelected(selectedEntries)) {
			return;
		}

		for (Visor entry : selectedEntries) {
			selectedEntry = entry;
			if (selectedEntry.getListPrio() == 0) {
				Notification.show(v18.getTranslation("notification.onTop"));
				return;
			}
			prevEntry = (Visor) service.getDAO().findByPriority(selectedEntry.getListPrio() - 1).get(0);

			selectedEntry.setListPrio(selectedEntry.getListPrio() - 1);
			prevEntry.setListPrio(prevEntry.getListPrio() + 1);

			service.getDAO().update(selectedEntry);
			service.getDAO().update(prevEntry);
		}
		refreshGrid();
	}

	private void topRow() {
		if (!onlyOneSelected(selectedEntries)) {
			return;
		}

		for (Visor entry : selectedEntries) {
			selectedEntry = entry;
		}

		if (selectedEntry.getListPrio() == 1) {
			Notification.show(v18.getTranslation("notification.onTop"));
			return;
		}

		List<Visor> list = service.getDAO().findAll();
		for (Visor tmp : list) {
			if (tmp.getListPrio() < selectedEntry.getListPrio()) {
				tmp.setListPrio(tmp.getListPrio() + 1);
			}
			service.getDAO().update(tmp);
		}
		selectedEntry.setListPrio(1);
		service.getDAO().update(selectedEntry);
		refreshGrid();
	}

	private void downRow() {
		nextEntry = new Visor();

		if (!onlyOneSelected(selectedEntries)) {
			return;
		}

		for (Visor entry : selectedEntries) {
			selectedEntry = entry;
		}

		if (selectedEntry.getListPrio() == getMaxListPrio()) {
			Notification.show(v18.getTranslation("notification.onBottom"));
			return;
		}
		nextEntry = (Visor) service.getDAO().findByPriority(selectedEntry.getListPrio() + 1).get(0);
		selectedEntry.setListPrio(selectedEntry.getListPrio() + 1);
		nextEntry.setListPrio(nextEntry.getListPrio() - 1);
		service.getDAO().update(selectedEntry);
		service.getDAO().update(nextEntry);
		refreshGrid();
	}

	private void bottomRow() {
		int maxListPrio = 0;

		if (!onlyOneSelected(selectedEntries)) {
			return;
		}

		for (Visor entry : selectedEntries) {
			selectedEntry = entry;
		}

		maxListPrio = getMaxListPrio();
		if (selectedEntry.getListPrio() == maxListPrio) {
			Notification.show(v18.getTranslation("notification.onBottom"));
			return;
		}

		List<Visor> list = service.getDAO().findAll();
		for (Visor tmp : list) {
			if (tmp.getListPrio() > selectedEntry.getListPrio()) {
				tmp.setListPrio(tmp.getListPrio() - 1);
			}
			service.getDAO().update(tmp);
		}
		selectedEntry.setListPrio(maxListPrio);
		service.getDAO().update(selectedEntry);
		refreshGrid();
	}

	public void updateRow(Visor entry) {
		service.getDAO().update(entry);
		refreshGrid();
	}

	public void refreshGrid() {
		List<Visor> list = service.getDAO().findAll();

		Collections.sort(list, new Comparator<Visor>() {
			@Override
			public int compare(Visor o1, Visor o2) {
				// TODO Auto-generated method stub
				return o2.getListPrio().compareTo(o1.getListPrio());
			}
		});
		dataProvider = DataProvider.ofCollection(list);
		dataProvider.setSortOrder(Visor::getListPrio, SortDirection.ASCENDING);
		grid.setDataProvider(dataProvider);

//		grid.setSortProperty(v18.getTranslation("basic.listprio"));
//		grid.sort(v18.getTranslation("basic.listprio"), SortDirection.ASCENDING);
//		grid.setItems(list);
	}

	public int getMaxListPrio() {
		int maxListPrio;
		List<Visor> list = service.getDAO().findAll();
		maxListPrio = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getListPrio() > maxListPrio) {
				maxListPrio = list.get(i).getListPrio();
			}
		}
		return maxListPrio;
	}

	private boolean onlyOneSelected(Set<Visor> selected) {
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

	public VisorService getService() {
		return service;
	}

	public Visor getSelectedEntry() {
		return selectedEntry;
	}

}