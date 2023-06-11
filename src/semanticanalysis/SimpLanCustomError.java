package semanticanalysis;

public class SimpLanCustomError extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String message = "Errore";

    public SimpLanCustomError(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
