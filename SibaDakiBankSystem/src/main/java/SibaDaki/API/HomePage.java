package SibaDaki.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2016/08/29.
 */
@RestController
@RequestMapping("/api/**")
public class HomePage {

    @Autowired
    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String index(){
        return "This is a Home Page";
    }
}
