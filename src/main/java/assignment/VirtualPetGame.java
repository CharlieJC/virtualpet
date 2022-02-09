package assignment;

import assignment.screen.Controller;
import assignment.screen.Model;
import assignment.screen.View;

/**
 * Main class for virtual pet game
 *
 * @author charlie
 */
public class VirtualPetGame {

    public static void main(String... args) {
        View view = new View();
        Model model = new Model();
        model.addObserver(view);
        Controller controller = new Controller(view, model);

    }
}
