
public class OperatingSystem {
	public static String getOperatingSystemName(){
		if (System.getProperty("os.name").contains("Windows")) {
			return "windows";
		} else if (System.getProperty("os.name").contains("Linux")&& System.getProperty("os.arch").contains("arm")==false) {
			return "linux";
		} else if (System.getProperty("os.name").contains("Linux")&& System.getProperty("os.arch").contains("arm")) {
			return "android";
		}else{
			return "n/a";
		}
		
	}
}
