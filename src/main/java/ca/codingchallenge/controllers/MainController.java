package ca.codingchallenge.controllers;
import ca.codingchallenge.model.ToDo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import codingchallenge.database.tables.MyList;

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
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    public List getHelloMessage2() {
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        MyList table = new MyList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, String>> myList = new ArrayList<Map<String, String>>();
// Create a JDBC Connection
        DSLContext ctx = null;
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            // Create a context for your database
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);
            Result<Record> result = ctx.select().from(table).fetch();
            System.out.println("rezultas" + result);
        //    String bandymas1 = ctx.selectFrom(table).fetchSingle(table.STATEMENT);
            String bandymas1 = null;
            for (Record r : result) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", r.getValue(table.ID).toString());
                map.put("testas", r.getValue(table.STATEMENT));
                map.put("date", formatter.format(r.getValue(table.CREATION_DATE)));
                myList.add(map);
            }
            return myList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/toDo")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
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
