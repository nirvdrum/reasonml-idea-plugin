package com.reason.hints;


import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.reason.Log;
import com.reason.ide.ORNotification;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.StandardCopyOption;

public class RincewindDownloader extends Task.Backgroundable {

    private static final double TOTAL_BYTES = 5_000_000.0;
    private static final int BUFFER_SIZE = 1024;
    private static final String DOWNLOAD_URL = "https://dl.bintray.com/giraud/ocaml/";
    private static final Log LOG = Log.create("hints");

    private static RincewindDownloader INSTANCE;
    private final VirtualFile m_sourceFile;

    public static RincewindDownloader getInstance(@NotNull Project project, @NotNull VirtualFile sourceFile) {
        if (INSTANCE == null) {
            INSTANCE = new RincewindDownloader(project, sourceFile);
        }
        return INSTANCE;
    }

    private RincewindDownloader(@Nullable Project project, @NotNull VirtualFile sourceFile) {
        //noinspection DialogTitleCapitalization
        super(project, "Downloading Rincewind binary");
        m_sourceFile = sourceFile;
    }

    @Override
    public void run(@NotNull ProgressIndicator indicator) {
        if (myProject.isDisposed()) {
            LOG.debug("Project is disposed, can't download rincewind");
            return;
        }

        InsightManagerImpl insightManager = (InsightManagerImpl) ServiceManager.getService(myProject, InsightManager.class);
        if (!insightManager.isDownloaded.get() && !insightManager.isDownloading.compareAndSet(false, true)) {
            // We are already in the process of downloading
            LOG.debug("Already downloading, abort");
            return;
        }

        try {
            File targetFile = insightManager.getRincewindFile(m_sourceFile);
            if (targetFile == null) {
                LOG.debug("No target file, abort downloading");
                return;
            }

            LOG.info("Downloading " + targetFile.getName() + "...");
            indicator.setIndeterminate(false);
            indicator.setFraction(0.0);

            try {
                // some code
                File partFile = new File(targetFile.getPath() + ".part");

                if (partFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    partFile.delete();
                }

                //noinspection ResultOfMethodCallIgnored
                partFile.createNewFile();

                FileOutputStream partFileOut = new FileOutputStream(partFile);

                java.net.URL url = new URL(DOWNLOAD_URL + insightManager.getRincewindFilename(m_sourceFile));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);

                connection.setConnectTimeout(240 * 1000);
                connection.setReadTimeout(240 * 1000);

                InputStream inputStream = connection.getInputStream();

                double totalBytesDownloaded = 0.0;

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = inputStream.read(buffer);
                while (bytesRead >= 0) {
                    indicator.setFraction(totalBytesDownloaded / TOTAL_BYTES);
                    totalBytesDownloaded += bytesRead;

                    partFileOut.write(buffer, 0, bytesRead);
                    bytesRead = inputStream.read(buffer);
                }

                connection.disconnect();
                partFileOut.close();
                inputStream.close();

                java.nio.file.Files.move(partFile.toPath(), targetFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
                if (!SystemInfo.isWindows) {
                    //noinspection ResultOfMethodCallIgnored
                    targetFile.setExecutable(true);
                }

                insightManager.isDownloaded.set(true);
                LOG.info(targetFile.getName() + " downloaded to " + targetFile.toPath().getParent());

                Notifications.Bus.notify(new ORNotification("Reason", "Downloaded " + targetFile, NotificationType.INFORMATION));

                Application application = ApplicationManager.getApplication();
                application.invokeLater(() -> application.runWriteAction(() -> {
                    VirtualFileManager.getInstance().syncRefresh();
                }));
            } catch (IOException e) {
                Notifications.Bus.notify(new ORNotification("Reason", "Can't download " + targetFile + "\n" + e, NotificationType.ERROR));
            }

            indicator.setFraction(1.0);
        } finally {
            insightManager.isDownloading.set(false);
        }
    }
}
