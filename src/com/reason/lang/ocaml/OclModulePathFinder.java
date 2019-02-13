package com.reason.lang.ocaml;

import com.intellij.psi.PsiElement;
import com.reason.ide.files.FileBase;
import com.reason.lang.BaseModulePathFinder;
import com.reason.lang.core.psi.PsiInclude;
import com.reason.lang.core.psi.PsiLocalOpen;
import com.reason.lang.core.psi.PsiNamedElement;
import com.reason.lang.core.psi.PsiOpen;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OclModulePathFinder extends BaseModulePathFinder {

    // Find the expression paths
    @NotNull
    public List<String> extractPotentialPaths(@NotNull PsiElement element) {
        List<String> qualifiedNames = new ArrayList<>();

        String path = extractPathName(element, OclTypes.INSTANCE);
        if (!path.isEmpty()) {
            qualifiedNames.add(path);
            qualifiedNames.add(((FileBase) element.getContainingFile()).asModuleName() + "." + path);
        }

        // Walk backward until top of the file is reached, trying to find local opens and opens/includes
        PsiElement item = element;
        while (item != null) {
            if (100 < qualifiedNames.size()) {
                break; // There must be a problem with the parser
            }

            if (item instanceof FileBase) {
                qualifiedNames.add(((FileBase) item).asModuleName());
                break;
            } else if (item instanceof PsiOpen || item instanceof PsiInclude) {
                String openName = ((PsiNamedElement) item).getName();
                // Add open value to all previous elements
                List<String> withOpenQualifier = qualifiedNames.stream().map(name -> openName + "." + name).collect(Collectors.toList());
                qualifiedNames.addAll(withOpenQualifier);

                qualifiedNames.add(openName);
            }

            PsiElement prevItem = item.getPrevSibling();
            if (prevItem == null) {
                PsiElement parent = item.getParent();
                if (parent instanceof PsiLocalOpen) {
                    qualifiedNames.add(((PsiLocalOpen) parent).getName());
                }
                item = parent;
            } else {
                item = prevItem;
            }
        }

        qualifiedNames.add("Pervasives");
        return qualifiedNames;
    }

}
