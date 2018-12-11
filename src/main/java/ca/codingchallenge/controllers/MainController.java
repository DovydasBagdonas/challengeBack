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
import codingchallenge.database.tables.ArchivedList;

@RestController
public class MainController {
    private List<ToDo> toDos = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();
    private DSLContext dsl;

    @GetMapping("/hello")
    public String getHelloMessage(){

        return "hello world";
    }

    @GetMapping("/getlist")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    public List getListOfToDo() {
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        MyList table = new MyList();
        List<Map<String, String>> myList = new ArrayList<Map<String, String>>();
// Create a JDBC Connection
        DSLContext ctx = null;
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);
            Result<Record> result = ctx.select().from(table).fetch();
            for (Record r : result) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", r.getValue(table.ID).toString());
                map.put("statement", r.getValue(table.STATEMENT));
                map.put("date", r.getValue(table.CREATION_DATE));
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
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        DSLContext ctx = null;
        MyList table = new MyList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);
            ctx.insertInto(table, table.NAME, table.STATEMENT, table.CREATION_DATE).values("", toDo.getStatement(), String.valueOf(java.sql.Date.valueOf(formatter.format(today)))).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(toDo.getStatement());
        toDos.add(toDo);
        return toDo;
    }

    @PostMapping("/archive")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    public ToDo archiveToDo(@RequestBody ToDo toDo){
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        DSLContext ctx = null;
        MyList table = new MyList();
        ArchivedList archiveTable = new ArchivedList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);
            ctx.insertInto(archiveTable, archiveTable.NAME, archiveTable.STATEMENT, archiveTable.ARCHIVE_DATE).values("", toDo.getStatement(), String.valueOf(java.sql.Date.valueOf(formatter.format(today)))).execute();
            ctx.delete(table).where(table.ID.eq((int) toDo.getId())).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(toDo.getStatement());
        toDos.add(toDo);
        return toDo;
    }

    @GetMapping("/archivelist")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
    public List getArchivedList() {
        String user = "dovydas";
        String pass = "testas";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        ArchivedList archiveTable = new ArchivedList();
        List<Map<String, String>> myList = new ArrayList<Map<String, String>>();
        DSLContext ctx = null;
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            ctx = DSL.using(conn, SQLDialect.POSTGRES_9_4);
            Result<Record> result = ctx.select().from(archiveTable).fetch();
            for (Record r : result) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", r.getValue(archiveTable.ID).toString());
                map.put("statement", r.getValue(archiveTable.STATEMENT));
                map.put("date", r.getValue(archiveTable.ARCHIVE_DATE));
                myList.add(map);
            }
            return myList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
