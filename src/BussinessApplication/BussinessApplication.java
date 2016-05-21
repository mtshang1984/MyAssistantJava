package BussinessApplication;

import LinuxAutomation.Host;
import LinuxAutomation.ScientificBrowsing;
import LinuxAutomation.VpsServer;

public class BussinessApplication {

	public BussinessApplication() {
		// TODO Auto-generated constructor stub
	}


	/**添加shadowsocks账号*/
	public void addShadowsocksAccount(Host host,int port,String password){
		VpsServer vpsServer=new VpsServer(host);
		vpsServer.initialize();
		vpsServer.addUser(port, password);
		vpsServer.close();
		
	}
	/**删除shadowsocks账号*/
	public void deleteShadowsocksAccount(Host host,int port){
		VpsServer vpsServer=new VpsServer(host);
		vpsServer.initialize();
		
		vpsServer.delteUser(port);
		vpsServer.close();
		
	}

	/**提供加速过的shadowsocks账号*/
	public void changeShadowsocksServer(Host vpsSpeedHost, Host vpsHost){
		ScientificBrowsing scientificBrowsing = new ScientificBrowsing(vpsSpeedHost);
		scientificBrowsing.initialize();
		scientificBrowsing.modifyServer(vpsHost.hostname, true);
		scientificBrowsing.close();
		
	}
	/**提供加速过的shadowsocks账号*/
	public void addSpeedShadowsocksAccount(Host host,String name, int port,int mapPort){
		ScientificBrowsing scientificBrowsing = new ScientificBrowsing(host);
		scientificBrowsing.initialize();
		scientificBrowsing.addPortMap(name,port,mapPort,true);
		scientificBrowsing.close();
		
	}
	/**删除提供加速过的shadowsocks账号*/
	public void deleteSpeedShadowsocksAccount(Host host,String name){
		ScientificBrowsing scientificBrowsing = new ScientificBrowsing(host);
		scientificBrowsing.initialize();
		scientificBrowsing.removePortMap(name,true);
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
//		addShadowsocksAccount(443, "!abcd1234");
//		addShadowsocksAccount(445, "hongqin");
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
