package org.app.view.student;

import java.util.Locale;
import javax.inject.Inject;

import org.app.controler.StudentService;
import org.app.controler.VisorService;
import org.app.model.entity.Student;
import org.app.model.entity.Visor;
import org.app.view.V18Cdi;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class StudentDetailView extends Dialog {
	@Inject
	V18Cdi v18;

	private static final long serialVersionUID = 1L;

	private TextField txfListPrio;
	private TextField txfValue;
	private TextArea txaComment;
	private Checkbox ckbEdit;
	private Button saveButton;
	private Locale loc;

	@SuppressWarnings("static-access")
	public StudentDetailView(StudentView parentView, Student selectedEntry) {
		loc = new Locale("de", "DE");
		StudentService service = parentView.getService();
		saveButton = new Button(v18.getTranslation("basic.save", loc));

		VerticalLayout subContent = new VerticalLayout();
		this.add(subContent);

		try {
			service.setEditing(false);

			TextField txfID = new TextField("ID");
			subContent.add(txfID);
			txfID.setValue(""+selectedEntry.getId());


			txaComment = new TextArea(v18.getTranslation("basic.comment", loc), "" + selectedEntry.getComment());
			subContent.add(txaComment);

			ckbEdit = new Checkbox(v18.getTranslation("basic.edit", loc));
			ckbEdit.addValueChangeListener(event -> {
				service.toggleEditing();
				if (event.getValue()) {
					saveButton.setEnabled(true);
				} else {
					saveButton.setEnabled(false);
				}
			});

			subContent.add(ckbEdit);

			saveButton.setEnabled(service.getEditing());
			subContent.add(saveButton);

			saveButton.addClickListener(event -> {
				selectedEntry.setComment(txaComment.getValue());
				parentView.updateRow(selectedEntry);
				parentView.refreshGrid();
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
