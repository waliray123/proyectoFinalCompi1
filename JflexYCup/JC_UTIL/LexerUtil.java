// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: LexerUtil.jflex

package analizadores;
import java_cup.runtime.*; 
import analizadores.symU.*;
import java.util.List;
import java.util.ArrayList;
import objetos.ErrorCom;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class LexerUtil implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\36\u0200\1\u0300\267\u0200\10\u0400\u1020\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\10\0\2\1\1\2\1\3\2\4\22\0\1\5\1\0"+
    "\1\6\2\7\3\0\2\7\3\0\1\10\1\0\1\11"+
    "\12\10\1\12\1\0\1\13\1\0\1\14\1\15\1\0"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\10\1\24"+
    "\1\25\2\10\1\26\1\27\1\30\1\31\1\32\1\10"+
    "\1\33\1\34\1\35\1\36\5\10\4\0\1\37\1\0"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\10\1\24"+
    "\1\25\2\10\1\26\1\27\1\30\1\31\1\32\1\10"+
    "\1\33\1\34\1\35\1\36\5\10\12\0\1\3\72\0"+
    "\100\7\60\0\2\40\115\0\1\41\u01a8\0\2\3\326\0"+
    "\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1280];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\4\1\5\1\1"+
    "\3\0\1\6\37\0\1\7\24\0\1\10\6\0\1\11"+
    "\1\12\2\0\1\6\2\0\1\13\3\0\1\14\1\0"+
    "\1\15\4\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\42\0\104\0\146\0\42\0\42\0\42\0\210"+
    "\0\252\0\314\0\356\0\42\0\u0110\0\u0132\0\u0154\0\u0176"+
    "\0\u0198\0\u01ba\0\u01dc\0\u01fe\0\u0220\0\u0242\0\u0264\0\u0286"+
    "\0\u02a8\0\u02ca\0\u02ec\0\u030e\0\u0330\0\u0352\0\u0374\0\u0396"+
    "\0\u03b8\0\u03da\0\u03fc\0\u041e\0\u0440\0\u0462\0\u0484\0\u04a6"+
    "\0\u04c8\0\u04ea\0\u050c\0\42\0\u052e\0\u0550\0\u0572\0\u0594"+
    "\0\u05b6\0\u05d8\0\u05fa\0\u061c\0\u063e\0\u0660\0\u0682\0\u06a4"+
    "\0\u06c6\0\u06e8\0\u070a\0\u072c\0\u074e\0\u0770\0\u0792\0\u07b4"+
    "\0\42\0\u07d6\0\u07f8\0\u081a\0\u083c\0\u085e\0\u0880\0\42"+
    "\0\42\0\u08a2\0\u08c4\0\u074e\0\u08e6\0\u0908\0\42\0\u092a"+
    "\0\u094c\0\u096e\0\42\0\u0990\0\42\0\u09b2\0\u09d4\0\u09f6"+
    "\0\u0a18";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\1\2\2\3\1\4\2\2\1\5\1\2"+
    "\1\6\1\7\3\2\1\10\21\2\43\0\2\3\1\0"+
    "\2\3\36\0\1\11\2\0\1\11\1\0\2\11\5\0"+
    "\6\11\1\12\13\11\41\0\1\13\4\0\1\11\2\0"+
    "\1\11\1\14\2\11\5\0\6\11\1\12\13\11\4\0"+
    "\1\11\2\0\1\11\1\14\2\11\5\0\6\11\1\12"+
    "\10\11\1\15\2\11\22\0\1\16\1\17\3\0\1\20"+
    "\2\0\1\21\5\0\1\22\1\0\1\20\3\0\1\11"+
    "\2\0\1\11\1\14\2\11\5\0\6\11\1\12\10\11"+
    "\1\23\2\11\20\0\1\24\41\0\1\25\44\0\1\26"+
    "\51\0\1\27\36\0\1\30\15\0\1\11\2\0\1\11"+
    "\1\14\2\11\5\0\6\11\1\12\5\11\1\31\5\11"+
    "\32\0\1\32\46\0\1\33\24\0\1\34\50\0\1\35"+
    "\47\0\1\36\6\0\1\11\2\0\1\11\1\14\2\11"+
    "\1\0\1\37\3\0\6\11\1\12\7\11\1\40\3\11"+
    "\1\0\1\41\35\0\1\42\35\0\1\43\26\0\1\44"+
    "\42\0\1\45\47\0\1\46\12\0\1\46\12\0\1\47"+
    "\32\0\1\11\2\0\1\11\1\14\2\11\1\0\1\37"+
    "\3\0\6\11\1\12\13\11\14\0\1\37\45\0\1\50"+
    "\4\0\1\51\12\0\1\52\37\0\1\53\4\0\1\53"+
    "\32\0\1\54\42\0\1\55\35\0\1\56\23\0\1\57"+
    "\50\0\1\60\37\0\1\61\57\0\1\62\4\0\1\62"+
    "\23\0\1\63\40\0\1\64\35\0\1\65\33\0\1\66"+
    "\5\0\21\66\30\0\1\67\12\0\1\67\27\0\1\70"+
    "\44\0\1\71\41\0\1\72\30\0\1\73\44\0\1\74"+
    "\16\0\2\75\3\0\3\75\1\76\5\75\21\76\3\75"+
    "\22\0\1\77\45\0\1\100\47\0\1\101\4\0\1\101"+
    "\33\0\1\102\24\0\1\103\45\0\1\104\27\0\1\105"+
    "\5\0\21\105\3\0\2\75\3\0\3\75\1\105\5\75"+
    "\21\105\3\75\33\0\1\106\37\0\1\107\37\0\1\110"+
    "\44\0\1\111\27\0\1\112\21\0\2\75\1\11\2\0"+
    "\1\113\1\114\1\113\1\105\1\113\3\75\1\113\6\105"+
    "\1\115\12\105\1\113\2\75\35\0\1\116\40\0\1\117"+
    "\4\0\1\117\24\0\1\120\17\0\1\11\2\0\1\11"+
    "\1\14\1\11\1\105\5\0\6\105\1\115\12\105\1\11"+
    "\2\0\2\75\1\11\2\0\1\113\1\114\1\113\1\105"+
    "\1\113\3\75\1\113\6\105\1\115\10\105\1\121\1\105"+
    "\1\113\2\75\31\0\1\122\26\0\1\123\23\0\2\75"+
    "\1\11\2\0\1\113\1\114\1\113\1\105\1\113\3\75"+
    "\1\113\6\105\1\115\10\105\1\124\1\105\1\113\2\75"+
    "\34\0\1\125\4\0\1\125\2\75\1\11\2\0\1\113"+
    "\1\114\1\113\1\105\1\113\3\75\1\113\6\105\1\115"+
    "\5\105\1\126\4\105\1\113\4\75\1\11\2\0\1\113"+
    "\1\114\1\113\1\105\1\113\1\127\2\75\1\113\6\105"+
    "\1\115\7\105\1\130\2\105\1\113\1\75\1\131\10\0"+
    "\1\105\1\47\4\0\21\105\3\0\2\75\1\11\2\0"+
    "\1\113\1\114\1\113\1\105\1\113\1\127\2\75\1\113"+
    "\6\105\1\115\12\105\1\113\2\75\10\0\1\105\1\0"+
    "\1\37\3\0\21\105\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2618];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\3\11\1\1\3\0\1\11\37\0"+
    "\1\11\24\0\1\11\6\0\2\11\2\0\1\1\2\0"+
    "\1\11\3\0\1\11\1\0\1\11\4\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[89];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    private List<ErrorCom> erroresCom;

    private void error(String lexeme) {
        erroresCom.add(new ErrorCom("Lexico","Simbolo no existe en el lenguaje",String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme,""));
    }    

    private void errorPrueba(String lexeme, String tipo) {
        erroresCom.add(new ErrorCom("PRUEBA",tipo,String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme,""));
    }

    public List<ErrorCom> getErroresCom() {
        return erroresCom;
    }




  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexerUtil(java.io.Reader in) {
      erroresCom = new ArrayList<>();
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { error(yytext()); new Symbol(symU.error,yyline,yycolumn, yytext());
            }
            // fall through
          case 14: break;
          case 2:
            { 
            }
            // fall through
          case 15: break;
          case 3:
            { return new Symbol(symU.BARRA,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 16: break;
          case 4:
            { return new Symbol(symU.MEN,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 17: break;
          case 5:
            { return new Symbol(symU.MAY,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 18: break;
          case 6:
            { return new Symbol(symU.VALP,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 19: break;
          case 7:
            { return new Symbol(symU.ID_CAP,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 20: break;
          case 8:
            { return new Symbol(symU.CANT_USOS,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 21: break;
          case 9:
            { return new Symbol(symU.DATOS_FORM,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 22: break;
          case 10:
            { return new Symbol(symU.NOMBRE_CAP,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 23: break;
          case 11:
            { return new Symbol(symU.CANT_FALLOS,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 24: break;
          case 12:
            { return new Symbol(symU.ULTIMA_FECHA,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 25: break;
          case 13:
            { return new Symbol(symU.CANT_ACIERTOS,yyline+1,yycolumn+1, yytext());
            }
            // fall through
          case 26: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java LexerUtil [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        LexerUtil scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new LexerUtil(reader);
          while ( !scanner.zzAtEOF ) scanner.next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
