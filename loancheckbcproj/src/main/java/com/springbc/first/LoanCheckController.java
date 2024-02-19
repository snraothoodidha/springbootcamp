package com.springbc.first;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class LoanCheckController {

	@GetMapping("/loancheckbc/{cs}/{loanamount}/{salary}")
	public ResponseEntity<LoanResponse> checkLoan(@PathVariable("cs")int cs,@PathVariable("loanamount")int loanamount, @PathVariable("salary")int salary){
		
		int approvedLoanAmount = 0;
		int status = 0;
		
		if(salary>50000 && cs >700) {
			status = 1;
			if(loanamount >100000) {
				approvedLoanAmount = 100000;
			}else {
				approvedLoanAmount = loanamount;
			}
		}
		
		LoanResponse lr = new LoanResponse(approvedLoanAmount,status);
		return ResponseEntity.ok(lr);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostResponse> getPostTest(@PathVariable("id")String id){
		String url= "https://jsonplaceholder.typicode.com/posts";
		
		PostResponse pr = RestClient.create().get().uri(url+id).retrieve().body(PostResponse.class);
		
		System.out.println("in post msg "+id);
		
		return ResponseEntity.ok(pr);
		
		
	}
}
