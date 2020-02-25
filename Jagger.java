/* Jagger.java */
/* Generated By:JavaCC: Do not edit this line. Jagger.java */
import java.io.*;
public class Jagger implements JaggerConstants {
    public static void main(String args[]) throws ParseException, FileNotFoundException
    {
                Jagger parser;
                if(args.length!=0){
                        File inputFile = new File(args[0]);
                        InputStream input = new FileInputStream(inputFile);
                parser = new Jagger(input);
                }else
                        parser = new Jagger(System.in);
        parser.mainloop();
    }

// Main lopp: read expressions on a line until end of file.
//     mainloop → (expression <EOL>)* <EOF>
  static final public void mainloop() throws ParseException {Expression a;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMBER:
      case BOOLEAN:
      case 14:
      case 15:
      case 20:
      case 22:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      a = relation();
      jj_consume_token(EOL);
VisitorPrettyPrint v=new VisitorPrettyPrint();
                                                        a.accept(v);
                                                        System.out.println();
                                                        VisitorEvaluation vv = new VisitorEvaluation();
                                                        double d = a.accept(vv);
                                                        System.out.println(d);
    }
    jj_consume_token(0);
}

// Expression (the axiom).
// R -> E ('<'E | '>'E | '<='E | '>='E | '=='E | '<>'E)
  static final public Expression relation() throws ParseException {Expression a, b;
    a = expression();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 8:{
        jj_consume_token(8);
        b = relation();
a = new Relation(a,b,RelationOperator.INF);
        break;
        }
      case 9:{
        jj_consume_token(9);
        b = relation();
a = new Relation(a,b,RelationOperator.SUP);
        break;
        }
      case 10:{
        jj_consume_token(10);
        b = relation();
a = new Relation(a,b,RelationOperator.INF_EQ);
        break;
        }
      case 11:{
        jj_consume_token(11);
        b = relation();
a = new Relation(a,b,RelationOperator.SUP_EQ);
        break;
        }
      case 12:{
        jj_consume_token(12);
        b = relation();
a = new Relation(a,b,RelationOperator.EQ);
        break;
        }
      case 13:{
        jj_consume_token(13);
        b = relation();
a = new Not(new Relation(a,b,RelationOperator.EQ));
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      ;
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

// Expression (the axiom).
// E -> T ('+'T | '-'T | '||'T)*
  static final public Expression expression() throws ParseException {Expression a,b;
    a = term();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 14:
    case 15:
    case 16:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 14:{
        jj_consume_token(14);
        b = expression();
a = new BinOp(a,b,BinarOperator.PLUS);
        break;
        }
      case 15:{
        jj_consume_token(15);
        b = expression();
a = new BinOp(a,b,BinarOperator.MINUS);
        break;
        }
      case 16:{
        jj_consume_token(16);
        b = expression();
a = new BinOp(a,b,BinarOperator.OR);
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

// Term.
// T -> F ('*'F | '/'F | '&&'F)*
  static final public Expression term() throws ParseException {Expression a,b;
    a = factor();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 17:
      case 18:
      case 19:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 17:{
        jj_consume_token(17);
        b = factor();
a = new BinOp(a,b,BinarOperator.MULT);
        break;
        }
      case 18:{
        jj_consume_token(18);
        b = factor();
a = new BinOp(a,b,BinarOperator.DIV);
        break;
        }
      case 19:{
        jj_consume_token(19);
        b = expression();
a = new BinOp(a,b,BinarOperator.AND);
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

// Factor of an expression.
// F -> <NUMBER> | <BOOLEAN> | "(" +E ")" | "(" -E ")" | '+'F | '-'F | '!'F
  static final public Expression factor() throws ParseException {Token t; Expression e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUMBER:{
      t = jj_consume_token(NUMBER);
{if ("" != null) return new Num(Double.parseDouble(t.toString()));}
      break;
      }
    case 20:{
      jj_consume_token(20);
      e = relation();
      jj_consume_token(21);
{if ("" != null) return e;}
      break;
      }
    case 15:{
      label_3:
      while (true) {
        jj_consume_token(15);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 15:{
          ;
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          break label_3;
        }
      }
      e = factor();
{if ("" != null) return new BinOp(new Num(0.0), e, BinarOperator.MINUS);}
      break;
      }
    case 14:{
      label_4:
      while (true) {
        jj_consume_token(14);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 14:{
          ;
          break;
          }
        default:
          jj_la1[8] = jj_gen;
          break label_4;
        }
      }
      e = factor();
{if ("" != null) return new BinOp(new Num(0.0), e, BinarOperator.PLUS);}
      break;
      }
    case 22:{
      jj_consume_token(22);
      e = factor();
{if ("" != null) return new Not(e);}
      break;
      }
    case BOOLEAN:{
      t = jj_consume_token(BOOLEAN);
{if ("" != null) return new Bool(Boolean.parseBoolean(t.toString()));}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public JaggerTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x50c050,0x3f00,0x3f00,0x1c000,0x1c000,0xe0000,0xe0000,0x8000,0x4000,0x50c050,};
	}

  /** Constructor with InputStream. */
  public Jagger(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Jagger(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new JaggerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Jagger(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new JaggerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new JaggerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Jagger(JaggerTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JaggerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[23];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 10; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 23; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
