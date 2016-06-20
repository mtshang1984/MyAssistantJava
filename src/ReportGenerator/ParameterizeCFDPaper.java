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
		wordAutomation.insertParagraph("摘要：本文提出了一种适用于复杂多构型室的自动参数化数值模拟方法，"
				+ "将成功将此方法应用于燃烧室零组件的设计中","关键词");			
		wordAutomation.insertParagraph("关键词：燃烧室，自动，参数化，数值模拟","关键词");	
		wordAutomation.insertParagraph("本文开发了一种","正文");	
		wordAutomation.insertParagraph("主要原理","标题 1");			
		wordAutomation.insertParagraph("实现方法","标题 1");			
		wordAutomation.insertParagraph("应用","标题 1");					
		wordAutomation.insertParagraph("结论","标题 1");			
//		wordAutomation.save(filename);	
		
//		wordAutomation.close();		
	}
	
}
