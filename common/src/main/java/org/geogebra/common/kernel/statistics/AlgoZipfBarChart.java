/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.statistics;

import org.apache.commons.math3.util.Cloner;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.algos.AlgoBarChart;
import org.geogebra.common.kernel.algos.DrawInformationAlgo;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoNumberValue;

/**
 * @author G. Sturr
 * @version 2011-06-21
 */

public class AlgoZipfBarChart extends AlgoBarChart {

	public AlgoZipfBarChart(Construction cons, String label, GeoNumberValue n,
			GeoNumberValue p) {
		super(cons, label, n, p, null, null, AlgoBarChart.TYPE_BARCHART_ZIPF);
	}

	public AlgoZipfBarChart(Construction cons, String label, GeoNumberValue n,
			GeoNumberValue p, GeoBoolean isCumulative) {
		super(cons, label, n, p, null, isCumulative, AlgoBarChart.TYPE_BARCHART_ZIPF);
	}

	private AlgoZipfBarChart(GeoNumberValue n, GeoNumberValue p,
			GeoBoolean isCumulative, GeoNumberValue a, GeoNumberValue b,
			double[] vals, double[] borders, int N) {
		super(n, p, null, isCumulative, AlgoBarChart.TYPE_BARCHART_ZIPF, a, b,
				vals, borders, N);
	}

	@Override
	public Commands getClassName() {
		return Commands.Zipf;
	}

	@Override
	public DrawInformationAlgo copy() {
		GeoBoolean b = (GeoBoolean) this.getIsCumulative();
		if (b != null) {
			b = b.copy();
		}
		return new AlgoZipfBarChart(
				(GeoNumberValue) this.getP1().deepCopy(kernel),
				(GeoNumberValue) this.getP2().deepCopy(kernel), b,
				(GeoNumberValue) this.getA().deepCopy(kernel),
				(GeoNumberValue) this.getB().deepCopy(kernel),
				Cloner.clone(getValues()), Cloner.clone(getLeftBorder()),
				getIntervals());

	}

}
