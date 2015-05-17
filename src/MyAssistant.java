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

		System.out.println(System.getProperty("os.arch"));
		RequirementsAnalysis requirements_analysis = new RequirementsAnalysis();
		requirements_analysis.AnalysisRequirement();
		requirements_analysis.ShowRequirement();
		
		if (Keyword.isEmpty()) {
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
