package exceptions;

public class ValidationException extends Exception {
  private static final long serialVersionUID = -599643883444978205L;
  protected String msg;

  public ValidationException(String msg) {
    super(msg);
    this.msg = msg;
  }

  @Override
  public String toString() {
    return msg;
  }
}