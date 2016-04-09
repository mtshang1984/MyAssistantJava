package BussinessApplication;

import LinuxAutomation.ScientificBrowsing;
import LinuxAutomation.VpsServer;

public class BussinessApplication {

	public BussinessApplication() {
		// TODO Auto-generated constructor stub
	}

	/**添加shadowsocks账号*/
	public void addShadowsocksAccount(int port,String password){
		VpsServer vpsServer=new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
		vpsServer.initialize();
		vpsServer.addUser(port, password);
		vpsServer.close();
		
	}

	/**删除shadowsocks账号*/
	public void deleteShadowsocksAccount(int port){
		VpsServer vpsServer=new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
		vpsServer.initialize();
		vpsServer.delteUser(port);
		vpsServer.close();
		
	}
	/**提供加速过的shadowsocks账号*/
	public void addSpeedShadowsocksAccount(String name, int port,int mapPort){
		ScientificBrowsing scientificBrowsing = new ScientificBrowsing("www.ijushan.com",22,"root","ShMT0659","smt","ShMT0659");
		scientificBrowsing.initialize();
		scientificBrowsing.addPortMap(name,port,mapPort);
		scientificBrowsing.close();
		
	}
	/**删除提供加速过的shadowsocks账号*/
	public void deleteSpeedShadowsocksAccount(String name){
		ScientificBrowsing scientificBrowsing = new ScientificBrowsing("www.ijushan.com",22,"root","ShMT0659","smt","ShMT0659");
		scientificBrowsing.initialize();
		scientificBrowsing.removePortMap(name);
		scientificBrowsing.close();
		
	}
	/**测试代码*/
	public void testCode(){

//		VpsServer vpsServer=new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
//		vpsServer.initialize();
//		vpsServer.installPackageByAptget("jq crudini", true);
//		vpsServer.uninstallShadowsocksPythonServer();
//		vpsServer.installShadowsocksPythonServer();
//		vpsServer.close();
//		addShadowsocksAccount(444, "chenyuqing");
		addShadowsocksAccount(443, "!abcd1234");
//		addSpeedShadowsocksAccount("chenyuqing",444,4440);
//		vpsServer.installShadowsocksPythonServer();
//		vpsServer.close();
//		addShadowsocksAccount(448, "zhuying");
//		addSpeedShadowsocksAccount("my_ss",443,4430);
//		addSpeedShadowsocksAccount("ssh",22,220);
//		addSpeedShadowsocksAccount("chenyuqing",444,4440);
//		deleteSpeedShadowsocksAccount("chenyuqing");

//		addShadowsocksAccount(448, "zhuying");
//		deleteShadowsocksAccount(448);
//		VpsServer vpsServer=new VpsServer("proxy.ijushan.com",22 ,"root","ShMT0659","smt","ShMT0659");
//		vpsServer.initialize();
////		vpsServer.setIptable();
////		vpsServer.addUser(443, "!abcd1234");
//		vpsServer.addUser(444, "chenyuqing");
////		vpsServer.addUser(448, "zhuying");
////		vpsServer.delteUser(448);
//		vpsServer.close();
		
	}
}
