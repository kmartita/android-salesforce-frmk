package io.omni.example.tools.metadata;

import com.sforce.soap.metadata.DeployOptions;
import com.sforce.ws.ConnectionException;
import io.omni.example.tools.PathToFile;
import io.qameta.allure.Step;
import org.zeroturnaround.zip.ZipUtil;
import java.io.*;
import java.nio.file.FileSystems;

public class DeploymentUtils {

    public static final String ZIP_EXTENSION = ".zip";
    private static final String SUCCEEDED_STATUS = "Succeeded";

    @Step
    public static void zipAndDeployFile(String path) {
        ZipUtil.pack(new File(path + File.separator), new File(path + ZIP_EXTENSION), true);
        deployFileAndWaitUntilSucceeded(path + ZIP_EXTENSION);
    }

    @Step
    public static void deployFileAndWaitUntilSucceeded(String pathName) {
        DeployOptions deployOptions = new DeployOptions();
        MetadataUtils metadataUtils = new MetadataUtils();
        deployOptions.setRollbackOnError(true);
        try {
            String id = metadataUtils.getConnection().deploy(readZipFile(pathName), deployOptions).getId();
            String deployStatus = metadataUtils.getConnection().checkDeployStatus(id, false).getStatus().name();
            int attempts = 30;
            while (attempts > 1 && !deployStatus.equalsIgnoreCase(SUCCEEDED_STATUS)) {
                attempts--;
                deployStatus = metadataUtils.getConnection().checkDeployStatus(id, false).getStatus().name();
            }
            System.out.printf("Deployment status = '%s'", deployStatus);
            if (!deployStatus.equalsIgnoreCase(SUCCEEDED_STATUS)) {
                throw new IllegalStateException(String.format("Deploy status is '%s'. Check the reasons of it.", deployStatus));
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readZipFile(String pathname) {
        File deployZip = new File(pathname);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = new FileInputStream(deployZip);
            int readbyte = -1;
            while ((readbyte = fis.read()) != -1) {
                bos.write(readbyte);
            }
            fis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            System.out.printf("There is no found file with '%s' path. n%s%n", pathname, e.getMessage());
        } catch (IOException e) {
            System.out.printf("Unable to parse file with path: %s%n%s", pathname, e.getMessage());
        }
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        String pathName = String.join(FileSystems.getDefault().getSeparator(), PathToFile.getPathToTestResourcesFolder(), "meetings", "meeting_cloned_flexi_page");
        zipAndDeployFile(pathName);
    }
}
