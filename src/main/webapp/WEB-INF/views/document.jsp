<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <style type="text/css">
      table {
      	margin:auto; 
      	text-align:center;
      }
    </style>
   
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="author" content="Kenneth Hu">

    <!-- <link rel="stylesheet" type="text/css" href="${path}/css/main.css"> -->
    
    <!-- <script src="https://raw.githubusercontent.com/shawntabrizi/ethbalance/master/web3.min.js"></script> -->
    
    
    <script src="../views/js/web3.min.js"></script>

    
    
  </head>

  <body>
    
    <table border=0 width=800>
      <tr>
        
        <td align=left width=310><img src="data:image/jpeg;base64,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" width="130" height="50"/></td>
        <td align=left style="font-size:24px;font-weight:bold">???????????????(????????? ?????????)</td>
      </tr>
    </table>
    <hr style="border:solid 2px;" width=800>
    <table style="border : 1px solid black;" width=800>
      <tr>
      
      		<c:choose> 
			    <c:when test="${empty EMP_NAME}">
			        <td align="center" style="font-size:20px;font-weight:bold">????????? ?????? ?????????.</td>
			    </c:when>
			    <c:otherwise>
			       <td align=left>
          			??? ?????????????????? ??????????????? ????????? ????????????, ??????????????? ????????? ????????? ????????? ??????????????????.<br>
          			??????????????? ????????? ?????????????????? ????????? ?????? ????????? ?????????????????? ????????????.
        			</td>
			    </c:otherwise>
			</c:choose>
      </tr>
    </table>

    <table border=0 width=800>
      <tr align=right>
        <th>????????????(NO) : ${IndeNO}</th>
      </tr>
      <tr align=right>
        <th>????????????(NO) : ${CerNO}</th>
      </tr>
      <tr align=left>
        <th> ${EMP_NAME}  ??????</th>
      </tr>
    </table>

    <table border=1 width=800>
      <tr>
        <th>????????????</th>
        <th>????????????</th>
        <th>??????</th>
        <th>????????????<br>(????????????)</th>
        <th>??????<br>(????????????)</th>
        <th>????????????</th>
        <th>????????????</th>
        <th>????????????<br>?????????</th>
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
          ??? ????????? ${NowDate} ?????? ?????? ????????? ?????? ???????????? ???????????????.
        </td>
      </tr>
    </table>

    <tr>&nbsp;</tr>

    <table border=0 width=800>
      <tr>
        <td align=center>???????????? : ${NowDate}</td>
      </tr>
    </table>

    <tr>&nbsp;</tr>

    <table border=0 width=800>
      <tr>
      		<c:choose> 
			    <c:when test="${empty EMP_NAME}">
			        <td align=left style="font-size:14px;font-weight:bold">????????? ?????? ?????????.</td>
			    </c:when>
			    <c:otherwise>
			       <td align=left style="font-size:14px;font-weight:bold">???????????? ?????? ??????</td>
			    </c:otherwise>
			</c:choose>
        
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
    <tr>&nbsp;</tr>
<br>
<br>
    <table border=0 width=800>
      <tr align=left>
        <td style="font-size:12px;font-weight:bold">
          ???) 1. ??????????????? ?????? ?????? ????????? ?????? ????????? ????????? ????????? ????????? ????????? ??????????????? ??? ????????? ????????????<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1?????? ?????????????????? ?????????????????????.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. ??? ???????????? ????????? ????????? ????????? ???????????? ??????????????? ??????????????? ?????? ??? ??? ????????? ??????????????? ?????????????????????</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;??????????????? ????????????.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. ???????????? ??????????????? ?????? ??????????????? ???????????? ?????????.</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4. ??? ??????????????? ???????????? ?????? ???????????? ?????????????????? ?????????????????????. ????????? ??? ???????????? ???????????? ?????????</br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;?????? ???????????? ????????? ??? ????????????.
        </td>   		
      </tr>  
    </table>
    
    
    
     <div class="container">

        <h1>??????????????? ??????</h1>

        <label for="name" class="col-lg-2 control-label"><h3>Node Info</h3></label>
        <input id="NodeInfo" type="text">
		
		<hr>

        <label for="name" class="col-lg-2 control-label"><h3>Balance</h3></label>
		<p>Account : <input id="Account" type="text"> </p>
        <p>Balance : <input id="Balance" type="text"></p>
		 <button id="checkBalance">Check Balance</button>

		<hr>
		<label for="name" class="col-lg-2 control-label"><h3>Transfer</h3></label>
		<p>From(????????????) : &nbsp &nbsp &nbsp <input id="From" type="text"> </p>
		<p>To(??????) : &nbsp &nbsp &nbsp &nbsp &nbsp <input id="To" type="text"> </p>
        <p>Amount : &nbsp <input id="Amount" type="text"></p>
		<p>datainfo : &nbsp <input id="datainfo" type="text"></p>
        <button id="Transfer">Transfer</button>
        <p>Transaction Hash : &nbsp  <span id="Tx"></span></p>


    </div>
    
    
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    
    

    <script>

		$( document ).ready(function() {
			console.log( "ready!" );
			
			if (typeof web3 !== 'undefined') {
				web3 = new Web3(web3.currentProvider);
			} else {
					// set the provider you want from Web3.providers
				web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));
			}
			web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));
			
			
			
			
			/* Get Node Info */
			web3.eth.getNodeInfo(function(error, result){
				if(error){
					console.log( "error" ,error);
				}
				else{
					console.log( "result",result );
					$('#NodeInfo').val(result);
				}
			});
			
			//$('#datainfo').val(web3.eth.abi.decodeParameters(['string', 'string' ], '0x8c952a420000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000077465737431323300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000c74657374206d6573736167650000000000000000000000000000000000000000'));
		
			//alert(web3.eth.abi.decodeParameters(['string', 'string'], '0x0000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000077465737431323300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000568736b696d000000000000000000000000000000000000000000000000000000'));

			/*
			const object1 = web3.eth.abi.decodeParameters([{
													type: 'string',
													name: 'myNumber'
													
												},{
													type: 'string',
													name: 'myString'
												}], '0x0000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000077465737431323300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000568736b696d000000000000000000000000000000000000000000000000000000');
			*/
		

			const object1 = web3.eth.abi.decodeParameters(['string','string'], '0x0000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000077465737431323300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000568736b696d000000000000000000000000000000000000000000000000000000')

			$('#datainfo').val(Object.values(object1))

			/*Get Balance */
			web3.eth.getAccounts(function(error, accounts) {
				if(error) {
					console.log(error);
				}
				$('#Account').val(accounts[0]);
				web3.eth.getBalance(accounts[0]).then(function(result){
					console.log( "Balance : " ,web3.utils.fromWei(result, 'ether'));
					$('#Balance').val(web3.utils.fromWei(result, 'ether'));
				});
			});
			
			$('#checkBalance').click(function() {
			    var _account = $('#Account').val();
				web3.eth.getBalance(_account).then(function(result){
					console.log( "Balance : " ,web3.utils.fromWei(result, 'ether'));
					$('#Balance').val(web3.utils.fromWei(result, 'ether'));
				});
			});
			
			
			/* Transfer */
			$('#Transfer').click(function() {
				$('#Tx').text('');
				var _from = $('#From').val();
			    var _to = $('#To').val();
				var _Amount = $('#Amount').val();
				var _datainfo = $('#datainfo').val();
				var txnObject = {
					"from":_from,
					"to": _to,
					"value": web3.utils.toWei(_Amount,'ether'),
					// "gas": 21000,         (optional)
					// "gasPrice": 4500000,  (optional)
					"data": 
					web3.eth.abi.encodeParameters(['string','string'], ['test123', 'hskim'])
					,  //(optional)
					// "nonce": 10           (optional)
				}
			
				web3.eth.sendTransaction(txnObject, function(error, result){
					if(error){
						console.log( "Transaction error" ,error);
					}
					else{
						var txn_hash = result; //Get transaction hash
						$('#Tx').text(txn_hash);

						
					}
				});
				
			});
			
		
		});
	
    </script>
</body>
</html>