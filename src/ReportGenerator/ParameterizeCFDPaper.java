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
		wordAutomation.createNewDocumentUseTemplete("Y:\\00_yun_dir\\04_study\\03_paper\\01_my_paper\\06_参数化\\航空动力学报\\论文模板新.dotx");
		wordAutomation.findText("题  目 （不超过20个字 加粗黑体2号）");
//		wordAutomation.findText("题  目");
		wordAutomation.insertParagraph("复杂多构型燃烧室自动参数化数值模拟方法及应用","标题");	
		wordAutomation.insertParagraph("尚明涛,迟鸿伟,王波,张弛,林宇震","署名");			
		wordAutomation.insertParagraph("摘要：本文提出了一种适用于复杂多构型燃烧室的自动参数化数值模拟方法，"
				+ "将成功将此方法应用于燃烧室零组件的设计中","关键词");			
		wordAutomation.insertParagraph("关键词：燃烧室，自动，参数化，数值模拟","关键词");	
		//引言
		wordAutomation.insertParagraph("传统燃烧室的研制过程需要开展大量的试验研究来确定燃烧室方案。随着燃烧数值模拟技术和计算机技术的不断发展，在筛选和优化试验方案方面数值模拟技术发挥着越来越重要的作用。相对于试验研究，数值模拟可以以较低的成本研究高温高压的运行工况，同时能够获得详细的内部流场信息以对不同的方案进行深入分析。","正文");	
		wordAutomation.insertParagraph("在燃烧室方案设计阶段，需要对大量的设计参数进行分析以找出最优方案。然而由于传统基于人工操作的数值模拟，","正文");	
		wordAutomation.insertParagraph("主要原理","标题 1");			
		wordAutomation.insertParagraph("实现方法","标题 1");			
		wordAutomation.insertParagraph("应用","标题 1");					
		wordAutomation.insertParagraph("结论","标题 1");			
//		wordAutomation.save(filename);	
		
//		wordAutomation.close();		
	}
	
}
