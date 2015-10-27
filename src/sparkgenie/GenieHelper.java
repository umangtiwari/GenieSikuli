package sparkgenie;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.adobe.genie.executor.Genie;
import com.adobe.genie.executor.LogConfig;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieTextInput;
import com.adobe.genie.genieCom.SWFApp;

public class GenieHelper {
	static WebDriver griddriver;
	static URL hubUrl;
	static String appId;
	static String elementType;
	static String elementId;
	static String actionPerformed;	
	static String param1;
	static String param2;
	static String result;	
	static Genie g;
	static protected SWFApp app;
	static String sessionId;

	// ------------Genie one iteration first child----------------------------//
//	public static String getTheChild1(String genieID, SWFApp app) {
//		try {
//			GenieLocatorInfo info = new GenieLocatorInfo();
//			info.index = 1;
//			GenieComponent[] comp = new GenieComponent(genieID, app)
//					.getChildren(info, false);
//
//			System.out.println(info.text);
//			return comp[0].getGenieID();
//
//		} catch (Exception e) {
//			return genieID;
//		}
//
//	}
//
//	public static String getTheChild2(String genieID, SWFApp app) {
//		try {
//			GenieLocatorInfo info = new GenieLocatorInfo();
//			info.index = 1;
//			GenieComponent[] comp = new GenieComponent(genieID, app)
//					.getChildren(info, false);
//			return comp[1].getGenieID();
//		} catch (Exception e) {
//			return genieID;
//		}
//
//	}

	// --------------------------------------------------------------//

	public static String executeCommand() {

		param1 = Server.param1;
		param2 = Server.param2;
		elementType = Server.elementType;
		appId = Server.appId;
		elementId = Server.elementId;
		actionPerformed = Server.actionPerformed;
		sessionId = Server.sessionId;
		String param2location[] = param2.split(",");
		System.out.println(elementType);
		System.out.println(actionPerformed);

		int p2l[] = new int[7];
		p2l[0] = Integer.parseInt(param2location[0]);
		p2l[1] = Integer.parseInt(param2location[1]);
		p2l[2] = Integer.parseInt(param2location[2]);
		p2l[3] = Integer.parseInt(param2location[3]);
		p2l[4] = Integer.parseInt(param2location[4]);
		p2l[5] = Integer.parseInt(param2location[5]);
		p2l[6] = Integer.parseInt(param2location[6]);

		try {

			if (elementType.equalsIgnoreCase("DisplayObject")) {
				System.out.println(elementType);
				if (actionPerformed.equalsIgnoreCase("Click")) {
					System.out.println("inside click");
					(new GenieDisplayObject(elementId, app)).click(p2l[0],
							p2l[1], p2l[2], p2l[3], p2l[4], p2l[5], p2l[6],
							true);
					return "click";
				}
//				if (actionPerformed.equalsIgnoreCase("getValueOf")) {
//					String newelementId = getTheChild1(elementId, app);
//					System.out.println(newelementId);
//					String newelementId1 = getTheChild2(newelementId, app);
//					System.out.println(newelementId1);
//					String newelementId2 = getTheChild1(newelementId1, app);
//					System.out.println(newelementId2);
//					String newelementId3 = getTheChild1(newelementId2, app);
//					System.out.println(newelementId3);
//					String content = (new GenieTextInput(newelementId3, app))
//							.getValueOf(param1);
//					return content;
//				}
				if (actionPerformed.equalsIgnoreCase("isVisible")) {
					if ((new GenieDisplayObject(elementId, app)).isVisible()) {
						String content = "true";
						return content;
					} else {
						String content = "false";
						return content;
					}

				}
			}

			if (elementType.equalsIgnoreCase("TextInput")) {
				System.out.println(elementType);
				if (actionPerformed.equalsIgnoreCase("Input")) {
					(new GenieTextInput(elementId, app)).input(param1);
					return "input";
				}
				if (actionPerformed.equalsIgnoreCase("Type")) {
					(new GenieTextInput(elementId, app)).type(param1);
					return "type";
				}
				if (actionPerformed.equalsIgnoreCase("getValueOf")) {
					String content = (new GenieTextInput(elementId, app))
							.getValueOf(param1);
					return content;
				}
				if (actionPerformed.equalsIgnoreCase("isVisible")) {
					if ((new GenieDisplayObject(elementId, app)).isVisible()) {
						String content = "true";
						return content;
					} else {
						String content = "false";
						return content;
					}
				}
			}

			// ---------- For File
			// Read-Write-------------------------------------------------------//
			// if(s.exists("images/"+param1)!= null)
			// {
			// String content = "true";

			// File file = new File("OP.txt");
			// if (file.exists()) {
			// file.createNewFile();

			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(content);
			// bw.close();
			// } else {
			// String content = "false";
			// File file = new File("OP.txt");
			// if (file.exists()) {
			// .createNewFile();
			// }
			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(content);
			// bw.close();
			// }

			// }
			// ---------------------------------------------------------------------------------------//

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "action_failed";
	}

	public static void initGenie(String appId, String sessionId) {

		GenieHelper.appId = appId;
		GenieHelper.sessionId = sessionId;

		try {
			hubUrl = new URL("http://localhost:4444/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setPlatform(Platform.LINUX);
		griddriver = new CustomisedRemoteWebDriver(hubUrl, capabilities,
				sessionId);
		LogConfig l = new LogConfig();
		g = null;

		try {
			Genie.EXIT_ON_FAILURE = true;
			g = Genie.init(l);
			app = g.connectToApp(appId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Genie Initialised !!");
	}

	public static void destroyGenie() {

		try {
			g.stop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
