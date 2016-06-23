package ReportGenerator;
 
import OfficeAutomation.WordAutomation;

public class ParameterizeCFDPaper {
	String filename;
	public ParameterizeCFDPaper() {
		// TODO Auto-generated constructor stub
		filename="D:\\00_yun_dir\\01_work\\05_paper\\defaultPaper.docx";
	}
	public ParameterizeCFDPaper(String filename) {
		// TODO Auto-generated constructor stub
		this.filename=filename;
	}
	public void generatePaper(){
		WordAutomation wordAutomation = new WordAutomation(true);
		wordAutomation.createNewDocumentUseTemplete("d:\\00_yun_dir\\04_study\\03_paper\\01_my_paper\\06_参数化\\航空动力学报\\论文模板新.dotx");
		wordAutomation.findText("题  目 （不超过20个字 加粗黑体2号）");
//		wordAutomation.findText("题  目");
		wordAutomation.insertParagraph("复杂多构型燃烧室自动参数化数值模拟方法及应用","标题");	
		wordAutomation.insertParagraph("尚明涛,迟鸿伟,王波,张弛,林宇震","署名");			
		wordAutomation.insertParagraph("摘要：本文提出了一种适用于复杂多构型燃烧室的自动参数化数值模拟方法，"
				+ "将成功将此方法应用于燃烧室零组件的设计中","关键词");			
		wordAutomation.insertParagraph("关键词：燃烧室，自动，参数化，数值模拟","关键词");	
		//引言
		wordAutomation.insertParagraph("传统燃烧室的研制过程需要开展大量的试验研究来确定燃烧室方案。随着燃烧数值模拟技术和计算机技术的不断发展，在筛选和优化试验方案方面数值模拟技术发挥着越来越重要的作用。相对于试验研究，数值模拟可以以较低的成本研究高温高压的运行工况，同时能够获得详细的内部流场信息以对不同的方案进行深入分析。","正文");	
		wordAutomation.insertParagraph("在燃烧室方案设计阶段，需要对大量的设计参数进行分析以找出最优方案。然而由于传统基于人工操作的数值模拟，难以在短时间内完成规模巨大的算例分析工作。且由于人工操作数值拟的工作质量容易受人员水平的影响而存在差异，使最终分析结果之间容易缺乏可比性。基于参数化数值模拟方法，并结合计算机自动化技术，实现燃烧室设计方案分析自动化，是解决目前燃烧室数值分析效率低和质量不可控的有效手段。","正文");	
		wordAutomation.insertParagraph("Snyder[1]等人采用参数化数值模拟方法对燃烧室出口温度分布进行了优化。","正文");	
		wordAutomation.insertParagraph("实现方法","标题 1");			
		wordAutomation.insertParagraph("应用","标题 1");					
		wordAutomation.insertParagraph("结论","标题 1");	
		//参考文献		
		wordAutomation.insertParagraph("参考文献","标题 1");			
		wordAutomation.insertParagraph("[1] Snyder T S, et al. Application of a CFD-based approach to as turbine combustion design[C]. Proceedings of ASME Turbo Expo, 2001-0062","正文");	
		wordAutomation.insertParagraph("[1] Mark K.Lai, et al. CFD-Based, Parametric, Design tool for gas turbine combustors from compressor deswirl exit to turbine inlet[C]. Proceedings of ASME Turbo Expo, 2002-30090","正文");	
		wordAutomation.insertParagraph("[1] Rudolph D, et al. Integrated proces for cfd modeling and optimization of gas turbine combustors[C]. Proceedings of ASME Turbo Expo, 2004-54011","正文");
		wordAutomation.insertParagraph("[1] Nima P, et al. Development of an automated preliminary combustion chamber design tool[C]. Proceedings of ASME Turbo Expo, 2006-90430","正文");
		wordAutomation.insertParagraph("[1] Nima P, et al. State-of-the-art combustor design utilizing the preliminary combustor design system precodes[C]. Proceedings of ASME Turbo Expo, 2008-50577","正文");
		wordAutomation.insertParagraph("[1] 石梦华, 等. 航空发动机燃烧室参数化建模[J]. 航空发动机, 2011,37(5):11-15.","正文");
		wordAutomation.insertParagraph("[1] 段冬霞, 等. 基于参数化建模方法的双级旋流器流场研究[J]. 燃气轮机技术, 2012,25(2):12-20.","正文");
		wordAutomation.insertParagraph("[2] Andreas A, et al. Automated combustor preliminary design using tools of different fidelity[C]. Proceedings of ASME Turbo Expo, 2013-944","正文");	
		wordAutomation.insertParagraph("[1] 段冬霞, 等. 燃气轮机燃烧室参数化CFD设计方法研究[J]. 燃气轮机技术, 2014,27(2):8-22","正文");
//		wordAutomation.save(filename);	
		
//		wordAutomation.close();		
	}
	
}
