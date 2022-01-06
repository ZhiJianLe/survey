package utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import service.SurveyService;


/**
 * @author LZJ
 * 定时任务
 */
@EnableScheduling
public class ScheduleTask {

    @Autowired
    private SurveyService surveyService;
    /**
     * 调查问卷状态的任务
     */
    @Scheduled(fixedRate=60000)
    public void state(){
        System.out.println("执行任务....");
        surveyService.updateState();
    }

}
