package semanticanalysis;

public class Error2 extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String message = "Errore";

    public Error2(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
