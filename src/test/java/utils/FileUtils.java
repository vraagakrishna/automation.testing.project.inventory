package utils;


import java.io.File;

public class FileUtils {

    // <editor-fold desc="Class Fields / Constants">
    private static final String downloadDir = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Downloads";
    // </editor-fold>

    // <editor-fold desc="Public Methods">
    public String getDownloadPath() {
        // Create folder if not exists
        File dir = new File(downloadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return downloadDir;
    }

    public void addFileToTest(String filename) {
        String filePath = "Downloads" + File.separator + filename;

        // Add a clickable link
        ReportManager.getTest()
                .info("Invoice downloaded: " +
                        "<a href='" + filePath + "' target='_blank'>" + filename + "</a>");
    }
    // </editor-fold>

}
