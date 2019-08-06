package org.geogebra.web.html5.factories;

import org.geogebra.common.factories.UtilFactory;
import org.geogebra.common.util.HttpRequest;
import org.geogebra.common.util.Prover;
import org.geogebra.common.util.URLEncoder;
import org.geogebra.common.util.Reflection;
import org.geogebra.common.util.GTimer;
import org.geogebra.common.util.GTimerListener;
import org.geogebra.common.util.debug.Log;
import org.geogebra.web.html5.sound.GTimerW;
import org.geogebra.web.html5.util.HttpRequestW;
import org.geogebra.web.html5.util.ProverW;
import org.geogebra.web.html5.util.URLEncoderW;
import org.geogebra.web.html5.util.debug.GeoGebraProfilerW;
import org.geogebra.web.html5.util.debug.LoggerW;

/**
 * Web implementations for various utils
 * 
 * @author Zoltan Kovacs
 */
public class UtilFactoryW extends UtilFactory {

	@Override
	public HttpRequest newHttpRequest() {
		return new HttpRequestW();
	}

	@Override
	public URLEncoder newURLEncoder() {
		return new URLEncoderW();
	}

	@Override
	public Log newGeoGebraLogger() {
		return new LoggerW();
	}

	@Override
	public Prover newProver() {
		return new ProverW();
	}

	@Override
	public double getMillisecondTime() {
		return GeoGebraProfilerW.getMillisecondTimeNative();
	}

	@Override
	public Reflection newReflection(Class clazz) {
		// used by BatchedUpdateWrapper
		// not needed currently
		return null;
	}

	@Override
	public GTimer newTimer(GTimerListener listener, int delay) {
		return new GTimerW(listener, delay);
	}
}
