package org.app.view.masterdetail.desk;

import java.util.List;

import org.app.model.entity.Desk;
import org.app.model.entity.ElytronRole;
import org.app.model.entity.Room;
import org.app.model.entity.enums.DefaultTheme;
import org.app.service.DeskService;
import org.app.service.RoomService;
import org.app.view.V18;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class DeskDetailView extends Dialog {

	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfDeskNumber;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;
	private Button cancelButton;
	private Desk selectedEntry;
	private DeskService service;
	private RoomService roomService;
	private ComboBox<Room> cbxRoom;

	public DeskDetailView(DeskView parentView) {
		v18 = new V18();
		selectedEntry = parentView.getSelectedEntry();
		service = parentView.getDeskService();
		roomService = parentView.getRoomService();
		saveButton = new Button(v18.getTranslation("basic.save"));
		saveButton.setEnabled(false);
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		cancelButton.setEnabled(true);

		FormLayout subContent = new FormLayout();

		try {
			TextField txfID = new TextField();
			txfID.setValue("" + selectedEntry.getId());
			txfID.setReadOnly(true);
			subContent.addFormItem(txfID, "ID");

			TextField txfUUID = new TextField();
			txfUUID.setValue("" + selectedEntry.getUuidEntry());
			txfUUID.setReadOnly(true);
			subContent.addFormItem(txfUUID, "UUID");

			txfDeskNumber = new TextField();
			txfDeskNumber.setValue(selectedEntry.getDeskNumber() != null ? selectedEntry.getDeskNumber() : "");
			subContent.addFormItem(txfDeskNumber, v18.getTranslation("md.desk"));

			List<Room> list = roomService.getDAO().findAll();
			cbxRoom = new ComboBox<>();
			cbxRoom.setItems(list);
			cbxRoom.setItemLabelGenerator(Room::getEntityValue);
			cbxRoom.setValue(selectedEntry.getRoom());
			subContent.addFormItem(cbxRoom, v18.getTranslation("md.room"));
			
			txaComment = new TextArea();
			txaComment.setValue(selectedEntry.getComment() != null ? selectedEntry.getComment() : "");
			subContent.addFormItem(txaComment, v18.getTranslation("basic.comment"));

			ckbEdit = new Checkbox(v18.getTranslation("basic.edit"));
			ckbEdit.addValueChangeListener(event -> {
				if (event.getValue()) {
					saveButton.setEnabled(true);
				} else {
					saveButton.setEnabled(false);
				}
			});

			subContent.add(ckbEdit);

			saveButton.setEnabled(false);
			subContent.add(saveButton);

			saveButton.addClickListener(event -> {
				selectedEntry.setDeskNumber(txfDeskNumber.getValue());
				selectedEntry.setRoom(cbxRoom.getValue());
				selectedEntry.setComment(txaComment.getValue());
				selectedEntry = service.getDAO().update(selectedEntry); /*important*/
				parentView.updateRow(selectedEntry);
				parentView.refreshGrid();
				close();
			});

			cancelButton.addClickListener(event -> {
				close();
			});

			addOpenedChangeListener(event -> txfDeskNumber.focus());

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);
			add(subContent);
			setWidth("400px");

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
