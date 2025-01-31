package com.reason;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiQualifiedNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public class Log {

    private static final String SEP = ": ";
    private final Logger m_log;

    private Log(String name) {
        m_log = Logger.getInstance("ReasonML." + name);
    }

    public static Log create(String name) {
        return new Log(name);
    }

    public boolean isDebugEnabled() {
        return m_log.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
        return m_log.isTraceEnabled();
    }

    public void debug(String comment) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment);
        }
    }

    public void debug(String comment, int t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t);
        }
    }

    public void debug(String comment, int t, @Nullable Collection t1) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t + (t1 != null && 1 == t1.size() ? " " + t1.iterator().next() : ""));
        }
    }

    public void debug(String comment, String t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t);
        }
    }

    public void debug(String comment, int t, String t1) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t + " " + t1);
        }
    }

    public void debug(String comment, String t, String t1) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t + " " + t1);
        }
    }

    public void debug(String comment, boolean t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t);
        }
    }

    public void debug(String comment, @Nullable PsiFile[] t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + (t == null ? "" : t.length + " "));
        }
    }

    public void debug(@NotNull String comment, @Nullable PsiFile t) {
        if (m_log.isDebugEnabled()) {
            debug(comment, t == null ? null : t.getVirtualFile());
        }
    }

    public void debug(@NotNull String comment, @NotNull String t, @Nullable PsiFile t1) {
        if (m_log.isDebugEnabled()) {
            debug(comment + SEP + t, t1 == null ? null : t1.getVirtualFile());
        }
    }

    public void debug(@NotNull String comment, @NotNull String t, @Nullable VirtualFile t1) {
        if (m_log.isDebugEnabled()) {
            debug(comment + SEP + t, t1);
        }
    }

    public void debug(@NotNull String comment, @Nullable VirtualFile t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + (t == null ? "<NULL>" : t.getCanonicalPath() + " "));
        }
    }

    public void debug(@NotNull String comment, @Nullable File t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + (t == null ? "<NULL>" : t.getPath() + " "));
        }
    }

    public void debug(String comment, @Nullable Collection<?> t) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + (t == null ? "" : t.size() + " ") + "[" + Joiner.join(", ", t) + "]");
        }
    }

//    public void debug(String comment, List t) {
//        if (m_log.isDebugEnabled()) {
//            m_log.debug(comment + SEP + (t == null ? "" : t.size() + " ") + "[" + Joiner.join(", ", t) + "]");
//        }
//    }

    public void debug(String comment, @NotNull PsiQualifiedNamedElement element) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + element.getQualifiedName() + " (" + element.getContainingFile().getVirtualFile().getPath() + ")");
        }
    }

    public void debug(String comment, @NotNull PsiQualifiedNamedElement element, int position) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + element.getQualifiedName() + " (" + element.getContainingFile().getVirtualFile().getPath() + ") pos=" + position);
        }
    }

    public void debug(String comment, String t, boolean t1) {
        if (m_log.isDebugEnabled()) {
            m_log.debug(comment + SEP + t + " " + t1);
        }
    }

    public void debug(@NotNull String comment, @NotNull Map<String, Integer> map) {
        if (m_log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            boolean start = true;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (!start) {
                    sb.append(", ");
                }
                sb.append(entry.getKey()).append(":").append(entry.getValue());
                start = false;

            }
            m_log.debug(comment + SEP + "[" + sb.toString() + "]");
        }
    }

    public void error(String message, Exception e) {
        m_log.error(message, e);
    }

    public void error(String msg) {
        m_log.error(msg);
    }

    public void info(String msg) {
        m_log.info(msg);
    }

    public void warn(String msg) {
        m_log.warn(msg);
    }

    public void trace(String msg) {
        if (m_log.isTraceEnabled()) {
            m_log.trace(msg);
        }
    }
}
