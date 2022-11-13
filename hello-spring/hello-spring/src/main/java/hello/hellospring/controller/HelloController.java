package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http통신에서 Body부에 이 내용을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // "hello spring" 이라는 글자가 그대로 보내짐
    }

    // 요게 api 방식
    @GetMapping("hello-api")
    @ResponseBody // hello객체를 넘기면 그 객체를 보고 일단 몇가지 조건을 보는데 객체면 jsonconverter가 동작하고
    // 문자면 stringconverter 동작
    // -> 그 형태로 반환해서 view없이 그냥 바로 브라우저에 전달
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // /hello-api?name=springggg -> {"name":"springggg"}
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
