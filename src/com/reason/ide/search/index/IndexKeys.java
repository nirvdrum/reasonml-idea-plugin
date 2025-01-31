package com.reason.ide.search.index;

import com.intellij.psi.stubs.StubIndexKey;
import com.intellij.util.indexing.ID;
import com.reason.ide.search.FileModuleData;
import com.reason.lang.core.psi.*;

public class IndexKeys {

    public static final ID<String, FileModuleData> FILE_MODULE = ID.create("reason.index.fileModule");

    public static final StubIndexKey<String, PsiInnerModule> MODULES = StubIndexKey.createIndexKey("reason.module");
    public static final StubIndexKey<String, PsiInnerModule> MODULES_COMP = StubIndexKey.createIndexKey("reason.module.comp");
    public static final StubIndexKey<Integer, PsiInnerModule> MODULES_FQN = StubIndexKey.createIndexKey("reason.module.fqn");
    public static final StubIndexKey<String, PsiVariantDeclaration> VARIANTS = StubIndexKey.createIndexKey("reason.variant");
    public static final StubIndexKey<Integer, PsiVariantDeclaration> VARIANTS_FQN = StubIndexKey.createIndexKey("reason.variant.fqn");
    public static final StubIndexKey<String, PsiLet> LETS = StubIndexKey.createIndexKey("reason.let");
    public static final StubIndexKey<String, PsiVal> VALS = StubIndexKey.createIndexKey("reason.val");
    public static final StubIndexKey<String, PsiExternal> EXTERNALS = StubIndexKey.createIndexKey("reason.external");
    public static final StubIndexKey<String, PsiType> TYPES = StubIndexKey.createIndexKey("reason.type");
    public static final StubIndexKey<String, PsiRecordField> RECORD_FIELDS = StubIndexKey.createIndexKey("reason.record_field");
    public static final StubIndexKey<String, PsiException> EXCEPTIONS = StubIndexKey.createIndexKey("reason.exception");
    public static final StubIndexKey<Integer, PsiException> EXCEPTIONS_FQN = StubIndexKey.createIndexKey("reason.exception.fqn");

    private IndexKeys() {
    }
}
