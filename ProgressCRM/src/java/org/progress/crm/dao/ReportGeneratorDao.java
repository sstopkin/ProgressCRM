package org.progress.crm.dao;

import java.io.File;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File reportGen() {
        return PDF.Gen();
    }
}
