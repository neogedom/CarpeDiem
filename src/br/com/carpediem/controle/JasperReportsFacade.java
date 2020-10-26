package br.com.carpediem.controle;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import br.com.carpediem.controle.reports.RepositorioReports;
import br.com.carpediem.dao.DAO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;


public class JasperReportsFacade
{
	static JasperPrint reportPrint = null;
	static String reportXML  = "";
	
	public static int getReportPageCount(String reportPath , Map<String, Object> parameters , Connection connection) throws Exception
	{
		return JasperFillManager.fillReport(reportPath , parameters , connection).getPages().size();
	}
	
	public static void fillAndShowReport(String reportPath , Map<String, Object> parameters , Connection connection) throws Exception
    {
       JasperViewer.viewReport(JasperFillManager.fillReport(reportPath , parameters , connection) , false);
    }
	
	public static String exportToXMLReport(String reportPath , Map<String, Object> parameters , Connection connection) throws Exception
    {
		System.out.println(reportPath);
		reportPrint = RepositorioReports.generateReport(reportPath, parameters, connection);
		reportPrint.setName("Faltas");
		reportXML = JasperExportManager.exportReportToXml(reportPrint);
		return reportXML;
    }
}