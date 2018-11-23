package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class LogDemo {

     static final Logger logger= LoggerFactory.getLogger(LogDemo.class);

    public void printLog(String json) {
        try {
            logger.debug("debug : " + json);
            logger.info("info : " + json);
            System.out.println("====================="+json+"=====================");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
