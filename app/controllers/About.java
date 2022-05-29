package controllers;

import play.*;
import play.mvc.*;

public class About extends Controller {
    public static void index() {
        Logger.info("Rendering about");
        Logger.error("some error has occurred");
        render("about.html");
    }
}
