package Controller;

import Model.GameItems.FieldStatus;
import Model.Model;
import View.View;
import javafx.application.Platform;

import java.util.Scanner;

public class DefaultController extends Controller {
    private Model model;
    private View view;
    private boolean aiIsActive;

    public DefaultController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to challenge the AI? [1] Yes [2] No: ");
        String answer = input.next();
        try {
            aiIsActive = checkAIQuestion(answer);
        } catch (Exception e) {
            System.out.println(e);
            startGame();
        }

        while (model.checkFinished() == null) {
            model.increaseTurns();
            System.out.println("This is turn " + model.getTurns());
            if (aiIsActive && model.getPlayer() == 2)
                System.out.println("Hello AI");
            else
                System.out.println("Hello player " + model.getPlayer());
            boolean done = false;
            while (!done) {
                done = true;
                System.out.print("Please Enter x: ");
                String inputX = input.next();
                System.out.print("Please Enter y: ");
                String inputY = input.next();
                try {
                    model.setFieldStatus(inputX, inputY);
                } catch (Exception e) {
                    System.out.println(e);
                    done = false;
                }
            }
            view.updatePosition();
            model.switchPlayer();
        }
        model.switchPlayer();
        if (model.checkFinished() != FieldStatus.NONE) {
            System.out.println("Player " + model.getPlayer() + " has won with " + model.checkFinished());
        } else {
            System.out.println("It's an draw RIP");
        }
        input.close();
    }

    private boolean checkAIQuestion(String answer) throws Exception {
        switch(Integer.parseInt(answer)) {
            case 1:
                System.out.println("You will now challenge the AI.");
              return true;
            case 2:
                System.out.println("This is a normal game.");
              return false;
            default:
                throw new Exception("Please choose between option 1 or option 2.");
          }
    }

    @Override
    public void run() {
        startGame();
    }
}
