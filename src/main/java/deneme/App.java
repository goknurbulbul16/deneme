/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package deneme;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(App.class);
        logger.error("Hello Word");


        int port = Integer.parseInt(System.getenv("PORT"));
        logger.error("current port number: "+port);

        System.out.println(new App().getGreeting());

        

        


        get ("/", (rq,rs)-> "Hello Word");

        get("/compute", (rq,rs)->{
            Map <String,String> map = new HashMap <String,String>();
            map.put("result", "not computed yet!");
            return new ModelAndView(map,"compute.mustache");
        },
        new MustacheTemplateEngine()
        );


        post("/compute", (req,res)->{
            String input1 = req.queryParams("input1");
            java.util.Scanner scn1 = new java.util.Scanner(input1);
            scn1.useDelimiter ("[;\r\n]+");
            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while(scn1.hasNext()){
                int value = Integer.parseInt(scn1.next().replaceAll("\\s",""));
                inputList.add(value);
            }
            scn1.close();
            System.out.println(inputList);

            String input2 = req.queryParams("input2").replaceAll("\\s","");
            int input2AsInt = Integer.parseInt(input2);

            boolean result = App.search(inputList,input2AsInt);
            Map <String,Boolean> map = new HashMap <String,Boolean>();
            map.put("result", result);
            return new ModelAndView(map,"compute.mustache");

        },
        new MustacheTemplateEngine()
        );

    }



    public static boolean search(ArrayList<Integer> array, int e){

        System.out.println("Inside search");
        if(array==null) return false; 
        for(int elt : array ){
            if(elt == e ) return true;
        }
        
        return false;
       }
}
