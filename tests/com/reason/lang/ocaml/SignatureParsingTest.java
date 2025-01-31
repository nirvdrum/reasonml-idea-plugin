package com.reason.lang.ocaml;

import com.reason.lang.BaseParsingTestCase;
import com.reason.lang.core.psi.*;
import com.reason.lang.core.signature.ORSignature;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class SignatureParsingTest extends BaseParsingTestCase {
    public SignatureParsingTest() {
        super("", "ml", new OclParserDefinition());
    }

    public void testLet() {
        PsiLet e = first(letExpressions(parseCode("let x:int = 1")));

        ORSignature signature = e.getORSignature();
        assertEquals("int", signature.asString(OclLanguage.INSTANCE));
        assertTrue(signature.isMandatory(0));
    }

    public void testVal() {
        PsiVal e = first(valExpressions(parseCode("val map : 'a option -> ('a -> 'b) -> 'b option")));

        ORSignature signature = e.getORSignature();
        assertEquals("'a option -> ('a -> 'b) -> 'b option", signature.asString(OclLanguage.INSTANCE));
        assertFalse(signature.isMandatory(0));
        assertTrue(signature.isMandatory(1));
        assertFalse(signature.isMandatory(2));
    }

    public void testTrimming() {
        PsiLet let = first(letExpressions(parseCode("let statelessComponent:\n  string ->\n  componentSpec(\n    stateless,\n    stateless,\n    noRetainedProps,\n    noRetainedProps,\n    actionless,\n  );\n")));

        PsiSignature signature = let.getPsiSignature();
        assertEquals("string -> componentSpec(stateless, stateless, noRetainedProps, noRetainedProps, actionless)", signature.asString(OclLanguage.INSTANCE));
    }

    public void testParsingNamedParams() {
        PsiLet let = first(letExpressions(parseCode("let padding: v:length -> h:length -> rule")));

        ORSignature signature = let.getORSignature();
        assertEquals(3, signature.getTypes().length);
        assertEquals("v:length -> h:length -> rule", signature.asString(OclLanguage.INSTANCE));
        assertTrue(signature.isMandatory(0));
        assertTrue(signature.isMandatory(1));
    }

    public void testOptionalFun() {
        PsiLet let = first(letExpressions(parseCode("let x: int -> string option -> string = fun a  -> fun b  -> c")));

        ORSignature signature = let.getORSignature();
        assertEquals(3, signature.getTypes().length);
        assertEquals("int -> string option -> string", signature.asString(OclLanguage.INSTANCE));
        assertTrue(signature.isMandatory(0));
        assertFalse(signature.isMandatory(1));
    }

    public void testOptionalFunParameters() {
        PsiLet let = first(letExpressions(parseCode("let x (a : int) (b : string option) (c : bool) (d : float) = 3")));

        PsiFunction function = (PsiFunction) let.getBinding().getFirstChild();
        List<PsiParameter> parameters = new ArrayList<>(function.getParameters());

        assertSize(4, parameters);
        assertTrue(parameters.get(0).getPsiSignature().asHMSignature().isMandatory(0));
        assertFalse(parameters.get(1).getPsiSignature().asHMSignature().isMandatory(0));
        assertTrue(parameters.get(2).getPsiSignature().asHMSignature().isMandatory(0));
        assertTrue(parameters.get(3).getPsiSignature().asHMSignature().isMandatory(0));
    }

    public void testUnitFunParameter() {
        PsiLet e = first(letExpressions(parseCode("let x (a : int) () = a")));

        PsiFunction function = (PsiFunction) e.getBinding().getFirstChild();
        List<PsiParameter> parameters = new ArrayList<>(function.getParameters());

        assertSize(2, parameters);
        assertEquals("(a : int)", parameters.get(0).getText());
        assertEquals("()", parameters.get(1).getText());
    }

    public void testSignatureItems() {
        PsiLet e = first(letExpressions(parseCode("let createAction: < children : React.element; dispatch : ([ `Arity_1 of Redux.Actions.opaqueFsa ], unit) Js.Internal.fn; url : 'url > Js.t -> React.element;")));
        ORSignature signature = e.getORSignature();

        assertEquals(2, signature.getTypes().length);
    }

}
