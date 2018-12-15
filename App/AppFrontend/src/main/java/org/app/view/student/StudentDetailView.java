package org.app.view.student;

import java.time.LocalDate;
import java.util.List;

import org.app.controler.StudentService;
import org.app.model.entity.SgiGroup;
import org.app.model.entity.Student;
import org.app.model.entity.Visor;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class StudentDetailView extends Dialog {
	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfFirstName;
	private TextField txfLastName;
	private TextField txfAccountName;
	private TextField txfPassword;
	private TextField txfMailaddress;
	private DatePicker dtpStart;
	private DatePicker dtpEnd;
	private ComboBox<Visor> cbxVisor;
	private ComboBox<SgiGroup> cbxSgiGroup;
	private Checkbox ckbEdit;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private Student selectedEntry;
	private StudentService service;

	public StudentDetailView(StudentView parentView) {
		v18 = new V18();
		selectedEntry = parentView.getSelectedEntry();
		service = parentView.getService();
		saveButton = new Button(v18.getTranslation("basic.save"));
		saveButton.setEnabled(false);
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		cancelButton.setEnabled(true);

		FormLayout subContent = new FormLayout();

		try {
			service.setEditing(false);

			TextField txfID = new TextField();
			txfID.setValue("" + selectedEntry.getId());
			txfID.setReadOnly(true);
			subContent.addFormItem(txfID, "ID");

			TextField txfUUID = new TextField();
			txfUUID.setValue("" + selectedEntry.getUuidEntry());
			txfUUID.setReadOnly(true);
			subContent.addFormItem(txfUUID, "UUID");

			txfFirstName = new TextField();
			txfFirstName.setValue(selectedEntry.getFirstName() != null ? selectedEntry.getFirstName() : "");
			subContent.addFormItem(txfFirstName, v18.getTranslation("person.firstname"));

			txfLastName = new TextField();
			txfLastName.setValue(selectedEntry.getLastName() != null ? selectedEntry.getLastName() : "");
			subContent.addFormItem(txfLastName, v18.getTranslation("person.lastname"));

			txfAccountName = new TextField();
			txfAccountName.setValue(selectedEntry.getAccountName() != null ? selectedEntry.getAccountName() : "");
			subContent.addFormItem(txfAccountName, v18.getTranslation("account.username"));

			txfPassword = new TextField();
			txfPassword.setValue(selectedEntry.getInitialPassword() != null ? selectedEntry.getInitialPassword() : "");
			subContent.addFormItem(txfPassword, v18.getTranslation("account.password"));

			List<Visor> visorList = parentView.getVisorService().getDAO().findAll();
			cbxVisor = new ComboBox<>();
			cbxVisor.setItems(visorList);
			cbxVisor.setItemLabelGenerator(Visor::getEntityValue);
			cbxVisor.setValue(selectedEntry.getVisor());
			subContent.addFormItem(cbxVisor, v18.getTranslation("md.visor"));

			List<SgiGroup> sgiGroupList = parentView.getSgiGroupService().getDAO().findAll();
			cbxSgiGroup = new ComboBox<>();
			cbxSgiGroup.setItems(sgiGroupList);
			cbxSgiGroup.setItemLabelGenerator(SgiGroup::getEntityValue);
			cbxSgiGroup.setValue(selectedEntry.getSgiGroup());
			subContent.add(cbxSgiGroup);
			subContent.addFormItem(cbxSgiGroup, v18.getTranslation("account.group"));

			txfMailaddress = new TextField();
			txfMailaddress.setValue(selectedEntry.getMailaddress() != null ? selectedEntry.getMailaddress() : "");
			subContent.add(txfMailaddress);
			subContent.addFormItem(txfMailaddress, v18.getTranslation("basic.email"));

			dtpStart = new DatePicker();
			String tmpStart = selectedEntry.getStartDate() != null ? selectedEntry.getStartDate() : "";
//			LocalDate tmpStartDate = LocalDate.parse(tmpStart) != null ? LocalDate.parse(tmpStart) : null;
			dtpStart.setValue(parseDate(tmpStart));
			subContent.addFormItem(dtpStart, "Start-" + v18.getTranslation("basic.date"));

			dtpEnd = new DatePicker();
			String tmpEnd = selectedEntry.getEndDate() != null ? selectedEntry.getEndDate() : "";
//			LocalDate tmpEndDate = LocalDate.parse(tmpEnd) != null ? LocalDate.parse(tmpEnd) : null;
			dtpEnd.setValue(parseDate(tmpEnd));
			subContent.addFormItem(dtpEnd, "End-" + v18.getTranslation("basic.date"));

			txaComment = new TextArea();
			txaComment.setValue(selectedEntry.getComment() != null ? selectedEntry.getComment() : "");
			subContent.addFormItem(txaComment, v18.getTranslation("basic.comment"));

			ckbEdit = new Checkbox(v18.getTranslation("basic.edit"));
			ckbEdit.addValueChangeListener(event -> {
				service.toggleEditing();
				if (event.getValue()) {
					saveButton.setEnabled(true);
				} else {
					saveButton.setEnabled(false);
				}
			});
			subContent.add(ckbEdit);

			saveButton.addClickListener(event -> {
				selectedEntry.setFirstName(txfFirstName.getValue());
				selectedEntry.setLastName(txfLastName.getValue());
				selectedEntry.setAccountName(txfAccountName.getValue());
				selectedEntry.setInitialPassword(txfPassword.getValue());
				selectedEntry.setVisor(cbxVisor.getValue());
				selectedEntry.setSgiGroup(cbxSgiGroup.getValue());
				selectedEntry.setMailaddress(txfMailaddress.getValue());
				selectedEntry.setStartDate(dtpStart.getValue() != null ? dtpStart.getValue().toString() : "");
				selectedEntry.setEndDate(dtpEnd.getValue() != null ? dtpEnd.getValue().toString() : "");
				selectedEntry.setComment(txaComment.getValue());
				selectedEntry = service.getDAO().update(selectedEntry); /*important*/
				parentView.updateRow(selectedEntry);
				parentView.refreshGrid();
				close();
			});

			cancelButton.addClickListener(event -> {
				close();
			});
			
			addOpenedChangeListener(event -> txfFirstName.focus());


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
	
	private LocalDate parseDate(final String in) {
	    if (in.isEmpty()) { 
	        return null; 
	    }
	    return LocalDate.parse(in);
	}

}
