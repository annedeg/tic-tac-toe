package Model;

import Model.GameItems.Board;
import Model.GameItems.FieldStatus;

import java.util.ArrayList;

public class Game extends Model {
    Board board;
    int turns;
    int player = 1;

    public Game() {
        turns = 0;
        board = new Board();
    }


    public FieldStatus checkFinished() {
        if (!(turns < 5)) {
            //Check horizontal
            for (int i = 0; i < 3; i++) {
                ArrayList<FieldStatus> fieldStatuses = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    fieldStatuses.add(board.getFieldStatus(i,j));
                }
                if(fieldStatuses.get(0) != FieldStatus.NONE  && fieldStatuses.get(0) == fieldStatuses.get(1) && fieldStatuses.get(0) == fieldStatuses.get(2)) {
                    return fieldStatuses.get(0);
                }
            }

            //Check vertical
            for (int j = 0; j < 3; j++) {
                ArrayList<FieldStatus> fieldStatuses = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    fieldStatuses.add(board.getFieldStatus(i,j));
                }
                if(fieldStatuses.get(0) != FieldStatus.NONE && fieldStatuses.get(0) == fieldStatuses.get(1) && fieldStatuses.get(0) == fieldStatuses.get(2)) {
                    return fieldStatuses.get(0);
                }
            }

            //Check other
            if(board.getFieldStatus(0,0) != FieldStatus.NONE && board.getFieldStatus(0,0) == board.getFieldStatus(1,1) && board.getFieldStatus(0,0) ==  board.getFieldStatus(2,2)) {
                return board.getFieldStatus(0, 0);
            }
            if(board.getFieldStatus(2,0) != FieldStatus.NONE && board.getFieldStatus(2,0)  == board.getFieldStatus(1,1) && board.getFieldStatus(2,0) ==  board.getFieldStatus(0,2)) {
                return board.getFieldStatus(0, 0);
            }

        } else if(turns > 9) {
            return FieldStatus.NONE;
        }
        return null;
    }

    public void setFieldStatus(int x, int y) throws Exception {
        FieldStatus fieldStatus = FieldStatus.NONE;
        if(player == 1) {
            fieldStatus = FieldStatus.CIRCLE;
        } else {
            fieldStatus = FieldStatus.CROSS;
        }
        try {
            board.setFieldStatus(x, y, fieldStatus);
        } catch (Exception e) {
            throw e;
        }
    }

    public FieldStatus getFieldStatus(int x, int y) {
        return board.getFieldStatus(x,y);
    }

    public int getPlayer() {
        return player;
    }

    @Override
    public void switchPlayer() {
        if(player == 1) {
            player = 2;
        } else {
            player = 1;
        }
    }

    @Override
    public int getTurns() {
        return turns;
    }

    @Override
    public void increaseTurns() {
        turns++;
    }
}
