package org.app.view.student;

import java.util.List;
import org.app.controler.StudentService;
import org.app.model.entity.SgiGroup;
import org.app.model.entity.Student;
import org.app.model.entity.Visor;
import org.app.view.V18;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

		VerticalLayout subContent = new VerticalLayout();
		add(subContent);

		try {
			txfFirstName = new TextField(v18.getTranslation("person.lastname"));
			subContent.add(txfFirstName);

			txfLastName = new TextField(v18.getTranslation("person.lastname"));
			subContent.add(txfLastName);

			txfAccountName = new TextField(v18.getTranslation("account.username"));
			subContent.add(txfAccountName);

			txfPassword = new TextField(v18.getTranslation("account.password"));
			subContent.add(txfPassword);
			
			List<Visor> visorList = parentView.getVisorService().getDAO().findAll();
			cbxVisor = new ComboBox<Visor>(v18.getTranslation("basic.visor"));
			cbxVisor.setItems(visorList);
			cbxVisor.setItemLabelGenerator(Visor::getEntityValue);
			subContent.add(cbxVisor);
			
			List<SgiGroup> sgiGroupList = parentView.getSgiGroupService().getDAO().findAll();
			cbxSgiGroup = new ComboBox<SgiGroup>(v18.getTranslation("account.group"));
			cbxSgiGroup.setItems(sgiGroupList);
			cbxSgiGroup.setItemLabelGenerator(SgiGroup::getEntityValue);
			subContent.add(cbxSgiGroup);

			txfMailaddress = new TextField(v18.getTranslation("basic.email"));
			subContent.add(txfMailaddress);

			txaComment = new TextArea(v18.getTranslation("basic.comment"));
			subContent.add(txaComment);

			Div bottomMenuBar = new Div(saveButton, cancelButton);
			subContent.add(bottomMenuBar);
			
			saveButton.addClickListener(event -> {
				if ((txfAccountName .getValue()).isEmpty()) {
					this.close();
				}
				newEntry.setFirstName(txfFirstName.getValue());
				newEntry.setLastName(txfLastName.getValue());
				newEntry.setAccountName(txfAccountName.getValue());
				newEntry.setInitialPassword(txfPassword.getValue());
				newEntry.setMailaddress(txfMailaddress.getValue());
				newEntry.setComment(txaComment.getValue());				
				newEntry.setVisor(cbxVisor.getValue());
				newEntry.setSgiGroup(cbxSgiGroup.getValue());

				service.getDAO().create(newEntry);
				parentView.refreshGrid();
				close();
			});
			
			cancelButton.addClickListener(event -> {
				this.close();
			});

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
