package org.app.common.polymer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("icon-button")
@HtmlImport("frontend://styles/shared-styles.html")
public class IconButton extends PolymerTemplate<TemplateModel> {
	
    @Id("labelx")
    private Div labelx;

    @Id("iconx")
    private Icon iconx;
    
    public void setText(Component text) {
        this.labelx.removeAll();
        this.labelx.add(text);
    }

    public void setIcon(VaadinIcon icon) {
        this.iconx.getElement().setAttribute("icon", "vaadin:" + icon.name().toLowerCase().replace('_', '-'));
      }
    

//    public IconButton() {
//        Element label = ElementFactory.createLabel("Main layout header");
//        Element button = ElementFactory.createButton("Click me");
//
//        getElement().appendChild(label, button);
//    }
}