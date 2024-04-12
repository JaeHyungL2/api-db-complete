package com.example.demo.controller;


import com.example.demo.dto.ResponseVo;
import com.example.demo.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InitController {

    private final ApiService apiService;

    @GetMapping("/saveData")
    public ResponseEntity<ResponseVo> testOpenAPI(){

        // 변수 설정 (요청할 api 파라미터에맞게)
        String apiKey="7a21da61f8c34441b3cb174cc1623aa0";
        String type="json";
        Integer plndex= 5;
        Integer pSize=10;

/*
        // WebClient 초기화ㅡ 서비스로직으로 뺏음.
        WebClient webClient = WebClient.builder()
                .baseUrl("https://openapi.gg.go.kr/")
                .build();
*/

        // api서비스에서json파싱 후 결과 받기.

        apiService.callApi(apiKey,type,plndex,pSize);


        // ResponseVo 객체를 생성하여 응답 설정
        ResponseVo responseVo = new ResponseVo();
        responseVo.setUcd("00");
        responseVo.setMessage("데이터 처리 완료");

        // ResponseEntity를 통해 클라이언트에 응답
        return ResponseEntity.ok(responseVo);
        //성공 및 실패 시 반환할 ResponseVo 객체를 설정하여 API 호출 결과를 클라이언트에 알려, 서비스의 실제 결과를 클라이언트에 전달하는 역할도 가능하게끔.

    }
//        //↓수정
//        // 호출할 API URL
//        String apiUrl = "BrthspprtMnychldprvtrtbz?key={apiKey}&type={type}&plndex={plndex}&pSize={pSize}";
//
//        // API 호출 및 응답 수신
//        String birthResponse = webClient.get()
//                .uri(apiUrl,apiKey,type,plndex,pSize // 필요한 모든 파라미터 전달
//                )
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        //assert birthResponse != null;
//        String processedResponse = birthResponse.replace("\":", "").replace("\\", "").replace(":", "");
//
//
//        // 로깅 및 응답 구성
//        log.info("응답종료"+"response: " + birthResponse);
//
//
//        ResponseVo responseVo = new ResponseVo();
//        if(birthResponse == null) {
//            responseVo.setUcd("99");
//            responseVo.setMessage("조회실패");
//        }else {
//            responseVo.setUcd("경기도드림 api가능");
//            responseVo.setMessage(birthResponse);
//        }
//        return ResponseEntity.ok(responseVo);
//    }
}


//private WebClient webClient;
//webclient통한 특정url호출후, 응답값 출력하는 로직 작성!

//    @GetMapping("/hello")
//    public ResponseEntity<ResponseVo> initApi(@RequestParam String input){
//        ResponseVo responseVo = new ResponseVo();
//        //hello api에  인풋값이 정상적으로 들어왔는지 확인로직
//        responseVo.setUcd("00");
//        responseVo.setMessage("수신된 값은 : " + input);
//        return ResponseEntity.ok(responseVo);
//    }


/*
@RestController
public class TestController {

    @Autowired
    private PublicRepository repository;

    @PostMapping("/saveData")
    public String saveData() {

        return "요청성공";
    }

}

 */



