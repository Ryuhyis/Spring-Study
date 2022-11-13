package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.desktop.SystemEventListener;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 컨테이너가 뜰때 이 생성자를 호출하는데 autowire
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ // form에서 input의 name = 'name' 이었음, 이 name을 가지고 스프링이 form에 있는 name 필드 값이랑 연결시켜줌
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입 끝나면 홈 화면으로 보내버리기
    }

    @GetMapping("/members")
    public String  list(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members); // 모델에다 담아서 뷰에 넘기기

        return "members/memberList";
    }
}
