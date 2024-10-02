package com.immenser.util;

import com.immenser.exception.UserInputException;
import com.immenser.model.Position;

public class StringToPositionParser {

    public static Position getPosition(String position) throws UserInputException {
        char column = position.charAt(0);
        if (column < 'А' || column > 'К') {
            throw new UserInputException("Номер столбца должен быть буквой в диапазоне от А до К. Попробуй еще раз.");
        }
        try {
            int row = Integer.parseInt(position.substring(1));
            if (row < 1 || row > 10) {
                throw new UserInputException("Номер строки должен быть в диапазоне от 1 до 10. Попробуй еще раз.");
            }
            return new Position(row, column - 'А' + 1);
        } catch (NumberFormatException e) {
            throw new UserInputException("Номер строки должен быть числом в диапазоне от 1 до 10. Попробуй еще раз.");
        }
    }
}
