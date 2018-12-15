package org.app.view.masterdetail.desk;

import java.util.List;

import javax.annotation.PostConstruct;

import org.app.controler.DeskService;
import org.app.model.entity.Desk;
import org.app.model.entity.Desk_AUD;
import org.app.model.entity.Room;
import org.app.view.V18;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class DeskAuditView extends Dialog {

	private static final long serialVersionUID = 1L;
	private V18 v18;
	private Button cancelButton;
	private Desk selectedEntry;
	private DeskService service;
	private Grid<Desk_AUD> grid;

	public DeskAuditView(DeskView parentView) {
		v18 = new V18();
		selectedEntry = parentView.getSelectedEntry();
		service = parentView.getDeskService();

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

		grid = new Grid<>();
		grid.setHeight("90%");
		grid.setWidth("100%");
		List<Desk_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		int tmp = list.size();
		System.out.println("Größßße: " + tmp);
		DataProvider<Desk_AUD, ?> dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);
		grid.setSelectionMode(SelectionMode.MULTI);

		grid.addColumn(aud -> {
			return "" + aud.getReventity().getDeskNumber();
		}).setHeader(v18.getTranslation("md.desk")).setResizable(true);

//		grid.addColumn(aud -> {
//			return "" + aud.getReventity().getRoom();
//		}).setHeader(v18.getTranslation("md.room")).setResizable(true);

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
