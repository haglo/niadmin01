package org.app.view.masterdetail.visor;

import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.app.controler.VisorService;
import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;
import org.app.view.V18;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;

public class VisorAuditView extends Dialog {
	@Inject
	V18 v18;

	private static final long serialVersionUID = 1L;
	private Locale loc;

	@SuppressWarnings("static-access")
	public VisorAuditView(Visor selectedEntry, VisorService service) {
		loc = new Locale("de", "DE");

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		Grid<Visor_AUD> grid = new Grid<Visor_AUD>();
		List<Visor_AUD> list = service.getDAO().findAudById(selectedEntry.getId());
		DataProvider<Visor_AUD, ?> dataProvider = DataProvider.ofCollection(list);
		grid.setDataProvider(dataProvider);
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.MULTI);

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getListPrio();
			return result;
		}).setHeader(v18.getTranslation("basic.listprio", loc));

		grid.addColumn(aud -> {
			String result = "" + aud.getReventity().getMdValue();
			return result;
		}).setHeader(v18.getTranslation("title.value", loc));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevision().getRevisionDate();
			return result;
		}).setHeader(v18.getTranslation("basic.date", loc));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevision().getElytronUser().getUsername();
			return result;
		}).setHeader(v18.getTranslation("account.username", loc));

		grid.addColumn(aud -> {
			String result = "" + aud.getRevType();
			return result;
		}).setHeader(v18.getTranslation("basic.type", loc));

		subContent.add(grid);
		this.setWidth("80%");

	}

}
