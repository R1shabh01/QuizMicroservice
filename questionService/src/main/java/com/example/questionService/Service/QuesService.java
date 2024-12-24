package com.example.questionService.Service;
import com.example.questionService.Model.Ques;
import com.example.questionService.Model.QuesWrapper;
import com.example.questionService.Model.Response;
import com.example.questionService.Repositories.QuesRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuesService {

    @Autowired
    private QuesRepo quesRepo;

    public Ques addQuestion(Ques ques) {
        return quesRepo.save(ques);
    }

    public List<Ques> allQues() {
        return quesRepo.findAll();
    }

    public List<Ques> addAllQuestion(@Valid List<Ques> ques) {
        return quesRepo.saveAll(ques);
    }

    public List<Ques> findByCategory(String category) {
        return quesRepo.findAllByCategory(category);
    }

    public List<Integer> getQuestionsForQuiz(String category, int num) {
        List<Integer> questions = quesRepo.findRandomQuestionsByCategory(category, num);
        return questions;
    }

    public List<QuesWrapper> getQuesFromId(List<Integer> quesId) {
        List<Ques> ques = new ArrayList<>();
        List<QuesWrapper> quesWrap = new ArrayList<>();
        for (Integer id : quesId){
            ques.add(quesRepo.findById(id).get());
        }
        for (Ques q : ques){
            QuesWrapper wrap = new QuesWrapper(q.getQuesNo(),q.getQuestion(),q.getOpt1(),q.getOpt2(),q.getOpt3(),q.getOpt4());
            quesWrap.add(wrap);
        }
        return quesWrap;
    }

    public Integer calculateResult(int id, List<Response> responses) {
        int rightAns = 0;
        int i=0;
        for(Response response : responses){
            Optional<Ques> question = quesRepo.findById(response.getId());
            if(response.getResponse().equals(question.get().getAnswer()))
                rightAns++;
            i++;
        }
        return rightAns;
    }
}
