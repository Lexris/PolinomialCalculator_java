import ControllerMVC.Controller;
import ModelMVC.Model;
import ViewMVC.View;

public class MainMVC {
    public MainMVC() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}
