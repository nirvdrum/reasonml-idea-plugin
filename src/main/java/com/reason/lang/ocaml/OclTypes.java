package com.reason.lang.ocaml;

import com.reason.lang.core.stub.type.*;
import com.reason.lang.core.type.ORCompositeElementType;
import com.reason.lang.core.type.ORTokenElementType;
import com.reason.lang.core.type.ORTypes;

public class OclTypes extends ORTypes {

    public static final OclTypes INSTANCE = new OclTypes();

    private OclTypes() {
        // Composite element types

        EXTERNAL_STMT = new PsiExternalStubElementType("EXTERNAL_STMT", OclLanguage.INSTANCE);
        LET_STMT = new PsiLetStubElementType("LET_STMT", OclLanguage.INSTANCE);
        EXP_TYPE = new PsiTypeStubElementType("EXP_TYPE", OclLanguage.INSTANCE);
        VAL_EXPR = new PsiValStubElementType("VAL_EXPRESSION", OclLanguage.INSTANCE);
        MODULE_STMT = new PsiModuleStubElementType("MODULE_STMT", OclLanguage.INSTANCE);

        ANNOTATION_EXPR = new ORCompositeElementType("ANNOTATION_EXPRESSION", OclLanguage.INSTANCE);
        EXCEPTION_EXPR = new ORCompositeElementType("EXCEPTION_EXPRESSION", OclLanguage.INSTANCE);
        INCLUDE_STMT = new ORCompositeElementType("INCLUDE_STMT", OclLanguage.INSTANCE);
        MACRO_EXPR = new ORCompositeElementType("MACRO_EXPR", OclLanguage.INSTANCE);
        MACRO_NAME = new ORCompositeElementType("MACRO_NAME", OclLanguage.INSTANCE);
        C_FUN_EXPR = new ORCompositeElementType("C_FUN_EXPR", OclLanguage.INSTANCE);
        C_FUN_PARAM = new ORCompositeElementType("C_FUNC_PARAM", OclLanguage.INSTANCE);
        C_FUN_PARAMS = new ORCompositeElementType("C_FUN_PARAMS", OclLanguage.INSTANCE);
        C_FUN_BODY = new ORCompositeElementType("C_FUN_BODY", OclLanguage.INSTANCE);
        C_JS_OBJECT = new ORCompositeElementType("C_JS_OBJECT", OclLanguage.INSTANCE);
        C_JS_OBJECT_FIELD = new ORCompositeElementType("C_JS_OBJECT_FIELD", OclLanguage.INSTANCE);
        C_MACRO_RAW_BODY = new ORCompositeElementType("C_MACRO_RAW_BODY", OclLanguage.INSTANCE);
        C_ML_INTERPOLATOR = new ORCompositeElementType("C_ML_INTERPOLATOR", OclLanguage.INSTANCE);
        C_UNKNOWN_EXPR = new ORCompositeElementType("C_UNKNOWN_EXPR", OclLanguage.INSTANCE);
        C_VARIANT_CONSTRUCTOR = new ORCompositeElementType("C_VARIANT_CONSTRUCTOR", OclLanguage.INSTANCE);
        MODULE_PATH = new ORCompositeElementType("MODULE_PATH", OclLanguage.INSTANCE);
        CLASS_CONSTR = new ORCompositeElementType("CLASS_CONSTR", OclLanguage.INSTANCE);
        CLASS_PARAMS = new ORCompositeElementType("CLASS_PARAMS", OclLanguage.INSTANCE);
        CLASS_STMT = new ORCompositeElementType("CLASS_STMT", OclLanguage.INSTANCE);
        CLASS_FIELD = new ORCompositeElementType("CLASS_FIELD", OclLanguage.INSTANCE);
        CLASS_METHOD = new ORCompositeElementType("CLASS_METHOD", OclLanguage.INSTANCE);
        OPEN_STMT = new ORCompositeElementType("OPEN_STMT", OclLanguage.INSTANCE);
        ASSERT_STMT = new ORCompositeElementType("ASSERT_STMT", OclLanguage.INSTANCE);
        FUN_CALL_PARAMS = new ORCompositeElementType("FUN_CALL_PARAMS", OclLanguage.INSTANCE);
        IF_STMT = new ORCompositeElementType("IF_STMT", OclLanguage.INSTANCE);
        LET_BINDING = new ORCompositeElementType("LET_BINDING", OclLanguage.INSTANCE);
        TYPE_CONSTR_NAME = new ORCompositeElementType("TYPE_CONSTR_NAME", OclLanguage.INSTANCE);
        TYPE_BINDING = new ORCompositeElementType("TYPE_BINDING", OclLanguage.INSTANCE);
        MATCH_EXPR = new ORCompositeElementType("MATCH_EXPR", OclLanguage.INSTANCE);
        PATTERN_MATCH_EXPR = new ORCompositeElementType("PATTERN_MATCH_EXPR", OclLanguage.INSTANCE);
        SCOPED_EXPR = new ORCompositeElementType("SCOPED_EXPR", OclLanguage.INSTANCE);
        SIG_SCOPE = new ORCompositeElementType("SIG_SCOPE", OclLanguage.INSTANCE);
        NAMED_SYMBOL = new ORCompositeElementType("NAMED_SYMBOL", OclLanguage.INSTANCE);
        BIN_CONDITION = new ORCompositeElementType("BIN_CONDITION", OclLanguage.INSTANCE);
        VARIANT_EXP = new ORCompositeElementType("VARIANT_EXP", OclLanguage.INSTANCE);
        SWITCH_EXPR = new ORCompositeElementType("SWITCH_EXPR", OclLanguage.INSTANCE);
        TAG_START = new ORCompositeElementType("TAG_START", OclLanguage.INSTANCE);
        TAG_CLOSE = new ORCompositeElementType("TAG_CLOSE", OclLanguage.INSTANCE);
        TAG_PROPERTY = new ORCompositeElementType("TAG_PROPERTY", OclLanguage.INSTANCE);
        RECORD_EXPR = new ORCompositeElementType("RECORD_EXPR", OclLanguage.INSTANCE);
        RECORD_FIELD = new ORCompositeElementType("RECORD_FIELD", OclLanguage.INSTANCE);
        INTERPOLATION_EXPR = new ORCompositeElementType("INTERPOLATION_EXPR", OclLanguage.INSTANCE);
        TRY_EXPR = new ORCompositeElementType("TRY_EXPR", OclLanguage.INSTANCE);
        WITH_EXPR = new ORCompositeElementType("WITH_EXPR", OclLanguage.INSTANCE);
        UPPER_SYMBOL = new ORCompositeElementType("UPPER_SYMBOL", OclLanguage.INSTANCE);
        LOWER_SYMBOL = new ORCompositeElementType("LOWER_SYMBOL", OclLanguage.INSTANCE);
        STRUCT_EXPR = new ORCompositeElementType("STRUCT_EXPR", OclLanguage.INSTANCE);
        MIXIN_FIELD = new ORCompositeElementType("MIXIN_FIELD", OclLanguage.INSTANCE);

        // Token element types

        ARRAY = new ORTokenElementType("ARRAY", OclLanguage.INSTANCE);
        BOOL = new ORTokenElementType("BOOL", OclLanguage.INSTANCE);
        CHAR = new ORTokenElementType("CHAR", OclLanguage.INSTANCE);
        INT = new ORTokenElementType("INT", OclLanguage.INSTANCE);
        FLOAT = new ORTokenElementType("FLOAT", OclLanguage.INSTANCE);
        LIST = new ORTokenElementType("LIST", OclLanguage.INSTANCE);
        STRING = new ORTokenElementType("STRING", OclLanguage.INSTANCE);

        BOOL_VALUE = new ORTokenElementType("BOOL_VALUE", OclLanguage.INSTANCE);
        STRING_VALUE = new ORTokenElementType("STRING_VALUE", OclLanguage.INSTANCE);
        FLOAT_VALUE = new ORTokenElementType("FLOAT_VALUE", OclLanguage.INSTANCE);
        CHAR_VALUE = new ORTokenElementType("CHAR_VALUE", OclLanguage.INSTANCE);
        INT_VALUE = new ORTokenElementType("INT_VALUE", OclLanguage.INSTANCE);
        SWITCH = new ORTokenElementType("SWITCH", OclLanguage.INSTANCE);
        GENERIC_COND = new ORTokenElementType("GENERIC_COND", OclLanguage.INSTANCE);
        FUNCTION = new ORTokenElementType("FUNCTION", OclLanguage.INSTANCE);
        FUN = new ORTokenElementType("FUN", OclLanguage.INSTANCE);
        FUNCTOR = new ORTokenElementType("FUNCTOR", OclLanguage.INSTANCE);
        IF = new ORTokenElementType("IF", OclLanguage.INSTANCE);
        EXCEPTION_NAME = new ORTokenElementType("EXCEPTION_NAME", OclLanguage.INSTANCE);
        LOCAL_OPEN = new ORTokenElementType("LOCAL_OPEN", OclLanguage.INSTANCE);
        PROPERTY_NAME = new ORTokenElementType("PROPERTY_NAME", OclLanguage.INSTANCE);
        AND = new ORTokenElementType("AND", OclLanguage.INSTANCE);
        ANDAND = new ORTokenElementType("ANDAND", OclLanguage.INSTANCE);
        ARROBASE = new ORTokenElementType("ARROBASE", OclLanguage.INSTANCE);
        ARROW = new ORTokenElementType("ARROW", OclLanguage.INSTANCE);
        ASSERT = new ORTokenElementType("ASSERT", OclLanguage.INSTANCE);
        AS = new ORTokenElementType("AS", OclLanguage.INSTANCE);
        BACKTICK = new ORTokenElementType("BACKTICK", OclLanguage.INSTANCE);
        BEGIN = new ORTokenElementType("BEGIN", OclLanguage.INSTANCE);
        CARRET = new ORTokenElementType("CARRET", OclLanguage.INSTANCE);
        COLON = new ORTokenElementType("COLON", OclLanguage.INSTANCE);
        COMMA = new ORTokenElementType("COMMA", OclLanguage.INSTANCE);
        COMMENT = new ORTokenElementType("COMMENT", OclLanguage.INSTANCE);
        DIFF = new ORTokenElementType("DIFF", OclLanguage.INSTANCE);
        LT_OR_EQUAL = new ORTokenElementType("LT_OR_EQUAL", OclLanguage.INSTANCE);
        GT_OR_EQUAL = new ORTokenElementType("GT_OR_EQUAL", OclLanguage.INSTANCE);
        DOLLAR = new ORTokenElementType("DOLLAR", OclLanguage.INSTANCE);
        DOT = new ORTokenElementType("DOT", OclLanguage.INSTANCE);
        DOTDOTDOT = new ORTokenElementType("DOTDOTDOT", OclLanguage.INSTANCE);
        DO = new ORTokenElementType("DO", OclLanguage.INSTANCE);
        DONE = new ORTokenElementType("DONE", OclLanguage.INSTANCE);
        ELSE = new ORTokenElementType("ELSE", OclLanguage.INSTANCE);
        END = new ORTokenElementType("END", OclLanguage.INSTANCE);
        NOT_EQ = new ORTokenElementType("EQ", OclLanguage.INSTANCE);
        NOT_EQEQ = new ORTokenElementType("EQEQ", OclLanguage.INSTANCE);
        EQ = new ORTokenElementType("EQ", OclLanguage.INSTANCE);
        EQEQ = new ORTokenElementType("EQEQ", OclLanguage.INSTANCE);
        EQEQEQ = new ORTokenElementType("EQEQEQ", OclLanguage.INSTANCE);
        EXCEPTION = new ORTokenElementType("EXCEPTION", OclLanguage.INSTANCE);
        EXCLAMATION_MARK = new ORTokenElementType("EXCLAMATION_MARK", OclLanguage.INSTANCE);
        EXTERNAL = new ORTokenElementType("EXTERNAL", OclLanguage.INSTANCE);
        FOR = new ORTokenElementType("FOR", OclLanguage.INSTANCE);
        TYPE_ARGUMENT = new ORTokenElementType("TYPE_ARGUMENT", OclLanguage.INSTANCE);
        GT = new ORTokenElementType("GT", OclLanguage.INSTANCE);
        IN = new ORTokenElementType("IN", OclLanguage.INSTANCE);
        LAZY = new ORTokenElementType("LAZY", OclLanguage.INSTANCE);
        INCLUDE = new ORTokenElementType("INCLUDE", OclLanguage.INSTANCE);
        LARRAY = new ORTokenElementType("LARRAY", OclLanguage.INSTANCE);
        LBRACE = new ORTokenElementType("LBRACE", OclLanguage.INSTANCE);
        LBRACKET = new ORTokenElementType("LBRACKET", OclLanguage.INSTANCE);
        LET = new ORTokenElementType("LET", OclLanguage.INSTANCE);
        LIDENT = new ORTokenElementType("LIDENT", OclLanguage.INSTANCE);
        LPAREN = new ORTokenElementType("LPAREN", OclLanguage.INSTANCE);
        LT = new ORTokenElementType("LT", OclLanguage.INSTANCE);
        MATCH = new ORTokenElementType("MATCH", OclLanguage.INSTANCE);
        MINUS = new ORTokenElementType("MINUS", OclLanguage.INSTANCE);
        MINUSDOT = new ORTokenElementType("MINUSDOT", OclLanguage.INSTANCE);
        MODULE = new ORTokenElementType("MODULE", OclLanguage.INSTANCE);
        MUTABLE = new ORTokenElementType("MUTABLE", OclLanguage.INSTANCE);
        NONE = new ORTokenElementType("NONE", OclLanguage.INSTANCE);
        OF = new ORTokenElementType("OF", OclLanguage.INSTANCE);
        OPEN = new ORTokenElementType("OPEN", OclLanguage.INSTANCE);
        OPTION = new ORTokenElementType("OPTION", OclLanguage.INSTANCE);
        POLY_VARIANT = new ORTokenElementType("POLY_VARIANT", OclLanguage.INSTANCE);
        VARIANT_NAME = new ORTokenElementType("VARIANT_NAME", OclLanguage.INSTANCE);
        PIPE = new ORTokenElementType("PIPE", OclLanguage.INSTANCE);
        PIPE_FORWARD = new ORTokenElementType("PIPE_FORWARD", OclLanguage.INSTANCE);
        PIPE_FIRST = new ORTokenElementType("PIPE_FIRST", OclLanguage.INSTANCE);
        PLUS = new ORTokenElementType("PLUS", OclLanguage.INSTANCE);
        PERCENT = new ORTokenElementType("PERCENT", OclLanguage.INSTANCE);
        PLUSDOT = new ORTokenElementType("PLUSDOT", OclLanguage.INSTANCE);
        QUESTION_MARK = new ORTokenElementType("QUESTION_MARK", OclLanguage.INSTANCE);
        QUOTE = new ORTokenElementType("QUOTE", OclLanguage.INSTANCE);
        RAISE = new ORTokenElementType("RAISE", OclLanguage.INSTANCE);
        RARRAY = new ORTokenElementType("RARRAY", OclLanguage.INSTANCE);
        RBRACE = new ORTokenElementType("RBRACE", OclLanguage.INSTANCE);
        RBRACKET = new ORTokenElementType("RBRACKET", OclLanguage.INSTANCE);
        REC = new ORTokenElementType("REC", OclLanguage.INSTANCE);
        REF = new ORTokenElementType("REF", OclLanguage.INSTANCE);
        RPAREN = new ORTokenElementType("RPAREN", OclLanguage.INSTANCE);
        SEMI = new ORTokenElementType("SEMI", OclLanguage.INSTANCE);
        SIG = new ORTokenElementType("SIG", OclLanguage.INSTANCE);
        SHARP = new ORTokenElementType("SHARP", OclLanguage.INSTANCE);
        SHARPSHARP = new ORTokenElementType("SHARPSHARP", OclLanguage.INSTANCE);
        SHORTCUT = new ORTokenElementType("SHORTCUT", OclLanguage.INSTANCE);
        SLASH = new ORTokenElementType("SLASH", OclLanguage.INSTANCE);
        SLASHDOT = new ORTokenElementType("SLASHDOT", OclLanguage.INSTANCE);
        SOME = new ORTokenElementType("SOME", OclLanguage.INSTANCE);
        STAR = new ORTokenElementType("STAR", OclLanguage.INSTANCE);
        STARDOT = new ORTokenElementType("STARDOT", OclLanguage.INSTANCE);
        STRUCT = new ORTokenElementType("STRUCT", OclLanguage.INSTANCE);
        TAG_AUTO_CLOSE = new ORTokenElementType("TAG_AUTO_CLOSE", OclLanguage.INSTANCE);
        TAG_NAME = new ORTokenElementType("TAG_NAME", OclLanguage.INSTANCE);
        TAG_LT = new ORTokenElementType("TAG_LT", OclLanguage.INSTANCE);
        TAG_LT_SLASH = new ORTokenElementType("TAG_LT_SLASH", OclLanguage.INSTANCE);
        TAG_GT = new ORTokenElementType("TAG_GT", OclLanguage.INSTANCE);
        TILDE = new ORTokenElementType("TILDE", OclLanguage.INSTANCE);
        TO = new ORTokenElementType("TO", OclLanguage.INSTANCE);
        THEN = new ORTokenElementType("THEN", OclLanguage.INSTANCE);
        TRY = new ORTokenElementType("TRY", OclLanguage.INSTANCE);
        TYPE = new ORTokenElementType("TYPE", OclLanguage.INSTANCE);
        UIDENT = new ORTokenElementType("UIDENT", OclLanguage.INSTANCE);
        UNIT = new ORTokenElementType("UNIT", OclLanguage.INSTANCE);
        VAL = new ORTokenElementType("VAL", OclLanguage.INSTANCE);
        PUB = new ORTokenElementType("PUB", OclLanguage.INSTANCE);
        PRI = new ORTokenElementType("PRI", OclLanguage.INSTANCE);
        WHEN = new ORTokenElementType("WHEN", OclLanguage.INSTANCE);
        WHILE = new ORTokenElementType("WHILE", OclLanguage.INSTANCE);
        WITH = new ORTokenElementType("WITH", OclLanguage.INSTANCE);

        ASR = new ORTokenElementType("ASR", OclLanguage.INSTANCE);
        CLASS = new ORTokenElementType("CLASS", OclLanguage.INSTANCE);
        CONSTRAINT = new ORTokenElementType("CONSTRAINT", OclLanguage.INSTANCE);
        DOWNTO = new ORTokenElementType("DOWNTO", OclLanguage.INSTANCE);
        INHERIT = new ORTokenElementType("INHERIT", OclLanguage.INSTANCE);
        INITIALIZER = new ORTokenElementType("INITIALIZER", OclLanguage.INSTANCE);
        LAND = new ORTokenElementType("LAND", OclLanguage.INSTANCE);
        LOR = new ORTokenElementType("LOR", OclLanguage.INSTANCE);
        LSL = new ORTokenElementType("LSL", OclLanguage.INSTANCE);
        LSR = new ORTokenElementType("LSR", OclLanguage.INSTANCE);
        LXOR = new ORTokenElementType("LXOR", OclLanguage.INSTANCE);
        METHOD = new ORTokenElementType("METHOD", OclLanguage.INSTANCE);
        MOD = new ORTokenElementType("MOD", OclLanguage.INSTANCE);
        NEW = new ORTokenElementType("NEW", OclLanguage.INSTANCE);
        NONREC = new ORTokenElementType("NONREC", OclLanguage.INSTANCE);
        OR = new ORTokenElementType("OR", OclLanguage.INSTANCE);
        PRIVATE = new ORTokenElementType("PRIVATE", OclLanguage.INSTANCE);
        VIRTUAL = new ORTokenElementType("VIRTUAL", OclLanguage.INSTANCE);

        COLON_EQ = new ORTokenElementType("COLON_EQ", OclLanguage.INSTANCE);
        COLON_GT = new ORTokenElementType("COLON_GT", OclLanguage.INSTANCE);
        DOTDOT = new ORTokenElementType("DOTDOT", OclLanguage.INSTANCE);
        SEMISEMI = new ORTokenElementType("SEMISEMI", OclLanguage.INSTANCE);
        GT_BRACKET = new ORTokenElementType("GT_BRACKET", OclLanguage.INSTANCE);
        GT_BRACE = new ORTokenElementType("GT_BRACE", OclLanguage.INSTANCE);
        LEFT_ARROW = new ORTokenElementType("LEFT_ARROW", OclLanguage.INSTANCE);
        RIGHT_ARROW = new ORTokenElementType("RIGHT_ARROW", OclLanguage.INSTANCE);

        OBJECT = new ORTokenElementType("OBJECT", OclLanguage.INSTANCE);
        RECORD = new ORTokenElementType("RECORD", OclLanguage.INSTANCE);

        AMPERSAND = new ORTokenElementType("AMPERSAND", OclLanguage.INSTANCE);
        BRACKET_GT = new ORTokenElementType("BRACKET_GT", OclLanguage.INSTANCE);
        BRACKET_LT = new ORTokenElementType("BRACKET_LT", OclLanguage.INSTANCE);
        BRACE_LT = new ORTokenElementType("BRACE_LT", OclLanguage.INSTANCE);

        ML_STRING_OPEN = new ORTokenElementType("ML_STRING_OPEN", OclLanguage.INSTANCE);
        ML_STRING_CLOSE = new ORTokenElementType("ML_STRING_CLOSE", OclLanguage.INSTANCE);
        JS_STRING_OPEN = new ORTokenElementType("JS_STRING_OPEN", OclLanguage.INSTANCE);
        JS_STRING_CLOSE = new ORTokenElementType("JS_STRING_CLOSE", OclLanguage.INSTANCE);
        UNDERSCORE = new ORTokenElementType("UNDERSCORE", OclLanguage.INSTANCE);
    }
}
