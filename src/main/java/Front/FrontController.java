package Front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.catalog.Catalog;
import java.util.List;

@RestController
public class FrontController {
    FrontService frontService;
    @Autowired
    public FrontController(FrontService frontService){
        this.frontService=frontService;
    }
    @GetMapping("/search")
    public List getAll(){

        return frontService.getAll();
    }
    @GetMapping("/search/{s}")
    public List getTopic(@PathVariable String s){
        return frontService.getSearch(s);
    }
//    @GetMapping("/search/{s}")
//    public List getTopicb(@RequestBody String s){
//        return frontService.getSearch(s);
//    }

    @GetMapping("/info")
    public List getallInfo(){
        return frontService.getallinfo();
    }
    @GetMapping("/info/{s}")
    public List getInfo(@PathVariable String s){
        return frontService.getInfo(s);
    }
    //    @GetMapping("/info")
//    public List getInfob(@RequestBody String s){
//        return frontService.getInfoo(s);
//    }
    @GetMapping("/purshase/{s}")
    public String pursh(@PathVariable String s){
        return frontService.pursh(s);
    }




}
