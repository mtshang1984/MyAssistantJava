package SearchEngine;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Browser {
	private static int countCalling = 0;

	// 启动chrome浏览器打开网址
	public void openUrl(String stringUrl) {
		try {
			String stringCommand = "";
			OperatingSystem operatingSystem=new OperatingSystem();
			switch (operatingSystem.getOperatingSystemName()) {
			default:
			case windows:
				// Windows下启动Chrome浏览器打开网页
				stringCommand = "chrome " + stringUrl;
				Runtime.getRuntime().exec(stringCommand);
				try {
					if (countCalling == 0) {
						Thread.sleep(200);
					} else {
						Thread.sleep(100);
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				countCalling++;
				// System.out.println(string_command);
				break;
			case linux:

				// Linux下启动w3m文本浏览器打开网页
				if (stringUrl != "") {
					if (countCalling == 0) {
						stringCommand = "#!/bin/bash\n";
						stringCommand = stringCommand + "w3m -N \""
								+ stringUrl + "\"";
						if (stringUrl.contains("google.com")
								|| stringUrl.contains("twitter.com"))
							stringCommand += " -o http_proxy=http://127.0.0.1:8087 -o https_proxy=http://127.0.0.1:8087";
						else
							stringCommand += " -no-proxy";

						stringCommand += " -o user_agent=\"Mozilla/5.0 (Linux; ; ) AppleWebKit/ (KHTML, like Gecko) Chrome/ Mobile Safari/\"";
					} else {
						stringCommand = stringCommand + " -N \"" + stringUrl
								+ "\"";
					}
					try {
						String shellFileName = "url_to_open.sh";
						File shellFile = new File(shellFileName);
						if (countCalling == 0) {
							if (shellFile.exists()) {
								shellFile.delete();
							} else {
								shellFile.createNewFile();
							}

						} else if (!shellFile.exists()) {
							System.out.println("错误：未找到" + shellFileName
									+ "文件");
							System.exit(1);
						}

						FileOutputStream outputStream = new FileOutputStream(
								shellFile, true);
						outputStream.write(stringCommand.toString().getBytes(
								"utf-8"));
						outputStream.close();
						Runtime.getRuntime().exec(
								"chmod 777 " + shellFileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(stringCommand);
					countCalling++;
				}
				break;
			case android:
				System.out.println(System.getProperty("os.name")+"."+System.getProperty("os.arch")+" is not support!");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
