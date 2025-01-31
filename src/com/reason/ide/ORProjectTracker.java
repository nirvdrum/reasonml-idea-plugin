package com.reason.ide;

import com.intellij.AppTopics;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.roots.ModuleRootEvent;
import com.intellij.openapi.roots.ModuleRootListener;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.ArrayUtil;
import com.intellij.util.messages.MessageBusConnection;
import com.reason.OCamlSdk;
import com.reason.OCamlSourcesOrderRootType;
import com.reason.ide.format.ReformatOnSave;
import gnu.trove.Equality;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.ProjectTopics.PROJECT_ROOTS;

public class ORProjectTracker implements ProjectComponent {

    private final Project m_project;
    @Nullable
    private MessageBusConnection m_messageBusConnection;
    @Nullable
    private ORVirtualFileListener m_vfListener;
    @Nullable
    private ORFileEditorListener m_fileEditorListener;

    protected ORProjectTracker(Project project) {
        m_project = project;
    }

    @Override
    public void projectOpened() {
        m_messageBusConnection = m_project.getMessageBus().connect();

        m_messageBusConnection.subscribe(PROJECT_ROOTS,
                new ModuleRootListener() {
                    @Override
                    public void rootsChanged(ModuleRootEvent event) {
                        ApplicationManager.getApplication().invokeLater(() -> {
                            Project project = (Project) event.getSource();
                            if (!project.isDisposed()) {
                                Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();
                                if (projectSdk != null && projectSdk.getSdkType() instanceof OCamlSdk) {
                                    // Hack to get OCaml sources indexed like java sources
                                    // Find a better way to do it !!
                                    VirtualFile[] ocamlSources = projectSdk.getRootProvider().getFiles(OCamlSourcesOrderRootType.getInstance());
                                    VirtualFile[] javaSources = projectSdk.getRootProvider().getFiles(OrderRootType.SOURCES);
                                    boolean equals = ArrayUtil.equals(ocamlSources, javaSources, (Equality<VirtualFile>) (v1, v2) -> v1.getPath().equals(v2.getPath()));

                                    if (!equals) {
                                        SdkModificator sdkModificator = projectSdk.getSdkModificator();

                                        sdkModificator.removeRoots(OrderRootType.SOURCES);
                                        for (VirtualFile root : ocamlSources) {
                                            sdkModificator.addRoot(root, OrderRootType.SOURCES);
                                        }

                                        sdkModificator.commitChanges();
                                    }
                                }
                            }
                        });
                    }
                });

        m_fileEditorListener = new ORFileEditorListener(m_project);
        m_messageBusConnection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, m_fileEditorListener);
        m_messageBusConnection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new ReformatOnSave(m_project));

        m_vfListener = new ORVirtualFileListener(m_project);
        VirtualFileManager.getInstance().addVirtualFileListener(m_vfListener);
    }

    @Override
    public void projectClosed() {
        if (m_vfListener != null) {
            VirtualFileManager.getInstance().removeVirtualFileListener(m_vfListener);
            m_vfListener = null;
        }
        if (m_messageBusConnection != null) {
            m_messageBusConnection.disconnect();
            m_messageBusConnection = null;
        }
    }

    public boolean isOpen(@NotNull VirtualFile file) {
        return m_fileEditorListener != null && m_fileEditorListener.isOpen(file);
    }
}
