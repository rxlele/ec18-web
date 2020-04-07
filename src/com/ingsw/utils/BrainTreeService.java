package com.ingsw.utils;

import java.math.BigDecimal;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

public class BrainTreeService {
	
	
	private static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  "5t6r5fkxb9qbdb98",
			  "h24nbmtgzqsq9knr",
			  "fafe26e027be313f5a4ad4fca61d442c"
			);
	
	public String getToken() {
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
		return gateway.clientToken().generate(clientTokenRequest);
	}
	
	public boolean createTransaction(String nonce, String paymentAmount) {
		TransactionRequest request = new TransactionRequest()
				  .amount(new BigDecimal(paymentAmount))
				  .paymentMethodNonce(nonce)
				  .options()
				    .submitForSettlement(true)
				    .done();
				Result<Transaction> result = gateway.transaction().sale(request);
		return result.isSuccess();
	}
}
