package practice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws JSONException {
		String projId = "36506058e10245cc8b5f7e5276cf463d";
		String money = "99.00";
		JSONObject fybxDto = new JSONObject();
		fybxDto.put("projId", projId);
		fybxDto.put("projCode", "SC-YWZX2018010");
		fybxDto.put("projName", "����ר����Ŀ-0516");
		fybxDto.put("totalAmount", money);//�ܱ������
		fybxDto.put("bxrIds", "3b8f52b4b8f54bd597557361efcb8736");
		fybxDto.put("bxrNames", "�����");
		fybxDto.put("pmo", "");
		fybxDto.put("typeFlag", "");
		fybxDto.put("xjAmount", 0);
		fybxDto.put("xjTip", "");
		fybxDto.put("czJson", "");
		
		JSONArray details = new JSONArray();
		JSONObject details_type = new JSONObject();
		details_type.put("amount", money);//��ϸ�������
		details_type.put("bxType", "ס�޷�");
		details_type.put("tip", "");
		details.put(0, details_type);
		
		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", "3b8f52b4b8f54bd597557361efcb8736");
		recieve_user.put("userName", "�����");
		recieve_user.put("paymentAmount", money);
		recieve_user.put("paymentMethod", "���п�");
		recieve_user.put("tip", "˳˳��������");
		recieve.put(0, recieve_user);
		
		JSONObject fybxJson  = new JSONObject();
		fybxJson.put("saveFilePath","");
		fybxJson.put("fybxDto", fybxDto);
		fybxJson.put("details", details);
		fybxJson.put("recieve", recieve);
		System.out.println(fybxJson);
	
	
	}

}
