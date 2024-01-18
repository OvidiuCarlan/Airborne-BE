package airborne.business.impl;

import airborne.domain.Report;
import airborne.persistance.entity.ReportEntity;

public class ReportConverter {
    private ReportConverter(){

    }

    public static Report convert(ReportEntity report){
        return Report.builder()
                .id(report.getId())
                .reporter(UserConverter.convert(report.getReporter()))
                .reported(UserConverter.convert(report.getReported()))
                .reason(report.getReason())
                .build();
    }
}
