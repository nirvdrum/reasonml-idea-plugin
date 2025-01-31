package com.reason.hints;


import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.Log;
import com.reason.build.bs.compiler.BsProcess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.reason.Platform.getOsPrefix;

public class InsightManagerImpl implements InsightManager {

    private static final Log LOG = Log.create("hints.insight");

    @NotNull
    AtomicBoolean isDownloaded = new AtomicBoolean(false);
    @NotNull
    AtomicBoolean isDownloading = new AtomicBoolean(false);

    private final Project m_project;

    private InsightManagerImpl(@NotNull Project project) {
        m_project = project;
    }

    @Override
    public boolean useCmt() {
        return isDownloaded.get();
    }

    @Override
    public void downloadRincewindIfNeeded(@NotNull VirtualFile sourceFile) {
        if (!isDownloaded.get()) {
            File rincewind = getRincewindFile(sourceFile);
            if (rincewind == null || !rincewind.exists()) {
                if (!m_project.isDisposed()) {
                    LOG.debug("Downloading rincewind in background");
                    ProgressManager.getInstance().run(RincewindDownloader.getInstance(m_project, sourceFile));
                }
            } else {
                isDownloaded.compareAndSet(false, true);
            }
        }
    }

    @Nullable
    @Override
    public File getRincewindFile(@NotNull VirtualFile sourceFile) {
        String filename = getRincewindFilename(sourceFile);
        if (filename == null) {
            return null;
        }

        IdeaPluginDescriptor plugin = PluginManager.getPlugin(PluginId.getId("reasonml"));
        if (plugin != null) {
            return new File(plugin.getPath(), filename);
        }

        return new File(System.getProperty("java.io.tmpdir"), filename);
    }

    @Nullable
    @Override
    public String getRincewindFilename(@NotNull VirtualFile sourceFile) {
        String ocamlVersion = ServiceManager.getService(m_project, BsProcess.class).getOCamlVersion(sourceFile);
        return ocamlVersion == null ? null : "rincewind_" + getOsPrefix() + ocamlVersion + "-" + getAppVersion(ocamlVersion) + ".exe";
    }

    @Override
    public void queryTypes(@NotNull VirtualFile sourceFile, @NotNull Path cmtPath, @NotNull ProcessTerminated runAfter) {
        if (isDownloaded.get()) {
            File rincewindFile = getRincewindFile(sourceFile);
            LOG.debug("rincewind", rincewindFile);
            if (rincewindFile != null) {
                RincewindProcess.getInstance(m_project).types(sourceFile, rincewindFile.getPath(), cmtPath.toString(), runAfter);
            }
        }
    }

    private String getAppVersion(@NotNull String ocamlVersion) {
        switch (ocamlVersion) {
            case "4.02":
                return "0.4";
            default:
                return "0.5-dev";
        }
    }
}
