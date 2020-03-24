package Model.GameItems;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Board {
    public ArrayList<Field> fields = new ArrayList<>(9);

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Field newField = new Field(i, j);
                fields.add(newField);
            }
        }
    }

    public void setFieldStatus(String x, String y, FieldStatus status) throws Exception {
        for (Field field : fields) {
            int xInt = 0;
            int yInt = 0;
            try {
                xInt = Integer.parseInt(x);
            } catch (NumberFormatException e) {
                throw new Exception("The x input is incorrect.");
            }
            try {
                yInt = Integer.parseInt(y);
            } catch (NumberFormatException e) {
                throw new Exception("The y input is incorrect.");
            }
            if (field.getX() == xInt && field.getY() == yInt) {
                if (field.getFieldStatus() == FieldStatus.NONE) {
                    field.setState(status);
                    return;
                } else {
                    throw new Exception("This location is already in use.");
                }
            }
        }
        throw new Exception("This location does not exist.");
    }

    public FieldStatus getFieldStatus(int x, int y) {
        for (Field field : fields) {
            if (field.getX() == x && field.getY() == y) {
                return field.getFieldStatus();
            }
        }
        return null;
    }
}
