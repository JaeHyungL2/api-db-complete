package com.example.demo.service;


import com.example.demo.entity.PublicData;
import com.example.demo.repository.CallRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiService {
    private final WebClient webClient;
    private final CallRepository callRepository;

    @Autowired
    public ApiService(WebClient.Builder webClientBuilder, CallRepository callRepository) {
        this.webClient = webClientBuilder.baseUrl("https://openapi.gg.go.kr/").build();
        this.callRepository = callRepository; //생성자주입
    }

    public void callApi(String apiKey, String type, Integer plndex, Integer pSize) {
        String apiUrl = "BrthspprtMnychldprvtrtbz?key={apiKey}&type={type}&plndex={plndex}&pSize={pSize}";
        String responseData= webClient.get()
                .uri(apiUrl, apiKey, type, plndex, pSize)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        parseJson(responseData);

    }


    public void parseJson(String jsonData) {

        //String strjson = "{\"BrthspprtMnychldprvtrtbz\":[{\"head\":[{\"list_total_count\":41},{\"RESULT\":{\"CODE\":\"INFO-000\",\"MESSAGE\":\"정상 처리되었습니다.\"}},{\"api_version\":\"1.0\"}]},{\"row\":[{\"SIGUN_NM\":\"김포시\",\"SIGUN_CD\":\"41570\",\"BIZ_NM\":\"출산축하금\",\"CHILD_2_SPORTAMT_DTLS\":\"1000\",\"CHILD_3_SPORTAMT_DTLS\":\"1000\",\"CHILD_4_SPORTAMT_DTLS\":\"1000\",\"CHILD_5_ABOVE_SPORTAMT_DTLS\":\"1000\",\"SPORT_PERD\":\"1회\",\"PAYMNT_STD_CONT\":\"신생아의 출생일을 기준으로 180일 이전부터 신청일 현재까지 김포시에 주민등록을 두고 거주한 보호자로 하며 대상자녀는 주민등록상 동일 세대원이어야 함(거주기간이 180일 미만인 경우 신생아의 출생일로부터 180일이 경과한 날까지 시에 주민등록을 두고 거주하였을 경우 신청 가능)\",\"BASIS_CONT\":\"김포시 출산장려 지원 등에 관한 조례\"}]}]}";
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject json = (JSONObject) jsonParser.parse(jsonData);
            JSONArray dataArray = (JSONArray) json.get("BrthspprtMnychldprvtrtbz");
            JSONObject dataObject = (JSONObject) dataArray.get(1); // 데이터 배열에서 두 번째 객체 접근
            JSONArray rows = (JSONArray) dataObject.get("row");

            for (Object row : rows) {
                JSONObject rowObj = (JSONObject) row;
                String sigunNm = (String) rowObj.get("SIGUN_NM");
                String bizNm= (String) rowObj.get("BIZ_NM");
                System.out.print("시군명: ");
                System.out.println("사업명: ");
                // 대망의 db저장
                PublicData entity = new PublicData();
                entity.setSigunNm(sigunNm);
                entity.setBizNm(bizNm);
                callRepository.save(entity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        //Api 호출 결과를 db에 저장하는 로직을 추가





            //ApiService 호출 시 발생할 수 있는 예외를 try-catch 블록을 사용하여 처리함으로, 이를 통해 API 호출 실패 시 적절한 오류 메시지를 클라이언트에 반환할 수 있습니다.
//
//        object obj = jsonParser.parse(strJson);
//
//        // 문자열을 JSON 객체로 파싱
//        JSONObject json = (JSONObject) obj;
//
//        System.out.println(jsonObj.get("userId")); //sim
//        System.out.println(jsonObj.get("userPw")); //simpw
//        System.out.println(jsonObj.get("userInfo")); // {"sex":"male","age":50}
//
//        // "BrthspprtMnychldprvtrtbz" 키를 사용하여 데이터 배열 접근
//        JSONArray dataArray = (JSONArray) json.get("BrthspprtMnychldprvtrtbz");
//
//        // 데이터 배열 내의 첫 번째 객체(이 예시에서는 'row' 객체를 포함하는 객체)에 접근
//        JSONObject dataObject = (JSONObject) dataArray.get(1); // 배열의 첫 번째 요소는 메타데이터를 포함하고 있으므로, 두 번째 요소를 사용
//
//        // 'row' 키를 사용하여 실제 데이터 배열에 접근
//        JSONArray rows = (JSONArray) dataObject.get("row");
//        // 각 데이터 항목에 대해 반복
//        for (Object row : rows) {
//            JSONObject rowObj = (JSONObject) row;
//
//            // 필요한 정보 추출
//            String sigunNm = (String) rowObj.get("SIGUN_NM"); // 시군명
//            String bizNm = (String) rowObj.get("BIZ_NM"); // 사업명
//
//            // 출력
//            System.out.println("시군명: " + sigunNm + ", 사업명: " + bizNm);
//        }


}




/*

package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws ParseException {
		// 여기에 API 응답으로 받은 JSON 문자열을 넣습니다.
		String responseData = "{\"BrthspprtMnychldprvtrtbz\":[{\"head\":[{\"list_total_count\":41},{\"RESULT\":{\"CODE\":\"INFO-000\",\"MESSAGE\":\"정상 처리되었습니다.\"}},{\"api_version\":\"1.0\"}]},{\"row\":[{\"SIGUN_NM\":\"김포시\",\"SIGUN_CD\":\"41570\",\"BIZ_NM\":\"출산축하금\",\"CHILD_2_SPORTAMT_DTLS\":\"1000\",\"CHILD_3_SPORTAMT_DTLS\":\"1000\",\"CHILD_4_SPORTAMT_DTLS\":\"1000\",\"CHILD_5_ABOVE_SPORTAMT_DTLS\":\"1000\",\"SPORT_PERD\":\"1회\",\"PAYMNT_STD_CONT\":\"신생아의 출생일을 기준으로 180일 이전부터 신청일 현재까지 김포시에 주민등록을 두고 거주한 보호자로 하며 대상자녀는 주민등록상 동일 세대원이어야 함(거주기간이 180일 미만인 경우 신생아의 출생일로부터 180일이 경과한 날까지 시에 주민등록을 두고 거주하였을 경우 신청 가능)\",\"BASIS_CONT\":\"김포시 출산장려 지원 등에 관한 조례\"}]}]}";

		// JSON 파서 초기화
		JSONParser parser = new JSONParser();

		// 문자열을 JSON 객체로 파싱
		JSONObject json = (JSONObject) parser.parse(responseData);

		// "BrthspprtMnychldprvtrtbz" 키를 사용하여 데이터 배열 접근
		JSONArray dataArray = (JSONArray) json.get("BrthspprtMnychldprvtrtbz");

		// 데이터 배열 내의 첫 번째 객체(이 예시에서는 'row' 객체를 포함하는 객체)에 접근
		JSONObject dataObject = (JSONObject) dataArray.get(1); // 배열의 첫 번째 요소는 메타데이터를 포함하고 있으므로, 두 번째 요소를 사용

		// 'row' 키를 사용하여 실제 데이터 배열에 접근
		JSONArray rows = (JSONArray) dataObject.get("row");

		// 각 데이터 항목에 대해 반복
		for (Object row : rows) {
			JSONObject rowObj = (JSONObject) row;

			// 필요한 정보 추출
			String sigunNm = (String) rowObj.get("SIGUN_NM"); // 시군명
			String bizNm = (String) rowObj.get("BIZ_NM"); // 사업명

			// 출력
			System.out.println("시군명: " + sigunNm + ", 사업명: " + bizNm);
		}
	}
}


 */