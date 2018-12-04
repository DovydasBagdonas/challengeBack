package ca.codingchallenge.controllers;
import ca.codingchallenge.model.ToDo;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import codingchallenge.database.tables.MyList;
import codingchallenge.database.tables.records.MyListRecord;

@RestController
public class MainController {
    private List<ToDo> toDos = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();
    private DSLContext dsl;

    @GetMapping("/hello")
    public String getHelloMessage(){


        return "hello world";
    }

    @GetMapping("/hello2")
    public String getHelloMessage2() {
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";

// Create a JDBC Connection
        DSLContext ctx = null;
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            // Create a context for your database
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);

            // Do something useful ...

        } catch (Exception e) {
            e.printStackTrace();
        }

//        Result<MyListRecord> result = ctx.select().from(MyList).fetch();
        return "aaaabbb";
    }
    @PostMapping("/toDo")
    public ToDo createNewToDo(@RequestBody ToDo toDo){
        toDo.setId(nextId.incrementAndGet());

        toDos.add(toDo);
        return toDo;
    }

    @GetMapping("toDo")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    public List<ToDo> getAllList(){
        return toDos;
    }

    @GetMapping("toDo1")
    @ResponseBody
    public List<ToDo> getAllList1(){
        return toDos;
    }

    @GetMapping("toDo/{id}")
    public ToDo getOneToDo(@PathVariable("id") long toDoId){
        for (ToDo toDo : toDos) {
            if (toDo.getId() == toDoId){
                return toDo;
            }
        }

        throw new IllegalArgumentException();

    }

    @PostMapping("toDo/{id}")
    public ToDo editOneToDo(
            @PathVariable("id") long toDoId,
            @RequestBody ToDo newToDo
    ){
        for (ToDo toDo : toDos) {
            if (toDo.getId() == toDoId){
                toDos.remove(toDo);
                newToDo.setId(toDoId);
                toDos.add(newToDo);
                return toDo;
            }
        }

        throw new IllegalArgumentException();

    }
}
