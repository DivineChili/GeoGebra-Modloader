package org.geogebra.web.full.gui.view.algebra.contextmenu;

import org.geogebra.web.full.gui.view.algebra.AlgebraViewW;

/**
 * AV menu items for CAS-capable apps
 */
public class AlgebraMenuItemCollectionCAS extends AlgebraMenuItemCollection {

	/**
	 * @param algebraView
	 *            algebra view
	 */
	public AlgebraMenuItemCollectionCAS(AlgebraViewW algebraView) {
		super(algebraView);
		addAction(0, new SolveAction());
	}

}
