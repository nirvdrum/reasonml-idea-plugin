package com.reason.lang.core.type;

import com.intellij.psi.tree.IElementType;

public abstract class ORTypes {
    // Composite element types

    public IElementType EXTERNAL_STMT;
    public IElementType LET_STMT;
    public IElementType MODULE_STMT;
    public IElementType CLASS_STMT;
    public IElementType EXP_TYPE;
    public IElementType VAL_EXPR;
    public IElementType CLASS_PARAMS;
    public IElementType CLASS_CONSTR;
    public IElementType CLASS_FIELD;
    public IElementType CLASS_METHOD;
    public IElementType ANNOTATION_EXPR;
    public IElementType EXCEPTION_EXPR;
    public IElementType OPEN_STMT;
    public IElementType INCLUDE_STMT;
    public IElementType LET_BINDING;
    public IElementType MACRO_EXPR;
    public IElementType MACRO_NAME;
    public IElementType C_FUN_EXPR;
    public IElementType C_FUN_PARAMS;
    public IElementType C_FUN_PARAM;
    public IElementType C_FUN_BODY;
    public IElementType C_JS_OBJECT;
    public IElementType C_JS_OBJECT_FIELD;
    public IElementType C_MACRO_RAW_BODY;
    public IElementType C_ML_INTERPOLATOR;
    public IElementType C_UNKNOWN_EXPR;
    public IElementType C_VARIANT_CONSTRUCTOR;
    public IElementType MIXIN_FIELD;
    public IElementType MODULE_PATH;
    public IElementType ASSERT_STMT;
    public IElementType SCOPED_EXPR;
    public IElementType VARIANT_EXP;
    public IElementType IF_STMT;
    public IElementType BIN_CONDITION;
    public IElementType NAMED_SYMBOL;
    public IElementType PATTERN_MATCH_EXPR;
    public IElementType INTERPOLATION_EXPR;
    public IElementType SIG_SCOPE;
    public IElementType TAG_START;
    public IElementType TAG_CLOSE;
    public IElementType TAG_PROPERTY;
    public IElementType FUN_CALL_PARAMS;
    public IElementType RECORD_EXPR;
    public IElementType RECORD_FIELD;
    public IElementType SWITCH_EXPR;
    public IElementType MATCH_EXPR;
    public IElementType TRY_EXPR;
    public IElementType WITH_EXPR;
    public IElementType TYPE_CONSTR_NAME;
    public IElementType TYPE_BINDING;
    public IElementType UPPER_SYMBOL;
    public IElementType LOWER_SYMBOL;
    public IElementType STRUCT_EXPR;

    // Token element types

    public ORTokenElementType ARRAY;
    public ORTokenElementType BOOL;
    public ORTokenElementType CHAR;
    public ORTokenElementType FLOAT;
    public ORTokenElementType INT;
    public ORTokenElementType LIST;
    public ORTokenElementType STRING;

    public ORTokenElementType BOOL_VALUE;
    public ORTokenElementType STRING_VALUE;
    public ORTokenElementType FLOAT_VALUE;
    public ORTokenElementType CHAR_VALUE;
    public ORTokenElementType INT_VALUE;

    public ORTokenElementType AND;
    public ORTokenElementType ANDAND;
    public ORTokenElementType ASSERT;
    public ORTokenElementType BEGIN;
    public ORTokenElementType CLASS;
    public ORTokenElementType CONSTRAINT;
    public ORTokenElementType DO;
    public ORTokenElementType DONE;
    public ORTokenElementType DOWNTO;
    public ORTokenElementType ELSE;
    public ORTokenElementType END;
    public ORTokenElementType EXCEPTION;
    public ORTokenElementType EXTERNAL;
    public ORTokenElementType FOR;
    public ORTokenElementType FUN;
    public ORTokenElementType FUNCTION;
    public ORTokenElementType FUNCTOR;
    public ORTokenElementType IF;
    public ORTokenElementType IN;
    public ORTokenElementType INCLUDE;
    public ORTokenElementType INHERIT;
    public ORTokenElementType INITIALIZER;
    public ORTokenElementType LAZY;
    public ORTokenElementType LET;
    public ORTokenElementType MODULE;
    public ORTokenElementType MUTABLE;
    public ORTokenElementType NEW;
    public ORTokenElementType NONREC;
    public ORTokenElementType OBJECT;
    public ORTokenElementType OF;
    public ORTokenElementType OPEN;
    public ORTokenElementType OR;
    public ORTokenElementType PUB;
    public ORTokenElementType PRI;
    public ORTokenElementType REC;
    public ORTokenElementType SIG;
    public ORTokenElementType STRUCT;
    public ORTokenElementType SWITCH;
    public ORTokenElementType THEN;
    public ORTokenElementType TO;
    public ORTokenElementType TRY;
    public ORTokenElementType TYPE;
    public ORTokenElementType VAL;
    public ORTokenElementType VIRTUAL;
    public ORTokenElementType WHEN;
    public ORTokenElementType WHILE;
    public ORTokenElementType WITH;

    public ORTokenElementType GENERIC_COND;
    public ORTokenElementType EXCEPTION_NAME;
    public ORTokenElementType LOCAL_OPEN;
    public ORTokenElementType PROPERTY_NAME;
    public ORTokenElementType SHARPSHARP;
    public ORTokenElementType ARROBASE;
    public ORTokenElementType ARROW;
    public ORTokenElementType AS;
    public ORTokenElementType BACKTICK;
    public ORTokenElementType CARRET;
    public ORTokenElementType COLON;
    public ORTokenElementType COMMA;
    public ORTokenElementType COMMENT;
    public ORTokenElementType DIFF;
    public ORTokenElementType LT_OR_EQUAL;
    public ORTokenElementType GT_OR_EQUAL;
    public ORTokenElementType DOLLAR;
    public ORTokenElementType DOT;
    public ORTokenElementType DOTDOTDOT;
    public ORTokenElementType NOT_EQ;
    public ORTokenElementType NOT_EQEQ;
    public ORTokenElementType EQ;
    public ORTokenElementType EQEQ;
    public ORTokenElementType EQEQEQ;
    public ORTokenElementType EXCLAMATION_MARK;
    public ORTokenElementType TYPE_ARGUMENT;
    public ORTokenElementType GT;
    public ORTokenElementType LARRAY;
    public ORTokenElementType LBRACE;
    public ORTokenElementType LBRACKET;
    public ORTokenElementType LIDENT;
    public ORTokenElementType LPAREN;
    public ORTokenElementType LT;
    public ORTokenElementType MATCH;
    public ORTokenElementType MINUS;
    public ORTokenElementType MINUSDOT;
    public ORTokenElementType NONE;
    public ORTokenElementType OPTION;
    public ORTokenElementType POLY_VARIANT;
    public ORTokenElementType VARIANT_NAME;
    public ORTokenElementType PIPE;
    public ORTokenElementType PIPE_FORWARD;
    public ORTokenElementType PIPE_FIRST;
    public ORTokenElementType PLUS;
    public ORTokenElementType PERCENT;
    public ORTokenElementType PLUSDOT;
    public ORTokenElementType QUESTION_MARK;
    public ORTokenElementType QUOTE;
    public ORTokenElementType RAISE;
    public ORTokenElementType RARRAY;
    public ORTokenElementType RBRACE;
    public ORTokenElementType RBRACKET;
    public ORTokenElementType REF;
    public ORTokenElementType RPAREN;
    public ORTokenElementType SEMI;
    public ORTokenElementType SHARP;
    public ORTokenElementType SHORTCUT;
    public ORTokenElementType SLASH;
    public ORTokenElementType SLASHDOT;
    public ORTokenElementType SOME;
    public ORTokenElementType STAR;
    public ORTokenElementType STARDOT;
    public ORTokenElementType TAG_AUTO_CLOSE;
    public ORTokenElementType TAG_NAME;
    public ORTokenElementType TAG_LT;
    public ORTokenElementType TAG_LT_SLASH;
    public ORTokenElementType TAG_GT;
    public ORTokenElementType TILDE;
    public ORTokenElementType UIDENT;
    public ORTokenElementType UNIT;
    public ORTokenElementType RECORD;
    public ORTokenElementType ASR;
    public ORTokenElementType LAND;
    public ORTokenElementType LOR;
    public ORTokenElementType LSL;
    public ORTokenElementType LSR;
    public ORTokenElementType LXOR;
    public ORTokenElementType METHOD;
    public ORTokenElementType MOD;
    public ORTokenElementType PRIVATE;
    public ORTokenElementType COLON_EQ;
    public ORTokenElementType COLON_GT;
    public ORTokenElementType DOTDOT;
    public ORTokenElementType SEMISEMI;
    public ORTokenElementType GT_BRACKET;
    public ORTokenElementType GT_BRACE;
    public ORTokenElementType LEFT_ARROW;
    public ORTokenElementType RIGHT_ARROW;
    public ORTokenElementType AMPERSAND;
    public ORTokenElementType BRACKET_GT;
    public ORTokenElementType BRACKET_LT;
    public ORTokenElementType BRACE_LT;
    public ORTokenElementType ML_STRING_OPEN;
    public ORTokenElementType ML_STRING_CLOSE;
    public ORTokenElementType JS_STRING_OPEN;
    public ORTokenElementType JS_STRING_CLOSE;
    public ORTokenElementType UNDERSCORE;
}
