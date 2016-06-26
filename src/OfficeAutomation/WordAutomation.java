package OfficeAutomation;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordAutomation {
	// word文档
	private Dispatch document;
	// word运行程序对象
	private ActiveXComponent word;
	// 所有word文档集合
	private Dispatch documents;
	// 选定的范围或插入点
	private Dispatch selection;
	private boolean saveOnExit = true;

	public WordAutomation(boolean visible) {
		ComThread.InitSTA();// 线程启动
		if (word == null) {
			word = new ActiveXComponent("Word.Application");
			word.setProperty("Visible", new Variant(visible)); // 不可见打开word
			word.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
		}
		if (documents == null)
			documents = word.getProperty("Documents").toDispatch();
	}

	/**
	 * 设置退出时参数
	 * 
	 * @param saveOnExit
	 *            boolean true-退出时保存文件，false-退出时不保存文件
	 */
	public void setSaveOnExit(boolean saveOnExit) {
		this.saveOnExit = saveOnExit;
	}

	/**
	 * 创建一个新的word文档
	 * 
	 */
	public void createNewDocument() {
		document = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 基于模板创建一个新的word文档
	 * 
	 */
	public void createNewDocumentUseTemplete(String filenameTemplete) {
		document = Dispatch.invoke(documents, "Add", Dispatch.Method,
				new Object[] { filenameTemplete, new Variant(false) }, new int[0]).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 打开一个已存在的文档
	 * 
	 * @param docPath
	 */
	public void openDocument(String docPath) {
		// closeDocument();
		File file = new File(docPath);
		// if(file.canWrite()==false){
		if (file.exists()) {
			document = Dispatch.call(documents, "Open", docPath).toDispatch();
			selection = Dispatch.get(word, "Selection").toDispatch();
		}
	}

	/**
	 * 只读方式打开一个加密的文档
	 * 
	 * @param docPath-文件全名
	 * @param pwd-密码
	 */
	public void openDocumentOnlyRead(String docPath, String pwd) throws Exception {
		// closeDocument();
		document = Dispatch.callN(documents, "Open", new Object[] { docPath, new Variant(false), new Variant(true),
				new Variant(true), pwd, "", new Variant(false) }).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 打开一个加密的文档
	 * 
	 * @param docPath
	 * @param pwd
	 * @throws Exception
	 */
	public void openDocument(String docPath, String pwd) throws Exception {
		// closeDocument();
		document = Dispatch
				.callN(documents, "Open",
						new Object[] { docPath, new Variant(false), new Variant(false), new Variant(true), pwd })
				.toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 从选定内容或插入点开始查找文本
	 * 
	 * @param toFindText
	 *            要查找的文本
	 * @return boolean true-查找到并选中该文本，false-未查找到文本
	 */
	@SuppressWarnings("static-access")
	public boolean findText(String toFindText) {
		if (toFindText == null || toFindText.equals(""))
			return false;
		// 从selection所在位置开始查询
		Dispatch find = word.call(selection, "Find").toDispatch();
		// 设置要查找的内容
		Dispatch.put(find, "Text", toFindText);
		// 向前查找
		Dispatch.put(find, "Forward", "True");
		// 设置格式
		Dispatch.put(find, "Format", "True");
		// 大小写匹配
		Dispatch.put(find, "MatchCase", "True");
		// 全字匹配
		Dispatch.put(find, "MatchWholeWord", "false");
		// 查找并选中
		return Dispatch.call(find, "Execute").getBoolean();
	}

	/**
	 * 把选定的内容或插入点向上移动
	 * 
	 * @param pos
	 *            移动的距离
	 */
	public void moveUp(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveUp");
		}
	}

	/**
	 * 把选定的内容或者插入点向下移动
	 * 
	 * @param pos
	 *            移动的距离
	 */
	public void moveDown(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveDown");
		}
	}

	/**
	 * 把选定的内容或者插入点向左移动
	 * 
	 * @param pos
	 *            移动的距离
	 */
	public void moveLeft(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveLeft");
		}
	}

	/**
	 * 把选定的内容或者插入点向右移动
	 * 
	 * @param pos
	 *            移动的距离
	 */
	public void moveRight(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 把插入点移动到文件首位置
	 * 
	 */
	public void moveStart() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(selection, "HomeKey", new Variant(6));
	}

	/**
	 * 选择整段
	 * 
	 */
	public void selectParagraph() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.invoke(selection, "MoveDown", Dispatch.Method,
				new Object[] { new Variant(4), new Variant(1), new Variant(0) }, new int[1]);
		Dispatch.invoke(selection, "MoveUp", Dispatch.Method,
				new Object[] { new Variant(4), new Variant(1), new Variant(1) }, new int[1]);
	}

	/**
	 * 选择整段
	 * 
	 */
	public void selectParagraph2() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.invoke(selection, "MoveUp", Dispatch.Method,
				new Object[] { new Variant(4), new Variant(1), new Variant(1) }, new int[1]);
	}

	/**
	 * 清除格式
	 * 
	 */
	public void clearFormatting() {
		Dispatch.call(selection, "ClearFormatting");
	}

	/**
	 * 设置当前选定内容的字体
	 * 
	 * @param boldSize
	 * @param italicSize
	 * @param underLineSize
	 *            下划线
	 * @param colorSize
	 *            字体颜色
	 * @param size
	 *            字体大小
	 * @param name
	 *            字体名称
	 * @param hidden
	 *            是否隐藏
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name,
			boolean hidden) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(bold));
		Dispatch.put(font, "Italic", new Variant(italic));
		Dispatch.put(font, "Underline", new Variant(underLine));
		Dispatch.put(font, "Color", colorSize);
		Dispatch.put(font, "Size", size);
		Dispatch.put(font, "Hidden", hidden);
	}

	/**
	 * 设置格式
	 * 
	 */
	public void setStyles(String style) {
		Dispatch.put(selection, "Style", style);
	}

	/**
	 * 设置整段格式
	 * 
	 */
	public void setParagraphStyles(String style) {
		selectParagraph();
		clearFormatting();
		Dispatch.put(selection, "Style", style);
	}

	/**
	 * 设置段落开头到光标位置格式
	 * 
	 */
	public void setParagraphStyles2(String style) {
		selectParagraph2();
		clearFormatting();
		// Dispatch activeDocument=Dispatch.get(word,
		// "ActiveDocument").toDispatch();
		// Dispatch
		// style2=Dispatch.call(activeDocument,"Styles",style).toDispatch();
		Dispatch.put(selection, "Style", style);
	}

	/**
	 * 在当前插入点插入字符串
	 * 
	 * @param newText
	 *            要插入的新字符串
	 */
	public void insertText(String newText) {
		Dispatch.call(selection, "TypeText", newText);
	}

	/**
	 * 在当前插入点插入字符串
	 * 
	 * @param newText
	 *            要插入的新字符串
	 */
	public void insertText(String newText, String style) {
		Dispatch.call(selection, "TypeText", newText);
		setParagraphStyles2(style);
		moveRight(1);
	}

	/**
	 * 在当前插入点插入换行符
	 * 
	 * @param newText
	 */
	public void insertParagraphSpliter() {
		Dispatch.call(selection, "TypeParagraph");
	}

	/**
	 * 在当前插入点插入一段话
	 * 
	 * @param newText
	 */
	public void insertParagraph(String newText) {
		Dispatch.call(selection, "TypeText", newText);
		Dispatch.call(selection, "TypeParagraph");
	}

	/**
	 * 在当前插入点插入一段话
	 * 
	 * @param newText
	 */
	public void insertParagraph(String newText, String style) {
		Dispatch.call(selection, "TypeText", newText);
		setParagraphStyles2(style);
		moveRight(1);
		insertParagraphSpliter();
	}

	/**
	 * 把选定选定内容设定为替换文本
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param newText
	 *            要替换的内容
	 * @return
	 */
	public boolean replaceText(String toFindText, String newText) {
		if (!findText(toFindText))
			return false;
		Dispatch.put(selection, "Text", newText);
		return true;
	}

	/**
	 * 全局替换文本
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param newText
	 *            要替换的内容
	 */
	public void replaceAllText(String toFindText, String newText) {
		while (findText(toFindText)) {
			Dispatch.put(selection, "Text", newText);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 插入图片
	 * 
	 * @param jpegFilePath‘
	 *            文件名称
	 */
	public void insertImage(String imageFilePath) {
		Dispatch selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();
		Dispatch.call(image, "AddPicture", imageFilePath);
	}

	/**
	 * 文件保存或另存为
	 * 
	 * @param filename
	 *            保存或另存为路径
	 */
	public void save(String filename) {
		// Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(),
		// "FileSaveAs", savePath);
		// Dispatch.call(document, "SaveAs",savePath);
		// document = Dispatch.invoke(document, "SaveAS2", Dispatch.Method,
		// new Object[] { filename, new Variant(16), new Variant(false), new
		// Variant(""), new Variant(true),
		// new Variant(""), new Variant(false), new Variant(false), new
		// Variant(false), new Variant(false),
		// new Variant(false), new Variant(936), new Variant(false), new
		// Variant(false), new Variant(0),
		// new Variant(false), new Variant(14) },
		// new int[0]).toDispatch();
		 Dispatch.invoke(document, "SaveAS2", Dispatch.Method,
				new Object[] { filename, new Variant(16), new Variant(false), new Variant(""), new Variant(true),
						new Variant(""), new Variant(false), new Variant(false), new Variant(false), new Variant(false),
						new Variant(false), new Variant(936), new Variant(false), new Variant(false), new Variant(0),
						new Variant(false), new Variant(14) },
				new int[1]);
	}

	/**
	 * 文件保存为html格式
	 * 
	 * @param savePath
	 * @param htmlPath
	 */
	public void saveAsHtml(String htmlPath) {
		Dispatch.invoke(document, "SaveAs", Dispatch.Method, new Object[] { htmlPath, new Variant(8) }, new int[1]);
	}

	/**
	 * 关闭文档
	 * 
	 * @param val
	 *            0不保存修改 -1 保存修改 -2 提示是否保存修改
	 */
	public void closeDocument(int val) {
		if (document != null) {
			Dispatch.call(document, "Close", new Variant(val));
			document = null;
		}
	}

	/**
	 * 关闭当前word文档
	 * 
	 */
	public void closeDocument() {
		if (document != null) {
			Dispatch.call(document, "Save");
			Dispatch.call(document, "Close", new Variant(saveOnExit));
			document = null;
		}
	}

	public void closeDocumentWithoutSave() {
		if (document != null) {
			Dispatch.call(document, "Close", new Variant(false));
			document = null;
		}
	}

	/**
	 * 保存并关闭全部应用
	 * 
	 */
	public void close() {
		// closeDocument(-1);
		closeDocument(-1);
		if (word != null) {
			// Dispatch.call(word, "Quit");
			word.invoke("Quit", new Variant[] {});
			word = null;
		}
		selection = null;
		documents = null;
		// ComThread.Release();// 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理

	}

	/**
	 * 
	 * @param toFindText
	 *            要查找的字符串
	 * @param imagePath
	 *            图片路径
	 * @return
	 */
	public boolean replaceImage(String toFindText, String imagePath) {
		if (!findText(toFindText))
			return false;
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
		return true;
	}

	/**
	 * 全局替换图片
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param imagePath
	 *            图片路径
	 */
	public void replaceAllImage(String toFindText, String imagePath) {
		while (findText(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 合并单元格
	 * 
	 * @param tableIndex
	 * @param fstCellRowIdx
	 * @param fstCellColIdx
	 * @param secCellRowIdx
	 * @param secCellColIdx
	 */
	public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx))
				.toDispatch();
		Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx))
				.toDispatch();
		Dispatch.call(fstCell, "Merge", secCell);
	}

	/**
	 * 在指定的单元格里填写数据
	 * 
	 * @param tableIndex
	 * @param cellRowIdx
	 * @param cellColIdx
	 * @param txt
	 */
	public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch.put(selection, "Text", txt);
	}

	/**
	 * 在指定的单元格里填写数据
	 * 
	 * @param tableIndex
	 * @param cellRowIdx
	 * @param cellColIdx
	 * @param txt
	 */
	public void putTxtToCellCenter(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
		Dispatch.call(cell, "Select");
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();
		Dispatch.put(alignment, "Alignment", "3");
		Dispatch.put(selection, "Text", txt);
	}

	/**
	 * 在当前文档拷贝剪贴板数据
	 * 
	 * @param pos
	 */
	public void pasteExcelSheet(String pos) {
		moveStart();
		if (this.findText(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}

	/**
	 * 在当前文档指定的位置拷贝表格
	 * 
	 * @param pos
	 *            当前文档指定的位置
	 * @param tableIndex
	 *            被拷贝的表格在word文档中所处的位置
	 */
	public void copyTable(String pos, int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch range = Dispatch.get(table, "Range").toDispatch();
		Dispatch.call(range, "Copy");
		if (this.findText(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}

	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的表格
	 * 
	 * @param anotherDocPath
	 *            另一个文档的磁盘路径
	 * @param tableIndex
	 *            被拷贝的表格在另一格文档中的位置
	 * @param pos
	 *            当前文档指定的位置
	 */
	public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
			// 所有表格
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			// 要填充的表格
			Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
			Dispatch range = Dispatch.get(table, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			if (this.findText(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的图片
	 * 
	 * @param anotherDocPath
	 *            另一个文档的磁盘路径
	 * @param shapeIndex
	 *            被拷贝的图片在另一格文档中的位置
	 * @param pos
	 *            当前文档指定的位置
	 */
	public void copyImageFromAnotherDoc(String anotherDocPath, int shapeIndex, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath).toDispatch();
			Dispatch shapes = Dispatch.get(doc2, "InLineShapes").toDispatch();
			Dispatch shape = Dispatch.call(shapes, "Item", new Variant(shapeIndex)).toDispatch();
			Dispatch imageRange = Dispatch.get(shape, "Range").toDispatch();
			Dispatch.call(imageRange, "Copy");
			if (this.findText(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/**
	 * 创建表格
	 * 
	 * @param pos
	 *            位置
	 * @param cols
	 *            列数
	 * @param rows
	 *            行数
	 */
	public void createTable(String pos, int numCols, int numRows) {
		if (findText(pos)) {
			Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
			Dispatch range = Dispatch.get(selection, "Range").toDispatch();
			Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols))
					.toDispatch();
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 在指定行前面增加行
	 * 
	 * @param tableIndex
	 *            word文件中的第N张表(从1开始)
	 * @param rowIndex
	 *            指定行的序号(从1开始)
	 */
	public void addTableRow(int tableIndex, int rowIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)).toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/**
	 * 在第1行前增加一行
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addFirstTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/**
	 * 在最后1行前增加一行
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addLastTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}

	/**
	 * 增加一行
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}

	/**
	 * 增加一列
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addCol(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/**
	 * 在指定列前面增加表格的列
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 * @param colIndex
	 *            制定列的序号 (从1开始)
	 */
	public void addTableCol(int tableIndex, int colIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		System.out.println(Dispatch.get(cols, "Count"));
		Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex)).toDispatch();
		// Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/**
	 * 在第1列前增加一列
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addFirstTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/**
	 * 在最后一列前增加一列
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void addLastTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "Last").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}

	/**
	 * 设置当前选定内容的字体
	 * 
	 * @param boldSize
	 * @param italicSize
	 * @param underLineSize
	 *            下划线
	 * @param colorSize
	 *            字体颜色
	 * @param size
	 *            字体大小
	 * @param name
	 *            字体名称
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(bold));
		Dispatch.put(font, "Italic", new Variant(italic));
		Dispatch.put(font, "Underline", new Variant(underLine));
		Dispatch.put(font, "Color", colorSize);
		Dispatch.put(font, "Size", size);
	}

	public void setFontCenter(String name) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();
		Dispatch.put(alignment, "Alignment", "3");
		Dispatch.call(selection, "TypeText", name);
	}

	/**
	 * 删除一行
	 * 
	 * @param tableIndex
	 *            word文档中的第N张表(从1开始)
	 */
	public void delRow(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Object temp1 = Dispatch.get(rows, "Count");
		String temp2 = temp1.toString();
		int count = Integer.parseInt(temp2);
		while (count > 1) {
			Dispatch row = Dispatch.get(rows, "Last").toDispatch();
			Dispatch.call(row, "Delete");
			rows = Dispatch.get(table, "Rows").toDispatch();
			temp1 = Dispatch.get(rows, "Count");
			temp2 = temp1.toString();
			count = Integer.parseInt(temp2);
		}
	}

	public void setProp(String sName, String sValue) {
		Dispatch props = Dispatch.get(document, "CustomDocumentProperties").toDispatch();
		Dispatch prop = Dispatch.call(props, "Item", sName).toDispatch();
		String sOldVal = Dispatch.get(prop, "Value").toString();
		if (!sOldVal.equals(sValue))
			Dispatch.put(prop, "Value", sValue);
	}

	/**
	 * @param nType:
	 *            1, number; 2,bool; 3,date; 4,str;
	 */
	public void addProp(String sName, int nType, String sValue) {
		Dispatch props = Dispatch.get(document, "CustomDocumentProperties").toDispatch();
		Dispatch prop = null;
		try {
			prop = Dispatch.call(props, "Item", sName).toDispatch();
		} catch (Exception e) {
			prop = null;
		}
		if (prop != null)
			return;
		// 1, number; 2,bool; 3,date; 4,str;
		prop = Dispatch.call(props, "Add", sName, false, nType, sValue).toDispatch();
		Dispatch.put(prop, "Value", sValue);
	}

	public String getProp(String sName) {
		String sValue = null;
		Dispatch props = Dispatch.get(document, "CustomDocumentProperties").toDispatch();
		Dispatch prop = Dispatch.call(props, "Item", sName).toDispatch();
		sValue = Dispatch.get(prop, "Value").toString();
		@SuppressWarnings("unused")
		String sType = Dispatch.get(prop, "Type").toString();
		try {
			Dispatch prop0 = Dispatch.call(document, "CustomDocumentProperties", sName).toDispatch();
			sValue = Dispatch.get(prop0, "Value").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sValue;
	}

	public void fackChange() {
		Dispatch selection = Dispatch.call(document, "Range", 0, 0).toDispatch();
		Dispatch.call(selection, "InsertBefore", "A");
		Dispatch.call(selection, "Select");
		Dispatch.call(selection, "Delete");
	}

	/**
	 * 保护当前档,如果不存在, 使用expression.Protect(Type, NoReset, Password)
	 * 
	 * @param pwd
	 * @param type
	 *            WdProtectionType 常量之一(int 类型，只读)： 1-wdAllowOnlyComments 仅批注
	 *            2-wdAllowOnlyFormFields 仅填写窗体 0-wdAllowOnlyRevisions 仅修订
	 *            -1-wdNoProtection 无保护, 3-wdAllowOnlyReading 只读
	 * 
	 */
	public void protectedWord(String pwd, String type) {
		String protectionType = Dispatch.get(document, "ProtectionType").toString();
		if (protectionType.equals("-1")) {
			Dispatch.call(document, "Protect", Integer.parseInt(type), new Variant(true), pwd);
		}
	}

	/**
	 * 解除文档保护,如果存在
	 * 
	 * @param pwd
	 *            WdProtectionType 常量之一(int 类型，只读)： 1-wdAllowOnlyComments 仅批注
	 *            2-wdAllowOnlyFormFields 仅填写窗体 0-wdAllowOnlyRevisions 仅修订
	 *            -1-wdNoProtection 无保护, 3-wdAllowOnlyReading 只读
	 * 
	 */
	public void unProtectedWord(String pwd) {
		String protectionType = Dispatch.get(document, "ProtectionType").toString();
		if (!protectionType.equals("0") && !protectionType.equals("-1")) {
			Dispatch.call(document, "Unprotect", pwd);
		}
	}

	/**
	 * 返回文档的保护类型
	 * 
	 * @return
	 */
	public String getProtectedType() {
		return Dispatch.get(document, "ProtectionType").toString();
	}

	/**
	 * 设置word文档安全级别
	 * 
	 * @param value
	 *            1-msoAutomationSecurityByUI 使用“安全”对话框指定的安全设置。
	 *            2-msoAutomationSecurityForceDisable
	 *            在程序打开的所有文件中禁用所有宏，而不显示任何安全提醒。 3-msoAutomationSecurityLow
	 *            启用所有宏，这是启动应用程序时的默认值。
	 */
	public void setAutomationSecurity(int value) {
		word.setProperty("AutomationSecurity", new Variant(value));
	}

	/**
	 * 在word中插入标签 labelName是标签名，labelValue是标签值
	 * 
	 * @param labelName
	 * @param labelValue
	 */
	public void insertLabelValue(String labelName, String labelValue) {

		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName).getBoolean();
		if (isExist == true) {
			Dispatch rangeItem1 = Dispatch.call(bookMarks, "Item", labelName).toDispatch();
			Dispatch range1 = Dispatch.call(rangeItem1, "Range").toDispatch();
			String bookMark1Value = Dispatch.get(range1, "Text").toString();
			System.out.println("书签内容：" + bookMark1Value);
		} else {
			System.out.println("当前书签不存在,重新建立!");
			// TODO 先插入文字，再查找选中文字，再插入标签
			this.insertText(labelValue);
			// this.find(labelValue);//查找文字，并选中
			this.setFont(true, true, true, "102,92,38", "20", "", true);
			Dispatch.call(bookMarks, "Add", labelName, selection);
			Dispatch.call(bookMarks, "Hidden", labelName);
		}
	}

	/**
	 * 在word中插入标签 labelName是标签名
	 * 
	 * @param labelName
	 */
	public void insertLabel(String labelName) {

		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName).getBoolean();
		if (isExist == true) {
			System.out.println("书签已存在");
		} else {
			System.out.println("建立书签：" + labelName);
			Dispatch.call(bookMarks, "Add", labelName, selection);
		}
	}

	/**
	 * 查找书签
	 * 
	 * @param labelName
	 * @return
	 */
	public boolean findLabel(String labelName) {
		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName).getBoolean();
		if (isExist == true) {
			return true;
		} else {
			System.out.println("当前书签不存在!");
			return false;
		}
	}

	/**
	 * 模糊查找书签,并返回准确的书签名称
	 * 
	 * @param labelName
	 * @return
	 */
	public String findLabelLike(String labelName) {
		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		int count = Dispatch.get(bookMarks, "Count").getInt(); // 书签数
		Dispatch rangeItem = null;
		String lname = "";
		for (int i = 1; i <= count; i++) {
			rangeItem = Dispatch.call(bookMarks, "Item", new Variant(i)).toDispatch();
			lname = Dispatch.call(rangeItem, "Name").toString();// 书签名称
			if (lname.startsWith(labelName)) {// 前面匹配
				// return lname.replaceFirst(labelName, "");//返回后面值
				return lname;
			}
		}
		return "";
	}

	/**
	 * 模糊删除书签
	 * 
	 * @param labelName
	 */
	public void deleteLableLike(String labelName) {
		Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
		int count = Dispatch.get(bookMarks, "Count").getInt(); // 书签数
		Dispatch rangeItem = null;
		String lname = "";
		for (int i = 1; i <= count; i++) {
			rangeItem = Dispatch.call(bookMarks, "Item", new Variant(i)).toDispatch();
			lname = Dispatch.call(rangeItem, "Name").toString();// 书签名称
			if (lname.startsWith(labelName)) {// 前面匹配
				Dispatch.call(rangeItem, "Delete");
				count--;// 书签已被删除，书签数目和当前书签都要相应减1，否则会报错:集合找不到
				i--;
			}
		}
	}

	/**
	 * 获取书签内容
	 * 
	 * @param labelName
	 * @return
	 */
	public String getLableValue(String labelName) {
		if (this.findLabel(labelName)) {
			Dispatch bookMarks = Dispatch.call(document, "Bookmarks").toDispatch();
			Dispatch rangeItem1 = Dispatch.call(bookMarks, "Item", labelName).toDispatch();
			Dispatch range1 = Dispatch.call(rangeItem1, "Range").toDispatch();
			Dispatch font = Dispatch.get(range1, "Font").toDispatch();
			Dispatch.put(font, "Hidden", new Variant(false)); // 显示书签内容
			String bookMark1Value = Dispatch.get(range1, "Text").toString();
			System.out.println("书签内容：" + bookMark1Value);
			// font = Dispatch.get(range1, "Font").toDispatch();
			// Dispatch.put(font, "Hidden", new Variant(true)); //隐藏书签内容
			return bookMark1Value;
		}
		return "";
	}

	/**
	 * 打印当前word文档
	 * 
	 */
	public void printFile() {
		if (document != null) {
			Dispatch.call(document, "PrintOut");
		}
	}

}
