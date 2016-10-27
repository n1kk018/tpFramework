package fr.afcepf.al28.framework.exception;

/**
 * Custom exceptions of the framework.
 * @author ronan
 */
public class FrameworkException extends Exception{
    /**
     * Identifiant de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * erreur generique dans l'enumeration.
     */
    private FrameworkErrorCode errorCode;
    /**
     * Constructeur avec message.
     * @param message message
     */
    public FrameworkException(String message) {
        super(message);
    }
    /**
     * Constructeur avec code erreur et message.
     * @param paramError code erreur
     * @param message message
     */
    public FrameworkException(FrameworkErrorCode paramError, String message) {
        super(message);
        errorCode = paramError;
    }
    /**
     * @return the errorCode
     */
    public FrameworkErrorCode getErrorCode() {
        return errorCode;
    }
    /**
     * @param paramErrorCode the errorCode to set
     */
    public void setErrorCode(FrameworkErrorCode paramErrorCode) {
        errorCode = paramErrorCode;
    }
}
