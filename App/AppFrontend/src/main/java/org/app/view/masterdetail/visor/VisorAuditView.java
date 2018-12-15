package org.app.view.masterdetail.visor;

import java.util.List;

import org.app.controler.VisorService;
import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class VisorAuditView extends Dialog {

	private static final long serialVersionUID = 1L;
	private V18 v18;
	private VisorService service;
	private Visor selectedEntry;
	private Button cancelButton;
	
	public VisorAuditView(VisorView parentView) {
		v18 = new V18();
		service = parentView.getService();
		selectedEntry = parentView.getSelectedEntry();

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

		Grid<Visor_AUD> grid = new Grid<Visor_AUD>();
		grid.setHeight("90%");
		grid.setWidth("100%");
		List<Visor_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		DataProvider<Visor_AUD, ?> dataProvider = DataProvider.ofCollection(list);
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
