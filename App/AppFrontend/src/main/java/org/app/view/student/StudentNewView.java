package org.app.view.student;

import java.time.LocalDate;
import java.util.List;
import org.app.controler.StudentService;
import org.app.model.entity.SgiGroup;
import org.app.model.entity.Student;
import org.app.model.entity.Visor;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class StudentNewView extends Dialog {

	private static final long serialVersionUID = 1L;

	private V18 v18;
	private TextField txfFirstName;
	private TextField txfLastName;
	private TextField txfAccountName;
	private TextField txfPassword;
	private TextField txfMailaddress;
	private ComboBox<Visor> cbxVisor;
	private ComboBox<SgiGroup> cbxSgiGroup = new ComboBox<>();
	private DatePicker dtpStart;
	private DatePicker dtpEnd;
	private TextArea txaComment;
	private Button saveButton;
	private Button cancelButton;
	private StudentService service;
	private Student newEntry;

	@SuppressWarnings("static-access")
	public StudentNewView(StudentView parentView) {
		v18 = new V18();
		service = parentView.getService();
		newEntry = new Student();
		saveButton = new Button(v18.getTranslation("basic.save"));
		cancelButton = new Button(v18.getTranslation("basic.cancel"));
		saveButton.setEnabled(true);

		FormLayout subContent = new FormLayout();
		add(subContent);

		try {
			txfFirstName = new TextField();
			subContent.addFormItem(txfFirstName, v18.getTranslation("person.firstname"));

			txfLastName = new TextField();
			subContent.addFormItem(txfLastName, v18.getTranslation("person.lastname"));

			txfAccountName = new TextField();
			subContent.addFormItem(txfAccountName, v18.getTranslation("account.username"));

			txfPassword = new TextField();
			subContent.addFormItem(txfPassword, v18.getTranslation("account.password"));

			List<Visor> visorList = parentView.getVisorService().getDAO().findAll();
			cbxVisor = new ComboBox<Visor>();
			cbxVisor.setItems(visorList);
			cbxVisor.setItemLabelGenerator(Visor::getEntityValue);
			subContent.addFormItem(cbxVisor, v18.getTranslation("md.visor"));

			List<SgiGroup> sgiGroupList = parentView.getSgiGroupService().getDAO().findAll();
			cbxSgiGroup = new ComboBox<SgiGroup>();
			cbxSgiGroup.setItems(sgiGroupList);
			cbxSgiGroup.setItemLabelGenerator(SgiGroup::getEntityValue);
			subContent.addFormItem(cbxSgiGroup, v18.getTranslation("account.group"));

			txfMailaddress = new TextField();
			subContent.addFormItem(txfMailaddress, v18.getTranslation("basic.email"));

			dtpStart = new DatePicker();
			dtpStart.setValue(LocalDate.now());
			subContent.addFormItem(dtpStart, "Start-" + v18.getTranslation("basic.date"));

			dtpEnd = new DatePicker();
			subContent.addFormItem(dtpEnd, "End-" + v18.getTranslation("basic.date"));

			txaComment = new TextArea();
			subContent.addFormItem(txaComment, v18.getTranslation("basic.comment"));

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);

			saveButton.addClickListener(event -> {
				newEntry.setFirstName(txfFirstName.getValue());
				newEntry.setLastName(txfLastName.getValue());
				newEntry.setAccountName(txfAccountName.getValue());
				newEntry.setInitialPassword(txfPassword.getValue());
				newEntry.setMailaddress(txfMailaddress.getValue());
				newEntry.setStartDate(dtpStart.getValue() != null ? dtpStart.getValue().toString() : "");
				newEntry.setEndDate(dtpEnd.getValue() != null ? dtpEnd.getValue().toString() : "");
				newEntry.setComment(txaComment.getValue());
				newEntry.setVisor(cbxVisor.getValue());
				newEntry.setSgiGroup(cbxSgiGroup.getValue());
				newEntry = service.getDAO().create(newEntry);
				parentView.refreshGrid();
				close();
			});

			cancelButton.addClickListener(event -> {
				close();
			});

			addOpenedChangeListener(event -> txfFirstName.focus());
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
