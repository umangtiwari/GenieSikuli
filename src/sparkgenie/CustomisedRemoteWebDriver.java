package sparkgenie;

import java.net.URL;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.logging.LocalLogs;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingHandler;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.NeedsLocalLogs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteLogs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.internal.JsonToWebElementConverter;

import com.google.common.collect.ImmutableSet;

public class CustomisedRemoteWebDriver extends RemoteWebDriver {

	private LocalLogs localLogs;
	protected static String sessionId;

	public CustomisedRemoteWebDriver(URL remoteAddress,
			DesiredCapabilities desiredCapabilities, String sessionId) {
		this(new HttpCommandExecutor(remoteAddress), desiredCapabilities, null,
				sessionId);
	}

	public CustomisedRemoteWebDriver(CommandExecutor executor,
			Capabilities desiredCapabilities,
			Capabilities requiredCapabilities, String sessionId) {

		CustomisedRemoteWebDriver.sessionId = sessionId;

		setCommandExecutor(executor);

		init(desiredCapabilities, requiredCapabilities);

		if (executor instanceof NeedsLocalLogs) {
			((NeedsLocalLogs) executor).setLocalLogs(localLogs);
		}
		startClient();
		startSession(desiredCapabilities, requiredCapabilities);
	}

	private void init(Capabilities desiredCapabilities,
			Capabilities requiredCapabilities) {

		(Logger.getLogger(RemoteWebDriver.class.getName()))
				.addHandler(LoggingHandler.getInstance());

		setElementConverter(new JsonToWebElementConverter(this));

		ImmutableSet.Builder<String> builder = new ImmutableSet.Builder<String>();

		boolean isProfilingEnabled = desiredCapabilities != null
				&& desiredCapabilities
						.is(CapabilityType.ENABLE_PROFILING_CAPABILITY);
		if (requiredCapabilities != null
				&& requiredCapabilities
						.getCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY) != null) {
			isProfilingEnabled = requiredCapabilities
					.is(CapabilityType.ENABLE_PROFILING_CAPABILITY);
		}
		if (isProfilingEnabled) {
			builder.add(LogType.PROFILER);
		}

		LoggingPreferences mergedLoggingPrefs = new LoggingPreferences();
		if (desiredCapabilities != null) {
			mergedLoggingPrefs
					.addPreferences((LoggingPreferences) desiredCapabilities
							.getCapability(CapabilityType.LOGGING_PREFS));
		}
		if (requiredCapabilities != null) {
			mergedLoggingPrefs
					.addPreferences((LoggingPreferences) requiredCapabilities
							.getCapability(CapabilityType.LOGGING_PREFS));
		}
		if ((mergedLoggingPrefs.getEnabledLogTypes().contains(LogType.CLIENT) && mergedLoggingPrefs
				.getLevel(LogType.CLIENT) != Level.OFF)
				|| !mergedLoggingPrefs.getEnabledLogTypes().contains(
						LogType.CLIENT)) {
			builder.add(LogType.CLIENT);
		}

		Set<String> logTypesToInclude = builder.build();

		LocalLogs performanceLogger = LocalLogs
				.getStoringLoggerInstance(logTypesToInclude);
		LocalLogs clientLogs = LocalLogs.getHandlerBasedLoggerInstance(
				LoggingHandler.getInstance(), logTypesToInclude);
		localLogs = LocalLogs.getCombinedLogsHolder(clientLogs,
				performanceLogger);
		new RemoteLogs(new RemoteExecuteMethod(this), localLogs);
	}

	protected void startSession(Capabilities desiredCapabilities,
			Capabilities requiredCapabilities) {
		setSessionId(sessionId);
	}

}
