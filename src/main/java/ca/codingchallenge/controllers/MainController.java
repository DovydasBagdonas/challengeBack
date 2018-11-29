package ca.codingchallenge.controllers;

import ca.codingchallenge.model.ToDo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainController {
    private List<ToDo> toDos = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/hello")
    public String getHelloMessage(){
        return "hello world";
    }

    @PostMapping("/toDo")
    public ToDo createNewToDo(@RequestBody ToDo toDo){
        // Set toDo to have next ID;
        toDo.setId(nextId.incrementAndGet());

        toDos.add(toDo);
        return toDo;
    }

    @GetMapping("toDo")
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
