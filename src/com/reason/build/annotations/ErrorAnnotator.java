package com.reason.build.annotations;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.impl.TextRangeInterval;
import com.intellij.problems.Problem;
import com.intellij.problems.WolfTheProblemSolver;
import com.intellij.psi.PsiFile;
import com.reason.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ErrorAnnotator extends ExternalAnnotator<Collection<ErrorAnnotator.BsbErrorAnnotation>, Collection<ErrorAnnotator.BsbErrorAnnotation>> {

    private static final Log LOG = Log.create("annotator");

    @Nullable
    @Override
    public Collection<ErrorAnnotator.BsbErrorAnnotation> collectInformation(@NotNull PsiFile file, @NotNull Editor editor, boolean hasErrors) {
        List<BsbErrorAnnotation> result = null;

        String filePath = file.getVirtualFile().getCanonicalPath();
        if (filePath != null) {
            ErrorsManager service = ServiceManager.getService(file.getProject(), ErrorsManager.class);
            Collection<OutputInfo> collectedInfo = service.getInfo(filePath);

            result = new ArrayList<>();

            for (OutputInfo info : collectedInfo) {
                LogicalPosition start = new LogicalPosition(info.lineStart < 1 ? 0 : info.lineStart - 1, info.colStart < 1 ? 0 : info.colStart - 1);
                LogicalPosition end = new LogicalPosition(info.lineEnd < 1 ? 0 : info.lineEnd - 1, info.colEnd < 1 ? 0 : info.colEnd - 1);
                String message = info.message.replace('\n', ' ').replaceAll("\\s+", " ").trim();

                int startOffset = editor.logicalPositionToOffset(start);
                int endOffset = editor.logicalPositionToOffset(end);
                if (0 <= startOffset && 0 <= endOffset && startOffset < endOffset) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("annotate " + startOffset + ":" + endOffset + " '" + message + "'");
                    }
                    TextRangeInterval range = new TextRangeInterval(startOffset, endOffset);
                    result.add(new BsbErrorAnnotation(info.isError, message, range, start));
                }
            }
        }

        return result;
    }

    @Nullable
    @Override
    public Collection<BsbErrorAnnotation> doAnnotate(@NotNull Collection<BsbErrorAnnotation> collectedInfo) {
        return collectedInfo.isEmpty() ? null : collectedInfo;
    }

    @Override
    public void apply(@NotNull PsiFile file, @NotNull Collection<BsbErrorAnnotation> annotationResult, @NotNull AnnotationHolder holder) {
        WolfTheProblemSolver problemSolver = WolfTheProblemSolver.getInstance(file.getProject());
        Collection<Problem> problems = new ArrayList<>();

        for (BsbErrorAnnotation annotation : annotationResult) {
            if (annotation.isError) {
                holder.createErrorAnnotation(annotation.range, annotation.message);
                problems.add(problemSolver.convertToProblem(file.getVirtualFile(), annotation.startPos.line, annotation.startPos.column, new String[]{annotation.message}));
            } else {
                holder.createWarningAnnotation(annotation.range, annotation.message);
            }
        }

        problemSolver.reportProblems(file.getVirtualFile(), problems);
    }

    static class BsbErrorAnnotation {
        final boolean isError;
        final String message;
        final TextRangeInterval range;
        private final LogicalPosition startPos;

        BsbErrorAnnotation(boolean isError, String message, TextRangeInterval textRange, LogicalPosition startPos) {
            this.isError = isError;
            this.message = message;
            this.range = textRange;
            this.startPos = startPos;
        }
    }
}
