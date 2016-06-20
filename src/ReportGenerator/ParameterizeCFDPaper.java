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
		wordAutomation.createNewDocument();
		wordAutomation.insertText("复杂多构型燃烧室自动参数化数值模拟方法及应用\n");	
		wordAutomation.insertText("尚明涛,迟鸿伟,王波,张弛,林宇震\n");			
		wordAutomation.insertText("摘要\n");			
		wordAutomation.insertText("本文提出了一种适用于复杂多构型室的自动参数化数值模拟方法，将成功将此方法应用于燃烧室零组件的设计中\n");			
		wordAutomation.insertText("1.前言\n");			
		wordAutomation.insertText("前言\n");			
		wordAutomation.insertText("2.主要原理\n");			
		wordAutomation.insertText("3.实现方法\n");			
		wordAutomation.insertText("4.应用\n");			
//		wordAutomation.save(filename);	
		
//		wordAutomation.close();		
	}
	
}
