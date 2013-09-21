package org.progress.crm.dao;

import java.util.Date;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public void reportGen(Date curDate) {
        PDF.Gen();
    }
}
