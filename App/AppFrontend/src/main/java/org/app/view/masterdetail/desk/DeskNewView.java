package org.app.view.masterdetail.desk;

import java.util.List;

import org.app.model.entity.Desk;
import org.app.model.entity.Room;
import org.app.service.DeskService;
import org.app.service.RoomService;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class DeskNewView extends Dialog {

	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfDeskNumber;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private DeskService service;
	private RoomService roomService;
	private Desk newEntry;
	private ComboBox<Room> cbxRoom;


	public DeskNewView(DeskView parentView) {
		v18 = new V18();
		service = parentView.getDeskService();
		roomService = parentView.getRoomService();
		newEntry = new Desk();
		saveButton = new Button(v18.getTranslation("basic.save"));
		saveButton.setEnabled(true);
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		cancelButton.setEnabled(true);


		try {
			txfDeskNumber = new TextField(v18.getTranslation("md.desk"));
			add(txfDeskNumber);

			List<Room> list = roomService.getDAO().findAll();
			cbxRoom = new ComboBox<>();
			cbxRoom.setItems(list);
			cbxRoom.setItemLabelGenerator(Room::getEntityValue);
			cbxRoom.setValue(roomService.getDAO().findByID(1));
			FormLayout subContent = new FormLayout();
			subContent.addFormItem(cbxRoom, v18.getTranslation("md.room"));
			add(subContent);

			txaComment = new TextArea(v18.getTranslation("basic.comment"));
			add(txaComment);

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			add(bottomMenuBar);

			saveButton.addClickListener(event -> {
				newEntry.setDeskNumber(txfDeskNumber.getValue());
				newEntry.setRoom(cbxRoom.getValue());
				newEntry.setComment(txaComment.getValue());
				newEntry = service.getDAO().create(newEntry); /*important*/
				parentView.refreshGrid();
				close();
			});

			cancelButton.addClickListener(event -> {
				close();
			});

			addOpenedChangeListener(event -> txfDeskNumber.focus());

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
