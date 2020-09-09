package ba.unsa.etf.rpr.project.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class PrintReport extends JFrame {
    public void showReport(Connection conn) throws JRException {
        JRDesignQuery query= new JRDesignQuery();
        query.setText("SELECT * FROM scientific_paper");
        String reportSrcFile = getClass().getResource("/reports/papers.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperDesign jd = JRXmlLoader.load(reportSrcFile);
        jd.setQuery(query);
        JasperReport jasperReport = JasperCompileManager.compileReport(jd);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        JRViewer viewer = new JRViewer(print);

        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }
}