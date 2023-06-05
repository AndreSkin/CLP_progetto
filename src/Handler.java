import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;

public class Handler extends BaseErrorListener {
    public static Handler handler= new Handler();
    ArrayList<String> errors;

    public Handler() {
        this.errors = new ArrayList<>();
    }
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add("Errore sintattico: Riga "+line+", Carattere: "+(charPositionInLine+1)+"\n");
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }
}
