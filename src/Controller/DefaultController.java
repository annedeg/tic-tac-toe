package Controller;

import Model.GameItems.FieldStatus;
import Model.Model;
import View.View;
import javafx.application.Platform;

import java.util.Scanner;

public class DefaultController extends Controller {
    private Model model;
    private View view;

    public DefaultController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        Scanner input = new Scanner(System.in);
        while(model.checkFinished() == null) {
            model.increaseTurns();
            System.out.println("This is turn " + model.getTurns());
            System.out.println("Hello player " + model.getPlayer());
            boolean done = false;
            while(!done) {
                done = true;
                System.out.print("Please Enter x: ");
                int inputX = input.nextInt();
                System.out.print("Please Enter y: ");
                int inputY = input.nextInt();

                try {
                    model.setFieldStatus(inputX, inputY);
                } catch (Exception e) {
                    System.out.println("Sorry this location has already been filled. Could you choose another field?");
                    done = false;
                }

            }
            view.updatePosition();
            model.switchPlayer();
        }
        model.switchPlayer();
        System.out.println("Player " + model.getPlayer() + " has won with " + model.checkFinished());
    }

    @Override
    public void run() {
        startGame();
    }
}
