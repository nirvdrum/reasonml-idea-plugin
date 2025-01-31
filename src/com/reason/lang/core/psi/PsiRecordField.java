package com.reason.lang.core.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiQualifiedNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.reason.lang.core.stub.PsiRecordFieldStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PsiRecordField extends PsiNamedElement, PsiQualifiedNamedElement, NavigatablePsiElement, PsiSignatureElement, StubBasedPsiElement<PsiRecordFieldStub> {

    @Nullable
    String getPathName();
}
