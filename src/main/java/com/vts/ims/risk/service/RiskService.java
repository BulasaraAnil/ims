package com.vts.ims.risk.service;

import java.util.List;

import com.vts.ims.risk.dto.RiskRegisterDto;

public interface RiskService {

	public long insertRiskRegister(RiskRegisterDto dto, String username) throws Exception;

	public List<RiskRegisterDto> getRiskRegisterList(Long revisionRecordId) throws Exception;

}