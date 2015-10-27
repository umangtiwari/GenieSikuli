package sparkgenie;

import static spark.Spark.get;

public class Server {
	static String sessionId;
	static String appId;
	static String elementType;
	static String elementId;
	static String actionPerformed;
	static String param1;
	static String param2;
	static String runType;
	static String result;
	static String actiontype;
	static String path = "images";

	public static void main(String[] args) {

		// --------------------Server
		// Check------------------------------------------//
		get("/hello", (req, res) -> "Hello World");

		// -----------------For
		// Sikuli-----------------------------------------------//
		get("/action/:actiontype/:param1/:param2", (request, response) -> {
			actiontype = request.params(":actiontype");
			param1 = request.params(":param1");

			param2 = request.params(":param2");
			result = SikuliHelper.executeCommand();
			System.out.println(result);
			return result;
		});

		// -------------------For Genie INIT and
		// DESTROY----------------------------//
		get("/control/:appId/:sessionId/:runType", (request, response) -> {
			appId = request.params(":appId");
			appId = appId.replaceAll("~~SPACE~~", " ");

			// appId = (appId);
			appId = ("[" + appId + "]");
			// System.out.println(appId);
			sessionId = request.params(":sessionId");
			// System.out.println(sessionId);
			runType = request.params(":runType");
			// System.out.println(runType);
			if (runType.equalsIgnoreCase("init")) {
				GenieHelper.initGenie(appId, sessionId);
			}

			if (runType.equalsIgnoreCase("destroy")) {
				GenieHelper.destroyGenie();
			}
			System.out.println(result);
			return result;
		});

		// --------------------------For Genie
		// Functions----------------------------------//
		get("/work/:elementType/:elementId/:actionPerformed/:param1/:param2/:runType", (request, response) -> {

			runType = request.params(":runType");
			// System.out.println(runType);
			elementType = request.params(":elementType");
			// System.out.println(elementType);
			elementId = request.params(":elementId");
			elementId = elementId.replace("~~CARET~~", "^");
			// System.out.println(elementId);
			actionPerformed = request.params(":actionPerformed");
			// System.out.println(actionPerformed);
			param1 = request.params(":param1");
			// System.out.println(param1);

			System.out.println(param2);
			param2 = request.params(":param2");
			// System.out.println(param2);
			runType = request.params(":runType");
			// System.out.println(runType);
			if (runType.equalsIgnoreCase("executeCommand")) {
				result = GenieHelper.executeCommand();
			}
			System.out.println(result);
			return result;
		});

	}
}