import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import SearchEngine.SearchEngine;
/**
 * 
 * @author smt
 *
 */

public class MyAssistant {
	public static void main(String[] args) {
		// 分析命令行参数
		String Keyword = "";
		String searchEngineSelection = "";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-k")) {
				for (int j = i + 1; j < args.length; j++) {
					if (!args[j].equals("-s") && !args[j].equals("-k")) {
						Keyword = Keyword + " " + args[j];
					} else {
						// i = j - 1;
						break;
					}

				}
			} else if (args[i].equals("-s")) {
				for (int j = i + 1; j < args.length; j++) {
					if (!args[j].equals("-s") && !args[j].equals("-k")) {
						searchEngineSelection = searchEngineSelection + " " + args[j];
						// System.out.println(Search_Engine);
					} else {
						// i = j - 1;
						break;
					}

				}

			}

		}
		// 搜索信息
		Keyword = Keyword.trim();
		searchEngineSelection = searchEngineSelection.trim();
		SearchEngine searchEngine=new SearchEngine();
		//RequirementsAnalysis requirements_analysis = new RequirementsAnalysis();
		//requirements_analysis.AnalysisRequirement();
		//requirements_analysis.ShowRequirement();
		
		if (Keyword.isEmpty()) {
			File configFile=new File("config.ini");
			if(!configFile.exists()){
				System.out.println("找不到config.ini文件！");
				System.exit(0);
			}else
			{
				try {
					FileInputStream fileInputStream=new FileInputStream(configFile); 
					InputStreamReader inputStreamReader;
					try {
						inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
						BufferedReader inputStream=new BufferedReader(inputStreamReader);
						String tempString = null;
						try {
							while((tempString=inputStream.readLine())!= null){
								System.out.println(tempString);
							}
							inputStreamReader.close();
						} catch (IOException e) {
							e.printStackTrace();
						} 
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			Assistant assistant=new Assistant();
			assistant.runToday();
		} else {
			if (searchEngineSelection.isEmpty())
				searchEngine.browseSearchResults("Web", Keyword);
			else
				searchEngine.browseSearchResults(searchEngineSelection, Keyword);
		}

	}
}
