package com.vts.ims.kpi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KpiMasterDto {
	                               
	private Long kpiId;
	private String kpiObjectives;
	private String kpiMerics; 
	private String kpiTarget; 
	private Long   kpiUnitId; 
	private String kpiUnitName; 

}