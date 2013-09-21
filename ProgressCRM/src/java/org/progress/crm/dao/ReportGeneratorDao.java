package org.progress.crm.dao;

import java.io.File;
import java.util.Date;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File reportGen(Date curDate) {
        return PDF.Gen();
    }
}
