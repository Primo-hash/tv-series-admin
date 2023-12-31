package no.abdulhadi.tvseriesadmin.model.factory;

import lombok.extern.java.Log;
import no.abdulhadi.tvseriesadmin.exception.ReportProducerException;

@Log
public class FactoryProducer {
    public static ReportFactory getReportFactory(FactoryEnum reportFactoryType){
        switch (reportFactoryType) {
            case SHOW_REPORT -> {
                return new ShowReportFactory();
            }
            default -> throw new ReportProducerException("Invalid report factory choice: " + reportFactoryType);
        }
    }
}
