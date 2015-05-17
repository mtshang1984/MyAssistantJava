import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Browser {
	private static int count_calling = 0;

	// 启动chrome浏览器打开网址
	public static void OpenUrl(String string_url) {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				// Windows下启动Chrome浏览器打开网页
				String string_command = "chrome " + string_url;
				Runtime.getRuntime().exec(string_command);
				try {
					if (count_calling == 0) {
						Thread.sleep(200);
					} else {
						Thread.sleep(100);
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				count_calling++;
				// System.out.println(string_command);
			} else if (System.getProperty("os.name").contains("Linux")) {
				// Linux下启动w3m文本浏览器打开网页
				String string_command = "";
				if (string_url != "") {
					if (count_calling == 0) {
						string_command = "#!/bin/bash\n";
						string_command = string_command + "w3m -N \""
								+ string_url + "\"";
						if (string_url.contains("google.com")
								|| string_url.contains("twitter.com"))
							string_command += " -o http_proxy=http://127.0.0.1:8087 -o https_proxy=http://127.0.0.1:8087";
						else
							string_command += " -no-proxy";

						string_command += " -o user_agent=\"Mozilla/5.0 (Linux; ; ) AppleWebKit/ (KHTML, like Gecko) Chrome/ Mobile Safari/\"";
					} else {
						string_command = string_command + " -N \"" + string_url
								+ "\"";
					}
					try {
						String shell_file_name = "url_to_open.sh";
						File shell_file = new File(shell_file_name);
						if (count_calling == 0) {
							if (shell_file.exists()) {
								shell_file.delete();
							} else {
								shell_file.createNewFile();
							}

						} else if (!shell_file.exists()) {
							System.out.println("错误：未找到" + shell_file_name
									+ "文件");
							System.exit(1);
						}

						FileOutputStream output_stream = new FileOutputStream(
								shell_file, true);
						output_stream.write(string_command.toString().getBytes(
								"utf-8"));
						output_stream.close();
						Runtime.getRuntime().exec(
								"chmod 777 " + shell_file_name);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println(string_command);
					count_calling++;
				}
			} else {
				System.out.println(System.getProperty("os.name"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
