package com.example.questionService.Controller;

import com.example.questionService.Model.Ques;
import com.example.questionService.Model.QuesWrapper;
import com.example.questionService.Model.Response;
import com.example.questionService.Service.QuesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class homeController {

    @Autowired
    private QuesService quesService;



    @GetMapping("/")
    public String Question(){
        return "this is question api ......";
    }
    @GetMapping("/questions")
    public List<Ques> allQues(){
        return quesService.allQues();

    }

    @PostMapping("/add")
    public ResponseEntity<String> addQues(@RequestBody @Valid List<Ques> ques , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(400).body(" Validation Failed : " + bindingResult.getAllErrors());
        }

        if(ques.size()==1){
            Ques q = quesService.addQuestion(ques.get(0));
            if(q != null){
                return ResponseEntity.ok("upload successful");
            }else{
                return ResponseEntity.status(400).body("upload unsuccessful");
            }
        }else {
            List<Ques> q = quesService.addAllQuestion(ques);
            if(q != null && !q.isEmpty()){
                return ResponseEntity.ok("upload successful for all questions");
            }else{
                return ResponseEntity.status(400).body("upload unsuccessful for a batch of question");
            }
        }

    }

    @GetMapping("/questions/category/{category}")
    public List<Ques> findByCategory(@PathVariable String category){
        return quesService.findByCategory(category);
    }

    @PostMapping("/create")
    public List<Integer> getQuestionForQuiz(@RequestParam String category ,@RequestParam int num) {
        return quesService.getQuestionsForQuiz(category,num);

    }
    @PostMapping("/get")
    public List<QuesWrapper> quesByIdForQuiz(@RequestBody List<Integer> quesId){
        return quesService.getQuesFromId(quesId);
    }

    @PostMapping("/submit/{id}")
    public Integer result(@PathVariable int id , @RequestBody List<Response> responses){
        return quesService.calculateResult(id, responses);
    }

}
