package org.app.view.masterdetail.room;

import java.util.List;

import org.app.model.entity.Room;
import org.app.model.entity.Room_AUD;
import org.app.service.RoomService;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class RoomAuditView extends Dialog {

	private static final long serialVersionUID = 1L;
	private V18 v18;
	private RoomService service;
	private Button cancelButton;
	
	public RoomAuditView(RoomView parentView, Room selectedEntry) {
		v18 = new V18();
		service = parentView.getService();

		/**
		 * Text & Cancel
		 */
		Div captionText = new Div();
		captionText.setText("Auditing");

		cancelButton = new Button(" X ");
		cancelButton.addClickListener(event -> {
			close();
		});
		cancelButton.setClassName("alignright");
		
		HorizontalLayout topBar = new HorizontalLayout(captionText, cancelButton);
		topBar.setHeight("10%");
		topBar.setWidth("100%");

		Grid<Room_AUD> grid = new Grid<Room_AUD>();
		grid.setHeight("90%");
		grid.setWidth("100%");
		List<Room_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		DataProvider<Room_AUD, ?> dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);
		grid.setSelectionMode(SelectionMode.MULTI);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getListPrio();
		}).setHeader(v18.getTranslation("basic.listprio")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getEntityValue();
		}).setHeader(v18.getTranslation("md.visor")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevision().getRevisionDate();
		}).setHeader(v18.getTranslation("basic.date")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevision().getElytronUser().getUsername();
		}).setHeader(v18.getTranslation("account.loggedinuser")).setResizable(true);

		grid.addColumn(aud -> {
			return "" + aud.getRevType();
		}).setHeader(v18.getTranslation("basic.type")).setResizable(true);

		setHeight("800px");
		setWidth("1800px");
		add(topBar);
		add(grid);
	}

}
