package sparkgenie;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URLDecoder;

import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class SikuliHelper {

	static String param1;
	static String param2;
	static int paramInt;
	static String actiontype;
	static String result = "false";
	static String myPath;

	public static String executeCommand() {
		Image paraimage = null;
		Screen s = new Screen();
		param1 = Server.param1;
		param2 = Server.param2;
		myPath = Server.path;
		actiontype = Server.actiontype;
		System.out.println(param1);
		System.out.println(param2);
		System.out.println(actiontype);

		param2 = param2.replaceAll("~~slash~~", "/");
		// myPath = myPath.replaceAll("-","/");

		try {
			// ----------------------------------------Local--------------------------------------------------//
			if (actiontype.equalsIgnoreCase("Click")) {
				int p2 = Integer.parseInt(param2);
				s.wait(myPath + "/" + param1, p2);
				s.click(myPath + "/" + param1);
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("Clickabove")) {
				int p2 = Integer.parseInt(param2);
				s.find(myPath + "/" + param1).above(p2).click();
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("Clickbelow")) {
				int p2 = Integer.parseInt(param2);
				s.find(myPath + "/" + param1).below(p2).click();
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("Clickleft")) {
				int p2 = Integer.parseInt(param2);
				s.find(myPath + "/" + param1).left(p2).click();
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("Clickright")) {
				int p2 = Integer.parseInt(param2);
				s.find(myPath + "/" + param1).right(p2).click();
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("Clickcoordinates")) {
				int p1 = Integer.parseInt(param1);
				int p2 = Integer.parseInt(param2);
				s.click(p1, p2);
				return "clickcoordinates pass";
			}

			if (actiontype.equalsIgnoreCase("type")) {
				// s.type(myPath + "/" + param1, param2);
				s.click(myPath + "/" + param1);
				String param0 = URLDecoder.decode(param2, "UTF-8");
				for (int i = 0; i < param0.length(); i++) {
					char c = param0.charAt(i);
					type(c);
				}
				return "type pass";
			}

			if (actiontype.equalsIgnoreCase("exists")) {
				int p2 = Integer.parseInt(param2);
				s.wait(myPath + "/" + param1, p2);
				if (s.exists(myPath + "/" + param1) != null)
					result = "true";
				else
					result = "false";
				return result;
			}
			if (actiontype.equalsIgnoreCase("Wait")) {
				paramInt = Integer.parseInt(param2);
				s.wait(myPath + "/" + param1, paramInt);
				return "pass";
			}
			if (actiontype.equalsIgnoreCase("dragDrop")) {

				s.dragDrop(myPath + "/" + param1, myPath + "/" + param2);
				return "dragDrop pass";
			}
			if (actiontype.equalsIgnoreCase("hover")) {
				s.hover(myPath + "/" + param1);
				return "click pass";
			}
			if (actiontype.equalsIgnoreCase("clickarea")) {
				Region SomeArea = s.find(myPath + "/" + param1);
				SomeArea.click(myPath + "/" + param2);
				return "clickedarea";

			}

			// ------------------------------------------Remote-------------------------------------------------//
			// URL imageUrl = new
			// URL("file://172.16.1.100/UserData/Arun%20Girdhar/images/"+param1);
			// paraimage = ImageIO.read(imageUrl);
			// System.out.println(System.getProperty("user.dir"));
			// myPath = System.getProperty("user.dir");
			// new File(myPath+"//images").mkdir();
			// ImageIO.write((RenderedImage) paraimage,"png",new
			// File(myPath+"\\images\\"+param1));
			// if (actiontype.equalsIgnoreCase("Click")) {
			// s.click(myPath+"\\images\\"+param1);
			// return "click";
			// }
			//
			// if (actiontype.equalsIgnoreCase("type")) {
			// s.type(myPath+"\\images\\"+param1,param2);
			// return "type";
			// }
			//
			// if (actiontype.equalsIgnoreCase("exists")) {
			// if(s.exists(myPath+"\\images\\"+param1)!=null)
			// result = "true";
			// return result;
			// }
			// if (actiontype.equalsIgnoreCase("Wait")) {
			// paramInt = Integer.parseInt(param2);
			// s.wait(myPath+"\\images\\"+param1,paramInt);
			// return "Wait";
			// }
			// -----------------------------------------Remote
			// Ends------------------------------------------
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
		if (actiontype.equalsIgnoreCase("exists"))
			return "false";
		else
			return "action_failed";
	}

	public static void type(char character) {
		switch (character) {
		case 'a':
			doType(KeyEvent.VK_A);
			break;
		case 'b':
			doType(KeyEvent.VK_B);
			break;
		case 'c':
			doType(KeyEvent.VK_C);
			break;
		case 'd':
			doType(KeyEvent.VK_D);
			break;
		case 'e':
			doType(KeyEvent.VK_E);
			break;
		case 'f':
			doType(KeyEvent.VK_F);
			break;
		case 'g':
			doType(KeyEvent.VK_G);
			break;
		case 'h':
			doType(KeyEvent.VK_H);
			break;
		case 'i':
			doType(KeyEvent.VK_I);
			break;
		case 'j':
			doType(KeyEvent.VK_J);
			break;
		case 'k':
			doType(KeyEvent.VK_K);
			break;
		case 'l':
			doType(KeyEvent.VK_L);
			break;
		case 'm':
			doType(KeyEvent.VK_M);
			break;
		case 'n':
			doType(KeyEvent.VK_N);
			break;
		case 'o':
			doType(KeyEvent.VK_O);
			break;
		case 'p':
			doType(KeyEvent.VK_P);
			break;
		case 'q':
			doType(KeyEvent.VK_Q);
			break;
		case 'r':
			doType(KeyEvent.VK_R);
			break;
		case 's':
			doType(KeyEvent.VK_S);
			break;
		case 't':
			doType(KeyEvent.VK_T);
			break;
		case 'u':
			doType(KeyEvent.VK_U);
			break;
		case 'v':
			doType(KeyEvent.VK_V);
			break;
		case 'w':
			doType(KeyEvent.VK_W);
			break;
		case 'x':
			doType(KeyEvent.VK_X);
			break;
		case 'y':
			doType(KeyEvent.VK_Y);
			break;
		case 'z':
			doType(KeyEvent.VK_Z);
			break;
		case 'A':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
			break;
		case 'B':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
			break;
		case 'C':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
			break;
		case 'D':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
			break;
		case 'E':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
			break;
		case 'F':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
			break;
		case 'G':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
			break;
		case 'H':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
			break;
		case 'I':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
			break;
		case 'J':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
			break;
		case 'K':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K);
			break;
		case 'L':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L);
			break;
		case 'M':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M);
			break;
		case 'N':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N);
			break;
		case 'O':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
			break;
		case 'P':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P);
			break;
		case 'Q':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q);
			break;
		case 'R':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
			break;
		case 'S':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S);
			break;
		case 'T':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T);
			break;
		case 'U':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
			break;
		case 'V':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V);
			break;
		case 'W':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W);
			break;
		case 'X':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X);
			break;
		case 'Y':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y);
			break;
		case 'Z':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z);
			break;
		case '`':
			doType(KeyEvent.VK_BACK_QUOTE);
			break;
		case '0':
			doType(KeyEvent.VK_0);
			break;
		case '1':
			doType(KeyEvent.VK_1);
			break;
		case '2':
			doType(KeyEvent.VK_2);
			break;
		case '3':
			doType(KeyEvent.VK_3);
			break;
		case '4':
			doType(KeyEvent.VK_4);
			break;
		case '5':
			doType(KeyEvent.VK_5);
			break;
		case '6':
			doType(KeyEvent.VK_6);
			break;
		case '7':
			doType(KeyEvent.VK_7);
			break;
		case '8':
			doType(KeyEvent.VK_8);
			break;
		case '9':
			doType(KeyEvent.VK_9);
			break;
		case '-':
			doType(KeyEvent.VK_MINUS);
			break;
		case '=':
			doType(KeyEvent.VK_EQUALS);
			break;
		case '~':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE);
			break;
		case '!':
			doType(KeyEvent.VK_EXCLAMATION_MARK);
			break;
		case '@':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_2);
			break;
		case '#':
			doType(KeyEvent.VK_NUMBER_SIGN);
			break;
		case '$':
			doType(KeyEvent.VK_DOLLAR);
			break;
		case '%':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
			break;
		case '^':
			doType(KeyEvent.VK_CIRCUMFLEX);
			break;
		case '&':
			doType(KeyEvent.VK_AMPERSAND);
			break;
		case '*':
			doType(KeyEvent.VK_ASTERISK);
			break;
		case '(':
			doType(KeyEvent.VK_LEFT_PARENTHESIS);
			break;
		case ')':
			doType(KeyEvent.VK_RIGHT_PARENTHESIS);
			break;
		case '_':
			doType(KeyEvent.VK_UNDERSCORE);
			break;
		case '+':
			doType(KeyEvent.VK_PLUS);
			break;
		case '\t':
			doType(KeyEvent.VK_TAB);
			break;
		case '\n':
			doType(KeyEvent.VK_ENTER);
			break;
		case '[':
			doType(KeyEvent.VK_OPEN_BRACKET);
			break;
		case ']':
			doType(KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '\\':
			doType(KeyEvent.VK_BACK_SLASH);
			break;
		case '{':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
			break;
		case '}':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
			break;
		case '|':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
			break;
		case ';':
			doType(KeyEvent.VK_SEMICOLON);
			break;
		case ':':
			doType(KeyEvent.VK_COLON);
			break;
		case '\'':
			doType(KeyEvent.VK_QUOTE);
			break;
		case '"':
			doType(KeyEvent.VK_QUOTEDBL);
			break;
		case ',':
			doType(KeyEvent.VK_COMMA);
			break;
		case '<':
			doType(KeyEvent.VK_LESS);
			break;
		case '.':
			doType(KeyEvent.VK_PERIOD);
			break;
		case '>':
			doType(KeyEvent.VK_GREATER);
			break;
		case '/':
			doType(KeyEvent.VK_SLASH);
			break;
		case '?':
			doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH);
			break;
		case ' ':
			doType(KeyEvent.VK_SPACE);
			break;
		default:
			doType(KeyEvent.VK_DEAD_DIAERESIS);
			doType(KeyEvent.VK_A);
			break;
//			throw new IllegalArgumentException("Cannot type character "
//					+ character);
		}
	}
	

	private static void doType(int... keyCodes) {
		doType(keyCodes, 0, keyCodes.length);
	}

	private static void doType(int[] keyCodes, int offset, int length) {
		try {
			Robot robot = new Robot();
			if (length == 0) {
				return;
			}

			robot.keyPress(keyCodes[offset]);
			doType(keyCodes, offset + 1, length - 1);
			robot.keyRelease(keyCodes[offset]);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
