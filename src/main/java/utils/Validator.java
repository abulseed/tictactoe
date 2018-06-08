package utils;

import exceptions.ValidationException;

public class Validator {
  private static int boardSize = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));

  public static int[] validate(String input) throws ValidationException {
    String[] splitted = input.split(",");
    if (splitted.length != 2) {
      throw new ValidationException("Incorrect Input");
    }

    try {
      int x = Integer.parseInt(splitted[0]);
      if (x >= Validator.boardSize) {
        throw new IndexOutOfBoundsException("X coordinate is out of boundry.");
      }
      int y = Integer.parseInt(splitted[1]);
      if (y >= Validator.boardSize) {
        throw new IndexOutOfBoundsException("Y coordinate is out of boundry.");
      }
      int[] res = { x, y };
      return res;
    } catch (Exception e) {
      throw new ValidationException(e.getMessage());
    }
  }
}