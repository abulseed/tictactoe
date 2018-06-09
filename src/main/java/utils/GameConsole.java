package utils;

import java.io.IOException;
import java.util.Scanner;

import exceptions.ValidationException;

public class GameConsole {
  private static Scanner sc;

  private GameConsole() {
    sc = new Scanner(System.in);
  }

  private static class GameConsoleHolder {
    private static final void OPEN_CONSOLE() {
      new GameConsole();
    }
  }

  public static void openConsole() {
    GameConsoleHolder.OPEN_CONSOLE();
  }

  public static void closeConsole() {
    GameConsole.sc.close();
  }

  public static void showMessage(String msg) {
    System.out.println(msg);
  }

  public static int[] readCoordinates() throws IOException, ValidationException {
    try {
      String input = GameConsole.sc.nextLine();
      return Validator.validate(input);
    } catch (ValidationException nfe) {
      throw new ValidationException(nfe.getMessage());
    } catch (Exception ex) {
      throw ex;
    }
  }
}