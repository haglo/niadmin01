/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.app.view.masterdetail;

import javax.inject.Inject;

import org.app.view.Language;
import org.app.view.LeftMenu;
import org.app.view.MainLayout;
import org.app.view.V18Cdi;
import org.app.view.masterdetail.visor.VisorView;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;;

@HtmlImport("styles/shared-styles.html")
@Route(value = "MasterDetail", layout = MainLayout.class)
@ParentLayout(MainLayout.class)
public class MasterDetail extends VerticalLayout implements RouterLayout {
	
	@Inject
	V18Cdi v18;

	private static final long serialVersionUID = 1L;
	private LeftMenu leftMenu;
	private final Div contentHolder = new Div();

	public MasterDetail() {

		setSizeFull();
		setClassName("main-layout-left");

		leftMenu = new LeftMenu();
		leftMenu.addView(TitleViewTemplate.class, TitleViewTemplate.VIEW_NAME, VaadinIcon.ELLIPSIS_V.create());
		leftMenu.addView(VisorView.class, VisorView.VIEW_NAME, VaadinIcon.LOCATION_ARROW.create());
		leftMenu.addView(Language.class, Language.VIEW_NAME, VaadinIcon.LOCATION_ARROW.create());

		VerticalLayout left = new VerticalLayout();

		left.add(leftMenu);
		SplitLayout layout = new SplitLayout();
		layout.setOrientation(Orientation.HORIZONTAL);
		layout.addToPrimary(left);
		layout.addToSecondary(contentHolder);
		layout.setSplitterPosition(25);
		layout.setSizeFull();
 		contentHolder.setClassName("content");

		add(layout);
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		contentHolder.getElement().appendChild(content.getElement());
	}
}
