<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
  <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs/qrcode.min.js"></script>
  <script type="text/javascript">
	  
  		var qrcode = new QRCode("qrcode");
	  
	  function makeCode () {    
	    var elText = document.getElementById("text1");
	    if (!elText.value) {
	      alert("Input a text");
	      elText.focus();
	      return;
	    } 
	    qrcode.makeCode(elText.value);
	  }
  </script>
  
    <style type="text/css">
      table {
      	margin:auto; 
      	text-align:center;
      }
    </style>
    
  </head>

  <body>
    <table border=0 width=800>
      <tr>
        
        <td align=left width=310><img src="data:image/jpeg;base64,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" width="130" height="50"/></td>
        <td align=left style="font-size:24px;font-weight:bold">부채증명원(은행보관용)</td>
      </tr>
    </table>
    <hr style="border:solid 2px;" width=800>
    <table style="border : 1px solid black;" width=800>
      <tr>
        <td align=left>
          본 부채증명원은 일반용도로 사용이 가능하며, 회생ㆍ파산 신청을 위해서 사용이 불가능합니다.<br>
          회생ㆍ파산 신청용 부채증명원은 영업점 창구 발급을 이용해주시기 바랍니다.
        </td>
      </tr>
    </table>

    <table border=0 width=800>
      <tr align=right>
        <th>발급번호(NO) : ${IndeNO}</th>
      </tr>
      <tr align=right>
        <th>인증번호(NO) : ${CerNO}</th>
      </tr>
      <tr align=left>
        <th> ${EMP_NAME}  귀하</th>
      </tr>
    </table>

    <table border=1 width=800>
      <tr>
        <th>대출종류</th>
        <th>자금용도</th>
        <th>통화</th>
        <th>한도금액<br>(평가금액)</th>
        <th>잔액<br>(평가금액)</th>
        <th>대출일자</th>
        <th>상환일자</th>
        <th>최종이자<br>계산일</th>
      </tr>
      <tr>
        <td>${LoansType}</td>
        <td>${purpose}</td>
        <td>${current}</td>
        <td>${LimitAmt}</td>
        <td>${RemainAmt}</td>
        <td>${LoanDate}</td>
        <td>${RepayDate}</td>
        <td>${FinalRate}% </td>
      </tr>
    </table>

    <table border=0 width=800>
      <tr align=left>
        <td style="font-size:14px;font-weight:bold">
          위 금액을 ${NowDate} 현재 귀하 명의의 부채 금액임을 증명합니다.
        </td>
      </tr>
    </table>
    <tr>&nbsp;</tr>
    <table border=0 width=800>
      <tr>
        <td align=center>발급일자 : ${NowDate}</td>
      </tr>
    </table>
    <tr>&nbsp;</tr>
    <table border=0 width=800>
      <tr>
        <td align=left style="font-size:14px;font-weight:bold">신한은행 고려대학교 지점</td>
      </tr>
    </table>
    <tr>&nbsp;</tr>
    <table border=0 width=800>
      <tr>
        <td align=center width=310><img src="data:image/jpeg;base64,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" width="130" height="50"/></td>
      </tr>
    </table>
<br>
<br>
	<table border=0 width=800>
      <tr>
        <td align=center>HASH NO</td>
      </tr>
      <tr>
      	<td align=center style="font-size:10px;font-weight:bold">${HashNo}</td>
      </tr>
    </table>
    <tr>&nbsp;</tr>
<br>
<br>
    <table border=0 width=800>
    <tr align=left>
        <td style="font-size:12px;font-weight:bold">
          주) 1. 평가금액은 원화 외에 통화에 대한 금액을 원화로 환산한 금액을 말하며 적용기준은 각 통화의 기준일자<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1회차 매매기준율을 적용하였습니다.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 본 증명서는 은행의 담보로 제공된 부동산의 피담보채무 확인용으로 사용 할 수 없으며 관련확인은 대출관리점으로</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;문의하시기 바랍니다.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. 파생상품 가지급금에 대한 대출일자는 발생일자 입니다.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4. 본 증명서에는 고객께서 발급 요청하신 대출계좌만을 표시하였습니다. 따라서 본 증명서에 기재되지 아니한</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;다른 대출금이 존재할 수 있습니다.
        </td>
      </tr>
    </table>
		<input id="text1" type="text" value="" style="width:30%" /><br />
		<input type="submit" value="QR Code 생성"onclick="makeCode();"><br/>
	<div id="qrcode" style="width:100px; height:100px; margin:auto; margin-top:15px;"></div>
  </body>
</html>