package com.github.jsanca;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(final String[] args ) {

        final Javalin app = Javalin.create().start(7000);
        //app.get("/plain", context -> context.result("Hello World"));
        //app.get("/json",  context -> context.json(createMap()));
        app.before("/:path-param", context -> {

            System.out.println("Before");
        });

        app.get("/:path-param", context -> {

            final String queryParam = context.queryParam("query-param");
            final String pathParam  = context.pathParam("path-param");
            final String formParam  = context.formParam("form-param");
            //final String body       = context.body();
            //final Map<String, Object> bodyMap = context.bodyAsClass(Map.class);

            System.out.println("query: " + queryParam);
            System.out.println("path: "  + pathParam);
            System.out.println("form: "  + formParam);
            //System.out.println("body: "  + body);
            //System.out.println("bodyMap: "  + bodyMap);
        });

        app.after("/:path-param", context -> {

            System.out.println("After");
        });
    }


    private static Map<String, String> createMap () {

        final Map<String, String> map = new HashMap<>();

        map.put("hello", "world");

        return map;
    }
}
