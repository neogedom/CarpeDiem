package br.com.carpediem.controle.reports;

import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;

import br.com.carpediem.exceptions.ReportException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RepositorioReports {

	public static JasperPrint generateReport(String pathModelReport, Map<String, Object>
			parameters, Connection connection) throws ReportException
			{
			 
				JasperPrint reportPrint = null;
				try{
					reportPrint = JasperFillManager.fillReport(pathModelReport, parameters, connection);
			 
				}catch(JRException e){
			 
					throw new ReportException(e.getMessage()); }
				return reportPrint;
			 }
}


