package org.geogebra.cas;

import static org.geogebra.test.util.IsEqualStringIgnoreWhitespaces.equalToIgnoreWhitespaces;

import java.util.HashSet;
import java.util.Locale;

import org.geogebra.cas.logging.CASTestLogger;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.KernelCAS;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.arithmetic.MyArbitraryConstant;
import org.geogebra.common.kernel.arithmetic.Traversing.CommandCollector;
import org.geogebra.common.kernel.geos.GeoCasCell;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.util.debug.Log;
import org.geogebra.desktop.headless.AppDNoGui;
import org.geogebra.desktop.main.LocalizationD;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseCASIntegrationTest {
	static public boolean silent = false;

	protected static Kernel kernel;
	private static AppDNoGui app;

	/**
	 * Logs all tests which don't give the expected but a valid result.
	 */
	static CASTestLogger logger;

	static MyArbitraryConstant arbconst;

	/**
	 * Create app and CAS.
	 */
	@BeforeClass
	public static void setupCas() {
		app = new AppDNoGui(new LocalizationD(3), false);

		if (silent) {
			Log.setLogger(null);
		}

		// Set language to something else than English to test automatic
		// translation.
		app.setLanguage(Locale.GERMANY);
		// app.fillCasCommandDict();

		kernel = app.getKernel();
		arbconst = new MyArbitraryConstant(
				new GeoCasCell(kernel.getConstruction()));
		logger = new CASTestLogger();

		// Setting the general timeout to 9 seconds. Feel free to change this.
		kernel.getApplication().getSettings().getCasSettings()
				.setTimeoutMilliseconds(9000);
	}

	/**
	 * Handles the logs about test warnings.
	 */
	@AfterClass
	public static void handleLogs() {
		if (!silent) {
			logger.handleLogs();
		}
	}

	/**
	 * Before every test: Clear the construction list to make sure there is
	 * nothing already defined.
	 */
	@Before
	public void beforeTest() {
		kernel.clearConstruction(true);
	}

	protected static void t(String input, String expectedResult,
			String... validResults) {
		ta(false, input, expectedResult, validResults);
	}

	static void ta(boolean keepInput, String input,
			String expectedResult, String... validResults) {
		GeoCasCell f = new GeoCasCell(kernel.getConstruction());
		ta(f, keepInput, input, expectedResult, validResults);
	}

	/**
	 * ta contains the code shared by {@link #t} and {@link #tk}. In explicit:
	 * If tkiontki is false, it behaves exactly like t used to. If tkiontki is
	 * true, it switches to Keepinput mode, simulating evaluation with
	 * Keepinput.
	 * 
	 * <p>
	 * Note: Direct calls to ta are "Not Recommended". Use t or tk instead.
	 * </p>
	 * 
	 * @param f
	 *            CAS cell
	 * @param keepInput
	 *            To Keepinput or not to Keepinput.
	 * @param input
	 *            The input.
	 * @param expectedResult
	 *            The expected result.
	 * @param validResults
	 *            Valid, but undesired results.
	 */
	protected static void ta(GeoCasCell f, boolean keepInput, String input,
			String expectedResult, String... validResults) {
		String result;

		try {

			f.setInput(input);
			if (keepInput) {
				f.setEvalCommand("Keepinput");
			}
			if (!f.hasVariablesOrCommands()) {
				kernel.getConstruction().addToConstructionList(f, false);
				f.computeOutput();
				f.setLabelOfTwinGeo();
			} else {
				kernel.getConstruction().removeFromConstructionList(f);
				KernelCAS.dependentCasCell(f);
			}

			boolean includesNumericCommand = false;
			HashSet<Command> commands = new HashSet<>();

			f.getInputVE().traverse(CommandCollector.getCollector(commands));

			if (!commands.isEmpty()) {
				for (Command cmd : commands) {
					String cmdName = cmd.getName();
					// Numeric used
					includesNumericCommand = includesNumericCommand
							|| ("Numeric".equals(cmdName)
									&& cmd.getArgumentNumber() > 1);
				}
			}

			result = f.getValue() != null
					? f.getValue()
							.toString(includesNumericCommand
									? StringTemplate.testNumeric
									: StringTemplate.testTemplate)
					: f.getOutput(StringTemplate.testTemplate);
			if (f.getValue() != null
					&& f.getValue().unwrap() instanceof GeoElement) {
				result = ((GeoElement) f.getValue().unwrap())
						.toValueString(StringTemplate.testTemplate);
			}

		} catch (Throwable t) {
			String sts = ArbitraryConstIntegrationTest.stacktrace(t);
			result = t.getClass().getName() + ":" + t.getMessage() + sts;
		}

		MatcherAssert.assertThat(result, equalToIgnoreWhitespaces(logger, input,
				expectedResult, validResults));
	}

	/**
	 * @param input
	 *            input
	 * @param expectedResult
	 *            preferred result
	 * @param validResults
	 *            alternative results
	 */
	static void tk(String input, String expectedResult,
			String... validResults) {
		ta(true, input, expectedResult, validResults);
	}

	protected static AppDNoGui getApp() {
		return app;
	}
}
