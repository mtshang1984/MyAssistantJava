import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import Assistant.AissitantStartup;
import Assistant.AssistantLife;
import Requirement.RequirementsAnalysis;
import SearchEngine.SearchEngine;


public class MyAssistant {

	public static void main(String[] args) {
		// 分析命令行参数
		String Keyword = "";
		String Search_Engine = "";
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
						Search_Engine = Search_Engine + " " + args[j];
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
		Search_Engine = Search_Engine.trim();
		//RequirementsAnalysis requirements_analysis = new RequirementsAnalysis();
		//requirements_analysis.AnalysisRequirement();
		//requirements_analysis.ShowRequirement();
		
		if (Keyword.isEmpty()) {
			File config_file=new File("config.ini");
			if(!config_file.exists()){
				System.out.println("找不到config.ini文件！");
				System.exit(0);
			}else
			{
				try {
					FileInputStream file_input_stream=new FileInputStream(config_file); 
					InputStreamReader input_stream_reader;
					try {
						input_stream_reader = new InputStreamReader(file_input_stream, "GBK");
						BufferedReader input_stream=new BufferedReader(input_stream_reader);
						String tempString = null;
						try {
							while((tempString=input_stream.readLine())!= null){
								System.out.println(tempString);
							}
							input_stream_reader.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			AssistantLife.Today();
			AissitantStartup.Today();
		} else {
			if (Search_Engine.isEmpty())
				SearchEngine.BrowseSearchResults("Web", Keyword);
			else
				SearchEngine.BrowseSearchResults(Search_Engine, Keyword);
		}

	}
}
