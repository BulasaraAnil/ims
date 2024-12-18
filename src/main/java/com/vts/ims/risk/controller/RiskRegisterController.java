package com.vts.ims.risk.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vts.ims.audit.dto.AuditorDto;
import com.vts.ims.audit.dto.IqaDto;
import com.vts.ims.risk.dto.RiskRegisterDto;
import com.vts.ims.risk.service.RiskService;


@CrossOrigin("*")
@RestController
public class RiskRegisterController {

	private static final Logger logger = LogManager.getLogger(RiskRegisterController.class);
	
	@Value("${appStorage}")
	private String storageDrive ;
	
	@Autowired
	RiskService service;
	
	
	
	@PostMapping(value = "/risk-register-list", produces = "application/json")
	public ResponseEntity<List<RiskRegisterDto>> riskRegisterList(@RequestHeader String username,@RequestBody String revisionRecordId) throws Exception {
		try {
			logger.info(new Date() + " Inside riskRegisterList" );
			List<RiskRegisterDto> dto=service.getRiskRegisterList(Long.parseLong(revisionRecordId));
			return new ResponseEntity<List<RiskRegisterDto>>( dto,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error fetching riskRegisterList: ", e);
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value = "/insert-risk-register", produces = "application/json")
	public ResponseEntity<String> insertriskregister(@RequestHeader String username, @RequestBody RiskRegisterDto dto) throws Exception {
		try {
			logger.info(new Date() + " Inside insert-risk-register" );
			 long insertRiskRegister=service.insertRiskRegister(dto,username);
			 if(insertRiskRegister > 0) {
				 return new ResponseEntity<String>("200" , HttpStatus.OK);
			 }else {
				 return new ResponseEntity<String>("500" , HttpStatus.BAD_REQUEST);
			 }
		} catch (Exception e) {
			 logger.error(new Date() +"error in insert-risk-register"+ e.getMessage());
			 e.printStackTrace();
			 return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
		}
	}
	
	
}