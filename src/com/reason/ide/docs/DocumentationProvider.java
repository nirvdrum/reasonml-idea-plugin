package com.reason.ide.docs;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.reason.Log;
import com.reason.Platform;
import com.reason.ide.files.FileBase;
import com.reason.ide.hints.SignatureProvider;
import com.reason.ide.search.PsiTypeElementProvider;
import com.reason.lang.core.ORUtil;
import com.reason.lang.core.psi.*;
import com.reason.lang.core.psi.PsiNamedElement;
import com.reason.lang.core.psi.PsiType;
import com.reason.lang.core.signature.ORSignature;
import com.reason.lang.ocaml.OclLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DocumentationProvider extends AbstractDocumentationProvider {

    private static final Log LOG = Log.create("doc");

    public static boolean isSpecialComment(@Nullable PsiElement element) {
        if (element == null) {
            return false;
        }

        String nextText = element.getText();
        return (nextText.startsWith("(**") || nextText.startsWith("/**")) && nextText.charAt(3) != '*';
    }

    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        if (element instanceof FileBase) {
            PsiElement child = element.getFirstChild();
            String text = "";

            PsiElement nextSibling = child;
            while (nextSibling instanceof PsiComment) {
                if (isSpecialComment(nextSibling)) {
                    text = nextSibling.getText();
                    nextSibling = null;
                } else {
                    // Not a special comment, try with next child until no more comments found
                    nextSibling = PsiTreeUtil.nextVisibleLeaf(nextSibling);
                }
            }

            if (!text.isEmpty()) {
                return DocFormatter.format(element.getContainingFile(), element, text);
            }
        } else if (element instanceof PsiUpperSymbol || element instanceof PsiLowerSymbol) {
            element = element.getParent();
            if (element instanceof PsiTypeConstrName) {
                element = element.getParent();
            }

            // Try to find a comment just below (OCaml only)
            if (element.getLanguage() == OclLanguage.INSTANCE) {
                PsiElement belowComment = findBelowComment(element);
                if (belowComment != null) {
                    return isSpecialComment(belowComment)
                            ? DocFormatter.format(element.getContainingFile(), element, belowComment.getText())
                            : belowComment.getText();
                }
            }

            // Else try to find a comment just above
            PsiElement aboveComment = findAboveComment(element);
            if (aboveComment != null) {
                return isSpecialComment(aboveComment)
                        ? DocFormatter.format(element.getContainingFile(), element, aboveComment.getText())
                        : aboveComment.getText();
            }
        }

        return super.generateDoc(element, originalElement);
    }

    @Nullable
    private PsiElement findAboveComment(@Nullable PsiElement element) {
        if (element == null) {
            return null;
        }

        PsiElement prevSibling = element.getPrevSibling();
        PsiElement prevPrevSibling = prevSibling == null ? null : prevSibling.getPrevSibling();
        if (prevPrevSibling instanceof PsiComment && prevSibling instanceof PsiWhiteSpace && prevSibling.getText().replaceAll("[ \t]", "").length() == 1) {
            return prevPrevSibling;
        }

        return null;
    }

    @Nullable
    private PsiElement findBelowComment(@Nullable PsiElement element) {
        if (element == null) {
            return null;
        }

        PsiElement nextSibling = element.getNextSibling();
        PsiElement nextNextSibling = nextSibling == null ? null : nextSibling.getNextSibling();
        if (nextNextSibling instanceof PsiComment && nextSibling instanceof PsiWhiteSpace && nextSibling.getText().replaceAll("[ \t]", "").length() == 1) {
            return nextNextSibling;
        }

        return null;
    }

    @Nullable
    @Override
    public String getQuickNavigateInfo(PsiElement element, @NotNull PsiElement originalElement) {
        PsiFile psiFile = originalElement.getContainingFile();

        String inferredType = "";
        SignatureProvider.InferredTypesWithLines signaturesContext = psiFile.getUserData(SignatureProvider.SIGNATURE_CONTEXT);
        if (signaturesContext != null) {
            ORSignature elementSignature = signaturesContext.getSignatureByOffset(originalElement.getTextOffset());
            if (elementSignature != null) {
                inferredType = elementSignature.asString(element.getLanguage());
            }
        }

        PsiReference reference = originalElement.getReference();
        if (reference != null) {
            PsiElement resolvedElement = reference.resolve();

            if (resolvedElement instanceof FileBase) {
                FileBase resolvedFile = (FileBase) resolvedElement;
                String relative_path = Platform.removeProjectDir(resolvedFile.getProject(), resolvedFile.getVirtualFile().getParent().getPath());
                return relative_path + "<br/>" + resolvedElement.getContainingFile();
            }

            if (!(resolvedElement instanceof PsiSignatureElement) && resolvedElement != null) {
                resolvedElement = resolvedElement.getParent();
            }

            if (resolvedElement instanceof PsiTypeConstrName) {
                PsiType type = (PsiType) resolvedElement.getParent();
                String desc = "type <b>" + resolvedElement.getText() + "</b>";
                String path = ORUtil.getQualifiedPath(type);

                String typeBinding = type.isAbstract() ? "<i>This is an abstract type</i>" : "<pre style='white-space:pre-wrap'>" + DocFormatter.escapeCodeForHtml(type.getBinding()) + "</pre>";
                return "[<i>" + type.getContainingFile() + "</i>] " + path + "<br/>" + desc + "<hr/>" + typeBinding;
            }

            if (resolvedElement instanceof PsiSignatureElement) {
                ORSignature signature = ((PsiSignatureElement) resolvedElement).getORSignature();
                if (!signature.isEmpty()) {
                    String sig = signature.asString(element.getLanguage());
                    if (resolvedElement instanceof PsiQualifiedNamedElement) {
                        String elementType = PsiTypeElementProvider.getType(resolvedElement);
                        String desc = (elementType == null ? "" : elementType + " ") + "<b>" + ((PsiQualifiedNamedElement) resolvedElement).getName() + "</b>";
                        String path = ORUtil.getQualifiedPath((PsiNamedElement) resolvedElement);

                        return "[<i>" + resolvedElement.getContainingFile() + "</i>] " + path + "<br/>" + desc + "<hr/>" + sig;
                    }
                    return sig;
                }
            }

            // No signature found, but resolved
            if (resolvedElement instanceof PsiQualifiedNamedElement) {
                String elementType = PsiTypeElementProvider.getType(resolvedElement);
                String desc = (elementType == null ? "" : elementType + " ") + "<b>" + ((PsiQualifiedNamedElement) resolvedElement).getName() + "</b>";
                String path = ORUtil.getQualifiedPath((PsiNamedElement) resolvedElement);

                String sig = inferredType.isEmpty() ? "<i>unknown signature</i>" : "<i>inferred:</i> " + inferredType;
                if (resolvedElement instanceof PsiVariantDeclaration) {
                    sig = "type " + ((PsiType) resolvedElement.getParent().getParent()).getName();
                }

                return "[<i>" + resolvedElement.getContainingFile() + "</i>] " + path + "<br/>" + desc + (resolvedElement instanceof PsiModule ? "" : "<hr/>" + sig);
            }
        }

        return super.getQuickNavigateInfo(element, originalElement);
    }
}
