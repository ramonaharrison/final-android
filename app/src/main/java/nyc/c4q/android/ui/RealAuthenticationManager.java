package nyc.c4q.android.ui;

public class RealAuthenticationManager implements AuthenticationManager {
  public boolean validateLogin(String email, String password) {
    // implement authentication logic
    // valid credentials are email: "c4q", password: "c4q"
    final String CREDENTIALS = "c4q";
    if (CREDENTIALS.equals(email) && CREDENTIALS.equals(password)) {
      return true;
    } else {
      return false;
    }
  }
}
