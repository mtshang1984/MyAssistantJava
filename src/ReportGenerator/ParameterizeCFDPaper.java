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
		wordAutomation.createNewDocumentUseTemplete("d:\\00_yun_dir\\04_study\\03_paper\\03_my_paper\\06_参数化\\航空动力学报\\论文模板新.dotx");
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
		wordAutomation.insertParagraph("Robert E M[1]等人采用参数化数值模拟方法对PW6000燃烧室出口温度分布进行了优化。Mark K L等人开发了可用于燃烧室详细设计的参数化数值模拟分析工具。石梦华等人研究用于CFD模拟的燃烧室参数化建模方法，段冬霞等人将参数化数值模拟方法应用于旋流器的流场和设计研究中。然而以上的研究大部分只针对于少数参数可变的情况，应用的场景受到限制。实际上在燃烧室研制中，研究的设计参数非常广泛，需要一种更为通用的参数化数值模拟工具。","正文");	
		wordAutomation.insertParagraph("本文提出了可用于燃烧室不同设计阶段、不同零组件设计的更为通用的参数化数值模拟方法。首先对燃烧室各种结构构型进行总结并抽象出特征参数进行描述，然后根据这些特征参数，开发出相应的网格生成、计算设置和后处理代码，以处理各种参数的变化。同时开发多算例并发运行控制程序，结合高性能计算平台，同时对优化范围内的选择的参数组合进行计算分析。最后将该方法应用于多种类型燃烧室设计中。","正文");	
		wordAutomation.insertParagraph("实现方法","标题 1");			
		wordAutomation.insertParagraph("思路","标题 2");			
		wordAutomation.insertParagraph("考虑到设计与CFD分析尽量耦合，设计结果输出给CFD作为分析的输入，CFD计算完成后，输出进行优化设计或者方案筛选所需的参数。为了提高运行过程的效率，所有的前后处理及计算任务尽可能调用远程高性能计算机并行执行，需要调用使用UG、Office软件的部分只能在Windows环境下的本机运行。整个运行过程细分为12个模块。","正文");	
		wordAutomation.insertParagraph("1 燃烧室气动热力设计：通过零维或一维的设计工具确定燃烧室气动热力方案。根据优化或筛选的需求，同时设计多个方案以用于下述CFD分析。","正文");	
		wordAutomation.insertParagraph("2 模型生成模块：根据燃烧室的设计结果，基于燃烧室参数化基准模型，生成相关的用于计算的CFD模型。","正文");	
		wordAutomation.insertParagraph("3 模型转换模块：将前一步生成UG模型，确保无损地转换为tin格式，以用于下一步网格生成。","正文");	
		wordAutomation.insertParagraph("4 网格生成：基于tin格式的燃烧室模型，结合软件内置的网格模板及网格尺度控制算法，生成满足下一步分析要求的网格。","正文");	
		wordAutomation.insertParagraph("5 获取边界名称：Fluent导入生成的网格后，其中的边界名称会发生变化,需要获取更新后的边界名称，以便后续生成合适的算例设置、计算和后处理脚本。","正文");	
		wordAutomation.insertParagraph("6 分割周期性边界并获取边界名称：对于使用周期性的算例，如果需要复制网格计算多个周期性的区域，需要利用Fluent分割周期性边界，再用交界面连接相邻区域。在Fluent中分割周期性边界后其名称会自动改变，需要获得更新后的名称，以用于生成算例设置脚本。","正文");	
		wordAutomation.insertParagraph("7 设置算例：根据不同的计算对象的不同和工况参数,设置相应的湍流、两相流、燃烧和排放预测模型及其边界条件，并给出流场初始化方法、计算控制参数及收敛判据。","正文");	
		wordAutomation.insertParagraph("8 计算：调用远程高性能计算机开发对所有算例初始化流场并计算。","正文");	
		wordAutomation.insertParagraph("9 转换计算结果文件格式：虽然Fluent数据格式可直接被大部分后处理软件直接读取，但是采用火焰数据库方法的算例和离散相数据，必须经格式转换后，才能被后处理识别出其组分场和离散相计算结果","正文");	
		wordAutomation.insertParagraph("10 统计结果：根据不同燃烧室构型，生成不同的后处理脚本，利用Fluent软件进行参数统计。","正文");	
		wordAutomation.insertParagraph("11 生成计算结果图片：生成与燃烧室构型相匹配的结果图片生成脚本，采用CFD Post进行处理。","正文");	
		wordAutomation.insertParagraph("12 生成计算分析报告：为了便于阅读和分析，按照约定的习惯整理定量的统计结果和计算结果图片，形成报告。","正文");	
		wordAutomation.insertParagraph("除了第一步燃烧室气动热力设计需要设计人员根据经验进行多方案设计以及程序尚未覆盖的结构及网格模板外，其余所有步骤均通过计算机无缝连接自动执行，因此可以避免大量的重复操作，缩短CFD工作周期。另外由于网格数量较多时，前后处理也需要大量的计算资源，因此除了第2、3和12等需要Windows运行环境且资源需求少的步骤外，其余所有步骤均利用远程高性能计算机并行执行，因此运行效率远超Ansys Workbench等采用本机进行前后处理的方法。同时每一个模块均会根据燃烧室构型和工况参数的不同，生成相适应的脚本，避免了传统参数化数值模拟方法只适用特定构型的问题。","正文");	
		wordAutomation.insertParagraph("参数化建模","标题 2");			
		wordAutomation.insertParagraph("实际的燃烧室结构模型是非常复杂的，包含了大量的细节结构：例如螺栓、安装座和支板等。这些结构细节对于流场的影响是有限的，却给网格生成带来非常大的麻烦，在建模时这些结构必须被移除掉。用于CFD分析的参数化建模主要基于气动设计的燃烧室流道及各零组件的设计获得的气体通道进行参数化建模。为了后期的优化设计，所有的设计参数应能体现在模型的可变参数中。提前建立几种不同构型的燃烧室流道、扩压器、旋流器、帽罩和喷油杆等参数化模型，以便根据设计结果组合形成算例所需的模型。由于这些模型可重用，大大缩短以往用于几何模型简化的时间。例如下图所示，根据流道设计结果的23个设计参数来驱动燃烧室的流道模型。其它的零组件通过与流道之间的位置关系，定位安装至燃烧室流道上。这些零组件模型本身也是设计结果全部关联，并被其驱动的。这样就将燃烧室气动热力及CFD模型耦合起来了。","正文");
		wordAutomation.insertParagraph("参数化基准模型的变化可以通过两种方式，一种是基于表达式方式，通过更新表达式参数的值来生成新模型。这种方式需要在UG中建立若干约束充分且合理的草图，将可变尺寸与表达参数关联起来，三维模型再通过这些草图进一步生成。另外一种是基于样条曲线的控制点坐标更新方式。像扩压器等通过样条曲线造型的结构，单独给其建立包含样条曲线的草图，并对每一条曲线进行命名，以例程序通过名称找到相应的线条进行更新。","正文");
		wordAutomation.insertParagraph("另外还有非常重要的一步是给几何体的面进行标记，以便被ICEM CFD识别出来。以往采用Parasolid等中间格式导入ICEM CFD时，需要在ICEM CFD中进行面的分组命名。模型更新后，命名要重新来过，效率十分低下，且无法自动化。本文采用Ansys的UG命名插件，在UG中标记所有的面名称,这些名称在基准模型的参数变化后仍然会保留下来。因些在建立流道和零组件参数化基准模型时，一并完成面命名，即可使衍生模型自动完成命名。","正文");
		
		wordAutomation.insertParagraph("自动网格生成","标题 2");			
		wordAutomation.insertParagraph("相对于四面体网格，采用六面体划分网格可以在减少网格数量的同时，保持离散的精度。但对于复杂的结构，六面体是非常难以生成的。因此对于结构特征比较规律的扩压器、火焰筒和环腔区域采用六面体网格，对于头部等构型较为复杂的区域采用四面体网格。不同区域之间采用交界面连接起来。","正文");
		wordAutomation.insertParagraph("建立一系列六面体网格生成的模板，这样对于采用六面体网格的区域，根据其构型选择合适的模板即可自动生成网可靠。对于采用四面体网格的区域，根据网格面的名称，自动选择合适的网格尺度和变化规律，便可进行网格生成。网格模板和网格尺度控制算法决定了网格的质量，需要不断根据计算结果积累数据改进方法。","正文");
		wordAutomation.insertParagraph("算例设置与计算","标题 2");			
		wordAutomation.insertParagraph("对燃烧室的流场分析，RANS方法在方案设计阶段仍占据主导作用。本文湍流模型选用Realizable k-e模型，壁面采用增强壁面处理。同时对于涉及到扩压器流动离的分析，补充采用SST模型进行对比。两相流模型采用颗粒随机轨道模型，对于不同形式的喷嘴，选择不同的模型给定雾化边界条件。燃烧模型采用部分预混燃烧模型，根据不同的工况参数，基于航空煤油23步化学反应机理生成小火焰数据库。避免了以往预先生成的数据库，因为工况参数变化而不适用的问题。排放模型采用热力型NOx模型，并考虑温度脉动对于排放生成的影响。","正文");
		wordAutomation.insertParagraph("求解过程分两个步骤，首先采用SIMPLE算法和一阶迎风离散格式进行计算，利用其稳定性快速获得初始场。然后采用COUPLE方法和二阶迎风格式以较少的步数获得较高精度的收敛结果。","正文");
		wordAutomation.insertParagraph("后处理","标题 2");			
		wordAutomation.insertParagraph("对于计算所关心的定量结果，统计流量分配、压力损失、旋流数、特征速度、回流区尺寸、出口排放、温度分布等参数，根据燃烧室构型和结构参数，生成对应的脚本文件进行统计。其它的后处理软件虽然也可以进行定量的统计，但由于统计方法可能与Fluent不同，会造结果存在差异，所以没有被采用。","正文");
		wordAutomation.insertParagraph("采用CFD Post，根据燃烧室的构型和结构参数创建相应的截面位置，建立该位置的压力、速度、温度、组分场的云图，同时建立流线、颗粒迹线等显示图片。为了获得较好的显示效果，根据构型的参数的变化，自动设置合适的视角和显示比例。最后将所有的图片加入CFD Post模板中一同导出。","正文");
    wordAutomation.insertParagraph("并发运行控制","标题 2");
	
		wordAutomation.insertParagraph("本机采用单核执行运行控制，然而同时控制大量的并行算例，因此串行的方式轮流执行各个算例。算例的每个步骤都需要时间，所处于的运行状态也不同。因此采用流水线设立检查点的方法来进行控制。程序不断访问检查点，检查任务的状态是否满足通过的条件，一旦通过就立即放行进入到下一个工作步骤。","正文");
		wordAutomation.insertParagraph("检查点是程序正常运行的关键，在这里要不光要检查任务是否在运行或者获得计算结果，重要的是检查计算过程和结果是否合理正常。因为当算例数量巨大的情况，难以靠人工的方法去监控算例。对于高性能计算机硬件或软件造成的异常以及其它程序可以处理的异常，应立即进行处理。对于程序无法处理的异常，提示设计人员进行人工处理。","正文");
		wordAutomation.insertParagraph("应用","标题 1");				
		wordAutomation.insertParagraph("头部方案筛选","标题 2");			
		wordAutomation.insertParagraph("帽罩优化设计","标题 2");			
		wordAutomation.insertParagraph("扩压器优化设计","标题 2");			
		wordAutomation.insertParagraph("结论","标题 1");
	
		//参考文献		
		wordAutomation.insertParagraph("参考文献","标题 1");			
		wordAutomation.insertParagraph("[1] Robert E M, et al. Application of an advanced CFD-based analysis system to the PW6000 Combustor to optimize exit temperature distribution-Part I: Description and validation of the analysis tool[C]. Proceedings of ASME Turbo Expo, 2001-0062","正文");	
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
