package com.shinhan.AutoBlock;

import java.io.File;
import java.io.FileWriter;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;
import com.shinhan.core.Transaction;
import com.shinhan.core.Block;
import com.shinhan.core.TransactionOutput;
import com.shinhan.core.Wallet;
import com.shinhan.util.StringUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
		
	//blockchain ArrayList
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>(); //list of all unspent transactions.
	
	public static int difficulty = 3;
	public static float minimumTransaction = 0.1f;
	
	public static Wallet walletA;
	public static Wallet walletB;

	
	public static HashMap<String, String>map = new HashMap<String, String>();
	public static ArrayList<HashMap<String, String>>list = new ArrayList<HashMap<String, String>>();
		
	
	@RequestMapping(value = "/Start", method = {RequestMethod.GET, RequestMethod.POST})
	public String Start(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model) throws Exception{
		
		System.out.println("/Start");
		
		return "Start";
	}
	
	@RequestMapping(value = "/result", method = {RequestMethod.GET, RequestMethod.POST})
	public String result(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model) throws Exception{
		
		System.out.println("/result");
		
		return "result";
	}
	
	
	@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(HttpServletRequest request, Locale locale, Model model) {
		
		System.out.println("Home Start");
		String strEmpName = "";
		strEmpName = request.getParameter("name");
		
		System.out.println(strEmpName);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		
		System.out.println(list.size());
		list.remove(map);
		System.out.println(list.size());
		//일단 그냥동작 하도록.
		execQuery(strEmpName);
		
		String strhashNo = "";
		strhashNo = fnStart();
		
		
		System.out.println(list.size());
		
		
		model.addAttribute("IndeNO", 		list.get(0).get("IndeNO") );
		model.addAttribute("CerNO", 		list.get(0).get("CerNO") );
		model.addAttribute("EMP_NAME", 		list.get(0).get("EMP_NAME") );
		model.addAttribute("LoansType", 	list.get(0).get("LoansType") );
		model.addAttribute("purpose", 		list.get(0).get("purpose") );
		model.addAttribute("current", 		list.get(0).get("current") );
		model.addAttribute("LimitAmt", 		list.get(0).get("LimitAmt") );
		model.addAttribute("RemainAmt", 	list.get(0).get("RemainAmt") );
		model.addAttribute("LoanDate", 		list.get(0).get("LoanDate") );
		model.addAttribute("RepayDate", 	list.get(0).get("RepayDate") );
		model.addAttribute("FinalRate", 	list.get(0).get("FinalRate") );
		model.addAttribute("HashNo", 		strhashNo );
		model.addAttribute("NowDate", 		formattedDate );
		
		
		return "home";
	}
	
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;

		
		System.out.println("Block Size : ");
		System.out.println(blockchain.size());
		
		//전체 블럭을 체크합니다.
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			//현재 블럭의 hash가 맞는지 체크합니다.
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			
			//이전 블럭의 hash값과 동일한지 체크합니다.
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	
	public static String fnStart() {
			
		
	String strHashNo = "";
		
	blockchain.removeAll(blockchain);
	//mariadb connection
	
	//DBConnection_maria ConDb = new DBConnection_maria();
	//ConDb.connectDB();
	//execQuery();
	
	//Setup Bouncey castle as a Security Provider
	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
	
	//Create the new wallets
	walletA = new Wallet();
	walletB = new Wallet();

	walletA.generateKeyPair();
	walletB.generateKeyPair();
	
	//Test public and private keys
	System.out.println("Private and public keys:");
	System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
	System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
	
	//테스트를 위한 Transaction생성(WalletA -> walletB : 100)  
	Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 100, null);
	//생성된 Transaction에 서명합니다.
	transaction.generateSignature(walletA.privateKey);
	
	//서명한 Transaction을 검증합니다.
	System.out.println("Is this Transaction Verify? " + transaction.verifiySignature());
	
	
	//초기 블럭을 만듭니다.
	blockchain.add(new Block("Genesis block", "first" ,"0"));
	//System.out.println("\nTrying to Mine Genesis block!");
	blockchain.get(0).mineBlock(difficulty);
	
	String strIndeNo = "";
	strIndeNo = list.get(0).get("IndeNO");
	blockchain.add(new Block("IndeNO",		strIndeNo,						blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 1 );
	blockchain.get(1).mineBlock(difficulty);

	blockchain.add(new Block("CerNO",		list.get(0).get("CerNO"),		blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 2 );
	blockchain.get(2).mineBlock(difficulty);
	
	String strEMP_NO = list.get(0).get("EMP_NAME"); 
	
	blockchain.add(new Block("EMP_NAME",	strEMP_NO				,		blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 3 );
	blockchain.get(3).mineBlock(difficulty);
	
	blockchain.add(new Block("LoansType",	list.get(0).get("LoansType"),	blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 4 );
	blockchain.get(4).mineBlock(difficulty);
	
	blockchain.add(new Block("purpose",		list.get(0).get("purpose"),		blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 5 );
	blockchain.get(5).mineBlock(difficulty);
	
	blockchain.add(new Block("current",		list.get(0).get("current"),		blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 6 );
	blockchain.get(6).mineBlock(difficulty);
	
	blockchain.add(new Block("LimitAmt",	list.get(0).get("LimitAmt"),	blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 7 );
	blockchain.get(7).mineBlock(difficulty);
	
	blockchain.add(new Block("RemainAmt",	list.get(0).get("RemainAmt"),	blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 8 );
	blockchain.get(8).mineBlock(difficulty);
	
	blockchain.add(new Block("LoanDate",	list.get(0).get("LoanDate"),	blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 9 );
	blockchain.get(9).mineBlock(difficulty);
	
	blockchain.add(new Block("RepayDate",	list.get(0).get("RepayDate"),	blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 10 );
	blockchain.get(10).mineBlock(difficulty);
	
	blockchain.add(new Block("FinalRate",list.get(0).get("FinalRate"),blockchain.get(blockchain.size()-1).hash));
	//System.out.printf("\nTrying to Mine block #%d", 11 );
	blockchain.get(11).mineBlock(difficulty);
	
	
	//중간에 다른값을 변경
	//blockchain.set(11,  new Block("hacking","TEST",blockchain.get(4).hash));
	
	
	//전체 블럭을 출력합니다.
	String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
	System.out.println("Block list : ");
	System.out.println(blockchainJson);
	
	//전체 blockchain이 정상인지 체크합니다.
	System.out.println("\nBlockchain is Valid : " + isChainValid());
	
	if(isChainValid()) {
		// table 발급번호 로 hash 값 업데이트  
		strHashNo = StringUtil.applySha256(strIndeNo);
		execUpdateQuery(strEMP_NO, strHashNo);
	}else {
		// 실패 페이지로 이동
		strHashNo = "";
	}
	
	
	long timestamp = new Date().getTime();
	
	System.out.println("\nWrite File Start");
	
	try {
		File file = new File("Black_Doc_" +  timestamp +  ".txt");
		System.out.println(file);
		FileWriter fw = new FileWriter(file, true);
		fw.write(blockchainJson.toString());
		fw.flush();
		fw.close();
	}catch(Exception ex) {
		ex.getMessage();
		System.out.println("\nWrite File Error : " + ex.getMessage());
	}
	return strHashNo;	
	}
	
	public static void execUpdateQuery(String strEmpName, String strHash) {
		Connection conn = null;
		Statement stmt = null;
		int rs = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/ujinehr";
			conn = DriverManager.getConnection(url, "jtdba", "Shinhan1!");
			System.out.println("DB 연결 성공");

		 String sql = " update Block_Indebtedness set hashNo = '" + strHash +  "'"
				  + " where EMP_NAME = '" + strEmpName + "'";
				  
		
		 System.out.println(sql);
		
		// 객체 생성
		stmt = conn.createStatement();
	
		// SQL실행
		rs = stmt.executeUpdate(sql);	
		// 결과값 출력
	
		
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
			System.out.println("mariaDB Driver를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Database 연결중 에러가 발생 했습니다.");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	@RequestMapping(value = "/document", method = {RequestMethod.GET, RequestMethod.POST})
	public String document(HttpServletRequest request, Locale locale, Model model) {
		
		System.out.println("document Start");
		String strEmpName 	= "";
		String strHash 		= "";
		
		strEmpName 	= request.getParameter("name");
		strHash 	= request.getParameter("hash");
		
		System.out.println(strEmpName);
		System.out.println(strHash);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
			
		list.remove(map);
	
		execQueryHash(strEmpName, strHash);
		
		model.addAttribute("IndeNO", 		list.get(0).get("IndeNO") );
		model.addAttribute("CerNO", 		list.get(0).get("CerNO") );
		model.addAttribute("EMP_NAME", 		list.get(0).get("EMP_NAME") );
		model.addAttribute("LoansType", 	list.get(0).get("LoansType") );
		model.addAttribute("purpose", 		list.get(0).get("purpose") );
		model.addAttribute("current", 		list.get(0).get("current") );
		model.addAttribute("LimitAmt", 		list.get(0).get("LimitAmt") );
		model.addAttribute("RemainAmt", 	list.get(0).get("RemainAmt") );
		model.addAttribute("LoanDate", 		list.get(0).get("LoanDate") );
		model.addAttribute("RepayDate", 	list.get(0).get("RepayDate") );
		model.addAttribute("FinalRate", 	list.get(0).get("FinalRate") );
		model.addAttribute("NowDate", 		formattedDate );
		
		return "document";
	}
	
	
	
	public static void execQuery(String strEmpName) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/ujinehr";
			conn = DriverManager.getConnection(url, "jtdba", "Shinhan1!");
			System.out.println("DB 연결 성공");

		 String sql = " select "
				  + " IndeNO, "
				  + " CerNO, "
				  + " EMP_NAME, "
				  + " LoansType, "
				  + " purpose, "
				  + " current, "
				  + " FORMAT(LimitAmt, 0) AS LimitAmt, "
				  + " FORMAT(RemainAmt,0) AS RemainAmt, "
				  + " LoanDate, "
				  + " RepayDate, "
				  + " FinalRate "
				  + " from Block_Indebtedness where EMP_NAME = '" +strEmpName+  "' ";
		
		 System.out.println(sql);
		
		// 객체 생성
		stmt = conn.createStatement();
	
		// SQL실행
		rs = stmt.executeQuery(sql);	
		// 결과값 출력
		
		String IndeNO 		= "";	//발급 번호 
		String CerNO 		= "";	//인증 번호 
		String EMP_NAME 	= "";	//이름 
		String LoansType 	= "";	//대출 종류 
		String purpose 		= "";	//자금 용도 
		String current 		= "";	//통화 
		String LimitAmt 	= "";	//한도금액 
		String RemainAmt 	= "";	//잔액 
		String LoanDate 	= "";	//대출 일자 
		String RepayDate 	= "";	//상황기일 
		String FinalRate 	= "";	//최종 이자 
		
	
		while (rs.next()) {
			
			IndeNO 		= rs.getString("IndeNO");
			CerNO 		= rs.getString("CerNO");
			EMP_NAME 	= rs.getString("EMP_NAME");
			LoansType 	= rs.getString("LoansType");
			purpose 	= rs.getString("purpose");
			current 	= rs.getString("current");
			LimitAmt 	= rs.getString("LimitAmt");
			RemainAmt 	= rs.getString("RemainAmt");
			LoanDate 	= rs.getString("LoanDate");
			RepayDate 	= rs.getString("RepayDate");
			FinalRate 	= rs.getString("FinalRate");
			
			System.out.println(IndeNO);
			System.out.println(CerNO);
			System.out.println(EMP_NAME);
			System.out.println(LoansType);
			System.out.println(purpose);
			System.out.println(current);
			System.out.println(LimitAmt);
			System.out.println(RemainAmt);
			System.out.println(LoanDate);
			System.out.println(RepayDate);
			System.out.println(FinalRate);
		}
		
		map.put("IndeNO", 		IndeNO);
		map.put("CerNO", 		CerNO);
		map.put("EMP_NAME", 	EMP_NAME);
		map.put("LoansType",	LoansType);
		map.put("purpose", 		purpose);
		map.put("current", 		current);
		map.put("LimitAmt",		LimitAmt);
		map.put("RemainAmt", 	RemainAmt);
		map.put("LoanDate", 	LoanDate);
		map.put("RepayDate",	RepayDate);
		map.put("FinalRate", 	FinalRate);
		
		list.add(map);
		
		//System.out.println(list.get(0).get("IndeNO"));
		
		
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
			System.out.println("mariaDB Driver를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Database 연결중 에러가 발생 했습니다.");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
public static void execQueryHash(String strEmpName, String strHash) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/ujinehr";
			conn = DriverManager.getConnection(url, "jtdba", "Shinhan1!");
			System.out.println("DB 연결 성공");

		 String sql = " select "
				  + " IndeNO, "
				  + " CerNO, "
				  + " EMP_NAME, "
				  + " LoansType, "
				  + " purpose, "
				  + " current, "
				  + " FORMAT(LimitAmt, 0) AS LimitAmt, "
				  + " FORMAT(RemainAmt,0) AS RemainAmt, "
				  + " LoanDate, "
				  + " RepayDate, "
				  + " FinalRate "
				  + " from Block_Indebtedness where EMP_NAME = '" +strEmpName+  "' "
				  + " and hashno = '" +strHash+  "' ";
				  
		 System.out.println(sql);
		
		// 객체 생성
		stmt = conn.createStatement();
	
		// SQL실행
		rs = stmt.executeQuery(sql);	
		// 결과값 출력
		
		String IndeNO 		= "";	//발급 번호 
		String CerNO 		= "";	//인증 번호 
		String EMP_NAME 	= "";	//이름 
		String LoansType 	= "";	//대출 종류 
		String purpose 		= "";	//자금 용도 
		String current 		= "";	//통화 
		String LimitAmt 	= "";	//한도금액 
		String RemainAmt 	= "";	//잔액 
		String LoanDate 	= "";	//대출 일자 
		String RepayDate 	= "";	//상황기일 
		String FinalRate 	= "";	//최종 이자 
		
	
		while (rs.next()) {
			
			IndeNO 		= rs.getString("IndeNO");
			CerNO 		= rs.getString("CerNO");
			EMP_NAME 	= rs.getString("EMP_NAME");
			LoansType 	= rs.getString("LoansType");
			purpose 	= rs.getString("purpose");
			current 	= rs.getString("current");
			LimitAmt 	= rs.getString("LimitAmt");
			RemainAmt 	= rs.getString("RemainAmt");
			LoanDate 	= rs.getString("LoanDate");
			RepayDate 	= rs.getString("RepayDate");
			FinalRate 	= rs.getString("FinalRate");
		}

		map.put("IndeNO", 		IndeNO);
		map.put("CerNO", 		CerNO);
		map.put("EMP_NAME", 	EMP_NAME);
		map.put("LoansType",	LoansType);
		map.put("purpose", 		purpose);
		map.put("current", 		current);
		map.put("LimitAmt",		LimitAmt);
		map.put("RemainAmt", 	RemainAmt);
		map.put("LoanDate", 	LoanDate);
		map.put("RepayDate",	RepayDate);
		map.put("FinalRate", 	FinalRate);
		
		list.add(map);
		
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
			System.out.println("mariaDB Driver를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Database 연결중 에러가 발생 했습니다.");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
