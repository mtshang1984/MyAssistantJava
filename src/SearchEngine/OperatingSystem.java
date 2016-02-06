package SearchEngine;

import CommonEnum.OperatingSystemEnum;

public class OperatingSystem {
	public OperatingSystemEnum getOperatingSystemName(){
		if (System.getProperty("os.name").contains("Windows")) {
			return OperatingSystemEnum.windows;
		} else if (System.getProperty("os.name").contains("Linux")&& System.getProperty("os.arch").contains("arm")==false) {
			return OperatingSystemEnum.linux;
		} else if (System.getProperty("os.name").contains("Linux")&& System.getProperty("os.arch").contains("arm")) {
			return OperatingSystemEnum.android;
		}else{
			return OperatingSystemEnum.na;
		}
		
	}
}
