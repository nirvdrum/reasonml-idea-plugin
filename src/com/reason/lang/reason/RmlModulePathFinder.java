package com.reason.lang.reason;

import com.intellij.psi.PsiElement;
import com.reason.Joiner;
import com.reason.ide.files.FileBase;
import com.reason.lang.BaseModulePathFinder;
import com.reason.lang.core.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.reason.lang.ModulePathFinder.Includes.containingFile;
import static com.reason.lang.ModulePathFinder.Includes.includedModules;


public class RmlModulePathFinder extends BaseModulePathFinder {

    // Find the expression paths
    @NotNull
    public List<String> extractPotentialPaths(@NotNull PsiElement element, @NotNull EnumSet<Includes> include, boolean addTypes) {
        List<String> qualifiedNames = new ArrayList<>();

        String path = extractPathName(element, RmlTypes.INSTANCE);
        String pathExtension = path.isEmpty() ? "" : "." + path;

        if (!path.isEmpty()) {
            qualifiedNames.add(path);
        }

        // Walk backward until top of the file is reached, trying to find local opens and opens/includes
        PsiElement item = element;
        while (item != null) {
            if (100 < qualifiedNames.size()) {
                break; // There must be a problem with the parser
            }

            if (item instanceof FileBase && include.contains(containingFile)) {
                qualifiedNames.add(((FileBase) item).asModuleName());
                break;
            } else if (item instanceof PsiInnerModule) {
                if (path.equals(((PsiInnerModule) item).getName())) {
                    qualifiedNames.add(((FileBase) element.getContainingFile()).asModuleName() + pathExtension);
                }

                PsiInnerModule module = (PsiInnerModule) item;
                String moduleName = module.getName();
                String moduleAlias = module.getAlias();
                if (moduleAlias != null) {
                    // Rewrite all current qn with this alias
                    List<String> withAlias = qualifiedNames.stream().map(name -> {
                        String[] tokens = name.split("\\.");
                        boolean found = false;
                        for (int i = 0; i < tokens.length; i++) {
                            String token = tokens[i];
                            if (token.equals(moduleName)) {
                                found = true;
                                tokens[i] = moduleAlias;
                                break;
                            }
                        }
                        if (found) {
                            return Joiner.join(".", tokens);
                        } else {
                            return null;
                        }
                    }).
                            filter(Objects::nonNull).
                            collect(Collectors.toList());

                    qualifiedNames.addAll(withAlias);
                }
            } else if (item instanceof PsiLocalOpen) {
                String openName = extractPathName(item, RmlTypes.INSTANCE);
                // Add local open value to all previous elements
                List<String> withOpenQualifier = qualifiedNames.stream().map(name -> openName + pathExtension).collect(Collectors.toList());
                qualifiedNames.addAll(withOpenQualifier);
                qualifiedNames.add(openName);
            } else if (item instanceof PsiOpen || (include.contains(includedModules) && item instanceof PsiInclude)) {
                String openName = ((PsiQualifiedElement) item).getQualifiedName();
                // Add open value to all previous elements
                List<String> withOpenQualifier = qualifiedNames.stream().map(name -> openName + pathExtension).collect(Collectors.toList());
                qualifiedNames.addAll(withOpenQualifier);
                qualifiedNames.add(openName + pathExtension);
            } else if (item instanceof PsiType && addTypes) {
                qualifiedNames.add(((PsiType) item).getQualifiedName() + pathExtension);
            }

            PsiElement prevItem = item.getPrevSibling();
            if (prevItem == null) {
                item = item.getParent();
            } else {
                item = prevItem;
            }
        }

        qualifiedNames.add("Pervasives");

        return qualifiedNames;
    }

}
