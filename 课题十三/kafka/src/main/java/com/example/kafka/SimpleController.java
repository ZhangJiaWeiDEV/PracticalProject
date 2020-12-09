package com.example.kafka;

import com.alibaba.fastjson.JSONObject;
import com.example.kafka.dao.MessageDao;
import com.example.kafka.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@AllArgsConstructor
public class SimpleController {
    private final KafkaTemplate<Object,Object> kafkaTemplate;

    private List<Message> messageList;
    @Resource
    private MessageDao messageDao;
    private List<String> stringList;

    @PostMapping("/send/{message}")
    public String send(@PathVariable String message){
        kafkaTemplate.send("topic1", "topci1:" + message);
        kafkaTemplate.send("topic2", "topci2:" + message);
        return message;
    }

    @CrossOrigin
    @PostMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam(value="file",required=false) MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();
        byte bytes[] = new byte[(int) file.getSize()];
        inputStream.read(bytes);
        String[] string = (new String(bytes)).split("\\r?\\n");

        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string));
        for (String str : stringArrayList) {
            Message message = new Message();
            message.setTime(str.substring(str.indexOf("\"time\":") + 7, str.indexOf(", \"status\"")));
            message.setStatus(Integer.parseInt(str.substring(str.indexOf("\"status\":") + 9, str.indexOf(", \"content\""))));
            message.setContent(str.substring(str.indexOf("\"content\":") + 10, str.length()));
            messageList.add(message);
            kafkaTemplate.send("topic1", "time:" + message.getTime());
            kafkaTemplate.send("topic1", "status:" + message.getStatus());
            kafkaTemplate.send("topic1", "content:" + message.getContent());
        }
        Map<String, String> result = new HashMap<>(16);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize() + "");
        return result;
    }

    @KafkaListener(topics = {"topic1", "topic2"})
    public String listen(String data) {
        stringList.add(data);
        System.out.println(stringList);
        return data;
    }

    @CrossOrigin
    @PostMapping("/getKafka")
    public List<String> getStringList(){
        return stringList;
    }

    @CrossOrigin
    @PostMapping("/save")
    public Integer save(){
        for(Message message:messageList){
            System.out.println(message);
            messageDao.insert(message);
        }
        messageList.clear();
        return 0;
    }

    @CrossOrigin
    @PostMapping("/getMessage")
    public List<Message> getMessage(){
        return messageDao.getAll();
    }
}
